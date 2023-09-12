package com.ktg.mes.pro.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.page.TableDataInfo;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdItem;
import com.ktg.mes.md.domain.MdProductBom;
import com.ktg.mes.md.service.IMdProductBomService;
import com.ktg.mes.pro.domain.ProTask;
import com.ktg.mes.pro.domain.ProWorkorderBom;
import com.ktg.mes.pro.service.IProTaskService;
import com.ktg.mes.pro.service.IProWorkorderBomService;
import com.ktg.mes.wm.domain.WmRtIssue;
import com.ktg.mes.wm.domain.WmRtIssueLine;
import com.ktg.mes.wm.domain.tx.RtIssueTxBean;
import io.minio.messages.Item;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ktg.common.annotation.Log;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.enums.BusinessType;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.common.utils.poi.ExcelUtil;

/**
 * 生产工单Controller
 * 
 * @author yinjinlu
 * @date 2022-05-09
 */
@RestController
@RequestMapping("/mes/pro/workorder")
public class ProWorkorderController extends BaseController
{
    @Autowired
    private IProWorkorderService proWorkorderService;

    @Autowired
    private IProWorkorderBomService proWorkorderBomService;

    @Autowired
    private IMdProductBomService mdProductBomService;

    @Autowired
    private IProTaskService proTaskService;

    /**
     * 查询生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProWorkorder proWorkorder)
    {
        startPage();
        List<ProWorkorder> list = proWorkorderService.selectProWorkorderList(proWorkorder);
        return getDataTable(list);
    }

    /**
     * 导出生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProWorkorder proWorkorder)
    {
        List<ProWorkorder> list = proWorkorderService.selectProWorkorderList(proWorkorder);
        ExcelUtil<ProWorkorder> util = new ExcelUtil<ProWorkorder>(ProWorkorder.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    /**
     * 获取生产工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:query')")
    @GetMapping(value = "/{workorderId}")
    public AjaxResult getInfo(@PathVariable("workorderId") Long workorderId)
    {
        return AjaxResult.success(proWorkorderService.selectProWorkorderByWorkorderId(workorderId));
    }

    /**
     * 新增生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProWorkorder proWorkorder)
    {
        if(UserConstants.NOT_UNIQUE.equals(proWorkorderService.checkWorkorderCodeUnique(proWorkorder))){
            return AjaxResult.error("生产工单编号已存在！");
        }

        if(proWorkorder.getParentId()==null || proWorkorder.getParentId()==0){
            proWorkorder.setAncestors("0");
        }
        proWorkorderService.insertProWorkorder(proWorkorder);

        Long workorderId = proWorkorder.getWorkorderId();
        generateBomLine(workorderId);
        proWorkorder.setCreateBy(getUsername());
        return AjaxResult.success(workorderId);
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProWorkorder proWorkorder)
    {
        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(proWorkorder.getWorkorderId());
        int ret =proWorkorderService.updateProWorkorder(proWorkorder);
        //如果是产品和数量发生变化则需要重新生成BOM组成
        if(ret >0){
            if(workorder.getProductId().longValue() != proWorkorder.getProductId().longValue() ||
                    workorder.getQuantity().compareTo(proWorkorder.getQuantity())!=0){
                removeBomLine(proWorkorder.getWorkorderId());
                generateBomLine(proWorkorder.getWorkorderId());
            }
        }
        return toAjax(ret);
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workorderIds}")
    public AjaxResult remove(@PathVariable Long[] workorderIds)
    {
        for (Long id:workorderIds
             ) {
            ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(id);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(workorder.getStatus())){
                return AjaxResult.error("只能删除草稿状态单据！");
            }
            removeBomLine(id);
        }
        return toAjax(proWorkorderService.deleteProWorkorderByWorkorderIds(workorderIds));
    }

    /**
     * 根据生产工单中的产品生成BOM物料行
     * @param workorderId
     */
    private void generateBomLine(Long workorderId){
        //先根据ID找到对应的产品
        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(workorderId);

        //根据产品找到BOM组成
        MdProductBom param = new MdProductBom();
        param.setItemId(workorder.getProductId());
        List<MdProductBom> boms = mdProductBomService.selectMdProductBomList(param);

        //生成BOM数据
        BigDecimal orderQuantitiy = workorder.getQuantity();
        ProWorkorderBom workorderBom = new ProWorkorderBom();
        if(CollUtil.isNotEmpty(boms)){
            for (MdProductBom bom:boms
                 ) {
                workorderBom.setWorkorderId(workorderId);
                workorderBom.setItemId(bom.getBomItemId());
                workorderBom.setItemCode(bom.getBomItemCode());
                workorderBom.setItemName(bom.getBomItemName());
                workorderBom.setItemSpc(bom.getBomItemSpec());
                workorderBom.setItemOrProduct(bom.getItemOrProduct());
                workorderBom.setUnitOfMeasure(bom.getUnitOfMeasure());
                workorderBom.setQuantity(orderQuantitiy.multiply(bom.getQuantity()));
                proWorkorderBomService.insertProWorkorderBom(workorderBom);
            }
        }
    }

    /**
     * 删除当前工单下所有BOM组成
     * @param workorderId
     */
    private void removeBomLine(Long workorderId){
        ProWorkorderBom param = new ProWorkorderBom();
        param.setWorkorderId(workorderId);
        proWorkorderBomService.deleteProWorkorderBomByWorkorderId(workorderId);
    }

    /**
     * 获取当前工单的物料需求清单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:list')")
    @GetMapping("/listItems")
    public TableDataInfo listItemss(ProWorkorder proWorkorder)
    {
        List<MdProductBom> result = new ArrayList<MdProductBom>();
        ProWorkorderBom param = new ProWorkorderBom();
        param.setWorkorderId(proWorkorder.getWorkorderId());
        List<ProWorkorderBom> boms = proWorkorderBomService.selectProWorkorderBomList(param);
        if(!CollectionUtils.isEmpty(boms)){
            for ( ProWorkorderBom bom: boms
                 ) {
                MdProductBom theBom = new MdProductBom();
                theBom.setBomItemId(bom.getItemId());
                result.addAll(getBoms(theBom,bom.getQuantity(),0));
            }
        }
        return getDataTable(result);
    }

    private List<MdProductBom> getBoms(MdProductBom item,BigDecimal quantity,int count){
        MdProductBom param = new MdProductBom();
        List<MdProductBom> results = new ArrayList<MdProductBom>();
        if(count >20){
            return results;
        }
        param.setItemId(item.getBomItemId());
        List<MdProductBom> boms = mdProductBomService.selectMdProductBomList(param);
        if(CollUtil.isNotEmpty(boms)){
            //最多20层依赖
            count ++;
            for (MdProductBom bomItem: boms
                 ) {
                bomItem.setQuantity(quantity.multiply(bomItem.getQuantity()));
                results.addAll(getBoms(bomItem,bomItem.getQuantity(),count));
            }
        }else{
            results.add(item);
        }
        return results;
    }


    /**
     * 完成工单
     * @param workorderId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:rtissue:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{workorderId}")
    public AjaxResult dofinish(@PathVariable Long workorderId){
        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(workorderId);

        //将此工单下所有的生产任务状态设置为已完成
        ProTask param = new ProTask();
        param.setWorkorderId(workorderId);
        List<ProTask> tasks = proTaskService.selectProTaskList(param);
        if(!CollectionUtils.isEmpty(tasks)){
            for (ProTask task:tasks
                 ) {
                task.setStatus(UserConstants.ORDER_STATUS_FINISHED);
                proTaskService.updateProTask(task);
            }
        }

        workorder.setStatus(UserConstants.ORDER_STATUS_FINISHED); //更新工单的状态
        proWorkorderService.updateProWorkorder(workorder);
        return AjaxResult.success();
    }

}

package com.ktg.mes.pro.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.md.domain.MdProductBom;
import com.ktg.mes.md.service.IMdProductBomService;
import com.ktg.mes.pro.domain.ProWorkorderBom;
import com.ktg.mes.pro.service.IProWorkorderBomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ktg.common.core.page.TableDataInfo;

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
        return toAjax(proWorkorderService.insertProWorkorder(proWorkorder));
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProWorkorder proWorkorder)
    {
        return toAjax(proWorkorderService.updateProWorkorder(proWorkorder));
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('mes:pro:workorder:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workorderIds}")
    public AjaxResult remove(@PathVariable Long[] workorderIds)
    {
        return toAjax(proWorkorderService.deleteProWorkorderByWorkorderIds(workorderIds));
    }

    /**
     * 根据生产工单中的产品生成对应的BOM组成物料清单
     * @param workOrderId
     */
    private void generateBomLine(Long workOrderId){

        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(workOrderId);
        Long itemId = workorder.getProductId(); //得到当前工单生产的物料/产品ID

        MdProductBom param = new MdProductBom();
        param.setItemId(itemId);
        List<MdProductBom> boms = mdProductBomService.selectMdProductBomList(param);

        ProWorkorderBom proWorkorderBom = new ProWorkorderBom();
        if(!CollUtil.isEmpty(boms)){
            for (MdProductBom bom: boms
                 ) {
                proWorkorderBom.setWorkorderId(workOrderId);
                proWorkorderBom.setItemId(bom.getBomItemId());
                proWorkorderBom.setItemCode(bom.getBomItemCode());
                proWorkorderBom.setItemName(bom.getBomItemName());
                proWorkorderBom.setItemSpc(bom.getBomItemSpec());
                proWorkorderBom.setUnitOfMeasure(bom.getUnitOfMeasure());
                proWorkorderBom.setItemOrProduct(""); //TODO
            }
        }

    }

}

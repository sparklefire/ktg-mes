package com.ktg.mes.wm.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.pro.domain.ProWorkorder;
import com.ktg.mes.pro.service.IProWorkorderService;
import com.ktg.mes.wm.domain.WmItemRecpt;
import com.ktg.mes.wm.domain.WmOutsourceRecptLine;
import com.ktg.mes.wm.domain.tx.ItemRecptTxBean;
import com.ktg.mes.wm.domain.tx.OutsourceRecptTxBean;
import com.ktg.mes.wm.service.IStorageCoreService;
import com.ktg.mes.wm.service.IWmOutsourceRecptLineService;
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
import com.ktg.mes.wm.domain.WmOutsourceRecpt;
import com.ktg.mes.wm.service.IWmOutsourceRecptService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 外协入库单Controller
 * 
 * @author yinjinlu
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/mes/wm/outsourcerecpt")
public class WmOutsourceRecptController extends BaseController
{
    @Autowired
    private IWmOutsourceRecptService wmOutsourceRecptService;

    @Autowired
    private IWmOutsourceRecptLineService wmOutsourceRecptLineService;

    @Autowired
    private IStorageCoreService storageCoreService;

    @Autowired
    private IProWorkorderService proWorkorderService;


    /**
     * 查询外协入库单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:list')")
    @GetMapping("/list")
    public TableDataInfo list(WmOutsourceRecpt wmOutsourceRecpt)
    {
        startPage();
        List<WmOutsourceRecpt> list = wmOutsourceRecptService.selectWmOutsourceRecptList(wmOutsourceRecpt);
        return getDataTable(list);
    }

    /**
     * 导出外协入库单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:export')")
    @Log(title = "外协入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WmOutsourceRecpt wmOutsourceRecpt)
    {
        List<WmOutsourceRecpt> list = wmOutsourceRecptService.selectWmOutsourceRecptList(wmOutsourceRecpt);
        ExcelUtil<WmOutsourceRecpt> util = new ExcelUtil<WmOutsourceRecpt>(WmOutsourceRecpt.class);
        util.exportExcel(response, list, "外协入库单数据");
    }

    /**
     * 获取外协入库单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:query')")
    @GetMapping(value = "/{recptId}")
    public AjaxResult getInfo(@PathVariable("recptId") Long recptId)
    {
        return AjaxResult.success(wmOutsourceRecptService.selectWmOutsourceRecptByRecptId(recptId));
    }

    /**
     * 新增外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:add')")
    @Log(title = "外协入库单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WmOutsourceRecpt wmOutsourceRecpt)
    {
        return toAjax(wmOutsourceRecptService.insertWmOutsourceRecpt(wmOutsourceRecpt));
    }

    /**
     * 修改外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:edit')")
    @Log(title = "外协入库单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WmOutsourceRecpt wmOutsourceRecpt)
    {
        return toAjax(wmOutsourceRecptService.updateWmOutsourceRecpt(wmOutsourceRecpt));
    }

    /**
     * 删除外协入库单
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:remove')")
    @Log(title = "外协入库单", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{recptIds}")
    public AjaxResult remove(@PathVariable Long[] recptIds)
    {
        for (Long recptId:recptIds
             ) {
            wmOutsourceRecptLineService.selectWmOutsourceRecptLineByRecptId(recptId);
        }
        return toAjax(wmOutsourceRecptService.deleteWmOutsourceRecptByRecptIds(recptIds));
    }

    /**
     * 执行入库
     * @return
     */
    @PreAuthorize("@ss.hasPermi('mes:wm:outsourcerecpt:edit')")
    @Log(title = "外协入库单", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/{recptId}")
    public AjaxResult execute(@PathVariable Long recptId){

        WmOutsourceRecpt recpt = wmOutsourceRecptService.selectWmOutsourceRecptByRecptId(recptId);

        List<WmOutsourceRecptLine> lines = wmOutsourceRecptLineService.selectWmOutsourceRecptLineByRecptId(recptId);
        if(CollectionUtils.isEmpty(lines)){
            return AjaxResult.error("请指定入库的物资！");
        }

        //构造Transaction事务，并执行库存更新逻辑
        List<OutsourceRecptTxBean> beans = wmOutsourceRecptService.getTxBeans(recptId);

        //调用库存核心
        storageCoreService.processOutsourceRecpt(beans);

        //根据当前入库的物料更新对应的生产工单/生产任务 已生产数量
        ProWorkorder workorder = proWorkorderService.selectProWorkorderByWorkorderId(recpt.getWorkorderId());
        if(!StringUtils.isNotNull(workorder)){
            return AjaxResult.error("未找到对应的外协工单/外协任务！");
        }

        //正常外协入库的产品必须先经过检验，确认合格数量后才能执行入库，并且更新外协工单的进度。此处暂时先直接根据入库数量更新外协工单的生产数量。
        BigDecimal produced = workorder.getQuantityProduced() == null?new BigDecimal(0):workorder.getQuantityProduced();
        for (int i = 0; i < lines.size(); i++) {
            WmOutsourceRecptLine line = lines.get(i);
            //判断入库的物资，如果是生产工单中的产品，则更新已生产数量
            if(line.getItemCode().equals(workorder.getProductCode())){
                workorder.setQuantityProduced( produced.add(line.getQuantityRecived()));
            }
        }
        proWorkorderService.updateProWorkorder(workorder);

        //更新单据状态
        recpt.setStatus(UserConstants.ORDER_STATUS_FINISHED);
        wmOutsourceRecptService.updateWmOutsourceRecpt(recpt);

        return AjaxResult.success();
    }

}

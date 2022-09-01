package com.ktg.mes.qc.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.mes.qc.domain.*;
import com.ktg.mes.qc.service.IQcOqcLineService;
import com.ktg.mes.qc.service.IQcTemplateIndexService;
import com.ktg.mes.qc.service.IQcTemplateProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.ktg.mes.qc.service.IQcOqcService;
import com.ktg.common.utils.poi.ExcelUtil;
import com.ktg.common.core.page.TableDataInfo;

/**
 * 出货检验单Controller
 * 
 * @author yinjinlu
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/mes/qc/oqc")
public class QcOqcController extends BaseController
{
    @Autowired
    private IQcOqcService qcOqcService;

    @Autowired
    private IQcOqcLineService qcOqcLineService;

    @Autowired
    private IQcTemplateProductService qcTemplateProductService;

    @Autowired
    private IQcTemplateIndexService qcTemplateIndexService;

    /**
     * 查询出货检验单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:list')")
    @GetMapping("/list")
    public TableDataInfo list(QcOqc qcOqc)
    {
        startPage();
        List<QcOqc> list = qcOqcService.selectQcOqcList(qcOqc);
        return getDataTable(list);
    }

    /**
     * 导出出货检验单列表
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:export')")
    @Log(title = "出货检验单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QcOqc qcOqc)
    {
        List<QcOqc> list = qcOqcService.selectQcOqcList(qcOqc);
        ExcelUtil<QcOqc> util = new ExcelUtil<QcOqc>(QcOqc.class);
        util.exportExcel(response, list, "出货检验单数据");
    }

    /**
     * 获取出货检验单详细信息
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:query')")
    @GetMapping(value = "/{oqcId}")
    public AjaxResult getInfo(@PathVariable("oqcId") Long oqcId)
    {
        return AjaxResult.success(qcOqcService.selectQcOqcByOqcId(oqcId));
    }

    /**
     * 新增出货检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:add')")
    @Log(title = "出货检验单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QcOqc qcOqc)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcOqcService.checkOqcCodeUnique(qcOqc))){
            return AjaxResult.error("出货单编号已存在!");
        }
        //自动获取对应的检测模板
        QcTemplateProduct param = new QcTemplateProduct();
        param.setItemId(qcOqc.getItemId());
        List<QcTemplateProduct> templates = qcTemplateProductService.selectQcTemplateProductList(param);
        if(CollUtil.isNotEmpty(templates)){
            qcOqc.setTemplateId(templates.get(0).getTemplateId());
        }else{
            return AjaxResult.error("当前产品未配置检测模板！");
        }
        //设置检测人
        qcOqc.setInspector(getUsername());
        //先保存单据
        qcOqcService.insertQcOqc(qcOqc);
        //生成行信息
        generateLine(qcOqc);

        Long oqcId = qcOqc.getOqcId();
        return AjaxResult.success(oqcId);
    }

    /**
     * 修改出货检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:edit')")
    @Log(title = "出货检验单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QcOqc qcOqc)
    {
        if(UserConstants.NOT_UNIQUE.equals(qcOqcService.checkOqcCodeUnique(qcOqc))){
            return AjaxResult.error("出货单编号已存在!");
        }
        return toAjax(qcOqcService.updateQcOqc(qcOqc));
    }

    /**
     * 删除出货检验单
     */
    @PreAuthorize("@ss.hasPermi('mes:qc:oqc:remove')")
    @Log(title = "出货检验单", businessType = BusinessType.DELETE)
    @Transactional
	@DeleteMapping("/{oqcIds}")
    public AjaxResult remove(@PathVariable Long[] oqcIds)
    {
        for (Long oqcId: oqcIds
             ) {
            QcOqc oqc = qcOqcService.selectQcOqcByOqcId(oqcId);
            if(!UserConstants.ORDER_STATUS_PREPARE.equals(oqc.getStatus())){
                return AjaxResult.error("只能删除状态为草稿的单据!");
            }
            qcOqcLineService.deleteByOqcId(oqcId);
        }

        return toAjax(qcOqcService.deleteQcOqcByOqcIds(oqcIds));
    }

    /**
     * 根据头信息生成行信息
     * @param oqc
     */
    private void generateLine(QcOqc oqc){
        QcTemplateIndex param = new QcTemplateIndex();
        param.setTemplateId(oqc.getTemplateId());
        List<QcTemplateIndex> indexs = qcTemplateIndexService.selectQcTemplateIndexList(param);
        if(CollUtil.isNotEmpty(indexs)){
            for (QcTemplateIndex index:indexs
            ) {
                QcOqcLine line = new QcOqcLine();
                line.setOqcId(oqc.getOqcId());
                line.setIndexId(index.getIndexId());
                line.setIndexCode(index.getIndexCode());
                line.setIndexName(index.getIndexName());
                line.setIndexType(index.getIndexType());
                line.setQcTool(index.getQcTool());
                line.setCheckMethod(index.getCheckMethod());
                line.setStanderVal(index.getStanderVal());
                line.setUnitOfMeasure(index.getUnitOfMeasure());
                line.setThresholdMax(index.getThresholdMax());
                line.setThresholdMin(index.getThresholdMin());
                line.setCrQuantity(new BigDecimal(0L));
                line.setMajQuantity(new BigDecimal(0L));
                line.setMajQuantity(new BigDecimal(0L));
                qcOqcLineService.insertQcOqcLine(line);
            }
        }
    }
}

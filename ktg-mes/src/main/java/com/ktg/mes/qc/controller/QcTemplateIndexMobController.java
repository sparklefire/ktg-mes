package com.ktg.mes.qc.controller;

import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.qc.domain.QcMobParam;
import com.ktg.mes.qc.domain.QcTemplate;
import com.ktg.mes.qc.domain.QcTemplateIndex;
import com.ktg.mes.qc.service.IQcTemplateIndexService;
import com.ktg.mes.qc.service.IQcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mobile/qc/templateindex")
public class QcTemplateIndexMobController {

    @Autowired
    private IQcTemplateService qcTemplateService;

    @Autowired
    private IQcTemplateIndexService qcTemplateIndexService;

    /**
     * 根据物料产品和质检类型查询对应的质检模板行信息
     */
    @GetMapping("/getLines")
    public AjaxResult getLines(QcMobParam param){

        //根据物料和质检类型查询模板
        QcTemplate template = qcTemplateService.findTemplateByProductIdAndQcType(param);
        if(StringUtils.isNull(template)){
            return AjaxResult.error("当前生产的产品未配置此类型的检验模板，请联系质量管理人员！");
        }

        //根据模板查询模板行
        QcTemplateIndex p = new QcTemplateIndex();
        p.setTemplateId(template.getTemplateId());
        List<QcTemplateIndex> indexList = qcTemplateIndexService.selectQcTemplateIndexList(p);
        return AjaxResult.success(indexList);
    }

}

package com.ktg.print.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ktg.print.mapper.PrintTemplateMapper;
import com.ktg.print.domain.PrintTemplate;
import com.ktg.print.service.IPrintTemplateService;

/**
 * 打印模板配置Service业务层处理
 * 
 * @author yinjinlu
 * @date 2024-04-17
 */
@Service
public class PrintTemplateServiceImpl implements IPrintTemplateService 
{
    @Autowired
    private PrintTemplateMapper printTemplateMapper;

    /**
     * 查询打印模板配置
     * 
     * @param templateId 打印模板配置主键
     * @return 打印模板配置
     */
    @Override
    public PrintTemplate selectPrintTemplateByTemplateId(Long templateId)
    {
        return printTemplateMapper.selectPrintTemplateByTemplateId(templateId);
    }

    /**
     * 查询打印模板配置列表
     * 
     * @param printTemplate 打印模板配置
     * @return 打印模板配置
     */
    @Override
    public List<PrintTemplate> selectPrintTemplateList(PrintTemplate printTemplate)
    {
        return printTemplateMapper.selectPrintTemplateList(printTemplate);
    }

    /**
     * 新增打印模板配置
     * 
     * @param printTemplate 打印模板配置
     * @return 结果
     */
    @Override
    public int insertPrintTemplate(PrintTemplate printTemplate)
    {
        printTemplate.setCreateTime(DateUtils.getNowDate());
        return printTemplateMapper.insertPrintTemplate(printTemplate);
    }

    /**
     * 修改打印模板配置
     * 
     * @param printTemplate 打印模板配置
     * @return 结果
     */
    @Override
    public int updatePrintTemplate(PrintTemplate printTemplate)
    {
        printTemplate.setUpdateTime(DateUtils.getNowDate());
        return printTemplateMapper.updatePrintTemplate(printTemplate);
    }

    /**
     * 批量删除打印模板配置
     * 
     * @param templateIds 需要删除的打印模板配置主键
     * @return 结果
     */
    @Override
    public int deletePrintTemplateByTemplateIds(Long[] templateIds)
    {
        return printTemplateMapper.deletePrintTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除打印模板配置信息
     * 
     * @param templateId 打印模板配置主键
     * @return 结果
     */
    @Override
    public int deletePrintTemplateByTemplateId(Long templateId)
    {
        return printTemplateMapper.deletePrintTemplateByTemplateId(templateId);
    }
}

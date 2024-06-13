package com.ktg.print.mapper;

import java.util.List;
import com.ktg.print.domain.PrintTemplate;

/**
 * 打印模板配置Mapper接口
 * 
 * @author yinjinlu
 * @date 2024-04-17
 */
public interface PrintTemplateMapper 
{
    /**
     * 查询打印模板配置
     * 
     * @param templateId 打印模板配置主键
     * @return 打印模板配置
     */
    public PrintTemplate selectPrintTemplateByTemplateId(Long templateId);

    /**
     * 查询打印模板配置列表
     * 
     * @param printTemplate 打印模板配置
     * @return 打印模板配置集合
     */
    public List<PrintTemplate> selectPrintTemplateList(PrintTemplate printTemplate);

    /**
     * 新增打印模板配置
     * 
     * @param printTemplate 打印模板配置
     * @return 结果
     */
    public int insertPrintTemplate(PrintTemplate printTemplate);

    /**
     * 修改打印模板配置
     * 
     * @param printTemplate 打印模板配置
     * @return 结果
     */
    public int updatePrintTemplate(PrintTemplate printTemplate);

    /**
     * 删除打印模板配置
     * 
     * @param templateId 打印模板配置主键
     * @return 结果
     */
    public int deletePrintTemplateByTemplateId(Long templateId);

    /**
     * 批量删除打印模板配置
     * 
     * @param templateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePrintTemplateByTemplateIds(Long[] templateIds);
}

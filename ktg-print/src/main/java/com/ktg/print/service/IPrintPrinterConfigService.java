package com.ktg.print.service;

import com.ktg.print.domain.PrintPrinterConfig;

import java.util.List;

/**
 * 打印机配置Service接口
 * 
 * @author yinjinlu
 * @date 2023-09-01
 */
public interface IPrintPrinterConfigService 
{
    /**
     * 查询打印机配置
     * 
     * @param printerId 打印机配置主键
     * @return 打印机配置
     */
    public PrintPrinterConfig selectPrintPrinterConfigByPrinterId(Long printerId);

    /**
     * 查询打印机配置列表
     * 
     * @param printPrinterConfig 打印机配置
     * @return 打印机配置集合
     */
    public List<PrintPrinterConfig> selectPrintPrinterConfigList(PrintPrinterConfig printPrinterConfig);

    /**
     * 新增打印机配置
     * 
     * @param printPrinterConfig 打印机配置
     * @return 结果
     */
    public int insertPrintPrinterConfig(PrintPrinterConfig printPrinterConfig);

    /**
     * 修改打印机配置
     * 
     * @param printPrinterConfig 打印机配置
     * @return 结果
     */
    public int updatePrintPrinterConfig(PrintPrinterConfig printPrinterConfig);

    /**
     * 批量删除打印机配置
     * 
     * @param printerIds 需要删除的打印机配置主键集合
     * @return 结果
     */
    public int deletePrintPrinterConfigByPrinterIds(Long[] printerIds);

    /**
     * 删除打印机配置信息
     * 
     * @param printerId 打印机配置主键
     * @return 结果
     */
    public int deletePrintPrinterConfigByPrinterId(Long printerId);
}

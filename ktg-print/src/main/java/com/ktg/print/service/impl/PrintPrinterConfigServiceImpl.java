package com.ktg.print.service.impl;

import java.util.List;
import com.ktg.common.utils.DateUtils;
import com.ktg.print.domain.PrintPrinterConfig;
import com.ktg.print.mapper.PrintPrinterConfigMapper;
import com.ktg.print.service.IPrintPrinterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 打印机配置Service业务层处理
 * 
 * @author yinjinlu
 * @date 2023-09-01
 */
@Service
public class PrintPrinterConfigServiceImpl implements IPrintPrinterConfigService
{
    @Autowired
    private PrintPrinterConfigMapper printPrinterConfigMapper;

    /**
     * 查询打印机配置
     * 
     * @param printerId 打印机配置主键
     * @return 打印机配置
     */
    @Override
    public PrintPrinterConfig selectPrintPrinterConfigByPrinterId(Long printerId)
    {
        return printPrinterConfigMapper.selectPrintPrinterConfigByPrinterId(printerId);
    }

    /**
     * 查询打印机配置列表
     * 
     * @param printPrinterConfig 打印机配置
     * @return 打印机配置
     */
    @Override
    public List<PrintPrinterConfig> selectPrintPrinterConfigList(PrintPrinterConfig printPrinterConfig)
    {
        return printPrinterConfigMapper.selectPrintPrinterConfigList(printPrinterConfig);
    }

    /**
     * 新增打印机配置
     * 
     * @param printPrinterConfig 打印机配置
     * @return 结果
     */
    @Override
    public int insertPrintPrinterConfig(PrintPrinterConfig printPrinterConfig)
    {
        printPrinterConfig.setCreateTime(DateUtils.getNowDate());
        return printPrinterConfigMapper.insertPrintPrinterConfig(printPrinterConfig);
    }

    /**
     * 修改打印机配置
     * 
     * @param printPrinterConfig 打印机配置
     * @return 结果
     */
    @Override
    public int updatePrintPrinterConfig(PrintPrinterConfig printPrinterConfig)
    {
        printPrinterConfig.setUpdateTime(DateUtils.getNowDate());
        return printPrinterConfigMapper.updatePrintPrinterConfig(printPrinterConfig);
    }

    /**
     * 批量删除打印机配置
     * 
     * @param printerIds 需要删除的打印机配置主键
     * @return 结果
     */
    @Override
    public int deletePrintPrinterConfigByPrinterIds(Long[] printerIds)
    {
        return printPrinterConfigMapper.deletePrintPrinterConfigByPrinterIds(printerIds);
    }

    /**
     * 删除打印机配置信息
     * 
     * @param printerId 打印机配置主键
     * @return 结果
     */
    @Override
    public int deletePrintPrinterConfigByPrinterId(Long printerId)
    {
        return printPrinterConfigMapper.deletePrintPrinterConfigByPrinterId(printerId);
    }
}

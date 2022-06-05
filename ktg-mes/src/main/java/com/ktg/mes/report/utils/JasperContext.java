package com.ktg.mes.report.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: pl.piomin.jasperreport.handler
 * @date:2020/4/15
 */
@Component
public final class JasperContext {

    @Resource
    private DataSource dataSource;

    @Autowired
    private JasperReportExport exportExcel, exportHtml, exportImage, exportPdf, exportWord;

    private JasperReportExport jasperReportExport;

    private JasperContext() {
    }

    public boolean setFormatSuffix(FormatSuffixEnum suffix) {
        boolean result = true;
        switch (suffix) {
            case doc:
                jasperReportExport = exportWord;
                break;
            case xls:
                jasperReportExport = exportExcel;
                break;
            case pdf:
                jasperReportExport = exportPdf;
                break;
            case html:
                jasperReportExport = exportHtml;
                break;
            case png:
                jasperReportExport = exportImage;
                break;
            default:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 读取资源
     *
     * @return
     */
    public JasperPrint read(Map<String, Object> query, String sourceName) {
        query = query == null ? new HashMap<>() : query;        //获取文件流
        ClassPathResource resource = new ClassPathResource("data" + File.separator + sourceName + ".jasper");
        try {
            InputStream jasperStream = resource.getInputStream();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, query, dataSource.getConnection());
            return jasperPrint;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeExport(JasperPrint jasperPrint, String openType, String fileName,String fileType) {
        jasperReportExport.execute(jasperPrint, openType, fileName,fileType);
    }
}

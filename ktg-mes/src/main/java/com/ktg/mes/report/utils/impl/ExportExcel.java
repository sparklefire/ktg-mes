package com.ktg.mes.report.utils.impl;


import com.ktg.mes.report.utils.JasperReportExport;
import com.ktg.mes.report.utils.OpenTypeEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: com.yingyinqi.jasperreport.handler
 * @date:2020/4/15
 */
@Service
public class ExportExcel extends JasperReportExport {

    @Override
    public void initOpenType(OpenTypeEnum openType, String fileName, String fileType) {
        response.setContentType("application/vnd_ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=template.xls");
    }

    @Override
    public void executEexport(JasperPrint jasperPrint) throws IOException, JRException {
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }
}

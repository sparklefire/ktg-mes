package com.ktg.mes.report.utils.impl;


import com.ktg.mes.report.utils.JasperReportExport;
import com.ktg.mes.report.utils.OpenTypeEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: com.yingyinqi.jasperreport.handler
 * @date:2020/4/15
 */
@Service
public class ExportPdf extends JasperReportExport {

    @Override
    public void initOpenType(OpenTypeEnum openType, String fileName, String fileType) {
        response.setContentType("application/pdf");
        if (openType.equals(OpenTypeEnum.attachment)) {
            response.setHeader("Content-disposition", "attachment; filename=file.pdf");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "No-cache");
            response.setDateHeader("Expires", 0);
        }else{
            response.setHeader("Content-Disposition", "inline");
        }
    }

    @Override
    public void executEexport(JasperPrint jasperPrint) throws IOException, JRException {
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
}

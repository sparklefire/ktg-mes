package com.ktg.mes.report.utils.impl;


import com.ktg.mes.report.utils.JasperReportExport;
import com.ktg.mes.report.utils.OpenTypeEnum;
import com.ktg.mes.report.utils.ThreadLocalUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author: create by libin
 * @version: v1.0
 * @description: com.yingyinqi.jasperreport.handler
 * @date:2020/4/15
 */
@Service
public class ExportImage extends JasperReportExport {
    private static String FILE_TYPE="fileType";
    @Override
    public void initOpenType(OpenTypeEnum openType, String fileName, String fileType) {
        response.setContentType("image/png");
        if (openType.equals(OpenTypeEnum.attachment)) {
            // response.setHeader("Content-disposition", "attachment; filename="+fileName+"."+fileType);
            response.setHeader("Content-disposition", "attachment;");
        }
        ThreadLocalUtil.set(FILE_TYPE,fileType);
    }

    @Override
    public void executEexport(JasperPrint jasperPrint) throws IOException, JRException {
        JRGraphics2DExporter exporter = new JRGraphics2DExporter();
        //创建一个影像对象,设置宽高，可自定义
        BufferedImage bufferedImage = new BufferedImage(jasperPrint.getPageWidth(), jasperPrint.getPageHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        SimpleGraphics2DExporterOutput graphics2DOutput = new SimpleGraphics2DExporterOutput();
        graphics2DOutput.setGraphics2D(graphics2D);
        exporter.setExporterOutput(graphics2DOutput);
        exporter.setConfiguration(new SimpleGraphics2DReportConfiguration());
        exporter.exportReport();
        graphics2D.dispose();// 释放资源信息
        ImageIO.write(bufferedImage,  ThreadLocalUtil.get(FILE_TYPE), response.getOutputStream()); // 生成图片
    }
}

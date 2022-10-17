package com.ktg.common.utils.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class BarcodeUtil {
	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "PNG";
    // 二维码尺寸，长宽尺寸一致
    private static final int QRCODE_SIZE = 300;
    // 二维码内部logo的宽和高
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    
    public static File generateBarCode(String msg, String barCodeFormat, String path) {
        File file = new File(path);
        try {
        	if(StringUtils.isEmpty(msg)){
        		throw new RuntimeException("条码内容不能为空！");
        	}
        	//检查父级目录是否存在，不存在先新建
        	File parentFile = file.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
        	}
			/*
			 * 生成条码
			 */
           if(UserConstants.QR_CODE.equals(barCodeFormat)){
        	   //生成二维码
        	   generateQRCode(msg, null, file.getAbsolutePath(), false);
           }else{
        	   //生成一维码
        	   generateLineCode(msg, barCodeFormat, file.getAbsolutePath());
           }
        }catch (Exception e) {
        	 throw new RuntimeException("生成条码发生错误："+e.getMessage());
		}
        return file;
    }
    /**
     * 生成一维条形码
     * @param msg 条形码的内容
     * @param barCodeFormat 条形码格式 see {@link UserConstants}
     * @param destFilePath  条形码存储的路径
     * @throws Exception 
     */
    public static void generateLineCode(String msg,String barCodeFormat,String destFilePath) throws Exception {
    	AbstractBarcodeBean bean = null;
    	if(UserConstants.EAN_CODE.equals(barCodeFormat)){
    		bean = new EAN13Bean();
    	}else if(UserConstants.CODE39_CODE.equals(barCodeFormat)){
    		bean = new Code39Bean();
    	}else if(UserConstants.UPC_CODE.equals(barCodeFormat)){
    		bean = new UPCABean();
    	}else{
    		throw new Exception("不支持的条码类型！");
    	}
        // 精细度
        final int dpi = 150;
		if (bean instanceof Code39Bean) {
			Code39Bean codeBean = (Code39Bean) bean;
			// module宽度
			final double moduleWidth = UnitConv.in2mm(1.0f / dpi);

			// 配置对象
			bean.setModuleWidth(moduleWidth);
			codeBean.setWideFactor(3);
			bean.doQuietZone(false);
		}

        String format = "image/png";
        try {

            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(new FileOutputStream(destFilePath), format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);

            // 生成条形码
            bean.generateBarcode(canvas, msg);

            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 生成带logo二维码，并保存到磁盘
     *
     * @param content      二维码的内容
     * @param imgPath      二维码内部插入的图片路径，如果不需要可以传空
     * @param destFilePath     整个二维码存储的路径
     * @param needCompress 二维码内部插入的图片是否需要压缩的标识
     * @throws Exception 异常
     */
    public static void generateQRCode(String content, String imgPath, String destFilePath, boolean needCompress) throws Exception {
        BufferedImage image = createQrCode(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, new File(destFilePath));
    }

    /**
     * 生成二维码图片
     *
     * @param content      二维码内容
     * @param imagePath    二维码内部图片路径,如果不需要可以传空
     * @param needCompress 二维码内部图片是否需要压缩标识
     * @return 二维码图片
     * @throws WriterException 异常
     */
    private static BufferedImage createQrCode(String content,String imagePath,boolean needCompress) throws WriterException, IOException {
    	/**
         * EncodeHintType 编码提示类型 枚举类型:
         * CHARACTER_SET: 设置字符编码类型 "UTF-8" QR_CODE类型默认的编码格式 "ISO-8859-1"
         * MARGIN: 设置二维码边距，单位像素，值越小，二维码距离四周越近
         * ERROR_CORRECTION: 设置误差校正
         *  ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
         *    不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
         */
        Map<EncodeHintType, Object> hints = new ConcurrentHashMap<>(3);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        //加密生成二维码的矩阵对象 二维的0 1 然后根据特定的值选择属性
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //设置二维码的黑色0xFF000000 和白色0xFFFFFFFF
                image.setRGB(i, j, bitMatrix.get(i, j) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (StringUtils.isBlank(imagePath)) {
            return image;
        }
        //插入小图标 logo
        insertImage(image, imagePath, needCompress);
        return image;
    }
    /**
     * 将logo插入二维码中间
     *
     * @param source       生成的二位码存储对象
     * @param imagePath    二维码内部图片的路径
     * 一般的业务场景会把插入的小logo放到oss生成url
     * @param needCompress 二维码内部图片是否需要压缩
     * @throws IOException 异常
     */
    private static void insertImage(BufferedImage source, String imagePath, boolean needCompress) throws IOException {
        File file = new File(imagePath);
        if (!file.exists()) {
            //可以自定义抛出异常
        	 throw new RuntimeException("文件路径不存在:" + imagePath);
        }
        //这里修改ImageIO.read() 内可以接受Url InputStream File ImageInputStream
        Image src  = ImageIO.read(file);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        //是否压缩
        if (needCompress) {
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        Graphics2D graphics = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graphics.drawImage(src,x,y,width,height,null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graphics.setStroke(new BasicStroke(3f));
        graphics.draw(shape);
        graphics.dispose();
    }
    
    
    public static void main(String[] args) {
    	String msg="item12345678";
    	String EANmsg="012345678901";
    	String UPCmsg="12345678901";
    	String path = "D:/test/barcode/"+UUID.randomUUID()+".png";  
    	generateBarCode(msg, UserConstants.QR_CODE, path);
	}
}

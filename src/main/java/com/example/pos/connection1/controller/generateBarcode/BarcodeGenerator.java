package com.example.pos.connection1.controller.generateBarcode;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import java.awt.Font;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import io.jsonwebtoken.io.IOException;

public class BarcodeGenerator {

    public static byte[] generateBarcode(String barcodeText, int width, int height)
            throws IOException, WriterException, java.io.IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeText, BarcodeFormat.CODE_128, width, height, hints);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public BufferedImage generateEAN13BarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateUPCBarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createUPCA(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateEAN128BarCodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createEAN128(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        barcode.setBarHeight(65);
        barcode.setBarWidth(3);
        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateCode128BarCodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createCode128(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateUSPSBarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createUSPS(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateSCC14ShippingCodeBarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createSCC14ShippingCode(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateCode39BarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createCode39(barcodeText, true);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generateGlobalTradeItemNumberBarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createGlobalTradeItemNumber(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public BufferedImage generatePDF417BarcodeImage(final String barcodeText) throws Exception {
        final Barcode barcode = BarcodeFactory.createPDF417(barcodeText);
        barcode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        return BarcodeImageHandler.getImage(barcode);
    }

    public static byte[] bufferedImageToByteArray(BufferedImage image, String format)
            throws IOException, java.io.IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
}

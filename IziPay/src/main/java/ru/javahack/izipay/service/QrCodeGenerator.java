package ru.javahack.izipay.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;

import static java.lang.String.format;

/**
 * @author FORESTER
 */
@Component
public class QrCodeGenerator {

    //TODO move to properties
    public static final String PAYMENT_URL = "http://pay.com";

    public static final String PAYMENT_URL_TEMPLATE = PAYMENT_URL + "?userId=%s&paymentAmount=%s";

    @Autowired
    private QrCodeStorage qrCodeStorage;

    @Autowired
    private UserService userService;

    public void updateQrCode(BigDecimal paymentAmount) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(generateURL(paymentAmount), BarcodeFormat.QR_CODE, 350, 350);
            File temp = File.createTempFile("temp-file", ".png");
            qrCodeStorage.updateQrCodeByUserId(userService.getUserId(), temp);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", temp.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateURL(BigDecimal paymentAmount) {
        return format(PAYMENT_URL_TEMPLATE, userService.getUserId(), paymentAmount);
    }
}

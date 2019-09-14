package ru.javahack.izipay.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;

import static java.lang.String.format;

/**
 * @author FORESTER
 */
@Component
public class QrCodeGenerator {

    @Value("${paymentUrl}")
    private String paymentUrl;

    public static final String URL_PARAMETERS_TEMPLATE = "?userId=%s&paymentAmount=%s";

    @Autowired
    private QrCodeStorage qrCodeStorage;

    @Autowired
    private UserService userService;

    public void updateQrCode(BigDecimal paymentAmount) {
        qrCodeStorage.updateQrCodeByUserId(userService.getUserId(), createNewQrCode(paymentAmount));
    }

    private File createNewQrCode(BigDecimal paymentAmount){
        File tempFile;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(generateURL(paymentAmount), BarcodeFormat.QR_CODE, 350, 350);
            tempFile = File.createTempFile("temp-file", ".png");
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", tempFile.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tempFile;
    }

    private String generateURL(BigDecimal paymentAmount) {
        return paymentUrl + format(URL_PARAMETERS_TEMPLATE, userService.getUserId(), paymentAmount);
    }
}

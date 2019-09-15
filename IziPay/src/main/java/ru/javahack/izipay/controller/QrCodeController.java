package ru.javahack.izipay.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javahack.izipay.service.QrCodeStorage;
import ru.javahack.izipay.service.UserService;

import java.io.IOException;

/**
 * @author FORESTER
 */
@RestController
@RequestMapping("/api")
public class QrCodeController {

    @Autowired
    private QrCodeStorage qrCodeStorage;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/QR", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQrCode() throws IOException {
        return IOUtils.toByteArray(qrCodeStorage.getQrCodeByUserIdOrDefault((userService.getUserId())).toURI());
    }

    @GetMapping("/paymentDone")
    public void paymentDone(@RequestParam long userId) {
        qrCodeStorage.updateOrderDone(userId);
    }
}

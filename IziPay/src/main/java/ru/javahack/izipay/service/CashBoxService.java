package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javahack.izipay.pojo.api.CountOfProducts;

import java.util.List;

/**
 * @author FORESTER
 */
@Service
public class CashBoxService {

    @Autowired
    private QrCodeSocketService socketService;

    @Autowired
    private QrCodeGenerator qrCodeGenerator;

    public void submitOrder(List<CountOfProducts> products) {
        //TODO добавить генерацию QR
        socketService.updateQrCode();
//        socketService.updateQrCode(qrCodeGenerator.generateQrCode(products));
    }
}

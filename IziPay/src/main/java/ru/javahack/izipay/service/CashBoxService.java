package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javahack.izipay.db.DataService;
import ru.javahack.izipay.pojo.api.CountOfProducts;

import java.math.BigDecimal;
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

    @Autowired
    private DataService dataService;

    public void submitOrder(List<CountOfProducts> products) {
        qrCodeGenerator.updateQrCode(countPaymentAmount(products));
        socketService.sendNotification();
    }

    public BigDecimal countPaymentAmount(List<CountOfProducts> products){
        return products.stream()
                .map(p -> getPriceByProductId(p.getId()).multiply(new BigDecimal(p.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getPriceByProductId(long productId){
        return dataService.getProduct(productId).getPrice();
    }
}

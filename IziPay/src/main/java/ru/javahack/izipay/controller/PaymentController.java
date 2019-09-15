package ru.javahack.izipay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author FORESTER
 */
@Controller
public class PaymentController {

    @GetMapping("/pay")
    public String paymentPage() {
        return "payment.html";
    }

    @GetMapping("/ok")
    public String paymentCopletedPage() {
        return "payment_ok.html";
    }
}

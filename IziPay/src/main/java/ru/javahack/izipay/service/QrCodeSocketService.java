package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author FORESTER
 */
@Service
public class QrCodeSocketService {

    //TODO вынести в проперти
    public static final String TOPIC_ENDPOINT = "/topic/test";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void updateQrCode() {
        simpMessagingTemplate.convertAndSend(TOPIC_ENDPOINT, "");
    }
}

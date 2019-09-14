package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author FORESTER
 */
@Service
public class QrCodeSocketService {

    @Value("${topicEndpoint}")
    private String topicEndpoint;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendNotification() {
        simpMessagingTemplate.convertAndSend(topicEndpoint, "");
    }
}

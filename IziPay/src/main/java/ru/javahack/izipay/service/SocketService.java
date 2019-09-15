package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author FORESTER
 */
@Service
public class SocketService {

    @Value("${topicEndpointQR}")
    private String topicEndpointQR;

    @Value("${topicEndpointCashBox}")
    private String topicEndpointCashBox;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendNotificationToQR() {
        simpMessagingTemplate.convertAndSend(topicEndpointQR, "");
    }

    public void sendNotificationToCashBox() {
        simpMessagingTemplate.convertAndSend(topicEndpointCashBox, "");
    }
}

package ru.javahack.izipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FORESTER
 */
@Service
public class QrCodeStorage {

    @Autowired
    private QrCodeSocketService qrCodeSocketService;

    private static final String IMG_PATH = "img/";
    private static final String IN_PROGRESS_IMG = "in_progress.png";
    private static final String DONE_IMG = "done.png";

    private File inProgressImage;
    private File completedImage;

    private Map<Long, File> files = new HashMap<>();

    public QrCodeStorage() {
        inProgressImage = new File(getClass().getClassLoader().getResource(IMG_PATH + IN_PROGRESS_IMG).getFile());
        completedImage = new File(getClass().getClassLoader().getResource(IMG_PATH + DONE_IMG).getFile());
    }

    public File getQrCodeByUserIdOrDefault(long userId) {
        return files.containsKey(userId) ? files.get(userId) : inProgressImage;
    }

    public void updateQrCodeByUserId(long userId, File qrCode) {
        files.put(userId, qrCode);
        qrCodeSocketService.sendNotification();
    }

    public void updateOrderDone(long userId) {
        files.put(userId, completedImage);
        qrCodeSocketService.sendNotification();
    }
}

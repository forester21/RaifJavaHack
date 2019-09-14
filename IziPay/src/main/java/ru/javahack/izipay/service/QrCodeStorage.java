package ru.javahack.izipay.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FORESTER
 */
@Service
public class QrCodeStorage {

    public static final String DEFAULT_IMAGE_PATH = "img/no-qr-code.png";

    private File defaultImage;

    private Map<Long, File> files = new HashMap<>();

    public QrCodeStorage() {
        defaultImage = new File(getClass().getClassLoader().getResource(DEFAULT_IMAGE_PATH).getFile());
    }

    public File getQrCodeByUserIdOrDefault(long userId) {
        return files.containsKey(userId) ? files.get(userId) : defaultImage;
    }

    public void updateQrCodeByUserId(long userId, File qrCode) {
        files.put(userId, qrCode);
    }
}

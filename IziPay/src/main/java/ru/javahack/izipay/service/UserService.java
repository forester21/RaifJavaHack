package ru.javahack.izipay.service;

import org.springframework.stereotype.Service;

/**
 * Mock class for getting user id from request
 *
 * @author FORESTER
 */
@Service
public class UserService {

    public static final long MOCK_USER_ID = 1L;

    public long getUserId(){
        return MOCK_USER_ID;
    }
}

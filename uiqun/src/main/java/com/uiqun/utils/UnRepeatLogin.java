package com.uiqun.utils;

import com.uiqun.model.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class UnRepeatLogin implements Serializable {
    private static Map<String, User> userMap=new HashMap<String, User>();

    public static Map<String, User> getUserMap() {
        return userMap;
    }

    public static void setUserMap(String sessionId ,User user) {
        userMap.put(sessionId,user);
    }
}

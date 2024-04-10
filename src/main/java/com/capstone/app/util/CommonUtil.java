package com.capstone.app.util;

import com.capstone.app.service.JwtService;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    @Autowired
    private JwtService jwtService;

    public static boolean authenticateToken(String token) {
        try {
            if (token == null || token.isEmpty() || new JwtService().extractExpiration(token).before(new Date())) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static <T> T convertStringToObject(String info, Class<T> clazz) {
        return new Gson().fromJson(info, clazz);
    }
}

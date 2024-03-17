package com.capstone.app.util;

import com.capstone.app.service.JwtService;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class CommonUtil {
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
}

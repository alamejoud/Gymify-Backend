package com.capstone.app.service;

import java.util.function.Function;

public interface BCryptServiceInterface {

    public String hash(String password);
    public boolean verifyHash(String password, String hash);
    public boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc);

}

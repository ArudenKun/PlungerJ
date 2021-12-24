package com.aruden.plungerv2;

public class Config {
    
    public static String get(String key) {
        return System.getenv(key.toUpperCase());
    }
}

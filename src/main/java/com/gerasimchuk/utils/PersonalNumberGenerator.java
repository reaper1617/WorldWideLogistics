package com.gerasimchuk.utils;

import java.util.Random;

public class PersonalNumberGenerator {

    public static String generate(int length){
        if (length < 1 || length > 10) return null;
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i = 0; i < length; i++){
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}

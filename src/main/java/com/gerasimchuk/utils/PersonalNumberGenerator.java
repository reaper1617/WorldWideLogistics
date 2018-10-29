package com.gerasimchuk.utils;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * The type Personal number generator.
 */
@Service
public class PersonalNumberGenerator {

    /**
     * Generate string.
     *
     * @param length the length
     * @return the string
     */
    public static String generate(int length){
        if (length < 1 || length > 10) return null;
        StringBuilder stringBuilder = new StringBuilder("");
        for(int i = 0; i < length; i++){
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }
}

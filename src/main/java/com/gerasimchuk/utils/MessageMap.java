package com.gerasimchuk.utils;

import java.util.HashMap;
import java.util.Map;

public class MessageMap {
    private static Map<MessageCode, String> map = new HashMap<MessageCode, String>();
    private static final MessageMap messageMap = new MessageMap();

    private MessageMap(){
        init();
    }

    public static MessageMap getInstance(){
        return messageMap;
    }


    public String get(MessageCode messageCode){
        return map.get(messageCode);
    }

    private void init(){


    }

}

package com.gerasimchuk.utils;

import com.gerasimchuk.enums.UpdateMessageType;
import org.springframework.stereotype.Component;


@Component
public class MessageConstructorImpl implements MessageConstructor {
    @Override
    public String createMessage(UpdateMessageType updateMessageType, JSONconvertable targetObject) {
        return updateMessageType + " " + targetObject.convertToJSONString();
    }
}

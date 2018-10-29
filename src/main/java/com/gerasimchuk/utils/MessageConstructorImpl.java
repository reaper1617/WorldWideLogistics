package com.gerasimchuk.utils;

import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.services.interfaces.StatisticService;
import org.springframework.stereotype.Component;


/**
 * The type Message constructor.
 */
@Component
public class MessageConstructorImpl implements MessageConstructor {
    @Override
    public String createMessage(UpdateMessageType updateMessageType, JSONconvertable targetObject) {
        return updateMessageType + " " + targetObject.convertToJSONString();
    }

}

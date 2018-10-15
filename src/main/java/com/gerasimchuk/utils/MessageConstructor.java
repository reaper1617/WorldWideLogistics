package com.gerasimchuk.utils;

import com.gerasimchuk.enums.UpdateMessageType;
import com.gerasimchuk.services.interfaces.StatisticService;

public interface MessageConstructor {

    String createMessage(UpdateMessageType updateMessageType, JSONconvertable targetObject);
}

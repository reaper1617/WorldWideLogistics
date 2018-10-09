package com.gerasimchuk.utils;

import com.gerasimchuk.enums.UpdateMessageType;

public interface MessageConstructor {

    String createMessage(UpdateMessageType updateMessageType, JSONconvertable targetObject);

}

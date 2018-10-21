package com.gerasimchuk.converters;

import com.gerasimchuk.dto.UserDTO;
import com.gerasimchuk.entities.User;

import java.util.List;

public interface UserToDTOConverter {
    UserDTO convert(User u);
    List<UserDTO> convert(List<User> users);
}

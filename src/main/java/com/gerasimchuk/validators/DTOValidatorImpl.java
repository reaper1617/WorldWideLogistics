package com.gerasimchuk.validators;

import org.springframework.stereotype.Service;

/** Validator for Data Transfer Objects
 * @author Reaper
 * @version 1.0
 */

@Service
public class DTOValidatorImpl implements DTOValidator {
    public boolean validate(Object dto) {
        // todo: implement logic!!!
        if (dto == null) return false;
        return true;
    }
}

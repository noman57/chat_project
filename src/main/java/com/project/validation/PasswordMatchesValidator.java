package com.project.validation;



import com.project.dto.account.CreateUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Noman
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o instanceof CreateUserDTO) {
            final CreateUserDTO userData = (CreateUserDTO) o;
            return userData.getPassword().equals(userData.getMatchingPassword());
       }
        return false;
    }
}

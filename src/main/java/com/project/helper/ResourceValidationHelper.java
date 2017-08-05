package com.project.helper;

import com.project.exception.BadRequestException;
import com.project.exception.ResourceNotFoundException;
import com.project.presistance.domain.User;
import com.project.presistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by abdullah.alnoman on 06.08.17.
 */
@Component
public class ResourceValidationHelper {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;

    /**
     * If a user found with the userID then it will return the user object otherwise throw ResourceNotFoundException
     * @param userID
     * @param locale
     * @return
     */
    public User isUserFound(String userID, Locale locale) {
        User user = userService.getUserByUserNameOrEmail(userID);
        if (user == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("message.userIDNotFound", new Object[] {userID}, locale));
        } else {
            return user;
        }
    }

    /**
     * If the username is already used then a BadRequestException will thrown
     * @param username
     * @param locale
     */
    public void isUsernameAlreadyUsed(String username, Locale locale) {
        if (userService.isUserExists(username)) {
            throw new BadRequestException(messageSource.getMessage("message.alreadyUsed", new Object[] {username}, locale));
        }
    }

    /**
     * If the email is already used then a BadRequestException will thrown
     * @param email
     * @param locale
     */
    public void isEmailAlreadyUsed(String email, Locale locale) {
        if (userService.isUserEmailExists(email)) {
            throw new BadRequestException(messageSource.getMessage("message.alreadyUsed", new Object[] {email}, locale));
        }
    }

    /**
     * If the phone number is already used then a BadRequestException will thrown
     * @param phoneNumber
     * @param locale
     */
    public void isPhoneNumberAlreadyUsed(String phoneNumber, Locale locale) {
        if (userService.isUserPhoneExists(phoneNumber)) {
            throw new BadRequestException(messageSource.getMessage("message.alreadyUsed", new Object[] {phoneNumber}, locale));
        }
    }
}

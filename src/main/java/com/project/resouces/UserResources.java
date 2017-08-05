package com.project.resouces;

import com.project.dto.account.CreateUserDTO;
import com.project.dto.account.GetProfileDTO;
import com.project.dto.account.LoginDTO;
import com.project.dto.common.Message;
import com.project.exception.BadRequestException;
import com.project.helper.MessageHelper;
import com.project.helper.ResourceValidationHelper;
import com.project.presistance.domain.User;
import com.project.presistance.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.project.dto.common.Response;
import com.project.enums.StatusType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Locale;

/**
 * Created by abdullah.alnoman on 05.08.17.
 */
@RestController
public class UserResources {





    /**
     * Return the profile information of a user
     * Here userID can be username or email address
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET
    )

    public ResponseEntity<?> test() {

        return new ResponseEntity<>("Test and hello", HttpStatus.OK);
    }



    private static Logger logger = Logger.getLogger(UserResources.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResourceValidationHelper validationHelper;
    @Autowired
    private MessageHelper messageHelper;


    /**
     * Return the profile information of a user
     * Here userID can be username or email address
     * @param userID
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/users/{userID}",
            method = RequestMethod.GET
    )

    public ResponseEntity<?> getUserProfile(@PathVariable("userID") final String userID, Locale locale) {
//        ResourceUtils.userNotExist(userService, messageSource, userID, locale);
        // If a user exist with the userID the it will return the user otherwise throw ResourceNotFoundException
        validationHelper.isUserFound(userID, locale);
        GetProfileDTO profile = userService.getProfile(userID);
        return new ResponseEntity<>(new Response(StatusType.OK, profile), HttpStatus.OK);
    }

    /**
     * @param loginDTO
     * @param locale
     * @return
     */
    @RequestMapping(
            value = "/users/login",
            method = RequestMethod.POST)

    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, Locale locale) {
        logger.debug("Inside Login resource method");
        // If a user exist with the userID the it will return the user otherwise throw ResourceNotFoundException
        User user = validationHelper.isUserFound(loginDTO.getUserId(), locale);
//        User user = userService.getUserByUserNameOrEmail(loginDTO.getUserId());
        GetProfileDTO profile;
        //if ((user != null) && (loginDTO.getPassword() != null)) {
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            profile = userService.getProfile(loginDTO.getUserId());
        } else {
            throw new BadRequestException(messageSource.getMessage("message.invalidCredentials", null, locale));
        }

        return new ResponseEntity<>(new Response(StatusType.OK, profile), HttpStatus.OK);
    }


    /**
     * Create a new user
     *
     * @param userData
     * @return
     * @throws Exception
     */
    @RequestMapping(
            value = "/users",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )

    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO userData, final Locale locale) {
        logger.debug("Inside createUser");
        validationHelper.isUsernameAlreadyUsed(userData.getUsername(), locale);
        validationHelper.isEmailAlreadyUsed(userData.getEmail(), locale);
        if (StringUtils.hasText(userData.getPhone())) {
            //ResourceUtils.phoneExist(userService, messageSource, userData.getPhone(), locale);
            validationHelper.isPhoneNumberAlreadyUsed(userData.getPhone(), locale);
        }

        User newUser = userService.createUser(userData);
        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUser.getUsername())
                .toUri();
        responseHeaders.setLocation(newUserUri);
        Message message = messageHelper.buildMessage201("message.accountCreated", locale);
        return new ResponseEntity<>(new Response(StatusType.OK, message), responseHeaders, HttpStatus.CREATED);
    }




}

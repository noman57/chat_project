package com.project.presistance.service.impl;

import com.project.dto.account.CreateUserDTO;
import com.project.dto.account.GetProfileDTO;
import com.project.enums.RoleType;
import com.project.presistance.domain.Role;
import com.project.presistance.domain.User;
import com.project.presistance.repository.RoleRepository;
import com.project.presistance.repository.UserRepository;
import com.project.presistance.service.UserService;
import org.apache.log4j.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by abdullah.alnoman on 05.08.17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    private ModelMapper modelMapper = new ModelMapper();
    /*
	@Autowired
	private SubscriberService subscriberService;
    @Autowired
    private ReviewService reviewService;*/

    @Override
    public User createUser(CreateUserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setCreatedOn(new Date());
        user.setLastUpdatedOn(new Date());


        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    @Transactional(readOnly = true)
    public GetProfileDTO getProfile(String userID) {
        User user = userRepository.findOne(userID);
        GetProfileDTO profile = modelMapper.map(user, GetProfileDTO.class);

        return profile;
    }



    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findOne(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUserNameOrEmail(String userID) {
        return userRepository.findByUsernameOrEmail(userID, userID);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }



    @Override
    @Transactional(readOnly = true)
    public boolean isUserExists(String username) {
        return userRepository.exists(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUserPhoneExists(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(RoleType roleType) {
        return roleRepository.findByName(roleType.name());
    }}



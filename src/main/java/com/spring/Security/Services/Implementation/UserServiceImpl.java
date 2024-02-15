package com.spring.Security.Services.Implementation;

import com.spring.Security.Persistence.Repositories.UserRepository;
import com.spring.Security.Persistence.entities.UserEntity;
import com.spring.Security.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}

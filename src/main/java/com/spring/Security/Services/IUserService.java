package com.spring.Security.Services;

import com.spring.Security.Persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {
    public List<UserEntity> findAllUsers ();
}

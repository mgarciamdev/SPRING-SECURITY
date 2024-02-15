package com.spring.Security.Services;

import com.spring.Security.Persistence.entities.UserEntity;
import com.spring.Security.Services.Models.dtos.LoginDTO;
import com.spring.Security.Services.Models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO login) throws Exception;

    public ResponseDTO register(UserEntity user) throws Exception;
}

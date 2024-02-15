package com.spring.Security.Services.Implementation;

import com.spring.Security.Persistence.Repositories.UserRepository;
import com.spring.Security.Persistence.entities.UserEntity;
import com.spring.Security.Services.IAuthService;
import com.spring.Security.Services.IJWTUtilityService;
import com.spring.Security.Services.Models.dtos.LoginDTO;
import com.spring.Security.Services.Models.dtos.ResponseDTO;
import com.spring.Security.Services.Models.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO login) throws Exception {
        try{
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());

            if (user.isEmpty()){
                jwt.put("error", "Usuario no registrado!");
                return jwt;
            }

            if (verifyPassword(login.getPassword(), user.get().getPassword())){
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("ERROR", "Fallo al momento de autenticar");
            }

            return jwt;

        } catch(Exception e){
            throw new Exception(e.toString());
        }
    }

    public ResponseDTO register(UserEntity user) throws Exception{
        try{
            ResponseDTO response = userValidation.validate(user);

            if (response.getNumOfErrors() > 0){
                return response;
            }

            List<UserEntity> getAllUsers = userRepository.findAll();

            for (UserEntity existingUser : getAllUsers){
                if (existingUser.getEmail().equals(user.getEmail())){
                    response.setMessage("Email alredy Exists");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("Usuario registrado correctamente");

            return response;

        } catch (Exception e){
            throw new Exception(e.toString());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}

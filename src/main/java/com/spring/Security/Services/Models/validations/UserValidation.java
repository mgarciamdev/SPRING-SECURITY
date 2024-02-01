package com.spring.Security.Services.Models.validations;

import com.spring.Security.Persistence.entities.UserEntity;
import com.spring.Security.Services.Models.dtos.ResponseDTO;

public class UserValidation {

    public ResponseDTO validate(UserEntity user){
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if(user.getFirstName() == null ||
                user.getFirstName().length() < 3 ||
                user.getFirstName().length() > 15
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo de nombre debe tener datos, y tiene que tener entre 3 y 15 caracteres");
        }

        if(user.getLastName() == null ||
                user.getLastName().length() < 3 ||
                user.getLastName().length() > 30
        ){
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("El campo de nombre debe tener datos, y tiene que tener entre 3 y 15 caracteres");
        }

        return response;
    }
}

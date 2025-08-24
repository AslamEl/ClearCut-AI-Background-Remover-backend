package in.aslammhd.clearcut.controller;


import in.aslammhd.clearcut.dto.UserDTO;
import in.aslammhd.clearcut.response.RemoveBgResponse;
import in.aslammhd.clearcut.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createOrUpdateUser(@RequestBody UserDTO userDTO, Authentication authentication){

        RemoveBgResponse response=null;
        try{
            if(!authentication.getName().equals(userDTO.getClerkId())){
                response= RemoveBgResponse.builder()
                            .success(false)
                            .data("user does not have the permission to access the resource")
                            .statusCode(HttpStatus.FORBIDDEN)
                            .build();

                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }


            UserDTO user= userService.saveUser(userDTO);
            response=RemoveBgResponse.builder()
                    .success(true)
                    .data(user)
                    .statusCode(HttpStatus.OK)
                    .build();
            return  ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception exception){
            response=RemoveBgResponse.builder()
                    .success(false)
                    .data(exception.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

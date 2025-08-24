package in.aslammhd.clearcut.controller;


import in.aslammhd.clearcut.dto.UserDTO;
import in.aslammhd.clearcut.response.RemoveBgResponse;
import in.aslammhd.clearcut.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
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
    public RemoveBgResponse createOrUpdateUser(@RequestBody UserDTO userDTO, Authentication authentication){
        try{
            if(!authentication.getName().equals(userDTO.getClerkId())){
                    return RemoveBgResponse.builder()
                            .success(false)
                            .data("User Unauthorized")
                            .statusCode(HttpStatus.FORBIDDEN)
                            .build();
            }


            UserDTO user= userService.saveUser(userDTO);
            return RemoveBgResponse.builder()
                    .success(true)
                    .data(user)
                    .statusCode(HttpStatus.CREATED)
                    .build();
        }catch (Exception exception){
            return RemoveBgResponse.builder()
                    .success(false)
                    .data(exception.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}

package in.aslammhd.clearcut.service.impl;

import in.aslammhd.clearcut.dto.UserDTO;
import in.aslammhd.clearcut.entity.UserEntity;
import in.aslammhd.clearcut.repository.UserRepository;
import in.aslammhd.clearcut.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUser=userRepository.findByClerkId(userDTO.getClerkId());
        if(optionalUser.isPresent()){

            UserEntity existingUser=optionalUser.get();

            existingUser.setEmail(userDTO.getEmail());
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setPhotoUrl(userDTO.getPhotoUrl());

            if(userDTO.getCredits()!=null){
                existingUser.setCredits(userDTO.getCredits());
            }
            existingUser=userRepository.save(existingUser);
            return mapToDTO(existingUser);

        }
        UserEntity newUser= mapToEntity(userDTO);
        userRepository.save(newUser);
        return mapToDTO(newUser);
    }

    private UserDTO mapToDTO(UserEntity newUser) {
        return UserDTO.builder()
                .clerkId(newUser.getClerkId())
                .credits(newUser.getCredits())
                .email(newUser.getEmail())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .build();
    }

    private UserEntity mapToEntity(UserDTO userDTO) {

        return UserEntity.builder()
                .clerkId(userDTO.getClerkId())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .photoUrl(userDTO.getPhotoUrl())
                .build();
    }
}

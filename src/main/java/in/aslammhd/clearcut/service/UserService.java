package in.aslammhd.clearcut.service;

import in.aslammhd.clearcut.dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO getUserByClerkId(String clerkId);

    void deleteUserByClerkId(String clerkId);
}

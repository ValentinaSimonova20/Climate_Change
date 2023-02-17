package com.simonova.ecoinformerapp.services;

import com.simonova.ecoinformerapp.models.ERole;
import com.simonova.ecoinformerapp.models.Role;
import com.simonova.ecoinformerapp.models.User;
import com.simonova.ecoinformerapp.models.dto.UserDto;
import com.simonova.ecoinformerapp.repository.RoleRepository;
import com.simonova.ecoinformerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Optional<Role> role = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(role.isEmpty()){
            role = Optional.of(checkRoleExist());
        }
        user.setRoles(Arrays.asList(role.get()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUsername(email).orElse(null);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getUsername().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);
        return roleRepository.save(role);
    }
}

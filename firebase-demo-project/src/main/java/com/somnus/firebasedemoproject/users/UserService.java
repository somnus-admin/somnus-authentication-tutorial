package com.somnus.firebasedemoproject.users;

import com.somnus.firebasedemoproject.auth.exceptions.SomnusDemoException;
import com.somnus.firebasedemoproject.auth.firebase.FirebaseParser;
import com.somnus.firebasedemoproject.auth.firebase.FirebaseTokenHolder;
import com.somnus.firebasedemoproject.users.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesHandler rolesHandler;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new SomnusDemoException("No user Found");

        List<RoleEntity> grantedAuthorities = new ArrayList<>();
        for (GrantedAuthority role : user.getAuthorities()) {
            grantedAuthorities.add(new RoleEntity(role.getAuthority()));
        }

        user.setAuthorities(grantedAuthorities);
        return user;
    }

    public UserDto authenticateUser(String firebaseToken, UserDto userDto) {
        if (StringUtils.isBlank(firebaseToken)) {
            throw new IllegalArgumentException("Blank Firebase Token");
        }
        FirebaseTokenHolder firebaseTokenHolder = FirebaseParser.parseToken(firebaseToken);

        // add user to repository if it does not exist already
        User user = userRepository.findByUserName(firebaseTokenHolder.getUid());
        if (user == null) {
            user = new User(firebaseTokenHolder.getUid(), firebaseTokenHolder.getEmail(),
                    userDto.getDisplayName(), rolesHandler.getRoles("User"));
            userRepository.save(user);
        }

        // create new UserDto
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getDisplayName());
    }

}

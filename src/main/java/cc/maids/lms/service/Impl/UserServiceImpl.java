package cc.maids.lms.service.Impl;

import cc.maids.lms.entities.User;
import cc.maids.lms.exceptions.ResourcesNotFoundException;
import cc.maids.lms.repository.UserRepository;
import cc.maids.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public User loadUserByUsername(String username){
                return userRepository.findByEmail(username).orElseThrow(() -> new ResourcesNotFoundException("We can't find an user with email"));
            }
        };
    }

    @Override
    public User save(User newUser) {
        if(newUser.getId() == null)
            newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }
}

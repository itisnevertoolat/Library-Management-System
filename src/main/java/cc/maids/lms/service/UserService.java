package cc.maids.lms.service;

import cc.maids.lms.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    public UserDetailsService userDetailsService();
    public User save(User newUser);
}

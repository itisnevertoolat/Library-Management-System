package cc.maids.lms.config;

import cc.maids.lms.entities.User;
import cc.maids.lms.repository.UserRepository;
import cc.maids.lms.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("password123"))
                    .build();

            userService.save(admin);
        }
    }

}

package com.terrasync.backend.config.seed;

import com.terrasync.backend.entity.User;
import com.terrasync.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("dev")
@Order(1) // executa primeiro
public class UserDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserDataSeeder.class);

    public UserDataSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            seedUsers();
            logger.info("Seeding Users finished.");
        }
    }

    private void seedUsers() {
        User user1 = new User();
        user1.setName("Matheus Dev");
        user1.setEmail("matheus.dev@terrasync.com");
        String hashedPassword = new BCryptPasswordEncoder().encode("123456");
        user1.setHashedPassword(hashedPassword); // Placeholder
        user1.setActive(true);

        userRepository.save(user1);
        logger.info("Created user: {}", user1.getName());
    }
}

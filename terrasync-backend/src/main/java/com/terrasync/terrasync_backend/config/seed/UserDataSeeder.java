package com.terrasync.terrasync_backend.config.seed;

import com.terrasync.terrasync_backend.entity.User;
import com.terrasync.terrasync_backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

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
        // TODO: Usar um encoder de password real aqui (ex: BCryptPasswordEncoder)
        user1.setHashedPassword("$2a$10$..."); // Placeholder
        user1.setActive(true);

        userRepository.save(user1);
        logger.info("Created user: {}", user1.getName());
    }
}

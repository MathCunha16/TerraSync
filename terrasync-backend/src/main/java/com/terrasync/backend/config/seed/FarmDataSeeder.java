package com.terrasync.backend.config.seed;

import com.terrasync.backend.entity.Farm;
import com.terrasync.backend.entity.User;
import com.terrasync.backend.repository.FarmRepository;
import com.terrasync.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;

@Configuration
@Profile("dev")
@Order(2) // Executa depois do UserDataSeeder
public class FarmDataSeeder implements CommandLineRunner {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(FarmDataSeeder.class);

    public FarmDataSeeder(FarmRepository farmRepository, UserRepository userRepository) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (farmRepository.count() == 0) {
            seedFarms();
            logger.info("Seeding Farms finished.");
        }
    }

    private void seedFarms() {
        // Pega o primeiro usuário criado pelo UserDataSeeder
        User user = userRepository.findById(1L).orElse(null);

        if (user != null) {
            Farm farm1 = new Farm();
            farm1.setUser(user);
            farm1.setName("Fazenda Santa Clara");
            farm1.setSizeInHectares(new BigDecimal("150.75"));
            farm1.setCity("Holambra");
            farm1.setState("SP");
            farm1.setCountry("Brasil");
            // Geolocation pode ser adicionada se necessário

            farmRepository.save(farm1);
            logger.info("Created farm: {}", farm1.getName());
        } else {
            logger.warn("Could not seed farms because user with ID 1 was not found.");
        }
    }
}

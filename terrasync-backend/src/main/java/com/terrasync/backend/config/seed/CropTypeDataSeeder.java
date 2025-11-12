package com.terrasync.backend.config.seed;

import com.terrasync.backend.entity.CropType;
import com.terrasync.backend.repository.CropTypeRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@Profile("dev") // só vai executar em perfil de desenvolvimento
@Order(3)
public class CropTypeDataSeeder implements CommandLineRunner {

    private final CropTypeRepository cropTypeRepository;
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CropTypeDataSeeder.class);

    @Autowired
    public CropTypeDataSeeder(CropTypeRepository cropTypeRepository) {
        this.cropTypeRepository = cropTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Checa se o banco de dados já tem os dados pra não inserir a cada restart
        if (cropTypeRepository.count() == 0) {
            seedCropTypes();
            logger.info("Seeding CropTypes finished");
        }
    }

    private void seedCropTypes() {
        List<CropType> cropTypesToSeed = List.of(
                new CropType("Tomato"),
                new CropType("Cucumber"),
                new CropType("Soy"),
                new CropType("Carrot"),
                new CropType("Potato")
        );
        cropTypeRepository.saveAll(cropTypesToSeed);
    }

}

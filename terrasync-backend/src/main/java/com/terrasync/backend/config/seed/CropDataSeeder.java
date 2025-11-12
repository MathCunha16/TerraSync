package com.terrasync.backend.config.seed;

import com.terrasync.backend.entity.Crop;
import com.terrasync.backend.entity.CropType;
import com.terrasync.backend.entity.Farm;
import com.terrasync.backend.entity.enums.CropStatus;
import com.terrasync.backend.repository.CropRepository;
import com.terrasync.backend.repository.CropTypeRepository;
import com.terrasync.backend.repository.FarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Configuration
@Profile("dev")
@Order(4) // Executa depois de User, CropType e Farm
public class CropDataSeeder implements CommandLineRunner {

    private final CropRepository cropRepository;
    private final FarmRepository farmRepository;
    private final CropTypeRepository cropTypeRepository;
    private final Logger logger = LoggerFactory.getLogger(CropDataSeeder.class);

    public CropDataSeeder(CropRepository cropRepository, FarmRepository farmRepository, CropTypeRepository cropTypeRepository) {
        this.cropRepository = cropRepository;
        this.farmRepository = farmRepository;
        this.cropTypeRepository = cropTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (cropRepository.count() == 0) {
            seedCrops();
            logger.info("Seeding Crops finished.");
        }
    }

    private void seedCrops() {
        // Pega a primeira fazenda e o primeiro tipo de cultura
        Farm farm = farmRepository.findById(1L).orElse(null);
        CropType cropType = cropTypeRepository.findById(1L).orElse(null);

        if (farm != null && cropType != null) {
            Crop crop1 = new Crop();
            crop1.setFarm(farm);
            crop1.setCropType(cropType);
            crop1.setName("Plantação de Tomate 2025");
            crop1.setPlantingDate(LocalDate.now().minusMonths(1));
            crop1.setStatus(CropStatus.GROWING);

            cropRepository.save(crop1);
            logger.info("Created crop: {}", crop1.getName());
        } else {
            logger.warn("Could not seed crops because farm or crop type was not found.");
        }
    }
}

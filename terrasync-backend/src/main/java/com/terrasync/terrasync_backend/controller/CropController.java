package com.terrasync.terrasync_backend.controller;

import com.terrasync.terrasync_backend.service.crop.CropServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crop")
public class CropController {

    private final CropServices cropServices;

    public CropController(CropServices cropServices) {
        this.cropServices = cropServices;
    }
}

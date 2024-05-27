package org.example.service.vehicle;

import org.example.repository.vehicle.VanRepository;

public class VanService {

    private VanRepository vanRepository;

    public VanService(VanRepository vanRepository) {
        this.vanRepository = vanRepository;
    }
}

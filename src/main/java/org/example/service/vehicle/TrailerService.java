package org.example.service.vehicle;

import org.example.repository.vehicle.TrailerRepository;

public class TrailerService {

    private TrailerRepository trailerRepository;

    public TrailerService(TrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }
}

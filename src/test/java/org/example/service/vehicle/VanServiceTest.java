package org.example.service.vehicle;

import org.example.model.vehicle.Van;
import org.example.model.vehicle.VanBuilder;
import org.example.model.vehicle.Vehicle;
import org.example.model.vehicle.VehicleType;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class VanServiceTest {

    @org.junit.jupiter.api.Test
    void castVehiclesToCars() {
        List<Vehicle> vehicles = new ArrayList<>(Collections.emptyList());
        Van fordTransit = new VanBuilder().setMake("Ford").setBrand("Transit").setVehicleType(VehicleType.VAN).setRegistrationPlate("TYU200").setCarryingCapacity(1500).setHp(210)
                .setTrunkSpaceHeight(200).setTrunkSpaceWidth(180).setPricePerDay(85).createVan();
        vehicles.add(fordTransit);
        VanService vanService = new VanService();
        List<Van> vans = vanService.castVehiclesToVans(vehicles);
        Assertions.assertEquals(fordTransit, vans.stream().findAny().get());
    }
}
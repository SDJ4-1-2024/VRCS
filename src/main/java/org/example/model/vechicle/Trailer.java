package org.example.model.vechicle;

import org.example.model.Client;
import org.example.model.Rent;

public class Trailer extends Vehicle{
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;

    public Trailer(int trunkSpaceHeight, int trunkSpaceWidth, int carryingCapacity) {
        super();
        this.trunkSpaceHeight = trunkSpaceHeight;
        this.trunkSpaceWidth = trunkSpaceWidth;
        this.carryingCapacity = carryingCapacity;
    }

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public Rent rent(Client client) {
        return null;
    }
}

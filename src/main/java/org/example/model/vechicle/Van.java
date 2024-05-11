package org.example.model.vechicle;

import org.example.model.Client;
import org.example.model.Rent;

public class Van extends Vehicle{
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;
    private int hp;

    public Van(int trunkSpaceHeight, int trunkSpaceWidth, int carryingCapacity, int hp) {
        super();
        this.trunkSpaceHeight = trunkSpaceHeight;
        this.trunkSpaceWidth = trunkSpaceWidth;
        this.carryingCapacity = carryingCapacity;
        this.hp = hp;
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

package org.example;

import org.example.model.vehicle.Car;
import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.Van;
import org.example.model.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private List<Van> vans;
    private List<Trailer> trailers;

    public List<Car> initializeCars(){
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Corolla", "REG001", VehicleType.CAR, 50, 5, 500, 150));
        cars.add(new Car("Honda", "Civic", "REG002", VehicleType.CAR, 55, 5, 450, 140));
        cars.add(new Car("Volkswagen", "Golf", "REG003", VehicleType.CAR, 60, 5, 470, 145));
        cars.add(new Car("Ford", "Focus", "REG004", VehicleType.CAR, 52, 5, 460, 135));
        cars.add(new Car("Chevrolet", "Cruze", "REG005", VehicleType.CAR, 48, 5, 490, 155));
        cars.add(new Car("Nissan", "Sentra", "REG006", VehicleType.CAR, 53, 5, 480, 150));
        cars.add(new Car("Hyundai", "Elantra", "REG007", VehicleType.CAR, 54, 5, 510, 160));
        cars.add(new Car("Kia", "Forte", "REG008", VehicleType.CAR, 51, 5, 500, 150));
        cars.add(new Car("Mazda", "Mazda3", "REG009", VehicleType.CAR, 56, 5, 475, 140));
        cars.add(new Car("Subaru", "Impreza", "REG010", VehicleType.CAR, 57, 5, 490, 150));
        cars.add(new Car("BMW", "3 Series", "REG011", VehicleType.CAR, 75, 5, 520, 170));
        cars.add(new Car("Mercedes-Benz", "C-Class", "REG012", VehicleType.CAR, 80, 5, 530, 180));
        cars.add(new Car("Audi", "A4", "REG013", VehicleType.CAR, 78, 5, 515, 175));
        cars.add(new Car("Lexus", "IS", "REG014", VehicleType.CAR, 77, 5, 525, 170));
        cars.add(new Car("Toyota", "Camry", "REG015", VehicleType.CAR, 65, 5, 505, 160));
        cars.add(new Car("Honda", "Accord", "REG016", VehicleType.CAR, 68, 5, 495, 155));
        cars.add(new Car("Volkswagen", "Passat", "REG017", VehicleType.CAR, 70, 5, 500, 160));
        cars.add(new Car("Ford", "Fusion", "REG018", VehicleType.CAR, 63, 5, 485, 150));
        cars.add(new Car("Chevrolet", "Malibu", "REG019", VehicleType.CAR, 67, 5, 490, 155));
        cars.add(new Car("Nissan", "Altima", "REG020", VehicleType.CAR, 66, 5, 480, 150));
        return cars;
    }

    public List<Van> initializeVans(){
        List<Van> vans = new ArrayList<>();
        vans.add(new Van("Fiat", "Ducato", "VAN001", VehicleType.VAN, 80, 180, 170, 2000, 120));
        vans.add(new Van("Mercedes-Benz", "Sprinter", "VAN002", VehicleType.VAN, 85, 200, 180, 2500, 150));
        vans.add(new Van("Ford", "Transit", "VAN003", VehicleType.VAN, 75, 175, 165, 2200, 130));
        vans.add(new Van("Volkswagen", "Crafter", "VAN004", VehicleType.VAN, 90, 190, 180, 2400, 140));
        vans.add(new Van("Renault", "Master", "VAN005", VehicleType.VAN, 70, 185, 170, 2300, 135));
        vans.add(new Van("Peugeot", "Boxer", "VAN006", VehicleType.VAN, 78, 180, 175, 2100, 125));
        vans.add(new Van("Citroen", "Jumper", "VAN007", VehicleType.VAN, 77, 180, 175, 2100, 125));
        vans.add(new Van("Nissan", "NV400", "VAN008", VehicleType.VAN, 80, 185, 170, 2300, 135));
        vans.add(new Van("Opel", "Movano", "VAN009", VehicleType.VAN, 82, 190, 175, 2200, 130));
        vans.add(new Van("Iveco", "Daily", "VAN010", VehicleType.VAN, 88, 195, 180, 2500, 150));
        vans.add(new Van("Toyota", "Hiace", "VAN011", VehicleType.VAN, 65, 170, 160, 2000, 120));
        vans.add(new Van("Hyundai", "H350", "VAN012", VehicleType.VAN, 72, 180, 170, 2200, 130));
        vans.add(new Van("Isuzu", "N-Series", "VAN013", VehicleType.VAN, 85, 195, 180, 2500, 150));
        vans.add(new Van("Ram", "ProMaster", "VAN014", VehicleType.VAN, 83, 190, 175, 2400, 140));
        vans.add(new Van("Chevrolet", "Express", "VAN015", VehicleType.VAN, 75, 175, 165, 2100, 125));
        vans.add(new Van("GMC", "Savana", "VAN016", VehicleType.VAN, 78, 180, 170, 2200, 130));
        vans.add(new Van("Honda", "Acty", "VAN017", VehicleType.VAN, 60, 160, 150, 1800, 110));
        vans.add(new Van("Fiat", "Talento", "VAN018", VehicleType.VAN, 70, 170, 160, 2000, 120));
        vans.add(new Van("Peugeot", "Expert", "VAN019", VehicleType.VAN, 68, 165, 155, 1900, 115));
        vans.add(new Van("Citroen", "Dispatch", "VAN020", VehicleType.VAN, 67, 165, 155, 1900, 115));
        return vans;
    }

    public static List<Trailer> initializeTrailers() {
        List<Trailer> trailers = new ArrayList<>();

        trailers.add(new Trailer("Utility", "Basic", "TRL001", VehicleType.TRAILER, 40, 150, 100, 1500));
        trailers.add(new Trailer("Cargo", "HeavyDuty", "TRL002", VehicleType.TRAILER, 50, 200, 120, 2000));
        trailers.add(new Trailer("Utility", "Pro", "TRL003", VehicleType.TRAILER, 45, 160, 110, 1800));
        trailers.add(new Trailer("Cargo", "Light", "TRL004", VehicleType.TRAILER, 35, 140, 90, 1400));
        trailers.add(new Trailer("Utility", "Economy", "TRL005", VehicleType.TRAILER, 30, 130, 80, 1300));
        trailers.add(new Trailer("Cargo", "Standard", "TRL006", VehicleType.TRAILER, 55, 210, 130, 2200));
        trailers.add(new Trailer("Utility", "Deluxe", "TRL007", VehicleType.TRAILER, 60, 220, 140, 2400));
        trailers.add(new Trailer("Cargo", "Premium", "TRL008", VehicleType.TRAILER, 65, 230, 150, 2500));
        trailers.add(new Trailer("Utility", "Compact", "TRL009", VehicleType.TRAILER, 25, 120, 70, 1200));
        trailers.add(new Trailer("Cargo", "XL", "TRL010", VehicleType.TRAILER, 70, 240, 160, 2600));
        trailers.add(new Trailer("Utility", "Mini", "TRL011", VehicleType.TRAILER, 20, 110, 60, 1100));
        trailers.add(new Trailer("Cargo", "XXL", "TRL012", VehicleType.TRAILER, 75, 250, 170, 2800));
        trailers.add(new Trailer("Utility", "Heavy", "TRL013", VehicleType.TRAILER, 55, 210, 130, 2200));
        trailers.add(new Trailer("Cargo", "Mega", "TRL014", VehicleType.TRAILER, 80, 260, 180, 3000));
        trailers.add(new Trailer("Utility", "Light", "TRL015", VehicleType.TRAILER, 35, 140, 90, 1400));
        trailers.add(new Trailer("Cargo", "Compact", "TRL016", VehicleType.TRAILER, 50, 200, 120, 2000));
        trailers.add(new Trailer("Utility", "Standard", "TRL017", VehicleType.TRAILER, 45, 160, 110, 1800));
        trailers.add(new Trailer("Cargo", "Economy", "TRL018", VehicleType.TRAILER, 40, 150, 100, 1500));
        trailers.add(new Trailer("Utility", "Pro", "TRL019", VehicleType.TRAILER, 60, 220, 140, 2400));
        trailers.add(new Trailer("Cargo", "HeavyDuty", "TRL020", VehicleType.TRAILER, 70, 240, 160, 2600));

        return trailers;
    }

}

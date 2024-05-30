classDiagram
direction BT
class Booking {
  + Booking(LocalDate, LocalDate, Vehicle, Client) 
  - LocalDate endDate
  - Client client
  - LocalDate startDate
  - Vehicle vehicle
  + getStartDate() LocalDate
  + getClient() Client
  + getEndDate() LocalDate
  + isOngoing() boolean
  + getVehicle() Vehicle
}
class Car {
  + Car(String, String, String, VehicleType, Integer, int, int, int) 
  - int hp
  - int numberOfSeats
  - int trunkCapacity
  + getTrunkCapacity() int
  + isBooked() boolean
  + getNumberOfSeats() int
  + getHp() int
  + isRented() boolean
}
class CarBuilder {
  + CarBuilder() 
  - Integer pricePerDay
  - int hp
  - String registrationPlate
  - VehicleType vehicleType
  - String make
  - String brand
  - int trunkCapacity
  - int numberOfSeats
  + setVehicleType(VehicleType) CarBuilder
  + setHp(int) CarBuilder
  + setPricePerDay(Integer) CarBuilder
  + setRegistrationPlate(String) CarBuilder
  + setNumberOfSeats(int) CarBuilder
  + createCar() Car
  + setTrunkCapacity(int) CarBuilder
  + setBrand(String) CarBuilder
  + setMake(String) CarBuilder
}
class Client {
  + Client(String, String, ClientType) 
  + Client(String, ClientType) 
  - String phoneNumber
  - String password
  - ClientType type
  + type() ClientType
  + phoneNumber() String
  + password() String
}
class ClientType {
<<enumeration>>
  + ClientType() 
  +  PERSONAL
  +  ADMIN
  +  COMPANY
  + valueOf(String) ClientType
  + values() ClientType[]
}
class Trailer {
  + Trailer(String, String, String, VehicleType, Integer, int, int, int) 
  - int trunkSpaceHeight
  - int trunkSpaceWidth
  - int carryingCapacity
  + setCarryingCapacity(int) void
  + getTrunkSpaceWidth() int
  + getCarryingCapacity() int
  + getTrunkSpaceHeight() int
  + isRented() boolean
  + isBooked() boolean
  + setTrunkSpaceHeight(int) void
  + setTrunkSpaceWidth(int) void
}
class TrailerBuilder {
  + TrailerBuilder() 
  - int trunkSpaceWidth
  - Integer pricePerDay
  - String registrationPlate
  - int carryingCapacity
  - String make
  - VehicleType vehicleType
  - String brand
  - int trunkSpaceHeight
  + setTrunkSpaceHeight(int) TrailerBuilder
  + setCarryingCapacity(int) TrailerBuilder
  + setBrand(String) TrailerBuilder
  + createTrailer() Trailer
  + setPricePerDay(Integer) TrailerBuilder
  + setMake(String) TrailerBuilder
  + setTrunkSpaceWidth(int) TrailerBuilder
  + setVehicleType(VehicleType) TrailerBuilder
  + setRegistrationPlate(String) TrailerBuilder
}
class Van {
  + Van(String, String, String, VehicleType, Integer, int, int, int, int) 
  - int hp
  - int trunkSpaceWidth
  - int carryingCapacity
  - int trunkSpaceHeight
  + isBooked() boolean
  + getHp() int
  + isRented() boolean
  + getTrunkSpaceWidth() int
  + getCarryingCapacity() int
  + getTrunkSpaceHeight() int
}
class VanBuilder {
  + VanBuilder() 
  - String make
  - String brand
  - int trunkSpaceHeight
  - VehicleType vehicleType
  - int trunkSpaceWidth
  - String registrationPlate
  - Integer pricePerDay
  - int carryingCapacity
  - int hp
  + setBrand(String) VanBuilder
  + setPricePerDay(Integer) VanBuilder
  + setTrunkSpaceWidth(int) VanBuilder
  + setHp(int) VanBuilder
  + setMake(String) VanBuilder
  + setCarryingCapacity(int) VanBuilder
  + setRegistrationPlate(String) VanBuilder
  + setTrunkSpaceHeight(int) VanBuilder
  + createVan() Van
  + setVehicleType(VehicleType) VanBuilder
}
class Vehicle {
  + Vehicle(String, String, String, VehicleType, Integer) 
  - Integer pricePerDay
  - String brand
  - String registrationPlate
  - String make
  - VehicleType vehicleType
  + getMake() String
  + isBooked() boolean
  + isRented() boolean
  + getVehicleType() VehicleType
  + getPricePerDay() Integer
  + getBrand() String
  + getRegistrationPlate() String
}
class VehicleType {
<<enumeration>>
  + VehicleType() 
  +  TRAILER
  +  CAR
  +  VAN
  + values() VehicleType[]
  + valueOf(String) VehicleType
}

Booking "1" *--> "client 1" Client 
Booking "1" *--> "vehicle 1" Vehicle 
Car  -->  Vehicle 
CarBuilder  ..>  Car : «create»
CarBuilder "1" *--> "vehicleType 1" VehicleType 
Trailer  -->  Vehicle 
TrailerBuilder  ..>  Trailer : «create»
TrailerBuilder "1" *--> "vehicleType 1" VehicleType 
Van  -->  Vehicle 
VanBuilder  ..>  Van : «create»
VanBuilder "1" *--> "vehicleType 1" VehicleType 
Vehicle "1" *--> "vehicleType 1" VehicleType 

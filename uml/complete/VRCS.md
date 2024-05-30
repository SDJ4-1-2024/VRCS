classDiagram
direction BT
class AddEditCarViewController {
  + AddEditCarViewController() 
  - TextField pricePerDayField
  - TextField numberOfSeatsField
  - TextField trunkCapacityField
  - TextField registrationPlateField
  - ObservableList~CarViewModel~ cars
  - TextField makeField
  - TextField hpField
  - TextField brandField
  - CarViewModel carViewModel
  - closeDialog() void
  - cancel() void
  + setViewModel(ObservableList~CarViewModel~, CarViewModel) void
  - saveCar() void
}
class AddEditTrailerViewController {
  + AddEditTrailerViewController() 
  - TextField registrationPlateField
  - ObservableList~TrailerViewModel~ trailers
  - TextField trunkSpaceWidthField
  - TextField brandField
  - TextField pricePerDayField
  - TrailerViewModel trailerViewModel
  - TextField carryingCapacityField
  - TextField makeField
  - TextField trunkSpaceHeightField
  + setViewModel(ObservableList~TrailerViewModel~, TrailerViewModel) void
  - saveTrailer() void
  - cancel() void
  - closeDialog() void
}
class AddEditVanViewController {
  + AddEditVanViewController() 
  - TextField makeField
  - TextField registrationPlateField
  - VanViewModel vanViewModel
  - TextField trunkSpaceWidthField
  - TextField carryingCapacityField
  - TextField pricePerDayField
  - ObservableList~VanViewModel~ vans
  - TextField brandField
  - TextField hpField
  - TextField trunkSpaceHeightField
  - closeDialog() void
  - saveVan() void
  - cancel() void
  + setViewModel(ObservableList~VanViewModel~, VanViewModel) void
}
class AdminViewController {
  + AdminViewController() 
  - AdminViewModel viewModel
  + setViewModel(AdminViewModel) void
  - goToVehicleTypeSelection() void
  - openVehiclesView() void
  - openBookingsView() void
}
class AdminViewModel {
  + AdminViewModel() 
  - ObservableList~Booking~ bookings
  + getBookings() ObservableList~Booking~
  + removeBooking(Booking) void
}
class AvailableCarsController {
  + AvailableCarsController() 
  - LocalDate startDate
  - RefreshableBookingsController refreshableBookingsController
  - TableColumn~Car, Integer~ numberOfSeats
  - TableColumn~Car, String~ registrationPlateColumn
  - VehicleType vehicleType
  - TableColumn~Car, Integer~ pricePerDayColumn
  - TableColumn~Car, Integer~ trunkCapacity
  - TableColumn~Car, String~ makeColumn
  - TableColumn~Car, String~ brandColumn
  - TextField phoneTextField
  - TableView~Car~ availableCarsTable
  - ObservableList~Car~ availableCarsData
  ~ VehicleRepository vehicleRepository
  - TableColumn~Car, String~ vehicleTypeColumn
  - LocalDate endDate
  ~ ClientRepository clientRepository
  - TableColumn~Car, Integer~ hp
  - loadAvailableVehicles() void
  - prepareAvailableCars(List~Car~) void
  + setDetails(VehicleType, LocalDate, LocalDate) void
  + initialize() void
  - setPhoneTFVisibility() void
  + setRefreshableBookingsController(RefreshableBookingsController) void
  - closeOrCancel() void
  - bookCar() void
}
class AvailableTrailersController {
  + AvailableTrailersController() 
  - LocalDate startDate
  - TableColumn~Trailer, String~ brandColumn
  + TextField phoneTextField
  - RefreshableBookingsController refreshableBookingsController
  - TableColumn~Trailer, Integer~ trunkSpaceHeightColumn
  - ObservableList~Trailer~ availableTrailersData
  - TableColumn~Trailer, String~ makeColumn
  - VehicleType vehicleType
  - TableColumn~Trailer, Integer~ carryingCapacityColumn
  - TableColumn~Trailer, String~ vehicleTypeColumn
  - TableColumn~Trailer, Integer~ trunkSpaceWidthColumn
  ~ VehicleRepository vehicleRepository
  - TableView~Trailer~ availableTrailersTable
  - TableColumn~Trailer, Integer~ pricePerDayColumn
  - LocalDate endDate
  - TableColumn~Trailer, String~ registrationPlateColumn
  ~ ClientRepository clientRepository
  - prepareAvailableTrailers(List~Trailer~) void
  + setDetails(VehicleType, LocalDate, LocalDate) void
  - loadAvailableVehicles() void
  + initialize() void
  - closeOrCancel() void
  + setRefreshableBookingsController(RefreshableBookingsController) void
  - bookTrailer() void
}
class AvailableVansController {
  + AvailableVansController() 
  - TableColumn~Van, Integer~ trunkSpaceHeightColumn
  - ObservableList~Van~ availableVansData
  ~ ClientRepository clientRepository
  - TableColumn~Van, Integer~ hpColumn
  - TableColumn~Van, Integer~ trunkSpaceWidthColumn
  + TextField phoneTextField
  - LocalDate startDate
  ~ VehicleRepository vehicleRepository
  - TableColumn~Van, String~ vehicleTypeColumn
  - TableColumn~Van, Integer~ carryingCapacityColumn
  - TableColumn~Van, Integer~ pricePerDayColumn
  - TableColumn~Van, String~ makeColumn
  - VehicleType vehicleType
  - TableView~Van~ availableVansTable
  - RefreshableBookingsController refreshableBookingsController
  - TableColumn~Van, String~ registrationPlateColumn
  - TableColumn~Van, String~ brandColumn
  - LocalDate endDate
  - bookVan() void
  - closeOrCancel() void
  + initialize() void
  + setDetails(VehicleType, LocalDate, LocalDate) void
  - loadAvailableVehicles() void
  + setRefreshableBookingsController(RefreshableBookingsController) void
  - prepareAvailableVans(List~Van~) void
}
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
class BookingViewModel {
  + BookingViewModel(Booking) 
  - StringProperty clientPhoneNumber
  - StringProperty vehicleRegistrationPlate
  - StringProperty vehicleType
  - StringProperty endDate
  - StringProperty startDate
  + vehicleRegistrationPlateProperty() StringProperty
  + getVehicleRegistrationPlate() String
  + clientPhoneNumberProperty() StringProperty
  + getVehicleType() String
  + vehicleTypeProperty() StringProperty
  + endDateProperty() StringProperty
  + getStartDate() String
  + startDateProperty() StringProperty
  + getEndDate() String
  + getClientPhoneNumber() String
}
class BookingsRepository {
  + BookingsRepository() 
  - ClientRepository clientRepository
  - VehicleRepository vehicleRepository
  + getBookingId(LocalDate, LocalDate, String, String) Optional~Integer~
  + loadBookings() List~Booking~
  + saveBooking(Date, Date, int, int) void
  + loadBooking(int) Optional~Booking~
  + loadBookingsByClientId(int) List~Booking~
  + removeBooking(Date, Date, Integer, Integer) void
}
class BookingsService {
  + BookingsService() 
  - BookingsRepository bookingsRepository
  + prepareBookings() ObservableList~BookingViewModel~
  + isRemoved(BookingViewModel) boolean
  - isOngoing(LocalDate, LocalDate) boolean
  - prepareVehicleId(BookingViewModel) Integer
  - prepareClientId(BookingViewModel) Integer
}
class BookingsViewController {
  + BookingsViewController() 
  - TableView~BookingViewModel~ bookingsTableView
  - ObservableList~BookingViewModel~ bookingData
  - TableColumn~BookingViewModel, String~ vehicleColumn
  - TableColumn~BookingViewModel, String~ endDateColumn
  - TableColumn~BookingViewModel, String~ clientColumn
  - TableColumn~BookingViewModel, String~ startDateColumn
  - BookingsService bookingsService
  - TableColumn~BookingViewModel, String~ vehicleTypeColumn
  + initialize() void
  - setBookings() void
  + refreshBookings() void
  - handleAdd() void
  - handleEdit() void
  - handleRemove() void
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
class CarRepository {
  + CarRepository() 
  + saveCar(Car, int) void
  + prepareCarById(int, String, String, String, int) Optional~Car~
}
class CarService {
  + CarService() 
  + castVehiclesToCars(List~Vehicle~) List~Car~
}
class CarViewController {
  + CarViewController() 
  - TableColumn~CarViewModel, Integer~ numberOfSeatsColumn
  - TableColumn~CarViewModel, Integer~ trunkCapacityColumn
  - TableView~CarViewModel~ carTable
  - TableColumn~CarViewModel, String~ makeColumn
  - TableColumn~CarViewModel, Integer~ hpColumn
  - TableColumn~CarViewModel, String~ brandColumn
  - TableColumn~CarViewModel, String~ registrationPlateColumn
  - ObservableList~CarViewModel~ carsData
  - TableColumn~CarViewModel, Integer~ pricePerDayColumn
  - prepareCars(List~Car~) void
  - removeCar() void
  - addCar() void
  - openCarDialog(CarViewModel) void
  + initialize() void
}
class CarViewModel {
  + CarViewModel(Car) 
  - ObjectProperty~VehicleType~ vehicleType
  - IntegerProperty hp
  - IntegerProperty pricePerDay
  - StringProperty make
  - StringProperty brand
  - IntegerProperty numberOfSeats
  - IntegerProperty trunkCapacity
  - StringProperty registrationPlate
  + getPricePerDay() int
  + brandProperty() StringProperty
  + getNumberOfSeats() int
  + getTrunkCapacity() int
  + vehicleTypeProperty() ObjectProperty~VehicleType~
  + getVehicleType() String
  + getBrand() String
  + getRegistrationPlate() String
  + makeProperty() StringProperty
  + getHp() int
  + getMake() String
  + registrationPlateProperty() StringProperty
  + pricePerDayProperty() IntegerProperty
  + numberOfSeatsProperty() IntegerProperty
  + trunkCapacityProperty() IntegerProperty
  + hpProperty() IntegerProperty
}
class Client {
  + Client(String, String, ClientType) 
  + Client(String, ClientType) 
  - String phoneNumber
  - String password
  - ClientType type
  + type() ClientType
  + password() String
  + phoneNumber() String
}
class ClientHandler {
  + ClientHandler(Socket) 
  - Socket clientSocket
  - BufferedReader in
  - PrintWriter out
  + run() void
}
class ClientRepository {
  + ClientRepository() 
  + prepareClients() HashMap~String, Client~
  + prepareClientById(int) Optional~Client~
  + saveClient(Client) boolean
  + prepareClientIdByPhoneNumber(String) Optional~Integer~
}
class ClientService {
  + ClientService(ClientRepository) 
  - ClientRepository clientRepository
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
class ClientUtils {
  + ClientUtils() 
  + isAdmin() boolean
  + getPhoneNumber() String
}
class ClientUtilsTest {
  + ClientUtilsTest() 
  - clearPhonePreference() void
  ~ isAdmin() void
}
class ClientViewController {
  + ClientViewController() 
  - TableColumn~BookingViewModel, String~ endDateColumn
  - TableColumn~BookingViewModel, String~ vehicleColumn
  - BookingsService bookingsService
  - TableColumn~BookingViewModel, String~ startDateColumn
  - TableView~BookingViewModel~ bookingsTableView
  - TableColumn~BookingViewModel, String~ vehicleTypeColumn
  - ObservableList~BookingViewModel~ bookingData
  + initialize() void
  - setBookings() void
  - goToVehicleTypeSelection() void
  - removeBooking() void
  + refreshBookings() void
}
class ClientViewModel {
  + ClientViewModel() 
  - ObservableList~Booking~ bookings
  + getBookings() ObservableList~Booking~
  + removeBooking(Booking) void
}
class DatabaseUtil {
  + DatabaseUtil() 
  - String PROPERTIES_FILE
  + getConnection() Connection
}
class DateRangeSelectionController {
  + DateRangeSelectionController() 
  - VehicleType vehicleType
  - RefreshableBookingsController refreshableBookingsController
  - DatePicker endDatePicker
  - int RENTAL_DAYS_LIMIT
  - String WRONG_DATES_LABEL
  - DatePicker startDatePicker
  + initialize() void
  - prepareCarView(LocalDate, LocalDate) void
  - goToAvailableVehicles() void
  - prepareVanView(LocalDate, LocalDate) void
  - prepareTrailerView(LocalDate, LocalDate) void
  + setVehicleType(VehicleType) void
  - datesAreFine(LocalDate, LocalDate) boolean
  + setRefreshableBookingsController(RefreshableBookingsController) void
}
class LocalDateConverter {
  + LocalDateConverter() 
  + convertToDatabaseColumn(LocalDate) Date
  + convertToEntityAttribute(Date) LocalDate
}
class LogLine {
  + LogLine(String) 
  - String text
  + toString() String
  + getText() String
}
class LoginViewController {
  + LoginViewController() 
  - TextField phoneNumberField
  - LoginViewModel viewModel
  - ComboBox~String~ clientTypeComboBox
  - PasswordField passwordField
  - openAdminView() void
  - handleLogin() void
  - closeLoginWindow() void
  + initialize() void
  - handleSignUp() void
  - openClientView() void
  + setViewModel(LoginViewModel) void
}
class LoginViewModel {
  + LoginViewModel() 
  - Map~String, Client~ clients
  - ClientRepository clientRepository
  - prepareClientsHashMap(ClientRepository) Map~String, Client~
  + authenticate(String, String) boolean
  + signUp(String, String, String) void
  + isAdmin(String) boolean
}
class Main {
  + Main() 
  + main(String[]) void
  + start(Stage) void
}
class ManageVehicleViewModel {
  + ManageVehicleViewModel(Vehicle) 
  - StringProperty make
  - StringProperty brand
  - StringProperty registrationPlate
  - StringProperty vehicleType
  - IntegerProperty pricePerDay
  + getVehicleType() String
  + pricePerDayProperty() IntegerProperty
  + makeProperty() StringProperty
  + getRegistrationPlate() String
  + brandProperty() StringProperty
  + getMake() String
  + getPricePerDay() Integer
  + getBrand() String
  + registrationPlateProperty() StringProperty
  + vehicleTypeProperty() StringProperty
}
class ManageVehiclesViewController {
  + ManageVehiclesViewController() 
  - manageCars() void
  - manageVans() void
  - manageTrailers() void
  - openManagementView(String) void
}
class PopUpUtil {
  + PopUpUtil() 
  + popUpInfo(String, String) void
  + popUpError(String, String) void
}
class RefreshableBookingsController {
<<Interface>>
  + refreshBookings() void
}
class RentViewModel {
  + RentViewModel(Booking) 
  - ObjectProperty~Vehicle~ vehicle
  - ObjectProperty~LocalDate~ startDate
  - BooleanProperty ongoing
  - ObjectProperty~LocalDate~ endDate
  - ObjectProperty~Client~ client
  + vehicleProperty() ObjectProperty~Vehicle~
  + startDateProperty() ObjectProperty~LocalDate~
  + endDateProperty() ObjectProperty~LocalDate~
  + clientProperty() ObjectProperty~Client~
  + ongoingProperty() BooleanProperty
}
class Server {
  + Server() 
  - int PORT
  - ExecutorService pool
  + main(String[]) void
}
class SingletonLog {
  - SingletonLog() 
  - ArrayList~LogLine~ logLines
  - SingletonLog instance
  - Lock lock
  + getInstance() SingletonLog
  + log() void
  + addLog(String) void
  + getLogLines() ArrayList~LogLine~
}
class SingletonLogTest {
  + SingletonLogTest() 
  ~ checkSingletonLog() void
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
class TrailerRepository {
  + TrailerRepository() 
  + saveTrailer(Trailer, int) void
  + prepareTrailerById(int, String, String, String, int) Optional~Trailer~
}
class TrailerService {
  + TrailerService() 
  + castVehiclesToTrailers(List~Vehicle~) List~Trailer~
}
class TrailerViewController {
  + TrailerViewController() 
  - TableColumn~TrailerViewModel, String~ makeColumn
  - TableView~TrailerViewModel~ trailerTable
  - TableColumn~TrailerViewModel, String~ registrationPlateColumn
  - TableColumn~TrailerViewModel, Integer~ trunkSpaceHeightColumn
  - TableColumn~TrailerViewModel, Integer~ trunkSpaceWidthColumn
  - ObservableList~TrailerViewModel~ trailerData
  - TableColumn~TrailerViewModel, Integer~ pricePerDayColumn
  - TableColumn~TrailerViewModel, String~ brandColumn
  - TableColumn~TrailerViewModel, Integer~ carryingCapacityColumn
  - prepareTrailers(List~Trailer~) void
  + initialize() void
  - addTrailer() void
  - removeTrailer() void
  - openTrailerDialog(TrailerViewModel) void
}
class TrailerViewModel {
  + TrailerViewModel(Trailer) 
  - StringProperty brand
  - IntegerProperty pricePerDay
  - StringProperty make
  - IntegerProperty carryingCapacity
  - IntegerProperty trunkSpaceHeight
  - StringProperty registrationPlate
  - IntegerProperty trunkSpaceWidth
  + getMake() String
  + brandProperty() StringProperty
  + trunkSpaceWidthProperty() IntegerProperty
  + getBrand() String
  + getTrunkSpaceHeight() int
  + registrationPlateProperty() StringProperty
  + getRegistrationPlate() String
  + carryingCapacityProperty() IntegerProperty
  + getPricePerDay() int
  + getTrunkSpaceWidth() int
  + makeProperty() StringProperty
  + trunkSpaceHeightProperty() IntegerProperty
  + getCarryingCapacity() int
  + pricePerDayProperty() IntegerProperty
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
class VanRepository {
  + VanRepository() 
  + prepareVanById(int, String, String, String, int) Optional~Van~
  + saveVan(Van, int) void
}
class VanService {
  + VanService() 
  + castVehiclesToVans(List~Vehicle~) List~Van~
}
class VanServiceTest {
  + VanServiceTest() 
  ~ castVehiclesToCars() void
}
class VanViewController {
  + VanViewController() 
  - TableColumn~VanViewModel, String~ brandColumn
  - TableView~VanViewModel~ vanTable
  - TableColumn~VanViewModel, Integer~ trunkSpaceWidthColumn
  - TableColumn~VanViewModel, Integer~ hpColumn
  - TableColumn~VanViewModel, String~ registrationPlateColumn
  - TableColumn~VanViewModel, Integer~ carryingCapacityColumn
  - TableColumn~VanViewModel, Integer~ trunkSpaceHeightColumn
  - TableColumn~VanViewModel, Integer~ pricePerDayColumn
  - ObservableList~VanViewModel~ vansData
  - TableColumn~VanViewModel, String~ makeColumn
  - addVan() void
  - openVanDialog(VanViewModel) void
  - removeVan() void
  + initialize() void
  - prepareVans(List~Van~) void
}
class VanViewModel {
  + VanViewModel(Van) 
  - IntegerProperty carryingCapacity
  - IntegerProperty hp
  - IntegerProperty trunkSpaceHeight
  - StringProperty make
  - IntegerProperty trunkSpaceWidth
  - StringProperty registrationPlate
  - StringProperty brand
  - IntegerProperty pricePerDay
  + makeProperty() StringProperty
  + getTrunkSpaceWidth() int
  + getRegistrationPlate() String
  + trunkSpaceHeightProperty() IntegerProperty
  + registrationPlateProperty() StringProperty
  + hpProperty() IntegerProperty
  + getCarryingCapacity() int
  + pricePerDayProperty() IntegerProperty
  + getMake() String
  + trunkSpaceWidthProperty() IntegerProperty
  + carryingCapacityProperty() IntegerProperty
  + brandProperty() StringProperty
  + getTrunkSpaceHeight() int
  + getHp() int
  + getPricePerDay() int
  + getBrand() String
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
class VehicleRepository {
  + VehicleRepository() 
  ~ TrailerRepository trailerRepository
  ~ VanRepository vanRepository
  ~ CarRepository carRepository
  + loadCars() List~Car~
  + loadVans() List~Van~
  + isVehicleAvailable(String) boolean
  + prepareVehicleById(int) Vehicle
  - prepareCompleteVehicle(ResultSet) Vehicle
  + loadAvailableVehiclesInTimePeriodRange(LocalDate, LocalDate, VehicleType) List~Vehicle~
  + saveVehicle(Vehicle) void
  + loadTrailers() List~Trailer~
  - prepareQuery(LocalDate, LocalDate, VehicleType) String
  + prepareVehicleIdByRegPlate(String) Optional~Integer~
  + removeVehicle(String) void
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
class VehicleTypeSelectionController {
  + VehicleTypeSelectionController() 
  - ComboBox~String~ vehicleTypeComboBox
  - RefreshableBookingsController refreshableBookingsController
  - goToDateRangeSelection() void
  + initialize() void
  + setRefreshableBookingsController(RefreshableBookingsController) void
}
class VehicleViewController {
  + VehicleViewController() 
  - TextField brandField
  - TextField vehicleTypeField
  - TextField bookedField
  - TextField rentedField
  - VehicleViewModel viewModel
  - TextField makeField
  - TextField registrationPlateField
  + setViewModel(VehicleViewModel) void
}
class VehicleViewModel {
  + VehicleViewModel(Vehicle) 
  - ObjectProperty~VehicleType~ vehicleType
  - StringProperty brand
  - StringProperty registrationPlate
  - IntegerProperty pricePerDay
  - StringProperty make
  + vehicleTypeProperty() ObjectProperty~VehicleType~
  + pricePerDayProperty() IntegerProperty
  + registrationPlateProperty() StringProperty
  + makeProperty() StringProperty
  + brandProperty() StringProperty
}

AddEditCarViewController  ..>  CarBuilder : «create»
AddEditCarViewController  ..>  CarViewModel : «create»
AddEditCarViewController "1" *--> "cars *" CarViewModel 
AddEditCarViewController  ..>  VehicleRepository : «create»
AddEditTrailerViewController  ..>  TrailerBuilder : «create»
AddEditTrailerViewController  ..>  TrailerViewModel : «create»
AddEditTrailerViewController "1" *--> "trailers *" TrailerViewModel 
AddEditTrailerViewController  ..>  VehicleRepository : «create»
AddEditVanViewController  ..>  VanBuilder : «create»
AddEditVanViewController  ..>  VanViewModel : «create»
AddEditVanViewController "1" *--> "vans *" VanViewModel 
AddEditVanViewController  ..>  VehicleRepository : «create»
AdminViewController "1" *--> "viewModel 1" AdminViewModel 
AdminViewModel "1" *--> "bookings *" Booking 
AvailableCarsController  ..>  BookingsRepository : «create»
AvailableCarsController "1" *--> "availableCarsData *" Car 
AvailableCarsController  ..>  CarService : «create»
AvailableCarsController "1" *--> "clientRepository 1" ClientRepository 
AvailableCarsController  ..>  ClientRepository : «create»
AvailableCarsController "1" *--> "refreshableBookingsController 1" RefreshableBookingsController 
AvailableCarsController "1" *--> "vehicleRepository 1" VehicleRepository 
AvailableCarsController  ..>  VehicleRepository : «create»
AvailableCarsController "1" *--> "vehicleType 1" VehicleType 
AvailableTrailersController  ..>  BookingsRepository : «create»
AvailableTrailersController "1" *--> "clientRepository 1" ClientRepository 
AvailableTrailersController  ..>  ClientRepository : «create»
AvailableTrailersController "1" *--> "refreshableBookingsController 1" RefreshableBookingsController 
AvailableTrailersController "1" *--> "availableTrailersData *" Trailer 
AvailableTrailersController  ..>  TrailerService : «create»
AvailableTrailersController  ..>  VehicleRepository : «create»
AvailableTrailersController "1" *--> "vehicleRepository 1" VehicleRepository 
AvailableTrailersController "1" *--> "vehicleType 1" VehicleType 
AvailableVansController  ..>  BookingsRepository : «create»
AvailableVansController "1" *--> "clientRepository 1" ClientRepository 
AvailableVansController  ..>  ClientRepository : «create»
AvailableVansController "1" *--> "refreshableBookingsController 1" RefreshableBookingsController 
AvailableVansController "1" *--> "availableVansData *" Van 
AvailableVansController  ..>  VanService : «create»
AvailableVansController  ..>  VehicleRepository : «create»
AvailableVansController "1" *--> "vehicleRepository 1" VehicleRepository 
AvailableVansController "1" *--> "vehicleType 1" VehicleType 
Booking "1" *--> "client 1" Client 
Booking "1" *--> "vehicle 1" Vehicle 
BookingsRepository  ..>  Booking : «create»
BookingsRepository "1" *--> "clientRepository 1" ClientRepository 
BookingsRepository  ..>  ClientRepository : «create»
BookingsRepository  ..>  VehicleRepository : «create»
BookingsRepository "1" *--> "vehicleRepository 1" VehicleRepository 
BookingsService  ..>  BookingViewModel : «create»
BookingsService "1" *--> "bookingsRepository 1" BookingsRepository 
BookingsService  ..>  BookingsRepository : «create»
BookingsService  ..>  ClientRepository : «create»
BookingsService  ..>  VehicleRepository : «create»
BookingsViewController "1" *--> "bookingData *" BookingViewModel 
BookingsViewController "1" *--> "bookingsService 1" BookingsService 
BookingsViewController  ..>  BookingsService : «create»
BookingsViewController  ..>  RefreshableBookingsController 
Car  -->  Vehicle 
CarBuilder  ..>  Car : «create»
CarBuilder "1" *--> "vehicleType 1" VehicleType 
CarRepository  ..>  CarBuilder : «create»
CarViewController "1" *--> "carsData *" CarViewModel 
CarViewController  ..>  CarViewModel : «create»
CarViewController  ..>  VehicleRepository : «create»
ClientRepository  ..>  Client : «create»
ClientService "1" *--> "clientRepository 1" ClientRepository 
ClientViewController "1" *--> "bookingData *" BookingViewModel 
ClientViewController  ..>  BookingsService : «create»
ClientViewController "1" *--> "bookingsService 1" BookingsService 
ClientViewController  ..>  RefreshableBookingsController 
ClientViewModel "1" *--> "bookings *" Booking 
DateRangeSelectionController "1" *--> "refreshableBookingsController 1" RefreshableBookingsController 
DateRangeSelectionController "1" *--> "vehicleType 1" VehicleType 
LoginViewController  ..>  AdminViewModel : «create»
LoginViewController "1" *--> "viewModel 1" LoginViewModel 
LoginViewModel  ..>  Client : «create»
LoginViewModel "1" *--> "clients *" Client 
LoginViewModel  ..>  ClientRepository : «create»
LoginViewModel "1" *--> "clientRepository 1" ClientRepository 
Main  ..>  LoginViewModel : «create»
Server  ..>  ClientHandler : «create»
SingletonLog "1" *--> "logLines *" LogLine 
SingletonLog  ..>  LogLine : «create»
Trailer  -->  Vehicle 
TrailerBuilder  ..>  Trailer : «create»
TrailerBuilder "1" *--> "vehicleType 1" VehicleType 
TrailerRepository  ..>  TrailerBuilder : «create»
TrailerViewController "1" *--> "trailerData *" TrailerViewModel 
TrailerViewController  ..>  TrailerViewModel : «create»
TrailerViewController  ..>  VehicleRepository : «create»
Van  -->  Vehicle 
VanBuilder  ..>  Van : «create»
VanBuilder "1" *--> "vehicleType 1" VehicleType 
VanRepository  ..>  VanBuilder : «create»
VanServiceTest  ..>  VanBuilder : «create»
VanServiceTest  ..>  VanService : «create»
VanViewController "1" *--> "vansData *" VanViewModel 
VanViewController  ..>  VanViewModel : «create»
VanViewController  ..>  VehicleRepository : «create»
Vehicle "1" *--> "vehicleType 1" VehicleType 
VehicleRepository "1" *--> "carRepository 1" CarRepository 
VehicleRepository  ..>  CarRepository : «create»
VehicleRepository "1" *--> "trailerRepository 1" TrailerRepository 
VehicleRepository  ..>  TrailerRepository : «create»
VehicleRepository  ..>  VanRepository : «create»
VehicleRepository "1" *--> "vanRepository 1" VanRepository 
VehicleTypeSelectionController "1" *--> "refreshableBookingsController 1" RefreshableBookingsController 
VehicleViewController "1" *--> "viewModel 1" VehicleViewModel 

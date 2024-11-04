package carsharing.database.car;

import carsharing.database.DbClient;
import carsharing.database.company.CompanyDao;
import carsharing.domain.Car;
import carsharing.domain.Company;

import java.util.List;

public class CarDaoImpl implements CarDao {

    private final DbClient dbClient;
    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS CAR " +
            "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "company_id INTEGER NOT NULL, " +
            "FOREIGN KEY (company_id) REFERENCES COMPANY(id))";
    private static final String ADD = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('%s', '%d');";
    private static final String FIND_BY_COMPANY_ID_SORT_BY_ID = "SELECT * FROM CAR WHERE company_id = %d ORDER BY id;";
    private static final String FIND_AVAILABLE_BY_COMPANY_ID_SORT_BY_ID = "SELECT CAR.* FROM CAR WHERE " +
            "CAR.company_id=%d AND NOT EXISTS( SELECT 1 FROM Customer WHERE Customer.rented_car_id = Car.id);";
    private static final String FIND_BY_ID = "SELECT * FROM CAR WHERE id = %d ORDER BY id;";
    private static final String UPDATE = "UPDATE CAR SET name = '%s', company_id = %d WHERE id = %d";

    public CarDaoImpl(DbClient dbClient) {
        this.dbClient = dbClient;
        dbClient.run(CREATE_DB);
    }

    @Override
    public List<Car> findByCompany_Id(int id) {
        return dbClient.listCarsByCompanyId(FIND_BY_COMPANY_ID_SORT_BY_ID.formatted(id));
    }

    @Override
    public List<Car> findAvailableByCompany_Id(int id) {
        return dbClient.listCarsByCompanyId(FIND_AVAILABLE_BY_COMPANY_ID_SORT_BY_ID.formatted(id));
    }

    @Override
    public Car findById(int id) {
        return dbClient.selectCar(FIND_BY_ID.formatted(id));
    }

    @Override
    public void add(String carName, int companyId) {
        dbClient.run(ADD.formatted(carName, companyId));
    }

    @Override
    public void update(Car car) {
        dbClient.run(UPDATE.formatted(car.getName(), car.getCompanyId(), car.getId()));
    }
}

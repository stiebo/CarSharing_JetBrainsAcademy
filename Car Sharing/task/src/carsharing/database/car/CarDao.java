package carsharing.database.car;

import carsharing.domain.Car;
import carsharing.domain.Company;

import java.util.List;

public interface CarDao {
    //List<Car> findAll();
    List<Car> findByCompany_Id(int id);
    List<Car> findAvailableByCompany_Id(int id);
    Car findById(int id);
    void add(String carName, int companyId);
    void update(Car car);
    //void deleteById(int id);
}

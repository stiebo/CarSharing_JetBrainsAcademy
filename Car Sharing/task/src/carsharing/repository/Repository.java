package carsharing.repository;

import carsharing.database.DbClient;
import carsharing.database.car.CarDao;
import carsharing.database.car.CarDaoImpl;
import carsharing.database.company.CompanyDao;
import carsharing.database.company.CompanyDaoImpl;
import carsharing.database.customer.CustomerDao;
import carsharing.database.customer.CustomerDaoImpl;
import carsharing.domain.Car;
import carsharing.domain.Company;
import carsharing.domain.Customer;

import java.util.List;

public class Repository {
    private final CompanyDao companyDao;
    private final CarDao carDao;
    private final CustomerDao customerDao;

    public Repository(DbClient dbClient) {
        this.companyDao = new CompanyDaoImpl(dbClient);
        this.carDao = new CarDaoImpl(dbClient);
        this.customerDao = new CustomerDaoImpl(dbClient);
    }

    public List<Company> listCompanies() {
        return companyDao.findAll();
    }

    public Company findCompanyById(int id) {
        return companyDao.findById(id);
    }

    public void addCompany(String companyName) {
        companyDao.add(companyName);
    }

    public List<Car> listCarsByCompanyId(int id) {
        return carDao.findByCompany_Id(id);
    }

    public List<Car> listAvailableCarsByCompanyId(int id) {
        return carDao.findAvailableByCompany_Id(id);
    }

    public Car findCarById(int id) {
        return carDao.findById(id);
    }

    public void addCompanyCar(String carName, int companyId) {
        carDao.add(carName, companyId);
    }

    public List<Customer> listCustomers() {
        return customerDao.findAll();
    }

    public void addCustomer(String customerName) {
        customerDao.add(customerName);
    }

    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }


}

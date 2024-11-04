package carsharing.database;

import carsharing.domain.Car;
import carsharing.domain.Company;
import carsharing.database.exception.ExecuteSQLException;
import carsharing.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbClient {
    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL;

    public DbClient(String filename) {
        if (filename == null || filename.isEmpty()) {
            filename = "anything";
        }
        String filepath = "./src/carsharing/db/";
        DB_URL = "jdbc:h2:" + filepath + filename;
    }

    public Car selectCar(String sql) throws ExecuteSQLException {
        Car car = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int company_id = resultSet.getInt("company_id");
                car = new Car(id, name, company_id);

                if (resultSet.next()) {
                    throw new IllegalStateException("Query returned more than one object");
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            throw new ExecuteSQLException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            }
        }
        return car;

    }

    public Company selectCompany(String sql) throws ExecuteSQLException {
        Company company = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                company = new Company(id, name);

                if (resultSet.next()) {
                    throw new IllegalStateException("Query returned more than one object");
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            throw new ExecuteSQLException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            }
        }
        return company;
    }

    public List<Car> listCarsByCompanyId(String sql) throws ExecuteSQLException {
        List<Car> cars = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int company_id = resultSet.getInt("company_id");
                cars.add(new Car(id, name, company_id));
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            throw new ExecuteSQLException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            }
        }
        return cars;
    }

    public List<Customer> listCustomers(String sql) throws ExecuteSQLException {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer rented_car_id = resultSet.getObject("rented_car_id", Integer.class);
                customers.add(new Customer(id, name, rented_car_id));
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            throw new ExecuteSQLException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            }
        }
        return customers;
    }

    public List<Company> listCompanies(String sql) throws ExecuteSQLException {
        List<Company> companies = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                companies.add(new Company(id, name));
            }
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            throw new ExecuteSQLException(e);
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            }
        }
        return companies;
    }


    public void run(String sql) throws ExecuteSQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            // For testing requirements
            conn.setAutoCommit(true);
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            throw new ExecuteSQLException(se);
        } catch (Exception e) {
            //Handle errors for Class.forName
            throw new ExecuteSQLException(e);
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                throw new ExecuteSQLException(se);
            } //end finally try
        } //end try
    }


}

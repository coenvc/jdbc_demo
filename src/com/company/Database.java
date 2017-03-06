package com.company;

import java.sql.ResultSet;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * Created by coenv on 22-2-2017.
 */
public class Database {
    String dbUrl = "jdbc:mysql://localhost:3306/Company?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "root";

    private Connection connection;
    private Properties properties;

    //Create properties

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", pass);
        }
        return properties;
    }

    //connect database

    public Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dbUrl, getProperties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insert(Employee employee) {
        String sql = "Insert into employee"
                + "(Name,Surname,Email)" +
                "values(?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setString(3,employee.getEmail());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            disconnect();
        }
    }

    public Employee getEmployeById(int id){
        String sql = "Select * from employee where id = ?";
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name =   resultSet.getString("name");
                String surname = resultSet.getNString("surname");
                String email = resultSet.getNString("email");
                int user_id = resultSet.getInt("id");

                employee = new Employee(user_id,name,surname,email);
            }


        }

        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            disconnect();
        }
        return employee;
    }


}



//    //deletes an employee from the database
//    public void deleteEmployee(int id){}
//    //updates the values of an existing employee
//    public void updateEmployee(Employee employee){}
//    //retrieves an employee from the database with the given Id
//    public Employee getEmployeeByid(int id){}
//    //retrieves a list of every employee in the database
//    public List<Employee> getAllEmployees(){}



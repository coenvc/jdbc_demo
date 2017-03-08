package com.company;

import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
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

            public static Connection connect(){
                try {
//                    Class.forName("com.mysql.cj.jdbc.Driver");
                    return DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local/dbi339814?autoReconnect=true&useSSL=false&useUnicode=true" ,"dbi339814","Mypassword123");
                } catch(Exception e){
                    e.printStackTrace();
                }

                return null;
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

    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        String sql = "Select * from employee";
        Employee employee = null;
        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name =   resultSet.getString("name");
                String surname = resultSet.getNString("surname");
                String email = resultSet.getNString("email");
                int user_id = resultSet.getInt("id");

                employee = new Employee(user_id,name,surname,email);
                employeeList.add(employee);
            }
        }

        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            disconnect();
        }
        return employeeList;
    }

    public void delete(int id) {
        String sql = "delete from employee where id  = ?";

        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            disconnect();
        }
    }
    public void update(Employee employee,int id) {
        String sql = "update employee set name = ?, surname = ?, email = ? where id = ?";

        try {
            PreparedStatement preparedStatement = this.connect().prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2,employee.getSurname());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setInt(4,id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            disconnect();
        }
    }


}



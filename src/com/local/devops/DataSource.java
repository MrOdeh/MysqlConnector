package com.local.devops;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

final class DataSource {
    public static final String DATABASE = "prod";
    private static final String TABLE_NAME = "customer";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass1234";
    private Connection connection;

    private final static DataSource instance = new DataSource();

    //Quries...
    public static final String CUSTOMER_ALL = "select * from " + TABLE_NAME;

    private DataSource() {

    }

    public static DataSource getInstance() {
        return instance;
    }

    public void open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            System.out.println("Connected :) ");
        } catch (SQLException e) {
            System.out.println("Something failed during the connection to DB");
        }
    }

    public void close() {
        try {
            connection.close();
            System.out.println("Connection has been closed :)");
        } catch (SQLException e) {
            System.out.println("Something wrong happend during the CLosing :(");
        }
    }

    public List<Customer> getCustomers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(CUSTOMER_ALL);
            List<Customer> customers = new ArrayList<>();
            while (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt(1));
                customer.setFirstName(result.getString(2));
                customer.setLastName(result.getString(3));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

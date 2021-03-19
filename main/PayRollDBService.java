package com.javapractice;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayRollDBService {


    private static Object PayRollDBService;
    private PreparedStatement PayRolDataStatement;
    private static PayRollDBService payRolDBService;
    private PreparedStatement PayRollDataStatement;
    private PreparedStatement getPayRollDataStatement;
    private Object PayRollData;
    private Connection connection;

    private PayRollDBService() {
    }

    public static PayRollDBService getInstance() {
        if (payRolDBService == null)
            PayRollDBService = new PayRollDBService();
        return PayRollDBService;
    }


    private synchronized Connection getConnection() {
        String JdbcURL = "jdbc:mysql://localhost:3306/payrolservice?useSSL=false";
        String userName = "root";
        String password = "Prasanna92";
        Connection con;
        System.out.println("Processing Thread: "+ Thread.currentThread().getName()+
                "Connection to database with Id:" + connectionCounter);
        con = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Processing Thread: " + Thread.currentThread().getName()+
                "Id: " + connectionCounter + " Connection is successful!!!!" + con);
        return con;

    }

    public List<PayRollData> readData() {
        String sql = "SELECT * FROM PayrolService; ";
        return this.getPayRollDataUsingDB(sql);
    }

    public static Map<String, Double> getAverageSalaryByGender() {
        String sql = "SELECT gender, AVG(SALARY) AS avg_salary FROM payrol GROUP BY gender;";
        Map<String, Double> genderToAverageSalaryMap = new HashMap<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double salary = resultSet.getDouble("avg_salary");
                genderToAverageSalaryMap.put(gender, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genderToAverageSalaryMap;
    }

    public List<PayRollData> getPayRollData(String name) {
        List<PayRollData> PayRollList = null;
        if (this.PayRollDataStatement == null)
            this.prepareStatementForEmployeeData();
        try {
            PayRollDataStatement.setString(1, name);
            ResultSet resultSet = this.getPayRollDataStatement.executeQuery();
            PayRollList = this.PayRollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PayRolList;
    }

    private List<PayRollData> PayRollData(ResultSet resultSet) {
        return null;
    }

    public List<PayRollData> getPayRollForDateRange(LocalDate startDate, LocalDate endDate) {
        String sql = String.format("SELCT * FROM payrol WHERE START BETWEEN '%s' AND '%s':",
                Date.valueOf(startDate), Date.valueOf(endDate));
        return this.getPayRollDataUsingDB(sql);

    }

    private List<PayRollData> getPayRollDataUsingDB(String sql) {
        List<PayRollData> PayRollServiceList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            PayRollServiceList = this.getPayRollData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return PayRollServiceList;
    }

    private List<PayRollData> getPayRollData(ResultSet resultSet) {
        return null;
    }

    private List<PayRollData> getPayRolData(ResultSet resultSet) {
        List<PayRollData> payRollDataList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startData = resultSet.getDate("start").toLocalDate();
                payRollDataList.add(new PayRollData(id, name, "M", salary, startData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payRollDataList;
    }


    private void prepareStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM PAYROL WHERE name = ?";
            PayRollDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PayRollList;
    }


    public int updatePayRollData(String name, double salary) {
        return this.updatePayRollDataUsingStatement(name, salary);
    }

    private int updatePayRollDataUsingStatement(String name, double salary) {
        String sql = String.format("update EmployeePayRoll set salary =%.2f where name= '%s'", salary, name);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public PayRollData addEmployeeToPayRollUC7(String name, double salary, LocalDate startDate, String gender) {
        int employeeId = -1;
        PayRollData payRollData = null;
        String sql = String.format("INSERT INTO payrol (name, gender, salary, start) " +
                "VALUES ( '%s', '%s', '%s', '%s')", name, gender, salary, Date.valueOf(startDate));
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) employeeId = resultSet.getInt(1);

            }
            PayRollData = new PayRollData(employeeId, name, "M", salary, startDate);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
                return PayRollData;
            }

            public Object addEmployeeToPayRoll (String name,double salary, LocalDate startDate, String gender){
                int employeeId = -1;
                Connection connection = null;
                PayRollData payRollData = null;
                try {
                    connection = this.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try (Statement statement = connection.createStatement()) {
                    double deductions = salary * 0.2;
                    double taxablePay = salary - deductions;
                    double tax = taxablePay * 0.1;
                    double netPay = salary - tax;
                    String sql = String.format("INSERT INTO payrol_details " + "(employee_id,basic_pay, deductions, taxable_pay, tax, net_pay) VALUES " +
                            "(%s, %s, %s, %s, %s, %s )", employeeId, salary, deductions, taxablePay, tax, netPay);
                    int rowAffected = statement.executeUpdate(sql);
                    if (rowAffected == 1) {
                        payRollData = new PayRollData(employeeId, name, "M", salary, startDate);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    try {
                        connection.rollback();
                        return payRollData;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return payRollData;
            }
        }
    }
}






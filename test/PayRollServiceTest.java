package com.javapractice;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class PayRollServiceTest {
    private static final Object DB_IO = 0;
    private static final Object FILE_IO = 0;

    @Test
    public void given3employeeWhenWrittenTOFileShouldMatchEmployeeEntries() {
        PayRolServiceData[] arrayOfEmps = {
                new PayRolServiceData(1, "Jeff Bezos", 10000.00);
        new PayRollServiceData(2, "Bill Gates", 20000.00);
        new PayRollServiceData(3, "Mark Zukerberg", 30000.00);
        };
        PayRollService payRollService;
        payRollService = new PayRolService(Arrays.asList(arrayOfEmps));
        payRollService.writePayRollServiceData(FILE_IO);
        payRollService.printData(FILE_IO);
        long entries = payRollService.countEntries(FILE_IO);
        Assert.assertEquals(3, entries);
    }

    @Test
    public void givenPayRolInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        PayRollService payRollService = new PayRollService();
        List<PayRollServiceData> payRollServiceData = payRollService.readPayRollData(DB_IO);
        Assert.assertEquals(3, payRollServiceData.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdates_ShouldSynkWithDB() {
        PayRolService PayRolService = new PayRolService();
        List<PayRollData> payRolData = PayRolService();
        PayRolService.UpdateEmployeeSalary("Terisa",30000.00);
        boolean result = PayRolService.checkPayRolInSyncWithDB("Terisa");
        Assert.assertTrue(result);
    }

    private List<PayRollData> PayRolService() {
        return null;
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount(){
        PayRolService payRolService = new PayRolService();
        payRolService.readPayRollData(DB_IO);
        LocalDate startDate = LocalDate.od(2018, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<PayRolServiceData> payRolServiceData = payRolService.readPayRollForDateRange(DB_IO, startDate, endDate);
        Assert.assertEquals(3, payRolServiceData.size());
    }


    private class PayRolServiceData {
        public PayRolServiceData(int i, String jeff_bezos, double v) {
            return null;
        }
    }

    private class PayRolService {
        public <T> PayRolService(List<T> asList) {

        }

        public boolean checkPayRolInSyncWithDB(String Terisa) {
            return false;
        }


        public Map<String, Double> readAveragesalaryByGender(Object dbIo) {
            return null;
        }

        public void readPayRollData(Object dbIo) {

        }

        public List<PayRolServiceData> readPayRollForDateRange(Object dbIo, LocalDate startDate, LocalDate endDate) {
            return null;
        }

        public void UpdateEmployeeSalary(String Terisa, double v) {

        }
    }

    @Test
    public void givenPayRollData_WhenAverageSalaryRetrivedByGender_ShouldReturnProperValue() {
        PayRollService payRollService = new PayRollService();
        payRollService.readPayRollData(DB_IO);
        Map<String, Double> averageSalaryByGender = payRollService.readAveragesalaryByGender((PayRollService.IOService) DB_IO);
        Assert.assertTrue(averageSalaryByGender.get("M").equals(20000.00) &&
                averageSalaryByGender.get("F").equals(30000.00));
    }

    @Test
    public void givenNewEmployee_WhenAdded_ShouldSyncWithDB() {
        PayRollService payRollService = new PayRollService();
        payRollService.readPayRollData(DB_IO);
        payRollService.addEmployeePayRoll("Mark", 50000.00, LocalDate.now(), "M");
        boolean result = PayRollService.checkPayRollInSyncWithDB("Mark");
        Assert.assertTrue(result);
    }

    @Test
    public void given6Employees_WhenAddedToDB_ShouldMatchEmployeeEntries() {
        PayRollData[] arrayOFEmps = {
                new PayRollData(0, "Jeff Bezos", "M", 10000.00, LocalDate.now(),
                new PayRollData(0, "Bill Gates", "M", 20000.00, LocalDate.now()),
                new PayRollData(0, "Mark Zuckerberg", "M", 30000.00, LocalDate.now()),
                new PayRollData(0, "Sunder", "M", 60000.00, LocalDate.now()),
                new PayRollData(0, "Mukesh", "M", 10000.00, LocalDate.now()),
                new PayRollData(0, "Anil", "M", 20000.00, LocalDate.now()),
                };

        PayRollService payRollService = new PayRollService();
        payRollService.readPayRollData(DB_IO);
        Instant start = Instant.now();
        payRollService.addEmployeeToPayRoll(Arrays.asList(arrayOFEmps));
        Instant end = Instant.now();
        System.out.println("Duration without Thread:" + Duration.between(start, end));
        Instant threadStart = Instant.now();
        PayRollService.addEmployeeToPayRollWithThreads(Array.asList(arrayOFEmps));
        Instant threadEnd = Instant.now();
        System.out.println("Duration wilth Thread: "+ Duration.between(threadStart, threadEnd));
       PayRollService.printData(DB_IO);
        Assert.assertEquals(13,payRollService.countEntries(DB_IO));
    }
}


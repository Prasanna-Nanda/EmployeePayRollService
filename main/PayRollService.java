package com.javapractice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PayRollService<payRollList> {
    public void addEmployeeToPayRoll(List<PayRollData> payRollData) {

    }

    private final Object PayRollDBService = null;
    private final Object PayRollList = null;

    public static Object checkPayRollInSyncWithDB(String mark) {
        return null;
    }

    public Map<String, Double> readAveragesalaryByGender(IOService ioService) {
            if(ioService.equals(IOService.DB_IO))
                return PayRollDBService.getAverageSalaryByGender();
            return null;
        }

    public void writePayRollServiceData(Object fileIo) {

    }

    public void addEmployeePayRoll(String mark, double v, LocalDate now, String m) {
        return;
    }

    public long countEntries(Object fileIo) {
        return 0;
    }

    public static void printData(Object fileIo) {

    }

    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
        private List<PayRollData> payRolDataList;
        private PayRollDBService payRolDBService;

        public PayRollService(){
            PayRollDBService = PayRollDBService.getInstance();
        }
        public PayRollService(List<PayRollData> PayRolList){
            this();
            this.PayRollList = PayRollList;
        }

        public static void main(String[] args){
            ArrayList<PayRollData> PayRollList = new ArrayList<>();
            PayRollService payRollService = new PayRollService();
            Scanner consoleInputReader = new Scanner(System.in);
            PayRollService.readPayRollData(consoleInputReader);
            PayRollService.writePayRollData(IOService.CONSOLE_IO);

        }
        static List<PayRollServiceData> readPayRollData(Object consoleInputReader){
            System.out.println("Enter Employee ID:");
            int id = consoleInputReader.nextInt();
            System.out.println("Enter Employee Name: ");
            String name = consoleInputReader.next();
            System.out.println("Enter Employee Salary: ");
            double salary = consoleInputReader.nextDouble();
            PayRollList.add(new PayRollData(id, name, salary));
            return null;
        }

        public List<PayRollData> readPayRolData(IOService ioService){
            if(ioService.equals(IOService.DB_IO))
                this.PayRollList = PayRollDBService.readData();
            return this.PayRollList;
        }

        public List<PayRollServiceData> readPayRolForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
            if(ioService.equals(IOService.DB_IO))
                return PayRollDBService.getEmployeeForDateRange(startDate, endDate);
            return null;
        }


        public boolean checkPayRolInSyncWithDB(String name){
            List<PayRollData> PayRollDBServiceDataList = PayRollDBService.getInstance().getPayRollData(name);
            return getPayRollDataList.get(0).equals(getPayRollData(name));
        }
        public void updateEmployeeSalary(String name, double salary){
            int result = PayRollDBService.updatePayRollData(name,salary);
            if(result ==0 ) return;
            PayRollData payRolData = this.getPayRolData(name);
            if(PayRollData != null ) PayRollData.salary=salary;

        }

        private PayRollData getPayRolData(String name) {
            return this.PayRollList.stream()
                    .filter(PayRolDataItem -> PayRolDataItem.name.equals(name))
                    .findFirst()
                    .orElse(null);
        }
         public void addEmployeeToPayRoll(List<PayRollData> payRollData) {
             PayRollDataList.forEach(PayRollData ->
                     System.out.println("Employee Being Added:" + payRollData.name);
             this.addEmployeeToPayRoll(payRollData.name, payRollData.salary,
                                    payRollData.startDate, payRollData.gender);
             System.out.println("Employee Added: " + payRollData.name);
         });
          //  System.out.println(this.payRollList);
        public void addEmployeeToPayRollWithThread(List<PayRollData>PayRollDataList)
                Map<Integer, Boolean> employeeAdditionStatus = new Hashmap<Integer, Boolean>();
                PayRollDataList.forEach(PayRollData ->

    {
        Runnable task = () -> {
            employeeAdditionStatus.put(PayRollData.hashCode(), false);
            System.out.println("Employee Being Added: " + Thread.currentThread().getName());
            this.addEmployeeToPayRoll(PayRollData.name, PayRollData.salary,
                    PayRollData.startDate, PayRollData.gender);
            employeeAdditionStatus.put(PayRollData.hashCode(), true);
            System.out.println("Employee Added: " + Thread.currentThread().getName());
        };
        Thread thread = new Thread(task, PayRollData.name);
        thread.start();
    });
    private final Object PayRollDataList;

    {
        System.out.println(PayRollDataList);
    }
                while (employeeAdditionStatus.containsValue(False)){

    {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }
                System.out.println(this.PayRollList);
}



        public void updateEmployeeSalary(String name, double salary){
            int result = new PayRollDBService().updatePayrolData(name, salary);
            if (result==0) return;
            PayRollData payRolData= this.getPayRolData(name);
        }


        public static void writePayRollData(IOService ioService){
            if(ioService.equals(IOService.CONSOLE_IO))
                System.out.println("\nWriting Employee Payroll roastto Console \n" + PayRollList);
            else if (ioService.equals(IOService.FILE_IO))
                new PayRollFileIOService().writeData(PayRollList);

        }
                public void printData(IOService ioService) {
                    if (ioService.equals(IOService.FILE_IO))
                        new PayRollFileIOService().printData();
                    else System.out.println(PayRollList);
                }
    {
        public void addEmployeeToPayRoll(String name, double salary, LocalDate startDate, String gender){
            PayRollDataList.add(payRolDBService.addEmployeeToPayRoll(name, salary, startDate, gender));

        }
    }

}

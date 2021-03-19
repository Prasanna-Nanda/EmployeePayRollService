package com.javapractice;

import java.time.LocalDate;

public class PayRollData {




        public static double salary;
    public static LocalDate startDate;
    public static String name;
    public int id;
        public String name;
        public double salary;
        public LocalDate startDate;


        public PayRollData(Integer Id, String name, String m, Double salary, LocalDate startDate){
            this.id=id;
            this.name=name;
            this.salary=salary;
        }
        public PayRollData(int id, String name, double salary, localDate startDate){
            this.(id,name,salary);
            this.startDate=startDate;
        }

    public static Integer hashcode() {
        return null;
    }

    @Override
    public static int hashCode() {
        return Object.hash(name, gender, salary, startDate);
    }
    @Override
        public String toString() { return "id="+id+ ",name'"+name+'\''+",salary="+salary;}

        @Override
        public  boolean equals(Object o){
            if (this ==o) return true;
            if (o=null || getClass() != o.getClass()) return false;
            PayRollData that = (PayRollData) o;
            return id == that.id&&
                    Double.compare(that.salary,salary)==0 &&
                    name.equals(that.name);
        }

    }

}

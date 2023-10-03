package com.cbfacademy;

import com.cbfacademy.Employee.Employee;
import com.cbfacademy.FileHandler.FilenameException;
import com.cbfacademy.FileHandler.JSONFileHandler;

import java.util.List;

public class App {
    public static void main(String... args){
        try {
            List<Employee> employees = JSONFileHandler.readFile("src/main/resources/example.json");
            System.out.println("\n" + employees);
        } catch(FilenameException f){
            System.out.println(f.getMessage());
        }

        try {
            Employee employee1 = new Employee(01, "Cilian", "Moats", List.of(new String[]{"Director", "SWE"}));

            JSONFileHandler.save(employee1, "src/main/resources/employees.json");
        } catch(FilenameException f){
            System.out.println(f.getMessage());
        }

    }
}

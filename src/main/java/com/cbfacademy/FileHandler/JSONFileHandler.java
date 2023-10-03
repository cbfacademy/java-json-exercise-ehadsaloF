package com.cbfacademy.FileHandler;

import com.cbfacademy.Employee.Employee;
import com.cbfacademy.FileHandler.FilenameException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONFileHandler {

    public static List<Employee> readFile(String filename) throws FilenameException {

        if(!filename.endsWith(".json")){
            throw new FilenameException("Not a JSON File");
        }
        Gson gson = new Gson();
        Type employeeType = new TypeToken<List<Employee>>(){}.getType();

        try (Reader reader = new FileReader(filename)) {

            // Convert JSON File to Java Object
            List<Employee> employee = gson.fromJson(reader, employeeType);

            return employee;

        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        catch (IOException i) {
            System.out.println(i.getMessage());
        }

        return null;
    }

    public static void save(Employee employee, String outputFile) throws FilenameException{

        if(!outputFile.endsWith(".json")){
            throw new FilenameException("Not a JSON File");
        }

        Gson gson = new Gson();

        // Check if the file exists
        boolean fileExists = new File(outputFile).exists();

        if(fileExists){
            try (FileWriter writer = new FileWriter(outputFile, true)) {

                gson.toJson(employee, writer);
                System.out.println("Updated " + outputFile + " successfully");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{

        try (FileWriter writer = new FileWriter(outputFile)) {

            gson.toJson(employee, writer);
            System.out.println("Created and saved new data to " + outputFile + " successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }}
    }
}

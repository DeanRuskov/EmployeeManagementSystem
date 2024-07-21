package service;

import model.Employee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeService {
    public void readData(String path, ArrayList<Employee> employees) throws IOException {
        try (Scanner scanner = new Scanner(Paths.get(path))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] parts = line.split(",");
                int id=Integer.parseInt(parts[0]);
                String name= parts[1];
                String department=parts[2];
                String role= parts[3];
                double salary= Double.parseDouble(parts[4]);
                employees.add(new Employee(id,name,department,role,salary));

            }
        }
    }
    public void writeData(String path, ArrayList<Employee>employees) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(path);
        for(var employee: employees){
            writer.println(employee.toCSV());
        }
        writer.close();
    }

}

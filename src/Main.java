import Constants.Constants;
import manager.EmployeeManager;
import model.Employee;
import service.EmployeeService;

import java.io.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<Employee> employees= new ArrayList<Employee>();
        EmployeeService service= new EmployeeService();
        service.readData(Constants.path,employees);

        EmployeeManager manager= new EmployeeManager(service);
        manager.displayCpmmands();
        while(manager.isRunning){
            manager.execute(employees);
        }
    }
}
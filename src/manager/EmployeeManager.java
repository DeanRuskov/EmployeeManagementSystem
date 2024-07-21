package manager;

import Constants.Constants;
import model.Employee;
import service.EmployeeService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManager {
    private final EmployeeService service;
    public boolean isRunning = true;
    public EmployeeManager(EmployeeService service) {
        this.service = service;
    }

    public void displayCpmmands(){
        System.out.println("Add: Adds a new employee\n" +
                "Fire: Fires an existing employee with a given id\n" +
                "List: Lists all employees\n" +
                "Search: Displays all employees with a given Id, Name, Department or Role\n" +
                "Edit: Allows for the editing of an employee's information with a given id\n" +
                "Save: Saves the data to a CSV file and exits the program");

    }

    public void execute( ArrayList<Employee> employees) throws FileNotFoundException {
        Scanner sc= new Scanner(System.in);
        String[] command= sc.nextLine().split(" ");
        String[] tempEmployee;
        switch (command[0]){
            case "Add":
                tempEmployee= sc.nextLine().split(", ");
                if(isPresent(Integer.parseInt(tempEmployee[0]),employees)){
                    System.out.println("An emloyee with this id already exists");
                    break;
                }
                employees.add(new Employee(Integer.parseInt(tempEmployee[0]),tempEmployee[1],tempEmployee[2],tempEmployee[3],Double.parseDouble(tempEmployee[4])));
                break;
            case "Fire":
                Fire(Integer.parseInt(command[1]),employees);
                break;
            case"List":
                for(var employee:employees){
                    System.out.println(employee);
                }
                break;
            case"Search":
                switch(command[1]){
                    case"Id":
                        for(var employee:employees){
                            if(employee.getId()==Integer.parseInt(command[2]))
                                System.out.println(employee);
                        }
                        break;
                    case"Name":
                        for(var employee:employees){
                            if(employee.getName().equals(command[2]))
                                System.out.println(employee);
                        }
                        break;
                    case "Department":
                        for(var employee:employees){
                            if(employee.getDepartment().equals(command[2]))
                                System.out.println(employee);
                        }
                        break;
                    case "Role":
                        for(var employee:employees){
                            if(employee.getRole().equals(command[2]))
                                System.out.println(employee);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "Edit":
                tempEmployee= sc.nextLine().split(", ");
                for(var employee:employees){
                    if(employee.getId()==Integer.parseInt(command[1])){
                       employee.setName(tempEmployee[0]);
                       employee.setDepartment(tempEmployee[1]);
                       employee.setRole(tempEmployee[2]);
                       employee.setSalary(Double.parseDouble(tempEmployee[3]));
                    }
                }
                break;
            case "Save":
                Save(employees);
                isRunning=false;
                break;
        default:
            System.out.println("Please enter a valid command");
        }
    }

    public boolean isPresent(int id, ArrayList<Employee> employees){
        for(var emloyee : employees){
            if (emloyee.getId()==id) {
                return true;
            }
        }
        return  false;
    }

    public boolean Fire(int id, ArrayList<Employee> employees){
        for (int i = 0; i < employees.size(); i++) {
            Employee employee= employees.get(i);
            if (employee.getId()==id){
                employees.remove(i);
                return true;
            }
        }
        return  false;
    }

    public void Save(ArrayList<Employee> employees) throws FileNotFoundException {
       service.writeData(Constants.path, employees);
    }
}

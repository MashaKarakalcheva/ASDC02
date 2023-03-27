import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    String lastName;
    String firstName;
    String position;
    String salaryCategory;
    int experience;
    long idnp;

    public Employee(String lastName, String firstName, String position, String salaryCategory, int experience, long idnp) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.salaryCategory = salaryCategory;
        this.experience = experience;
        this.idnp = idnp;
    }
}

public class SelectionSort {

    public static void main(String[] args) {
        Employee[] employees = readEmployeesFromFile("C:\\\\Users\\\\Kozochka\\\\IdeaProjects\\\\lab2 asdc\\\\src\\\\Employee.txt");
        selectionSort(employees, "lastName");
        printEmployees(employees);
    }

    public static Employee[] readEmployeesFromFile(String fileName) {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                String lastName = fields[0];
                String firstName = fields[1];
                String position = fields[2];
                String salaryCategory = fields[3];
                int experience = Integer.parseInt(fields[4].trim());
                long idnp = Long.parseLong(fields[5].trim());
                employees.add(new Employee(lastName, firstName, position, salaryCategory, experience, idnp));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return employees.toArray(new Employee[employees.size()]);
    }

    public static void selectionSort(Employee[] employees, String key) {
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.nanoTime();

        for (int i = 0; i < employees.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < employees.length; j++) {
                comparisons++;
                switch (key) {
                    case "idnp":
                        if (employees[j].idnp < employees[minIndex].idnp) {
                            minIndex = j;
                        }
                        break;
                    case "lastName":
                        if (employees[j].lastName.compareToIgnoreCase(employees[minIndex].lastName) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "firstName":
                        if (employees[j].firstName.compareToIgnoreCase(employees[minIndex].firstName) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "position":
                        if (employees[j].position.compareToIgnoreCase(employees[minIndex].position) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "salaryCategory":
                        if (employees[j].salaryCategory.compareToIgnoreCase(employees[minIndex].salaryCategory) < 0) {
                            minIndex = j;
                        }
                        break;
                    case "experience":
                        if (employees[j].experience < employees[minIndex].experience) {
                            minIndex = j;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid key: " + key);
                }
            }
            if (minIndex != i) {
                swaps++;
                Employee temp = employees[minIndex];
                employees[minIndex] = employees[i];
                employees[i] = temp;
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Сортировка проходит по следующему ключу - " + key + ":");
        System.out.println("Количество сравнений: " + comparisons);
        System.out.println("Количество обменов: " + swaps);
        System.out.println("Время выполнения алгоритма: " + duration + " ms");
    }

    public static void printEmployees(Employee[] employees) {
        System.out.println("Фамилия\tИмя\tДолжность\tОклад\tСтаж\tIDNP");
        for (Employee e : employees) {
            System.out.println(e.firstName + "\t" + e.lastName + "\t" + e.position + "\t" + e.salaryCategory + "\t" + e.experience + "\t" + e.idnp);
        }
    }}
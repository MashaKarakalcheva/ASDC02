import java.io.*;
import java.util.*;

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



public class BubbleSort {
    public static void main(String[] args) {
        Employee[] employees = readEmployeesFromFile("C:\\\\Users\\\\Kozochka\\\\IdeaProjects\\\\lab2 asdc\\\\src\\\\employee.txt ");
        sortEmployeesBySalary(employees);
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

    public static void sortEmployeesBySalary(Employee[] employees) {
        int n = employees.length;
        boolean swapped = true;
        int comparisons = 0;
        int swaps = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < n - 1 && swapped; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (employees[j].experience > employees[j + 1].experience) {
                    swaps++;
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                    swapped = true;
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; 
        System.out.println("Сортировка пузырьком по стажу работы:");
        System.out.println("Теоретическая оценка сложности: O(n^2)");
        System.out.println("Количество сравнений: " + comparisons);
        System.out.println("Количество обменов: " + swaps);
        System.out.println("Время выполнения алгоритма: " + duration + " ms");
    }

    public static void printEmployees(Employee[] employees) {
        System.out.println("Стаж работы\tФамилия\tИмя");
        for (Employee e : employees) {
            System.out.println(e.experience + "\t" + e.firstName + "\t" + e.lastName);
        }
    }
}

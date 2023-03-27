import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionSort {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("C:\\\\Users\\\\Kozochka\\\\IdeaProjects\\\\lab2 asdc\\\\src\\\\Employee.txt");
        Scanner scanner = new Scanner(file);


        int size = 0;
        while (scanner.hasNextLine()) {
            size++;
            scanner.nextLine();
        }
        scanner.close();

        Employee[] employees = new Employee[size];
        scanner = new Scanner(file);
        for (int i = 0; i < size; i++) {
            String[] data = scanner.nextLine().split(",");
            employees[i] = new Employee(data[0], data[1], data[2], data[3], Integer.parseInt(data[4].trim()), Long.parseLong(data[5].trim()));
        }
        scanner.close();

        long startTime = System.nanoTime();
        for (int i = 1; i < size; i++) {
            Employee key = employees[i];
            int j = i - 1;
            while (j >= 0 && employees[j].getPosition().compareTo(key.getPosition()) > 0) {
                employees[j + 1] = employees[j];
                j--;
            }
            employees[j + 1] = key;
        }
        long endTime = System.nanoTime();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("Теоретическая оценка сложности: O(n^2)");
        System.out.println("Количество сравнений: " + (size * (size - 1)) / 2);
        System.out.println("Количество перестановок: " + (size - 1));
        System.out.println("Время выполнения алгоритма: " + (endTime - startTime) + " наносекунд");
    }

    static class Employee {
        private String lastName;
        private String firstName;
        private String position;
        private String salaryCategory;
        private int experience;
        private long idnp;

        public Employee(String lastName, String firstName, String position, String salaryCategory, int experience, long idnp) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.position = position;
            this.salaryCategory = salaryCategory;
            this.experience = experience;
            this.idnp = idnp;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getPosition() {
            return position;
        }

        public String getSalaryCategory() {
            return salaryCategory;
        }

        public int getExperience() {
            return experience;
        }

        public long getIdnp() {
            return idnp;
        }

        @Override
        public String toString() {
            return lastName + "," + firstName + "," + position + "," + salaryCategory + "," + experience + "," + idnp;
        }
    }
}
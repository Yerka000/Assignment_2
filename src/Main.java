import java.util.ArrayList;
import java.util.Collections;

// Interface for objects that can be paid
interface Payable {
    double getPaymentAmount();
}

// Base class representing a person
class Person implements Payable, Comparable<Person> {
    // Helper static variable to automatically set IDs
    private static int idCounter = 1;

    // Fields
    private int id;
    private String name;
    private String surname;

    // Default constructor - automatically sets the ID
    public Person() {
        this.id = idCounter++;
    }

    // Parametrized constructor
    public Person(String name, String surname) {
        this(); // Calls the default constructor to set ID
        this.name = name;
        this.surname = surname;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Default implementation for Payable interface
    @Override
    public double getPaymentAmount() {
        return 0.0; // Default implementation for Person, overridden in subclasses
    }

    // Method to get the person's position (Student or Employee)
    public String getPosition() {
        return "Student"; // Default implementation, overridden in subclasses
    }

    // String representation of the object
    @Override
    public String toString() {
        return getPosition() + ": " + id + ". " + name + " " + surname;
    }

    // Implementation of compareTo method for sorting
    @Override
    public int compareTo(Person other) {
        return Double.compare(this.getPaymentAmount(), other.getPaymentAmount());
    }
}

// Class representing an employee, extends Person
class Employee extends Person {
    // Additional fields
    private String position;
    private double salary;

    // Default constructor
    public Employee() {
        super(); // Calls the default constructor of the superclass (Person)
    }

    // Parametrized constructor
    public Employee(String name, String surname, String position, double salary) {
        super(name, surname); // Calls the parametrized constructor of the superclass (Person)
        this.position = position;
        this.salary = salary;
    }

    // Getter for position
    public String getPosition() {
        return position;
    }

    // Getter and setter for salary
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Implementation of getPaymentAmount for Payable interface
    @Override
    public double getPaymentAmount() {
        return salary;
    }

    // String representation of the object
    @Override
    public String toString() {
        return "Employee: " + super.toString();
    }
}

// Class representing a student, extends Person
class Student extends Person {
    // Additional field
    private double gpa;

    // Default constructor
    public Student() {
        super(); // Calls the default constructor of the superclass (Person)
    }

    // Parametrized constructor
    public Student(String name, String surname, double gpa) {
        super(name, surname); // Calls the parametrized constructor of the superclass (Person)
        this.gpa = gpa;
    }

    // Getter and setter for GPA
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // Implementation of getPaymentAmount for Payable interface
    @Override
    public double getPaymentAmount() {
        return (gpa > 2.67) ? 36660.00 : 0.0; // Stipend is given if GPA is greater than 2.67
    }

    // String representation of the object
    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Creating an ArrayList to store people
        ArrayList<Person> peopleList = new ArrayList<>();

        // Adding instances of Employee and Student to the list
        peopleList.add(new Employee("John", "Lennon", "Manager", 27045.78));
        peopleList.add(new Student("Ringo", "Starr", 2.5));
        peopleList.add(new Student("Paul", "McCartney", 3.0));
        peopleList.add(new Employee("George", "Harrison", "Developer", 50000.00));

        // Sorting the list based on payment amount
        Collections.sort(peopleList);

        // Calling the printData method to display the information
        printData(peopleList);
    }

    // Method to print data for each person in the list
    public static void printData(Iterable<Person> people) {
        for (Person person : people) {
            System.out.println(person.getPosition() + ": " + person.getId() + ". " +
                    person.getName() + " " + person.getSurname() + " earns " +
                    person.getPaymentAmount() + " tenge");
        }
    }
}

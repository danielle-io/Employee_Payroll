import java.io.*;
import java.util.Scanner;

public class Payroll {

    private PrintWriter pw;
    private ObjectList list = new ObjectList();
    private ObjectList newList = new ObjectList();
    private String firstName;
    private String lastName;
    private char gender;
    private int tenure;
    private char rate;
    private double salary;

    /**
     * Overloaded constructor
     *
     * @param pw PrintWriter pointing to a file that will be written to
     */
    public Payroll(PrintWriter pw) {

        // Assigns the print writer passed into the private instance variable
        this.pw = pw;
    }

    public void employeeScan() throws IOException {

        // Open a file for input and create a Scanner object
        Scanner fileScan = new Scanner(new File("payfile.txt"));

        // Read a line from the file using the Scanner object
        while (fileScan.hasNext()) {

            // Create Employee object with a line from file
            Employee employee = new Employee(fileScan.nextLine());

            // Assign employee object to a node
            ObjectListNode node = new ObjectListNode(employee);

            assignEmp(node);

            // Insert node into the end of the ObjectList
            list.addLast(node);

            // Output contents of each node
            outputNodes();
        }
        // Close the Scanner object
        fileScan.close();
    }

    /**
     * Assigns an employee to the current employee variable
     * @param node ObjectListNode containing information to assign to an employee
     */
    private void assignEmp(ObjectListNode node){
        Employee emp = (Employee) node.getInfo();
        firstName = emp.getFirstName();
        lastName = emp.getLastName();
        gender = emp.getGender();
        tenure = emp.getTenure();
        rate = emp.getRate();
        salary = emp.getSalary();
    }

    public void outputHeader() {
        System.out.println("\t\t\t   Employee Table");
        pw.println("\t\tEmployee Table");
        System.out.println();
        pw.println();
        System.out.println("First\t    Last");
        pw.println("First\t    Last");
        System.out.println("Name\t    Name\t    Gender\t Tenure   Rate    Salary");
        pw.println("Name\t    Name\t           Gender        Tenure   Rate          Salary");
        System.out.println("_________________________________________________________");
        pw.println("_________________________________________________________");
    }

    private void outputNodes() {
        String lastNamePw = lastName;

        // Unify spacing
        if (lastName.length() <= 7) {
            lastName += "    \t  ";
            lastNamePw += "   ";
        } else{
            lastName += "      ";
            lastNamePw += "   ";
        }

        // Output employee info
        System.out.println(firstName + "\t    " + lastName + gender + "\t      " + tenure + "\t      "
                + rate + "\t      " + salary);

        if (lastName.length() >= 8) {
            String genderSpace = " \t " + Character.toString(gender);
            pw.println(firstName + "\t    " + lastNamePw + genderSpace + "\t      " + tenure + "\t   "
                    + rate + "\t      " + salary);
        }
        else{
            pw.println(firstName + "\t    " + lastNamePw + gender + "\t      " + tenure + "\t   "
                    + rate + "\t      " + salary);
        }
    }

    public void employeeStats() {
        employeeAmount();
        femaleNames();
        salaryTenure();
        giveRaise();
        alphabetical();
    }

    private void employeeAmount() {
        int count = 0;
        ObjectListNode node = list.getFirstNode();
        Employee employee;
        pw.println();

        // Increase count for each employee
        while (node != null) {
            count++;
            node = node.getNext();
        }

        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
        System.out.println("Amount of Employees on Payroll: " + count);
        pw.println("Amount of Employees on Payroll: " + count);
    }

    private void femaleNames() {
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
        System.out.println("Women on Payroll: ");
        pw.println("Women on Payroll: ");
        System.out.println();
        pw.println();

        ObjectListNode node = list.getFirstNode();
        Employee employee;

        while (node != null) {
            employee = (Employee) node.getInfo();
            gender = employee.getGender();
            if (gender == 'F') {
                printNamesOnly(employee);
            }
            node = node.getNext();
        }
    }

    /**
     * Prints an employee first name and last name
     * @param employee the employee to print
     */
    private void printNamesOnly(Employee employee){
        System.out.println(employee.getFirstName() + "\t    " + employee.getLastName());
        pw.println(employee.getFirstName() + "\t    " + employee.getLastName());
    }

    private void salaryTenure() {
        double yearly;

        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
        System.out.println("Employees Who Make Over $35k With At Least 5 Years Tenure: ");
        pw.println("Employees Who Make Over $35k With At Least 5 Years Tenure: ");
        System.out.println();
        pw.println();

        ObjectListNode node = list.getFirstNode();
        Employee employee;

        while (node != null) {
            employee = (Employee) node.getInfo();
            tenure = employee.getTenure();
            salary = employee.getSalary();
            rate = employee.getRate();

            // Only employees who have tenure of 5 or more years
            if (tenure >= 5) {

                // Calculate hourly salary
                if (rate == 'H') {
                    yearly = 0;

                // Calculate weekly salary
                } else
                    yearly = salary * 52;

                // Print those over $35,000
                if (yearly >= 35000) {
                    printNamesOnly(employee);
                }
            }
            node = node.getNext();
        }
    }

    private void giveRaise() {
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
        System.out.println("Employees Who Make Under $10/Hr or $350/Week Get a Raise ");
        pw.println("Employees Who Make Under $10/Hr or $350/Week Get a Raise ");
        System.out.println();
        pw.println();
        ObjectListNode node = list.getFirstNode();
        Employee employee;
        int count = 0;

        while (node != null) {
            employee = (Employee) node.getInfo();
            assignEmp(node);

            // Hourly
            if (rate == 'H') {
                if (salary < 10.00) {
                    employee.setSalary(salary + 0.75);
                    node.setInfo(employee);
                    salary = employee.getSalary();
                    printNameAndSalary(employee, count);
                    count = 1;
                }
            }

            // Weekly
            else if (salary < 350.00) {
                employee.setSalary(salary + 50.00);
                node.setInfo(employee);
                salary = employee.getSalary();
                printNameAndSalary(employee, count);
                count = 1;
            }
            node = node.getNext();
        }
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
    }

    /**
     * Prints the name of an employee and the salary of an employee
     * @param employee the employee to print
     * @param count the current iteration, if 0, print the header for the table as well
     */
    private void printNameAndSalary(Employee employee, int count){
        if (count == 0){
            System.out.println("First\t    Last");
            pw.println("First\t    Last");
            System.out.println("Name\t    Name\t   Salary");
            pw.println("Name\t    Name\t           Salary");
            System.out.println("__________________________________");
            pw.println("__________________________________");
        }

        // Unify spacing
        String space = "";
        String pwSalary = "";

        if (lastName.length() <= 7) {
            if (lastName.length() <= 4) {
                space += "        ";
                pwSalary = "\t\t" + Double.toString(salary);
            }
            if (lastName.length() == 5){
                space += "       ";
                pwSalary = "\t\t" + Double.toString(salary);
            }
            if (lastName.length() == 6 || lastName.length() == 7){
                space = "   \t";
                pwSalary = "  \t" + Double.toString(salary);
            }

        } else {
            space = "    ";
            pwSalary = " \t" + Double.toString(salary);
        }

        System.out.println(employee.getFirstName() + "\t    " + lastName + space + salary);
        pw.println(employee.getFirstName() + "\t    " + lastName + pwSalary);
    }

    private void alphabetical() {

        // Arrange nodes
        organize();

        // Print once organized
        printAlpha();

        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
    }

    private void organize(){
        System.out.println("Alphabetical List of Employees");
        pw.println("Alphabetical List of Employees");
        System.out.println();
        pw.println();
        Object info;

        // For first run-through with empty second list
        if(newList.size() < 1){
            info = list.removeFirst();
            newList.insert(info);
        }

        while (list.size() > 0) {
            info = list.removeFirst();
            newList.insert(info);
        }
    }

    private void printAlpha(){
        ObjectListNode node = newList.getFirstNode();
        int count = 0;
        while (node != null) {
            Employee employee = (Employee) node.getInfo();
            assignEmp(node);
            printNameAndSalary(employee, count);
            count = 1;
            node = node.getNext();
        }
    }

    public void hire() throws IOException{

        System.out.println("\t**New Hires Added**");
        pw.println("   **New Hires Added**");

        // Open a file for input and create a Scanner object
        Scanner fileScan = new Scanner(new File("hirefile.txt"));

        // Read a line from the file using the Scanner object
        while (fileScan.hasNext()) {

            // Create Employee object with a line from file
            Employee employee = new Employee(fileScan.nextLine());

            // Assign employee object to a node
            ObjectListNode node = new ObjectListNode(employee);

            assignEmp(node);

            // Insert node into the end of the ObjectList
            list.addLast(node);
        }

        // Close the Scanner object
        fileScan.close();
        organize();
        printHireOrFire();
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        pw.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  ");
        System.out.println();
        pw.println();
    }

    private void printHireOrFire(){
        System.out.println("First\t    Last");
        pw.println("First\t    Last");
        System.out.println("Name\t    Name\t ");
        pw.println("Name\t    Name\t ");
        System.out.println("________________________");
        pw.println("________________________");
        ObjectListNode node = newList.getFirstNode();
        while (node != null) {
            Employee employee = (Employee) node.getInfo();
            assignEmp(node);
            printNamesOnly(employee);
            node = node.getNext();
        }
    }

    public void fire() throws IOException{

        System.out.println(" **Fired Employees Removed**");
        pw.println("**Fired Employees Removed**");
        System.out.println("Alphabetical List of Employees");
        pw.println("Alphabetical List of Employees");
        System.out.println();
        pw.println();

        // Open a file for input and create a Scanner object
        Scanner fileScan = new Scanner(new File("firefile.txt"));

        // Read a line from the file using the Scanner object
        while (fileScan.hasNext()) {

            // Create Employee object with a line from file
            Employee employee = new Employee(fileScan.nextLine());

            // Assign employee object to a node
            ObjectListNode node = new ObjectListNode(employee);

            assignEmp(node);

            // Insert node into the end of the ObjectList
            list.addLast(node);
        }

        // Close the Scanner object
        fileScan.close();

        // Run until list is empty
        while (list.size() > 0){
            ObjectListNode newNode = newList.getFirstNode();
            ObjectListNode prevNode = newList.getFirstNode();
            ObjectListNode node = list.getFirstNode();
            Employee employee = (Employee) node.getInfo();
            lastName = employee.getLastName();
            firstName = employee.getFirstName();

            while (newNode != null){
                Employee newEmployee = (Employee) newNode.getInfo();

                // If both first and last name match
                if(employee.compareTo(newEmployee) == 0){
                   if (newNode.getNext() != null){
                        prevNode.setNext(newNode.getNext());
                   }
                    newList.remove(newEmployee);
                    list.removeFirst();
                }

                prevNode = newNode;
                newNode = newNode.getNext();
            }
        }

        // Print Fired Employees
        printHireOrFire();
    }
}








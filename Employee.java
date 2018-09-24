
public class Employee implements Comparable{

    private String firstName;
    private String lastName;
    private char gender;
    private int tenure;
    private char rate;
    private double salary;

    public Employee(String line){
        String delims = "[ ]+";
        String[] tokens = line.split(delims);
        int k = tokens[0].equals("") ? 1 : 0;
        this.firstName = tokens[k];
        this.lastName = tokens[k + 1];
        if(tokens.length > 2){
            this.gender = tokens[k + 2].charAt(0);
            this.tenure = Integer.parseInt(tokens[k + 3]);
            this.rate = tokens[k + 4].charAt(0);
            this.salary = Double.parseDouble(tokens[k + 5]);
        }
    }

    public Employee() {
        this.firstName = "";
        this.lastName = "";
        this.gender = '0';
        this.tenure = 0;
        this.rate = '0';
        this.salary = 0;
    }

    /**
     * Gets the first name of the employee
     * @return String this.firstName representing employee's first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Sets the first name of the employee
     * @param firstName a String representing the employee's first name
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Gets the last name of an employee
     * @return String this.lastName representing employee's last name
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Sets the last name of an employee
     * @param lastName a String representing the employee's last name
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Gets the employee's gender
     * @return char this.gender representing employee's gender
     */
    public char getGender(){
        return this.gender;
    }

    /**
     * Sets the employee's gender
     * @param gender a char with set to either 'M' or 'F'
     */
    public void setGender(char gender){
        this.gender = gender;
    }

    /**
     * The length of time and employee has worked for
     * @return int this.tenure representing employee's tenure
     */
    public int getTenure (){
        return this.tenure;
    }

    /**
     * Sets the employee's tenure
     * @param tenure the employee's tenure
     */
    public void setTenure(int tenure){
        this.tenure = tenure;
    }

    /**
     * Gets the employees rate, a char, representing hourly vs. weekly
     * @return char this.rate representing employee's rate (either 'H' or 'W')
     */
    public char getRate(){
        return this.rate;
    }

    /**
     * Sets the employee's rate to either 'H' (hourly), or 'W' (weekly)
     * @param rate a char representing 'H' or 'W'
     */
    public void setRate(char rate){
        this.rate = rate;
    }

    /**
     * Returns the employee's salary
     * @return double this.salary representing employee's salary
     */
    public double getSalary(){
        return this.salary;
    }

    /**
     * Sets the employee's salary
     * @param salary a double representing the employee's salary
     */
    public void setSalary(double salary){
        this.salary = salary;
    }

    /**
     * Compares the current employee to one passed in. Returns 0 if employee
     * has a matching first name and last name, otherwise returns an int representing
     * alphabetical order by last name, first name
     * @param o an Employee Object
     * @return int 0 if equal, -1 or 1 if employee does not match
     */
    public int compareTo(Object o){
        Employee s = (Employee) o;
        int check = 0;
        check = lastName.compareTo(s.getLastName());
        if(check != 0){
            return check;
        }
        check = firstName.compareTo(s.getFirstName());
        return check;
    }
}

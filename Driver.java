import java.io.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        Payroll payroll = new Payroll(pw);
        payroll.outputHeader();
        payroll.employeeScan();
        payroll.employeeStats();
        payroll.hire();
        payroll.fire();
        pw.close();
    }
}


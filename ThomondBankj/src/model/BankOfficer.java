package model;

import java.time.LocalDate;

public class BankOfficer extends BankStaff {
    public BankOfficer(String firstName, String lastName, String address, LocalDate dob, int empNo) {
        super(firstName, lastName, address, dob, empNo, "Bank Officer");
    }
}

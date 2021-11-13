/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 * @author markduah
 */
public class Student{
    private int ID;
    private String lastName;
    private String firstName;
    private String DOB;
    private String Age;
    private String Sex;
    private String GradeLevel;
    private String Counselor;
    private double CumulativeGPA;
    private String EMCName;
    private String EMCContatc;
    private String Streets;
    private String City;
    private String State;
    private String zipCode;
    private String grade;
    private int absences;
    private int present;

    public Student(int ID, String lastName, String firstName, String DOB, String Sex, String GradeLevel, String Counselor, double CumulativeGPA, String EMCName, String EMCContatc, String Streets, String City, String State, String zipCode) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.DOB = DOB;
        this.Sex = Sex;
        this.GradeLevel = GradeLevel;
        this.Counselor = Counselor;
        this.CumulativeGPA = CumulativeGPA;
        this.EMCName = EMCName;
        this.EMCContatc = EMCContatc;
        this.Streets = Streets;
        this.City = City;
        this.State = State;
        this.zipCode = zipCode;
    }
    public Student(int ID, String lastName, String firstName, String GradeLevel, String grade, int absences, int present, String Counselor){
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.GradeLevel = GradeLevel;
        this.grade = grade;
        this.absences = absences;
        this.present = present;
        this.Counselor = Counselor;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGradeLevel() {
        return GradeLevel;
    }

    public void setYear(String GradeLevel) {
        this.GradeLevel = GradeLevel;
    }

    public double getCumulativeGPA() {
        return CumulativeGPA;
    }

    public void setCumulativeGPA(double CumulativeGPA) {
        this.CumulativeGPA = CumulativeGPA;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getCounselor() {
        return Counselor;
    }

    public void setCounselor(String Counselor) {
        this.Counselor = Counselor;
    }

    public String getEMCName() {
        return EMCName;
    }

    public void setEMCName(String EMCName) {
        this.EMCName = EMCName;
    }

    public String getEMCContatc() {
        return EMCContatc;
    }

    public void setEMCContatc(String EMCContatc) {
        this.EMCContatc = EMCContatc;
    }
    
    private String ageCal() throws ParseException {
        String Age = "";
        if(this.DOB != null){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");   
            Date date = formatter.parse(this.DOB);

            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = zone.toLocalDate();

            Period period = Period.between(givenDate, LocalDate.now());
            int Dob = period.getYears();
            //this.Age = String.valueOf(Dob);
            Age = String.valueOf(Dob);  
        }else{
            System.out.println("DOB is NULL");
        }
        return Age;
    }

    public String getAge() throws ParseException{
        return ageCal();
    }

    public String getStreets() {
        return Streets;
    }

    public void setStreets(String Streets) {
        this.Streets = Streets;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }
    
    
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

/**
 *
 * @author markduah
 */
public class Assignment {
    private int ID;
    private String Description;
    private String StartDate;
    private String DueDate;

    public Assignment(int ID, String Description, String StartDate, String DueDate) {
        this.ID = ID;
        this.Description = Description;
        this.StartDate = StartDate;
        this.DueDate = DueDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }
    
    
}

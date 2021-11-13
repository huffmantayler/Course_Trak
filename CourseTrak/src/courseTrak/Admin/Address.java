/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

/**
 *
 * @author markduah
 */
public class Address {
    private String Street;
    private String City;
    private String State;
    private String zipCode;

    public Address(String Street, String City, String State, String zipCode) {
        this.Street = Street;
        this.City = City;
        this.State = State;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public void addAddress(){
        ///
    }
}

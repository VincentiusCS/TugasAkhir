/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.Class;

import java.util.ArrayList;
/**
 *
 * @author user
 */
public class Order extends Film {
    private int noBooking;
    private ArrayList<String> seats;
    private String username;
    private int totalBooking;
    private String kodeFilm;
 
    public Order(String kodeFilm, String name, String date, int noBooking, String username, ArrayList<String> seats) {
        super(kodeFilm, name, date);
        this.noBooking = noBooking;
        this.seats = seats;
        this.username = username;
        this.totalBooking = 0;
    }

    public Order(){

    }

    public int getNoBooking() {
        return this.noBooking;
    }

    public void setNoBooking(int noBooking) {
        this.noBooking = noBooking;
    }

    public String getkodeFilm(){
        return this.kodeFilm;
    }

    public void setKodeFilm(String kodeFilm){
        this.kodeFilm = kodeFilm;
    }

    public ArrayList<String> getSeats() {
        return this.seats;
    }

    public void setSeats(ArrayList<String> seats) {
        this.seats = seats;
    }
    public void addSeats(String seats){
        this.seats.add(seats);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalBooking() {
        return this.totalBooking;
    }

    public void setTotalBooking(int totalBooking) {
        this.totalBooking = totalBooking;
    }


    @Override
    public String toString() {
        return "{" +
            " noBooking='" + getNoBooking() + "'" +
            ", seats='" + getSeats() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }

    
}

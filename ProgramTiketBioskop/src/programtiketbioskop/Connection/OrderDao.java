/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import programtiketbioskop.Class.Order;

/**
 *
 * @author user
 */
public class OrderDao {
    private Connector con = new Connector();
    public Order getOrder(int noBooking){
        System.out.println("getOrder()");
        try{
            String query = "select * from orders where no_booking = ?";
            Order order = new Order();
            PreparedStatement pstm = con.koneksi.prepareStatement(query);
            pstm.setInt(1,noBooking);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = pstm.executeQuery();
            int loop = 1;
            while(rs.next()){
                order.setNoBooking(noBooking);
                order.setKodeFilm(rs.getString("kode_film"));
                order.setUsername(rs.getString("username"));
                order.setName(rs.getString("film"));
                order.setDate(rs.getString("tanggal"));
                order.addSeats(rs.getString("seat"));
                order.setTotalBooking(loop);
            }
            return order;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String[][] getOrderSeatList(String kodeFilm){
        System.out.println("getOrderSeatList()");
        try{
            String query = "select seat from orders where kode_film = ?";
            int data = countOrderFilm(kodeFilm);
            String[][] seatList = new String[data][1];
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            ptsm.setString(1,kodeFilm);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery();
            int i = 0;
            while(rs.next()){
                seatList[i][0] = rs.getString("seat");
                i++;
            }
            System.out.println(seatList.length);
            return seatList;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;   
    }
    public boolean isOrderExist(int noBooking){
        System.out.println("isOrderExist()");
        try{
            String query = "select * from orders";
            PreparedStatement pstm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = pstm.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("no_booking") == noBooking) return true; 
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean isContainOrder(String kodeFilm){
        System.out.println("isContainOrder()");
        try{
            String query = "select count(*) as num from orders where kode_film = ?";
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            ptsm.setString(1, kodeFilm);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery();
            rs.next();
            if(rs.getInt("num") > 0) return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    public int countOrderFilm(String kodeFilm){
        System.out.println("countOrderFilm()");
        String query = "select count(*) as num from orders where kode_film = ?";
        PreparedStatement pstm;
        try{
            pstm = con.koneksi.prepareStatement(query);
            pstm.setString(1, kodeFilm);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = pstm.executeQuery();
            rs.next();
            return rs.getInt("num");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public int countOrder(){
        System.out.println("countOrder()");
        String query = "select count(*) as num from orders";
        PreparedStatement pstm;
        try{
            pstm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = pstm.executeQuery(query);
            rs.next();
            return rs.getInt("num");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public void AddOrder(Order order){
        System.out.println("addOrder()");
        String query = "insert into orders (no_booking,kode_film,username,film,date,seat) values (?,?,?,?,?,?)";
        PreparedStatement pstm;
        try {
            con.statement = con.koneksi.createStatement();
            pstm = con.koneksi.prepareStatement(query);
            int loop = order.getSeats().size();
            for(int i = 0; i < loop; i++){
                pstm.setInt(1, order.getNoBooking());
                pstm.setString(2,order.getkodeFilm());
                pstm.setString(3, order.getUsername());
                pstm.setString(4, order.getName());                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
                pstm.setString(5, order.getDate());
                pstm.setString(6, order.getSeats().get(i));
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public boolean deleteOrder(int noBooking){
        System.out.println("deleteOrder()");
        String query = "delete from orders where no_booking = ?";
        PreparedStatement pstm;
        try{
           pstm = con.koneksi.prepareStatement(query);
           pstm.setInt(1, noBooking);
           pstm.executeQuery();
           return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
}

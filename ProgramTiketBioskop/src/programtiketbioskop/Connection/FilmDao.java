/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import programtiketbioskop.Class.Film;
/**
 *
 * @author user
 */
public class FilmDao {
     private Connector con = new Connector();
    public Film getFilm(String code){
        System.out.println("getFilm()");
        try{
            String query = "select * from movies";
            Film film = new Film();
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery(query);
            while(rs.next()){
                if(rs.getString("kode").equals(code)){
                    film.setCode(code);
                    film.setName(rs.getString("nama"));
                    film.setSynopsis(rs.getString("sinopsis"));
                    film.setImageUrl(rs.getString("gambar"));
                    film.setDate(rs.getString("tanggal"));
                }
            }
            return film;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;   
    }

    public ArrayList<Film> getFilmList(){
        System.out.println("getFilm()");
        try{
            String query = "select * from movies";
            ArrayList<Film> filmList = new ArrayList<>();
            Film film = new Film();
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery(query);
            while(rs.next()){
                film = new Film(
                    rs.getString("kode"),rs.getString("nama"),
                    rs.getString("sinopsis"),rs.getString("tanggal"),
                    rs.getString("gambar"));

                filmList.add(film);
            }
            return filmList;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;   
    }

    public boolean isContainFilm(){
        System.out.println("isContainFilm()");
        try{
            String query = "select count(*) as num from movies";
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery(query);
            rs.next();
            if(rs.getInt("num") > 0) return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    public int countFilm(){
        System.out.println("SelectFilm()");
        try{
            String query = "select count(*) as num from movies";
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery(query);
            rs.next();
            return rs.getInt("num");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return 0;
    }
    public boolean isFilmAdded(String code){
        System.out.println("SelectFilm()");
        try{
            String query = "select * from movies";
            PreparedStatement ptsm = con.koneksi.prepareStatement(query);
            con.statement = con.koneksi.createStatement();
            ResultSet rs = ptsm.executeQuery(query);
            while(rs.next()){
                if(rs.getString("kode").equals(code)) return true; 
            }  
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    } 
    public void addFilm(Film film){
        System.out.println("addFilm()");
        String query = "insert into movies(kode,nama,sinopsis,tanggal,gambar) values (?,?,?,?,?)";
        PreparedStatement pstm;
        try {
            con.statement = con.koneksi.createStatement();
            pstm = con.koneksi.prepareStatement(query);
            pstm.setString(1, film.getCode());
            pstm.setString(2, film.getName());
            pstm.setString(3, film.getSynopsis());
            pstm.setString(4, film.getDate());
            pstm.setString(5, film.getImageUrl());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean updateFilm(Film film){
        System.out.println("addFilm()");
        String query = "update movies set nama = ?,sinopsis = ?,tanggal = ?,gambar = ? where kode=?";
        PreparedStatement pstm;
        try {
            con.statement = con.koneksi.createStatement();
            pstm = con.koneksi.prepareStatement(query);
            pstm.setString(1, film.getName());
            pstm.setString(2, film.getSynopsis());
            pstm.setString(3, film.getDate());
            pstm.setString(4, film.getImageUrl());
            pstm.setString(5, film.getCode());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteFilm(String code){
        System.out.println("deleteFilm()");
        String query = "delete from movies where kode=?";
        PreparedStatement pstm;
        try{
           pstm = con.koneksi.prepareStatement(query);
           pstm.setString(1, code);
           pstm.executeUpdate();
           return true;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
}

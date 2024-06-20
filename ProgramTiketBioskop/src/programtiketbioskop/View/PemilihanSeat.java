/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import javax.swing.border.Border;

import programtiketbioskop.Class.Order;
import programtiketbioskop.Connection.OrderDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author user
 */
public class PemilihanSeat extends JFrame{
     // -- DEKLARASI PANEL & FRAME
    private static JPanel panel;
    private static JFrame frame;

    // -- DEKLARASI TABEL KODE FILM
    private static JTable tabel;

    // -- DEKLARASI LABEL GAMBAR POSISI SEAT
    private static JLabel labelGambar;
    private static JLabel labelSeat;

    // -- DEKLARASI TOMBOL KEMBALI DAN SUBMIT
    private static JButton backButton;
    private static JButton submitButton;
    private static JLabel[] seatLabel;
    private static JTextField[] submitField;
    private static JLabel labelTabel;
    private static JLabel labelDenah;

    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 17); 

    Border border = BorderFactory.createLineBorder(Color.gray,1);

    private static OrderDao orderDao = new OrderDao();
    private int noBooking;
    private Order order;
    private int totalBooking;
    private String[][] seatTerdaftar;


    public PemilihanSeat(Order order){
        this.order = order; 
        totalBooking = order.getTotalBooking();
        seatLabel = new JLabel[totalBooking];
        submitField = new JTextField[totalBooking];
    }

    public void initialize() {
        // -- DEKLARASI PANEL & FRAME
        panel = new JPanel();
        frame = new JFrame();

        // -- SET SIZE & TITLE FRAME
        frame.setSize(1250, 650);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Pemesanan");

        // -- SET WARNA BACKGROUND & LAYOUT PANEL
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // -- LABEL GAMBAR POSISI SEAT
        labelDenah = new JLabel("Denah Seat");
        labelDenah.setBounds( 860, 30, 305, 25);
        labelDenah.setFont(mainFont);
        labelDenah.setForeground(Color.black);
        panel.add(labelDenah);

        labelGambar = new JLabel();
        labelGambar.setBounds(600,70,600,350);
        labelGambar.setIcon(new ImageIcon("C:\\Users\\user\\OneDrive\\Dokumen\\NetBeansProjects\\Project-Akhir-PBO-main\\ProgramTiketBioskop\\img\\seat.png"));
        labelGambar.setBorder(border);
        panel.add(labelGambar);

        labelSeat = new JLabel("Masukkan No Seat ");
        labelSeat.setBounds(545, 10, 305, 25);
        labelSeat.setFont(mainFont);
        labelSeat.setForeground(Color.black);
        panel.add(labelSeat);

        // -- TABEL SEAT TERSEDIA
        labelTabel = new JLabel("Seat Tidak Tersedia");
        labelTabel.setBounds(230, 30, 305, 25);
        labelTabel.setFont(mainFont);
        labelTabel.setForeground(Color.black);
        panel.add(labelTabel);

        String columnTitle[] = {"Seat Tidak Tersedia"}; 
        if(orderDao.isContainOrder(order.getkodeFilm())){
            seatTerdaftar = orderDao.getOrderSeatList(order.getkodeFilm());
            tabel = new JTable(seatTerdaftar, columnTitle);
        }else{
            seatTerdaftar = new String[1][1];
            tabel = new JTable();
        }
        tabel.setBounds(40,70,530,350);
        tabel.setBorder(border);
        panel.add(tabel);
        System.out.println("Table ditambahkan");

        // -- JLABEL TOMBOL KEMBALI
        backButton = new JButton("kembali");
        backButton.setBounds(10,10,80,25);
        backButton.setForeground(Color.black);
        backButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HomeUser home = new HomeUser();
                    home.initialize();
                    frame.dispose();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }
            }});
        panel.add(backButton);
        
        int xLabel1 = 145;
        int xLabel2 = 65;
         // -- MENAMPILKAN FIELD PENGISIAN SEAT
         for(int i = 0; i < totalBooking; i++) {
            // -- LABEL NO SEAT
                seatLabel[i] = new JLabel("Masukkan Seat no " + (i + 1) + " :");
                seatLabel[i].setBounds(xLabel1, 470, 305, 25);
                seatLabel[i].setFont(mainFont);
                seatLabel[i].setForeground(Color.black);
                panel.add(seatLabel[i]);
    
                xLabel1 += 400;
    
            // -- SUBMIT FIELD 
                submitField[i] = new JTextField();
                submitField[i].setBounds(xLabel2, 500, 305, 25);
                submitField[i].setForeground(Color.black);
                panel.add(submitField[i]);
    
                xLabel2 += 400;
                // cobaTampil[i] = order.getSeat();
            }

        // -- SUBMIT BUTTON
        submitButton = new JButton("Submit");
        submitButton.setFont(mainFont);
        submitButton.setBounds(575, 550, 85, 35);
        submitButton.setForeground(Color.black);
        submitButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Cek Field Kosong
                    if(isFilled()){
                        if(!isBooked()){
                            int res = JOptionPane.showConfirmDialog(frame, "Yakin dengan Pemesanan",
                             "Konfirmasi",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                            
                            // Hasil Confirm = YES
                            if(res == JOptionPane.YES_OPTION){ 
                                noBooking = generatedNumber();
                                order.setNoBooking(noBooking);
                                ArrayList<String> seats = new ArrayList<>();
                                for(int i = 0; i < totalBooking; i++){
                                    seats.add(submitField[i].getText().toUpperCase());
                                }
                                order.setSeats(seats);
                                orderDao.AddOrder(order);
                                Checkout checkout = new Checkout(order);
                                checkout.initialize();
                                frame.dispose();
                            }
                        }else{
                            throw new Exception("Seat telah dibooking");
                        }
                    }else{
                        throw new Exception("Isi Field Kosong");
                    }
                } catch (Exception msg) {
                    JOptionPane.showMessageDialog(frame, msg.getMessage(), "Alert",JOptionPane.HEIGHT);
                }
            }
        });
        panel.add(submitButton);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW
    }
    public static int generatedNumber(){
        int leftLimit = 48; // letter '0'
        int rightLimit = 57; // letter '9'
        int targetStringLength = 6;
        int generatedNumber;
        do{
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int) 
                (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            generatedNumber = Integer.parseInt(buffer.toString());
        }while(orderDao.isOrderExist(generatedNumber));

        return generatedNumber;
    }

    // Fungsi Seat Terbooking
    boolean isBooked(){
        if(seatTerdaftar.length > 0){
            for(int i = 0; i < totalBooking; i++){
                String seat = submitField[i].getText().toUpperCase();
                for(int j = 0; j < seatTerdaftar.length; j++){
                    System.out.println(seatTerdaftar[j][0]);
                    if(seat.equals(seatTerdaftar[j][0])) return true; 
                }
            }
        }
        return false;
    }

    // Fungsi Field Kosong
    boolean isFilled(){
        for(int i = 0; i < totalBooking; i++){
            if(submitField[i].getText().isBlank()) return false;
        }
        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.ArrayList;

import programtiketbioskop.Class.Order;
/**
 *
 * @author user
 */
public class Checkout extends JFrame {
    private static JPanel panel;
    private static JFrame frame;
    // private static JScrollPane scrollPane;
    private static JLabel labelJudul;
    private static JLabel labelPemesanan;
    private static JLabel labelUsername;
    private static JLabel labelNoBooking;
    private static JLabel labelFilm;
    private static JLabel labelTanggal;
    private static JLabel[] labelSeat;

    private static JLabel textUsername;
    private static JLabel textNoBooking;
    private static JLabel textFilm;
    private static JLabel textTanggal;
    private static JLabel[] textSeat;

    private static JButton btnCheckOut;

    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 16);
    final private static Font thirdFont = new Font("TimesRoman", Font.PLAIN, 16);

    final int xTitleLabel = 50;
    final int widthTitleLabel = 150;
    final int heightTitleLabel = 30;

    final int xTextLabel = 200;
    final int widthTextLabel = 200;
    final int heightTextLabel = 30;

    private Order order;
    // private OrderDao orderDao = new OrderDao();

    public Checkout(Order order){
        System.out.println("Checkout");
        this.order = order;
        labelSeat = new JLabel[order.getSeats().size()];
        textSeat = new JLabel[order.getSeats().size()];
    }

    public void initialize(){
        System.out.println("Checkout --> initialize()");
        String username = order.getUsername();
        System.out.println("Username: " + username);
        String namaFilm = order.getName();
        int noBooking = order.getNoBooking();
        int totalBooking = order.getTotalBooking();
        System.out.println("Checkout --> Sukses");

        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(750, 450);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Checkout");

        panel.setLayout(null);
        panel.setBackground(Color.white);

        labelJudul = new JLabel("Checkout Pemesanan");
        labelJudul.setBounds(280,35,300,30);
        labelJudul.setFont(mainFont);
        labelJudul.setForeground(Color.black);
        panel.add(labelJudul);
        
        // Pemesanan
        labelPemesanan = new JLabel();
        labelPemesanan.setBounds(xTitleLabel,50,widthTitleLabel,heightTitleLabel);
        labelPemesanan.setFont(secondaryFont);
        panel.add(labelPemesanan);

        // No Booking
        labelNoBooking = new JLabel();
        labelNoBooking.setText("No.Booking");
        labelNoBooking.setBounds(xTitleLabel,100,widthTitleLabel,heightTitleLabel);
        labelNoBooking.setFont(secondaryFont);
        panel.add(labelNoBooking);

        textNoBooking = new JLabel(": ");
        textNoBooking.setText(": " + noBooking);
        textNoBooking.setBounds(xTextLabel,100,widthTextLabel,heightTextLabel);
        textNoBooking.setFont(thirdFont);
        panel.add(textNoBooking);

        // Username
        labelUsername = new JLabel();
        labelUsername.setText("Username");
        labelUsername.setBounds(xTitleLabel,130,widthTitleLabel,heightTitleLabel);
        labelUsername.setFont(secondaryFont);
        panel.add(labelUsername);

        textUsername = new JLabel();
        textUsername.setText(": " + username);
        textUsername.setBounds(xTextLabel,130,widthTextLabel,heightTextLabel);
        textUsername.setFont(thirdFont);
        panel.add(textUsername);

        // Nama Film
        labelFilm = new JLabel();
        labelFilm.setText("Nama Film");
        labelFilm.setBounds(xTitleLabel,160,widthTitleLabel,heightTitleLabel);
        labelFilm.setFont(secondaryFont);
        panel.add(labelFilm);

        textFilm = new JLabel();
        textFilm.setText(": " + namaFilm);
        textFilm.setBounds(xTextLabel,160,widthTextLabel,heightTextLabel);
        textFilm.setFont(thirdFont);
        panel.add(textFilm);

        // Tanggal
        labelTanggal = new JLabel();
        labelTanggal.setText("Tanggal");
        labelTanggal.setBounds(xTitleLabel,190,widthTitleLabel,heightTitleLabel);
        labelTanggal.setFont(secondaryFont);
        panel.add(labelTanggal);

        textTanggal = new JLabel();
        textTanggal.setText(": " + order.getDate());
        textTanggal.setBounds(xTextLabel,190,widthTextLabel,heightTextLabel);
        textTanggal.setFont(thirdFont);
        panel.add(textTanggal);

        int yLabel = 220;

        for(int i = 0; i < totalBooking; i++){
            // Seat
            labelSeat[i] = new JLabel();
            labelSeat[i].setText("No.Seat");
            labelSeat[i].setBounds(xTitleLabel,yLabel,widthTitleLabel,heightTitleLabel);
            labelSeat[i].setFont(secondaryFont);
            panel.add(labelSeat[i]);

            textSeat[i] = new JLabel();
            textSeat[i].setText(": " + order.getSeats().get(i));
            textSeat[i].setBounds(xTextLabel,yLabel,widthTextLabel,heightTextLabel);
            textSeat[i].setFont(thirdFont);
            panel.add(textSeat[i]);
            yLabel += 30;
        }

        btnCheckOut = new JButton();
        btnCheckOut.setText("Checkout");
        btnCheckOut.setBounds(320, 350, 100, 30);
        btnCheckOut.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                HomeUser user = new HomeUser(username);
                user.initialize();
                frame.dispose();
            }
            
        });
        panel.add(btnCheckOut);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); 
    }

}

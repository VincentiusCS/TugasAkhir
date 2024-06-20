/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import programtiketbioskop.Class.Film;
import programtiketbioskop.Class.Order;
import programtiketbioskop.Connection.FilmDao;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author user
 */
public class Pemesanan {
     // -- DEKLARASI PANEL & FRAME
    private static JPanel panel;
    private static JFrame frame;

    // -- DEKLARASI LABEL KODE FILM
    private static JLabel kodeLabel;

    // -- DEKLARASI TEXT FIELD KODE FILM
    private static JTextField kodeTextField;

    // -- DEKLARASI LABEL JUMLAH SEAT
    private static JLabel jumlahSeatText;

    // -- DEKLARASI TEXT FIELD JUMLAH SEAT
    private static JTextField inputJumlahSeat;

    // -- DEKLARASI TOMBOL KEMBALI DAN SUBMIT
    private static JButton backButton;
    private static JButton submitButton;

    private static FilmDao filmDao = new FilmDao();
    private static Film film;
    private Order order;

    private String username;

    public Pemesanan(String username){
        this.username = username;
    }

    public void initialize() {

        // -- DEKLARASI PANEL & FRAME
        panel = new JPanel();
        frame = new JFrame();

        // -- SET SIZE & TITLE FRAME
        frame.setSize(475, 405);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Pemesanan");

        // -- SET WARNA BACKGROUND & LAYOUT PANEL
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // -- LABEL KODE FILM
        kodeLabel = new JLabel("Masukkan Kode Film :");
        kodeLabel.setBounds(180,80,200,25);

        kodeLabel.setForeground(Color.black);
        panel.add(kodeLabel);

        // -- TEXT FIELD KODE FILM
        kodeTextField = new JTextField();
        kodeTextField.setBounds(140,115,200,20);
        panel.add(kodeTextField);

        // -- LABEL JUMLAH SEAT
        jumlahSeatText = new JLabel("Masukkan Jumlah Seat yang Hendak Dipesan :");
        jumlahSeatText.setBounds(110,200,400,25);
        jumlahSeatText.setForeground(Color.black);
        panel.add(jumlahSeatText);

        // -- TEXT FIELD JUMLAH SEAT
        inputJumlahSeat = new JTextField();
        inputJumlahSeat.setBounds(140,235,200,20);
        panel.add(inputJumlahSeat);

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

        // -- SUBMIT BUTTON
        submitButton = new JButton("Submit");
        submitButton.setBounds(190, 300, 85, 25);
        submitButton.setForeground(Color.black);
        submitButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // CEK APA ADA FILM DI DATABASE
                    if(filmDao.isContainFilm()) { // -- JALANKAN APABILA ADA DATA DALAM DATABASE
                        String kode = kodeTextField.getText();
                        // CEK KODE KOSONG ATAU TIDAK
                        if(!inputJumlahSeat.getText().isBlank() && !kodeTextField.getText().isBlank()){
                            int jumlahSeat = Integer.parseInt(inputJumlahSeat.getText());
                            // CEK SEAT < 3 ATAU > 0
                            if(jumlahSeat <= 3 && jumlahSeat > 0){
                                // CEK APAKAH KODE SUDAH TERDAFTAR KE DATABASE
                                if(filmDao.isFilmAdded(kode)){
                                    film = filmDao.getFilm(kode);
                                    order = new Order();
                                    order.setKodeFilm(kode);
                                    order.setUsername(username);
                                    order.setDate(film.getDate());
                                    order.setName(film.getName());
                                    order.setTotalBooking(jumlahSeat);
                                    PemilihanSeat ps = new PemilihanSeat(order);
                                    ps.initialize();
                                    frame.dispose();
                                }else{
                                    kodeTextField.setText("");
                                    throw new Exception("Tidak Ditemukan Kode Film!");
                                }
                            }else{
                                inputJumlahSeat.setText("");
                                throw new Exception("Jumlah Seat hanya bisa diisi 1-3 seat!");
                            }
                        }else{
                            throw new Exception("Field Tidak Boleh Kosong!");
                        }
                    } else {
                        throw new Exception("Tidak Ada Film Yang Terdaftar!");
                    }
                } catch (Exception msg) {
                    JOptionPane.showMessageDialog(frame, msg.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(submitButton);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW
    }
}

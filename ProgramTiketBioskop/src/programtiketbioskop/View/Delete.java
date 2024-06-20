/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import javax.swing.border.Border;


import programtiketbioskop.Class.Film;
import programtiketbioskop.Connection.FilmDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author user
 */
public class Delete extends JFrame {
     // -- DEKLARASI PANEL & FRAME
    private static JPanel panel;
    private static JFrame frame;

    // -- DEKLARASI TITLE TAMBAHKAN FILM
    private static JLabel menu;

    // -- DEKLARASI LABEL DAN TEXT FIELD FILE
    private static JLabel labelNama;
    // private static JLabel textFieldNama;
    private static JLabel labelKode;
    private static JLabel textFieldKode;
    private static JLabel labelSinopsis;
    private static JTextArea textSinopsis;
    private static JButton buttonSubmit;
    private static JLabel labelPenampilGambar;
    private static JLabel labelTanggal;
    private static JLabel pilihTanggal;

    // -- DEKLARASI LABEL DAN DROP FIELD IMAGE
    private static JLabel labelGambar;

    // -- DEKLARASI FONT YANG DIGUNAKAN
    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 18); 
    // final private static Font thirdFont = new Font("TimesRoman", Font.PLAIN, 14);

    final private Border border = BorderFactory.createLineBorder(Color.gray,1);

    private Film film;
    private FilmDao filmDao = new FilmDao();

    public Delete(Film film){
        this.film = film;
    }

    public void initialize() {
        // -- INSTANSIASI PANEL & FRAME
        panel = new JPanel();
        frame = new JFrame();
        
        // -- SET SIZE & TITLE FRAME
        frame.setSize(750, 790);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Edit Film");

        // -- SET WARNA BACKGROUND & LAYOUT PANEL
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // -- MAIN MENU TEXT
        menu = new JLabel("Menu Edit Film");
        menu.setBounds(280,20,300,30);
        menu.setFont(mainFont);
        menu.setForeground(Color.black);
        panel.add(menu);

        // -- LABEL NAMA
        labelNama = new JLabel("Nama Film ");
        labelNama.setBounds(50,80,170,30);
        labelNama.setFont(secondaryFont);
        labelNama.setForeground(Color.black);
        panel.add(labelNama);

        // -- TEXTFIELD NAMA
        labelNama = new JLabel(film.getName());
        labelNama.setBounds(200, 85, 290, 25);
        panel.add(labelNama);

        // -- LABEL KODE
        labelKode = new JLabel("Kode Film ");
        labelKode.setBounds(50,120,170,30);
        labelKode.setFont(secondaryFont);
        labelKode.setForeground(Color.black);
        panel.add(labelKode);

        // -- TEXTFIELD KODE FILM
        textFieldKode = new JLabel(film.getCode());
        textFieldKode.setBounds(200, 125, 290, 25);
        textFieldKode.setFont(secondaryFont);
        textFieldKode.setForeground(Color.black);
        panel.add(textFieldKode);

        // -- LABEL SINOPSIS
        labelSinopsis = new JLabel("Sinopsis Film ");
        labelSinopsis.setBounds(50,160,170,30);
        labelSinopsis.setFont(secondaryFont);
        labelSinopsis.setForeground(Color.black);
        panel.add(labelSinopsis);

        // -- TEXTAREA SINOPSIS
        textSinopsis = new JTextArea(film.getSynopsis());
        textSinopsis.setBounds(200, 165, 480, 205);
        textSinopsis.setLineWrap(true);
        textSinopsis.setEditable(false);
        textSinopsis.setForeground(Color.BLACK);
        panel.add(textSinopsis);

        // -- LABEL GAMBAR
        labelGambar = new JLabel("Poster Film ");
        labelGambar.setBounds(50,380,170,30);
        labelGambar.setFont(secondaryFont);
        labelGambar.setForeground(Color.black);
        panel.add(labelGambar);

        // -- BUTTON SUBMIT
        buttonSubmit = new JButton("Delete");
        buttonSubmit.setBounds(50, 600, 80, 25);
        buttonSubmit.setForeground(Color.black);
        buttonSubmit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int res = JOptionPane.showConfirmDialog(frame, "Yakin ingin menghapus?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

                    if(res==JOptionPane.YES_OPTION){
                        if(filmDao.deleteFilm(film.getCode())){
                            JOptionPane.showMessageDialog(frame, "Film Berhasil Dihapus!");
                            LihatAdmin lihat = new LihatAdmin();
                            lihat.initialize();
                            frame.dispose();
                        }else{
                            throw new Exception("Film gagal dihapus");
                        } 
                    } 
                            
                }catch(Exception msg){
                    JOptionPane.showMessageDialog(frame, msg.getMessage(), "Alert",JOptionPane.HEIGHT);
                }
            }
        });
        panel.add(buttonSubmit);
        // -- LABEL PENAMPIL GAMBAR
        labelPenampilGambar = new JLabel();
        labelPenampilGambar.setBounds(200,380,180,150);
        labelPenampilGambar.setFont(secondaryFont);
        labelPenampilGambar.setForeground(Color.black);
        labelPenampilGambar.setBorder(border);
        labelPenampilGambar.setIcon(new ImageIcon(new ImageIcon(film.getImageUrl()).getImage().getScaledInstance(labelPenampilGambar.getWidth(), labelPenampilGambar.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(labelPenampilGambar);


        // -- LABEL PILIH TANGGAL
        labelTanggal = new JLabel("Tanggal");
        labelTanggal.setBounds(50,500,180,150);
        labelTanggal.setFont(secondaryFont);
        labelTanggal.setForeground(Color.black);
        panel.add(labelTanggal);

        pilihTanggal = new JLabel();
        pilihTanggal.setBounds(200,565,250,30);
        pilihTanggal.setText(film.getDate());
        panel.add(pilihTanggal);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW
        
    }

}

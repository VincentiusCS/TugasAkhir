/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import programtiketbioskop.Class.Film;
import programtiketbioskop.Connection.FilmDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author user
 */
public class EditFilm extends JFrame {
     // -- DEKLARASI PANEL & FRAME
    private static JPanel panel;
    private static JFrame frame;

    // -- DEKLARASI TITLE TAMBAHKAN FILM
    private static JLabel menu;

    // -- DEKLARASI LABEL DAN TEXT FIELD FILE
    private static JLabel labelNama;
    private static JTextField textFieldNama;
    private static JLabel labelKode;
    private static JLabel textFieldKode;
    private static JLabel labelSinopsis;
    private static JTextField textFieldSinopsis;
    private static JButton buttonSubmit;
    private static JButton buttonAttach;
    private static JLabel labelPenampilGambar;
    private static JLabel labelPenampilPath;
    private static JLabel labelTanggal;
    private static JDateChooser pilihTanggal;

    // -- DEKLARASI LABEL DAN DROP FIELD IMAGE
    private static JLabel labelGambar;

    // -- DEKLARASI FONT YANG DIGUNAKAN
    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 18); 
    final private static Font thirdFont = new Font("TimesRoman", Font.PLAIN, 14);

    final private Border border = BorderFactory.createLineBorder(Color.gray,1);

    private Film film;
    private FilmDao filmDao = new FilmDao();

    public EditFilm(Film film){
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
        textFieldNama = new JTextField(50);
        textFieldNama.setBounds(200, 85, 290, 25);
        textFieldNama.setText(film.getName());
        panel.add(textFieldNama);

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

        // -- TEXTFIELD SINOPSIS
        textFieldSinopsis = new JTextField(50);
        textFieldSinopsis.setBounds(200, 165, 480, 205);
        textFieldSinopsis.setText(film.getSynopsis());
        panel.add(textFieldSinopsis);

        // -- LABEL GAMBAR
        labelGambar = new JLabel("Poster Film ");
        labelGambar.setBounds(50,380,170,30);
        labelGambar.setFont(secondaryFont);
        labelGambar.setForeground(Color.black);
        panel.add(labelGambar);

        // -- BUTTON SUBMIT
        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(490,550,190,25);
        buttonSubmit.setForeground(Color.black);
        buttonSubmit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldNama.getText();
                String code = textFieldKode.getText();
                String synopsis = textFieldSinopsis.getText();
                String imageUrl = labelPenampilPath.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
                String date = sdf.format(pilihTanggal.getDate().getTime());
                try{
                    if(!name.isBlank() && !code.isBlank() && !synopsis.isBlank() && !imageUrl.isBlank() && !date.isBlank()){
                        film = new Film(code,name,synopsis,date,imageUrl);
                        boolean isUpdate = filmDao.updateFilm(film);
                            if(isUpdate){
                                filmDao.addFilm(film);
                                JOptionPane.showMessageDialog(frame, "Film Berhasil Ditambahkan!");
                                int res = JOptionPane.showConfirmDialog(frame, "Update lagi?",
                                "Konfirmasi",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                                if(res == JOptionPane.YES_OPTION){
                                    frame.dispose();
                                    Kode kode = new Kode();
                                    kode.initialize();
                                }else{
                                    frame.dispose();
                                    HomeAdmin admin = new HomeAdmin();
                                    admin.initialize();
                                }
                            }else{
                                throw new Exception("Film Gagal Update!");
                            }
                    }else{
                        throw new Exception("Isi Field Kosong!");
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

        // -- LABEL TEXT PATH ADDRESS
        labelPenampilPath = new JLabel(film.getImageUrl());
        labelPenampilPath.setBounds(490,435,190,25);
        labelPenampilPath.setFont(thirdFont);
        labelPenampilPath.setForeground(Color.black);
        labelPenampilPath.setBorder(border);
        panel.add(labelPenampilPath);

        // -- BUTTON ATTACH
        buttonAttach = new JButton();
        buttonAttach.setText("Attach");
        buttonAttach.setBounds(547, 480, 80, 25);
        buttonAttach.setForeground(Color.black);
        buttonAttach.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                labelPenampilPath.setText(filename);
                labelPenampilGambar.setIcon(new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(labelPenampilGambar.getWidth(), labelPenampilGambar.getHeight(), Image.SCALE_SMOOTH)));
            }
        });
        panel.add(buttonAttach);

        // -- LABEL PILIH TANGGAL
        labelTanggal = new JLabel("Tanggal");
        labelTanggal.setBounds(50,500,180,150);
        labelTanggal.setFont(secondaryFont);
        labelTanggal.setForeground(Color.black);
        panel.add(labelTanggal);

        pilihTanggal = new JDateChooser();
        pilihTanggal.setBounds(200,565,250,30);
        pilihTanggal.setDateFormatString("dd-MMMM-yyyy");
        try {
            Date date = new SimpleDateFormat("dd-MMMM-yyyy").parse(film.getDate());
            pilihTanggal.setDate(date);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // pilihTanggal.setPreferredSize(new Dimension(250, 30));
        panel.add(pilihTanggal);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW
        
    }
}

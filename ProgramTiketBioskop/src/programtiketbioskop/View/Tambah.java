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
import java.text.SimpleDateFormat;
import java.util.Random;
/**
 *
 * @author user
 */
public class Tambah extends JFrame {
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
    private static JLabel labelTanggal;
    private static JDateChooser pilihTanggal;

    // -- DEKLARASI LABEL DAN DROP FIELD IMAGE
    private static JLabel labelTextGambar;
    private static JLabel labelPenampilGambar;
    private static JLabel labelTextPath;
    private static JLabel labelPenampilPath;

    //-- BUTTON KEMBALI
    private static JButton backButton;
    private static FilmDao filmDao = new FilmDao();
    private static Film film;

    // -- DEKLARASI FONT YANG DIGUNAKAN
    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 18);
    final private static Font fontKetiga = new Font("TimesRoman", Font.BOLD, 16);

    public void initialize() {
        // -- INSTANSIASI PANEL & FRAME
        panel = new JPanel();
        frame = new JFrame();

        // -- INSTANSIASI BORDER JLABEL
        Border border = BorderFactory.createLineBorder(Color.gray,1);
        
        // -- SET SIZE & TITLE FRAME
        frame.setSize(750, 790);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Tambahkan Film");

        // -- SET WARNA BACKGROUND & LAYOUT PANEL
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // -- MAIN MENU TEXT
        menu = new JLabel("Menu Tambahkan Film");
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
        panel.add(textFieldNama);

        // -- LABEL KODE
        labelKode = new JLabel("Kode Film ");
        labelKode.setBounds(50,120,170,30);
        labelKode.setFont(secondaryFont);
        labelKode.setForeground(Color.black);
        panel.add(labelKode);

        // -- TEXTFIELD KODE FILM
        textFieldKode = new JLabel(generateString());
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
        panel.add(textFieldSinopsis);

        // -- LABEL TEXT GAMBAR
        labelTextGambar = new JLabel("Poster Film ");
        labelTextGambar.setBounds(50,380,170,30);
        labelTextGambar.setFont(secondaryFont);
        labelTextGambar.setForeground(Color.black);
        panel.add(labelTextGambar);

        // -- LABEL TEXT PATH
        labelTextPath = new JLabel("Path File : ");
        labelTextPath.setBounds(390,430,170,30);
        labelTextPath.setFont(secondaryFont);
        labelTextPath.setForeground(Color.black);
        panel.add(labelTextPath);
        
        // -- LABEL PENAMPIL GAMBAR
        labelPenampilGambar = new JLabel();
        labelPenampilGambar.setBounds(200,380,180,150);
        labelPenampilGambar.setFont(secondaryFont);
        labelPenampilGambar.setForeground(Color.black);
        labelPenampilGambar.setBorder(border);
        panel.add(labelPenampilGambar);

        // -- LABEL TEXT PATH ADDRESS
        labelPenampilPath = new JLabel("");
        labelPenampilPath.setBounds(490,435,190,25);
        labelPenampilPath.setFont(secondaryFont);
        labelPenampilPath.setForeground(Color.black);
        labelPenampilPath.setBorder(border);
        panel.add(labelPenampilPath);

        // -- JLABEL TOMBOL KEMBALI
        backButton = new JButton("kembali");
        backButton.setBounds(10,10,80,25);
        backButton.setForeground(Color.black);
        backButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HomeAdmin home = new HomeAdmin();
                    home.initialize();
                    frame.dispose();
                } catch (Exception error) {
                    System.out.println(error.getMessage());
                }   
            }});
        panel.add(backButton);

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
                System.out.println(date);
                if(!name.isBlank() && !code.isBlank() && !synopsis.isBlank() && !imageUrl.isBlank() && !date.isBlank()){
                    try{
                        if(!filmDao.isFilmAdded(code)){
                            film = new Film(code,name,synopsis,date,imageUrl);
                            filmDao.addFilm(film);
                            JOptionPane.showMessageDialog(frame, "Film Berhasil Ditambahkan!");
                            JPanel panelRequestToAdd = new JPanel();
                            panelRequestToAdd.setSize(new Dimension(250, 50));
                            panelRequestToAdd.setLayout(null);
                            JLabel label1 = new JLabel("Tambah Film Lagi?");
                            label1.setVerticalAlignment(SwingConstants.BOTTOM);
                            label1.setBounds(70, 20, 250, 30);
                            label1.setHorizontalAlignment(SwingConstants.CENTER);
                            panelRequestToAdd.add(label1);
                            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
                            int res = JOptionPane.showConfirmDialog(null, panelRequestToAdd, "Konfirmasi",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.PLAIN_MESSAGE);
                            if(res == 0){
                                frame.dispose();
                                Tambah tambah = new Tambah();
                                tambah.initialize();
                            }else{
                                frame.dispose();
                                HomeAdmin admin = new HomeAdmin();
                                admin.initialize();
                            }
                        }else{
                            JOptionPane.showMessageDialog(frame, "Film Sudah Terdaftar!", "Alert", HEIGHT);
                        }
                    }catch(Exception exception){
                        System.err.println(exception.getMessage());
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Data Tidak Lengkap!", "Alert", HEIGHT);
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
        panel.add(labelPenampilGambar);

        // -- LABEL TEXT PATH ADDRESS
        labelPenampilPath = new JLabel("");
        labelPenampilPath.setBounds(490,435,190,25);
        labelPenampilPath.setFont(fontKetiga);
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
        // pilihTanggal.setPreferredSize(new Dimension(250, 30));
        panel.add(pilihTanggal);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW
    }

    public String generateString() {
        System.out.println("generateString()");
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        String generatedString;
        
        do{
            generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        }while(filmDao.isFilmAdded(generatedString));
        System.out.println(generatedString.toLowerCase());
        return generatedString.toLowerCase();
    }
}

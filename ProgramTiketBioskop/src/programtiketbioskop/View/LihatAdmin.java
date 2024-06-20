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
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.MouseInputAdapter;
/**
 *
 * @author user
 */
public class LihatAdmin extends JFrame{
    private static JPanel panel;
    private static JFrame frame; 
    private static JScrollPane scrollPanel;

    private static JLabel[] showImage;
    private static JLabel[] titleName;
    private static JLabel[] titleCode;
    private static JLabel[] titleDate;
    private static JLabel[] titleSynopsis;

    private static JLabel[] textName;
    private static JLabel[] textCode;
    private static JLabel[] textDate;
    private static JTextArea[] textSynopsis;
    private static JButton buttonEdit;
    private static JButton buttonHapus;
    private static JButton buttonTambah;

    private static JButton btnBack;

    private static JLabel textEmptyFilm;

    // private static Film film;
    private static FilmDao filmDao = new FilmDao();
    private static ArrayList<Film> filmList;

    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 16);
    final private static Font fontKetiga = new Font("TimesRoman", Font.PLAIN, 14);
    final private static Border border = BorderFactory.createLineBorder(Color.gray,1);

    final int widthFrame = 750;
    final int heigthFrame = 790;

    final int xShowImage = 100;
    int yShowImage = 70;
    final int widthShowImage = 180;
    final int heightShowImage = 190;

    final int xTitleLabel = 300;
    final int widthTitleLabel = 100;
    final int heightTitleLabel = 30;

    final int xTextLabel = 400;
    final int widthTextLabel = 200;
    final int heightTextLabel = 30;
    


    public LihatAdmin(){
        if(filmDao.isContainFilm()){
            int n = filmDao.countFilm();
            LihatAdmin.showImage = new JLabel[n];
            LihatAdmin.titleCode= new JLabel[n];
            LihatAdmin.titleName = new JLabel[n];
            LihatAdmin.titleSynopsis = new JLabel[n];
            LihatAdmin.titleDate = new JLabel[n];

            LihatAdmin.textName = new JLabel[n];
            LihatAdmin.textCode = new JLabel[n];
            LihatAdmin.textSynopsis = new JTextArea[n];
            LihatAdmin.textDate = new JLabel[n];
        }
    }

    public void initialize() {
        System.out.println("Liat() --> initilize()");
        frame = new JFrame();
        panel = new JPanel();
        scrollPanel = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setBounds(0,1000,750,600);
        scrollPanel.setViewportView(panel);
        panel.setMinimumSize(new Dimension(widthFrame, heigthFrame));
        panel.setAutoscrolls(true);

        GroupLayout layout = new GroupLayout(panel);

        frame.setSize(widthFrame, heigthFrame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel.setLayout(layout);
        panel.setBackground(Color.white);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        btnBack = new JButton("Kembali");
        btnBack.setBounds(10,20, 90, 25);
        btnBack.setForeground(Color.BLACK);
        btnBack.addActionListener((ActionListener) new ActionListener() {
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
        panel.add(btnBack);
        // --- BUTTON TAMBAH
        buttonTambah = new JButton();
        buttonTambah.setText("Tambah");
        buttonTambah.setBounds(500,20, 150, 25);
        buttonTambah.setForeground(Color.black);
        buttonTambah.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                try { 
                    Tambah tambah = new Tambah();
                    tambah.initialize();
                    frame.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL EDIT FILM
             @Override
             public void mouseEntered(MouseEvent e) {
                 try {
                     Font font = buttonTambah.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                     buttonTambah.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
 
             // -- MENGHILANGKAN UNDERLINE PADA TOMBOL EDIT FILM APABILA MOUSE TIDAK LEWAT TOMBOL
             @Override
             public void mouseExited(MouseEvent e) {
                 try {
                     Font font = buttonTambah.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, -1);
                     buttonTambah.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
             
         });
        panel.add(buttonTambah);
        
        int yLabel = 70;
        if(filmDao.isContainFilm()){
            filmList = filmDao.getFilmList();
            for(int i = 0; i < filmList.size(); i++){
                // -- TAMPIL GAMBAR
                showImage[i] = new JLabel();
                showImage[i].setBounds(xShowImage, yShowImage, widthShowImage, heightShowImage);
                showImage[i].setBorder(border);
                showImage[i].setIcon(new ImageIcon(new ImageIcon(filmList.get(i).getImageUrl()).getImage().getScaledInstance(showImage[i].getWidth(), showImage[i].getHeight(), Image.SCALE_SMOOTH)));
                panel.add(showImage[i]);
                yShowImage+= 300;

                // -- LABEL KODE
                titleCode[i] = new JLabel("Kode");
                String kode = filmList.get(i).getCode();
                titleCode[i].setBounds(xTitleLabel, yLabel, widthTitleLabel, heightTitleLabel);
                titleCode[i].setFont(secondaryFont);
                titleCode[i].setForeground(Color.BLACK);
                panel.add(titleCode[i]);

                // -- LABEL TEXT KODE
                textCode[i] = new JLabel(": " + filmList.get(i).getCode());
                textCode[i].setBounds(xTextLabel, yLabel, widthTextLabel, heightTextLabel);
                textCode[i].setFont(fontKetiga);
                textCode[i].setForeground(Color.BLACK);
                panel.add(textCode[i]); 

                yLabel += 50;

                // -- LABEL NAMA
                titleName[i] = new JLabel("Nama");
                titleName[i].setBounds(xTitleLabel, yLabel, widthTitleLabel, heightTitleLabel);
                titleName[i].setFont(secondaryFont);
                titleName[i].setForeground(Color.BLACK);
                panel.add(titleName[i]);

                // -- LABEL TEXT NAMA
                textName[i] = new JLabel(": " + filmList.get(i).getName());
                textName[i].setBounds(xTextLabel, yLabel, widthTextLabel, heightTextLabel);
                textName[i].setFont(fontKetiga);
                textName[i].setForeground(Color.BLACK);
                panel.add(textName[i]);

                yLabel += 50;

                // -- LABEL TANGGAL
                titleDate[i] = new JLabel("Tanggal");
                titleDate[i].setBounds(xTitleLabel, yLabel, widthTitleLabel, heightTitleLabel);
                titleDate[i].setFont(secondaryFont);
                titleDate[i].setForeground(Color.BLACK);
                panel.add(titleDate[i]);

                // -- LABEL TEXT TANGGAL
                textDate[i] = new JLabel(": " + filmList.get(i).getDate());
                textDate[i].setBounds(xTextLabel, yLabel, widthTextLabel, heightTextLabel);
                textDate[i].setFont(fontKetiga);
                textDate[i].setForeground(Color.BLACK);
                panel.add(textDate[i]);
                
                yLabel += 50;
                
                // -- LABEL SINOPSIS
                titleSynopsis[i] = new JLabel("Sinopsis");
                titleSynopsis[i].setBounds(xTitleLabel, yLabel, widthTitleLabel, heightTitleLabel);
                titleSynopsis[i].setFont(secondaryFont);
                titleSynopsis[i].setForeground(Color.BLACK);
                panel.add(titleSynopsis[i]);

                // -- LABEL TEXT SINOPSIS
                textSynopsis[i] = new JTextArea(": " + filmList.get(i).getSynopsis());
                textSynopsis[i].setLineWrap(true);
                textSynopsis[i].setEditable(false);
                textSynopsis[i].setBounds(xTextLabel, yLabel, 300, 150);
                textSynopsis[i].setFont(fontKetiga);
                textSynopsis[i].setForeground(Color.BLACK);
                panel.add(textSynopsis[i]);
                   yLabel += 50;
                
                // --- BUTTON EDIT
        buttonEdit = new JButton();
        buttonEdit.setText("Edit");
        buttonEdit.setBounds(xTitleLabel, yLabel, 80, 25);
        buttonEdit.setForeground(Color.black);
        buttonEdit.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                try { 
                    Film film = filmDao.getFilm(kode);
                    EditFilm editfilm = new EditFilm(film);
                    editfilm.initialize();
                    frame.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL EDIT FILM
             @Override
             public void mouseEntered(MouseEvent e) {
                 try {
                     Font font = buttonEdit.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                     buttonEdit.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
 
             // -- MENGHILANGKAN UNDERLINE PADA TOMBOL EDIT FILM APABILA MOUSE TIDAK LEWAT TOMBOL
             @Override
             public void mouseExited(MouseEvent e) {
                 try {
                     Font font = buttonEdit.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, -1);
                     buttonEdit.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
             
         });
        panel.add(buttonEdit);
        yLabel += 50;
                // --- BUTTON HAPUS
        buttonHapus = new JButton();
        buttonHapus.setText("Hapus");
        buttonHapus.setBounds(xTitleLabel, yLabel, 80, 25);
        buttonHapus.setForeground(Color.black);
        buttonHapus.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                try { 
                    Film film = filmDao.getFilm(kode);
                    Delete delete = new Delete(film);
                    delete.initialize();
                    frame.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL EDIT FILM
             @Override
             public void mouseEntered(MouseEvent e) {
                 try {
                     Font font = buttonHapus.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                     buttonHapus.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
 
             // -- MENGHILANGKAN UNDERLINE PADA TOMBOL EDIT FILM APABILA MOUSE TIDAK LEWAT TOMBOL
             @Override
             public void mouseExited(MouseEvent e) {
                 try {
                     Font font = buttonHapus.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, -1);
                     buttonHapus.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
             
         });
        panel.add(buttonHapus);
        yLabel += 100;
        yShowImage+= 50;
            }
        }else{
            textEmptyFilm = new JLabel();
            textEmptyFilm.setText("Maaf, tidak ada film yang tersedia!");
            textEmptyFilm.setBounds(widthFrame/2 - 150,heigthFrame/2 - 30,300,30);
            textEmptyFilm.setFont(mainFont);
            textEmptyFilm.setForeground(Color.black);
            scrollPanel.add(textEmptyFilm);
        }
        panel.setPreferredSize(new Dimension(widthFrame, yLabel));
        frame.add(scrollPanel);  
        frame.setTitle("Lihat Film");
        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true);
        // pack();
    }
}

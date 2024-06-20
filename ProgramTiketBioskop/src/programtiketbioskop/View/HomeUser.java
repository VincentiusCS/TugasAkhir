/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programtiketbioskop.View;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class HomeUser extends JFrame {
     // -- DEKLARASI PANEL & FRAME
    private static JPanel panel;
    private static JFrame frame;

    // -- DEKLARASI FONT MAIN MENU
    private static JLabel menu;

    // -- DEKLARASI PILIHAN MENU
    private static JLabel pemesanan;
    private static JLabel lihat;
    private static JLabel logout;

    // -- DEKLARASI FONT YANG DIGUNAKAN
    final private static Font mainFont = new Font("TimesRoman", Font.BOLD, 20); 
    final private static Font secondaryFont = new Font("TimesRoman", Font.BOLD, 15); 

    private String username;

    public HomeUser(String username){
        this.username = username;
    }

    public HomeUser(){
        
    }

    public void initialize() {
        // -- INSTANSIASI PANEL & FRAME
        panel = new JPanel();
        frame = new JFrame();
        
        // -- SET SIZE & TITLE FRAME
        frame.setSize(375, 265);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("Homepage");

        // -- SET WARNA BACKGROUND & LAYOUT PANEL
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // -- MAIN MENU TEXT
        menu = new JLabel("Main Menu");
        menu.setBounds(130,20,200,30);
        menu.setFont(mainFont);
        menu.setForeground(Color.black);
        panel.add(menu);

        // -- PEMESANAN TIKET
        pemesanan = new JLabel("1.   Pemesanan Tiket");
        pemesanan.setBounds(95,80,170,30);
        pemesanan.setFont(secondaryFont);
        pemesanan.setForeground(Color.black);
        pemesanan.addMouseListener(new MouseInputAdapter() {
           @Override
           public void mouseClicked(MouseEvent ev) {
               try {
                   Pemesanan pemesanan = new Pemesanan(username);
                   pemesanan.initialize();
                   frame.dispose();
               } catch (Exception e) {
                   System.out.println(e.getMessage());
               }
           }
            // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL PEMESANAN
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Font font = pemesanan.getFont();
                    Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    pemesanan.setFont(font.deriveFont(attributes));
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

            // -- MENGHILANGKAN UNDERLINE PADA TOMBOL PEMESANAN APABILA MOUSE TIDAK LEWAT TOMBOL
            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    Font font = pemesanan.getFont();
                    Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    pemesanan.setFont(font.deriveFont(attributes));
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        panel.add(pemesanan);

        // -- LIHAT FILM
        lihat = new JLabel("2.   Lihat Film");
        lihat.setBounds(95,110,150,30);
        lihat.setFont(secondaryFont);
        lihat.setForeground(Color.black);
        lihat.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                try {
                    Lihat lihat = new Lihat(username);
                    lihat.initialize();
                    frame.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
             // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL LIHAT FILM
             @Override
             public void mouseEntered(MouseEvent e) {
                 try {
                     Font font = lihat.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                     lihat.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
 
             // -- MENGHILANGKAN UNDERLINE PADA TOMBOL LIHAT FILM APABILA MOUSE TIDAK LEWAT TOMBOL
             @Override
             public void mouseExited(MouseEvent e) {
                 try {
                     Font font = lihat.getFont();
                     Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                     attributes.put(TextAttribute.UNDERLINE, -1);
                     lihat.setFont(font.deriveFont(attributes));
                 } catch(Exception exception) {
                     System.out.println(exception.getMessage());
                 }
             }
         });
        panel.add(lihat);

        // -- MEKANISME TOMBOL LOGOUT
        logout = new JLabel("3.   Logout");
        logout.setBounds(95,140,150,30);
        logout.setFont(secondaryFont);
        logout.setForeground(Color.black);
        logout.addMouseListener(new MouseInputAdapter() {
            // -- APABILA TOMBOL LOGOUT DI CLICK
            @Override
            public void mouseClicked(MouseEvent ev) {
                try {
                    int opsiKonfirmasi = JOptionPane.showConfirmDialog(frame, "Yakin ingin logout?", "Konfirmasi",  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (opsiKonfirmasi == JOptionPane.YES_OPTION) {
                        Login login = new Login();
                        login.initialize();
                        frame.dispose();
                    } 
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // -- MEMUNCULKAN UNDERLINE APABILA MOUSE LEWAT TOMBOL LOGOUT
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    Font font = logout.getFont();
                    Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    logout.setFont(font.deriveFont(attributes));
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

            // -- MENGHILANGKAN UNDERLINE PADA TOMBOL LOGOUT APABILA MOUSE TIDAK LEWAT TOMBOL
            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    Font font = logout.getFont();
                    Map<TextAttribute,Object> attributes = new HashMap<>(font.getAttributes());
                    attributes.put(TextAttribute.UNDERLINE, -1);
                    logout.setFont(font.deriveFont(attributes));
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
         });
        panel.add(logout);

        frame.setLocationRelativeTo(null); // -- BIKIN WINDOW PROGRAM DI TENGAH LAYAR
        frame.setVisible(true); // -- MEMUNCULKAN WINDOW

    }
}

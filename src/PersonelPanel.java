import javax.swing.*;
import java.awt.*;

public class PersonelPanel {

    JFrame frame;
    JButton doctorButon;
    JButton labButon;
    JButton adminButon;

    PersonelPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Personel Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
//        frame.setLayout(new GridLayout(5,3,10,10));

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
        gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
        gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
        gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

        //Üst Boşluk
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        frame.add(new JPanel(), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        frame.add(new JPanel(), gbc);


        doctorButon = new JButton();
        doctorButon.setText("Doktor Giriş");
//        doctorButon.setBounds(170,100,150,50);
        doctorButon.setFocusable(false);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(doctorButon, gbc);

        labButon = new JButton();
        labButon.setText("Lab Asistan ? :D Giriş");
//        labButon.setBounds(170,160,150,50);
        labButon.setFocusable(false);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(labButon, gbc);

        adminButon = new JButton();
        adminButon.setText("Admin Giriş");
//        adminButon.setBounds(170,220,150,50);
        adminButon.setFocusable(false);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(adminButon, gbc);

        //Alt Boşluk
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(new JPanel(), gbc);


//        //1.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//
//        //2.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(doctorButon);
//        frame.add(new JPanel()); //boş panel
//
//        //3.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(labButon);
//        frame.add(new JPanel()); //boş panel
//
//        //4.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(adminButon);
//        frame.add(new JPanel()); //boş panel
//
//        //5.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel

    }
}

import javax.swing.*;

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
        frame.setLayout(null);

        doctorButon = new JButton();
        doctorButon.setText("Doktor Giriş");
        doctorButon.setBounds(170,100,150,50);
        doctorButon.setFocusable(false);

        labButon = new JButton();
        labButon.setText("Lab Asistan ? :D Giriş");
        labButon.setBounds(170,160,150,50);
        labButon.setFocusable(false);

        adminButon = new JButton();
        adminButon.setText("Admin Giriş");
        adminButon.setBounds(170,220,150,50);
        adminButon.setFocusable(false);

        frame.add(doctorButon);
        frame.add(labButon);
        frame.add(adminButon);
    }
}

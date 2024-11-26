import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel implements ActionListener {

    JFrame frame;
    JButton hastaButon;
    JButton personelButon;

    LoginPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hoş Geldiniz!");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
//        frame.setLayout(new GridLayout(4,3,10,10));

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
        gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
        gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
        gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

        hastaButon = new JButton();
        personelButon = new JButton();

        hastaButon.setText("Hasta Giriş");
//        hastaButon.setBounds(170,115,150,50);
        hastaButon.setFocusable(false);
        hastaButon.addActionListener(this);

        //Boşluk
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JPanel(), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(new JPanel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(hastaButon, gbc);

        personelButon.setText("Personel Giriş");
//        personelButon.setBounds(170,175,150,50);
        personelButon.setFocusable(false);
        personelButon.addActionListener(this);

        gbc.gridy = 2;
        frame.add(personelButon, gbc);

        //Boşluk
        gbc.gridy = 3;
        frame.add(new JPanel(), gbc);


//        //1.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//
//        //2.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(hastaButon);
//        frame.add(new JPanel()); //boş panel
//
//        //3.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(personelButon);
//        frame.add(new JPanel()); //boş panel
//
//        //4.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hastaButon){
            frame.dispose();
            HastaPanel hastaPanel = new HastaPanel();
        }

        if(e.getSource() == personelButon){
            frame.dispose();
            PersonelPanel personelPanel = new PersonelPanel();
        }
    }
}

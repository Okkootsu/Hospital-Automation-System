import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Uygulama için Ana giriş yapma paneli

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


        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
        gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
        gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
        gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

        hastaButon = new JButton();
        personelButon = new JButton();

        hastaButon.setText("Hasta Giriş");
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
        personelButon.setFocusable(false);
        personelButon.addActionListener(this);

        gbc.gridy = 2;
        frame.add(personelButon, gbc);

        //Boşluk
        gbc.gridy = 3;
        frame.add(new JPanel(), gbc);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hastaButon){ // Hasta Girişe yönlendir
            frame.dispose();
            new HastaPanel();
        }

        if(e.getSource() == personelButon){ // Personel Girişe yönlendir
            frame.dispose();
            new PersonelPanel();
        }
    }
}

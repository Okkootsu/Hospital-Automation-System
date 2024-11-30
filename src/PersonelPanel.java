import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelPanel {

    JFrame frame;
    JButton doctorButon;
    JButton labButon;
    JButton adminButon;
    JButton goBackLoginButton;
    MainPanel mainPanel;

    PersonelPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Personel Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel = new MainPanel();

        frame.add(mainPanel);

    }

    MyPanel myPanel;

    JButton goBackButton;

    public class MyPanel extends JPanel implements ActionListener{
        MyPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Üst Boşluk
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(new JPanel(), gbc);

            gbc.gridx = 3;
            this.add(new JPanel(), gbc);

            JLabel nameLabel = new JLabel();
            nameLabel.setText("Ad Soyad:");

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(nameLabel, gbc);

            JTextField nameTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridwidth = 1;
            this.add(nameTextField, gbc);

            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre :");


            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);

            JTextField passwordTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridwidth = 1;
            this.add(passwordTextField, gbc);

            JButton loginButton = new JButton();
            loginButton.setText("Giriş Yap");
            loginButton.setFocusable(false);


            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(loginButton, gbc);

            goBackButton = new JButton();
            goBackButton.setText("Geri Dön");
            goBackButton.setFocusable(false);
            goBackButton.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(goBackButton, gbc);

            //Alt Boşluk
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == goBackButton){
                frame.remove(myPanel);
                myPanel = null;

                mainPanel = new MainPanel();
                frame.add(mainPanel);

                frame.revalidate();
                frame.repaint();
            }
        }
    }

    public class MainPanel extends JPanel implements ActionListener{
        MainPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Üst Boşluk
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);


            doctorButon = new JButton();
            doctorButon.setText("Doktor Giriş");
            doctorButon.setFocusable(false);
            doctorButon.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.add(doctorButon, gbc);

            labButon = new JButton();
            labButon.setText("Lab Asistan ? :D Giriş");
            labButon.setFocusable(false);
            labButon.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(labButon, gbc);

            adminButon = new JButton();
            adminButon.setText("Admin Giriş");
            adminButon.setFocusable(false);
            adminButon.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(adminButon, gbc);

            //Boş Alan
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            goBackLoginButton = new JButton();
            goBackLoginButton.setText("Geri Dön");
            goBackLoginButton.setFocusable(false);
            goBackLoginButton.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            this.add(goBackLoginButton, gbc);

            //Alt Boşluk
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == doctorButon){
                frame.remove(mainPanel);
                mainPanel = null;

                myPanel = new MyPanel();
                frame.add(myPanel);

                frame.revalidate();
                frame.repaint();
            }

            if (e.getSource() == labButon){
                frame.remove(mainPanel);
                mainPanel = null;

                myPanel = new MyPanel();
                frame.add(myPanel);

                frame.revalidate();
                frame.repaint();
            }

            if (e.getSource() == adminButon){
                frame.remove(mainPanel);
                mainPanel = null;

                myPanel = new MyPanel();
                frame.add(myPanel);

                frame.revalidate();
                frame.repaint();
            }

            if (e.getSource() == goBackLoginButton){
                frame.dispose();
                new LoginPanel();
            }
        }
    }
}

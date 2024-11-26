import javax.swing.*;
import java.awt.*;

public class HastaPanel {

    JFrame frame;

    HastaPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hasta Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
//        frame.setLayout(new GridLayout(6,5,5,10));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
        gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
        gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
        gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla


        //Üst Boşluk
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JPanel(), gbc);

        gbc.gridx = 4;
        frame.add(new JPanel(), gbc);


        JLabel nameLabel = new JLabel();
        nameLabel.setText("Ad Soyad:");
//        nameLabel.setBounds(140,100,55,40);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(nameLabel, gbc);

        JTextField nameTextField = new JTextField();
//        nameTextField.setBounds(200,110,150,25);

        gbc.gridx = 2;
        gbc.gridwidth = 2; //2 genişlikte
        frame.add(nameTextField, gbc);

        JLabel idLabel = new JLabel();
        idLabel.setText("TC Kimlik No:");
//        idLabel.setBounds(120,150,77,40);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(idLabel, gbc);

        JTextField idTextField = new JTextField();
//        idTextField.setBounds(200,160,150,25);

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        frame.add(idTextField, gbc);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Şifre :");
//        passwordLabel.setBounds(162,200,35,40);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(passwordLabel, gbc);

        JTextField passwordTextField = new JTextField();
//        passwordTextField.setBounds(200,210,150,25);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(passwordTextField, gbc);

        JButton loginButton = new JButton();
        loginButton.setText("Giriş Yap");
        loginButton.setFocusable(false);
//        loginButton.setBounds(225,240,75,50);

        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(loginButton, gbc);

        JButton registerButton = new JButton();
        registerButton.setText("Kayıt Ol");
        registerButton.setFocusable(false);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(registerButton, gbc);

        JButton goBackButton = new JButton();
        goBackButton.setText("Geri Dön");
        goBackButton.setFocusable(false);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(goBackButton, gbc);

        //Alt Boşluk
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        frame.add(new JPanel(), gbc);

//        //1.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//
//        //2.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(nameLabel);
//        frame.add(nameTextField);
//        frame.add(new JPanel()); //boş panel
//
//        //3.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(idLabel);
//        frame.add(idTextField);
//        frame.add(new JPanel()); //boş panel
//
//        //4.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(passwordLabel);
//        frame.add(passwordTextField);
//        frame.add(new JPanel()); //boş panel
//
//        //5.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(goBackButton);
//        frame.add(registerButton);
//        frame.add(loginButton);
//        frame.add(new JPanel()); //boş panel
//
//        //6.satır
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel
//        frame.add(new JPanel()); //boş panel

    }
}

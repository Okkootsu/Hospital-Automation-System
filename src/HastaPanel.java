import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HastaPanel implements ActionListener {

    JFrame frame;
    JButton goBackButton;

    HastaPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hasta Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);

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


        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(nameLabel, gbc);

        JTextField nameTextField = new JTextField();


        gbc.gridx = 2;
        gbc.gridwidth = 2; //2 genişlikte
        frame.add(nameTextField, gbc);

        JLabel idLabel = new JLabel();
        idLabel.setText("TC Kimlik No:");


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(idLabel, gbc);

        JTextField idTextField = new JTextField();


        gbc.gridx = 2;
        gbc.gridwidth = 2;
        frame.add(idTextField, gbc);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Şifre :");


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(passwordLabel, gbc);

        JTextField passwordTextField = new JTextField();


        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(passwordTextField, gbc);

        JButton loginButton = new JButton();
        loginButton.setText("Giriş Yap");
        loginButton.setFocusable(false);


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

        goBackButton = new JButton();
        goBackButton.setText("Geri Dön");
        goBackButton.setFocusable(false);
        goBackButton.addActionListener(this);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(goBackButton, gbc);

        //Alt Boşluk
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        frame.add(new JPanel(), gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBackButton){
            frame.dispose();
            new LoginPanel();
        }
    }
}

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
        frame.setLayout(null);

        JLabel nameLabel = new JLabel();
        nameLabel.setText("Ad Soyad:");
        nameLabel.setBounds(140,100,55,40);


        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(200,110,150,25);



        JLabel idLabel = new JLabel();
        idLabel.setText("TC Kimlik No:");
        idLabel.setBounds(120,150,77,40);


        JTextField idTextField = new JTextField();
        idTextField.setBounds(200,160,150,25);



        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Şifre :");
        passwordLabel.setBounds(162,200,35,40);


        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(200,210,150,25);



        JButton loginButton = new JButton();
        loginButton.setText("Giriş Yap");
        loginButton.setBounds(225,230,50,50);

        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(idLabel);
        frame.add(idTextField);
        frame.add(passwordLabel);
        frame.add(passwordTextField);
        frame.add(loginButton);
    }
}

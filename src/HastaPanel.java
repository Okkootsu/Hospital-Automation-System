import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HastaPanel {

    JFrame frame;
    JButton goBackLoginButton;
    JButton registerButton;
    MainPanel mainPanel;
    RegisterPanel registerPanel;
    JButton goBackButton;

    HastaPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hasta Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel = new MainPanel();

        frame.add(mainPanel);
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
            this.add(new JPanel(), gbc);

            gbc.gridx = 4;
            this.add(new JPanel(), gbc);


            JLabel nameLabel = new JLabel();
            nameLabel.setText("Ad Soyad:");


            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(nameLabel, gbc);

            JTextField nameTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridwidth = 2; //2 genişlikte
            this.add(nameTextField, gbc);


            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre :");


            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);

            JTextField passwordTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            this.add(passwordTextField, gbc);

            JButton loginButton = new JButton();
            loginButton.setText("Giriş Yap");
            loginButton.setFocusable(false);


            gbc.gridx = 3;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(loginButton, gbc);

            registerButton = new JButton();
            registerButton.setText("Kayıt Ol");
            registerButton.setFocusable(false);
            registerButton.addActionListener(this);

            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(registerButton, gbc);

            goBackLoginButton = new JButton();
            goBackLoginButton.setText("Geri Dön");
            goBackLoginButton.setFocusable(false);
            goBackLoginButton.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(goBackLoginButton, gbc);

            //Alt Boşluk
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == goBackLoginButton){
                frame.dispose();
                new LoginPanel();
            }

            if(e.getSource() == registerButton){
                frame.remove(mainPanel);
                mainPanel = null;

                registerPanel = new RegisterPanel();
                frame.add(registerPanel);

                frame.revalidate();
                frame.repaint();
            }
        }
    }

    JTextField nameTextField;
    JTextField tcTextField;
    JTextField passwordTextField;

    public class RegisterPanel extends JPanel implements ActionListener{
        RegisterPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //üst boşluk
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            JLabel nameLabel = new JLabel();
            nameLabel.setText("Ad Soyad:");

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.add(nameLabel, gbc);

            nameTextField = new JTextField();

            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.add(nameTextField, gbc);

            JLabel tcLabel = new JLabel();
            tcLabel.setText("TC Kimlik No:");

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(tcLabel, gbc);

            tcTextField = new JTextField();

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(tcTextField, gbc);

            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre:");

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);

            passwordTextField = new JTextField();

            gbc.gridx = 2;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            this.add(passwordTextField, gbc);

            goBackButton = new JButton();
            goBackButton.setText("Geri Dön");
            goBackButton.setFocusable(false);
            goBackButton.addActionListener(this);

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(goBackButton, gbc);

            registerButton = new JButton();
            registerButton.setText("Hesap Oluştur");
            registerButton.setFocusable(false);
            registerButton.addActionListener(this);

            gbc.gridx = 2;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(registerButton, gbc);

            //Alt Boşluk
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == goBackButton){
                frame.remove(registerPanel);
                registerPanel = null;

                mainPanel = new MainPanel();
                frame.add(mainPanel);

                frame.revalidate();
                frame.repaint();
            }

            if (e.getSource() == registerButton){

                BaseUser customer = new Customer();

                try {

                    customer.username = nameTextField.getText();
                    customer.password = passwordTextField.getText();
                    customer.tc = Long.parseLong(tcTextField.getText());

                    new Register(customer);

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Hata Kodu:"+exception.getMessage(),
                            "Bir Hata Oluştu",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

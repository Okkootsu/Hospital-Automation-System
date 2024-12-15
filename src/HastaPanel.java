
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HastaPanel {

    JFrame frame;
    MainPanel mainPanel;
    RegisterPanel registerPanel;


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


    private class MainPanel extends JPanel implements ActionListener{

        JButton registerButton;
        JButton loginButton;
        JButton goBackLoginButton;
        JTextField nameTextField;
        JTextField tcTextField;
        JPasswordField passwordTextField;
        JButton hackButton; //otomatik giriş yapmak için

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

            gbc.gridx = 5; //4 olması lazım
            this.add(new JPanel(), gbc);


            JLabel tcLabel = new JLabel();
            tcLabel.setText("TC Kimlik No:");

            gbc.gridx = 1;  gbc.gridy = 1;  gbc.gridwidth = 1;
            this.add(tcLabel, gbc);


            tcTextField = new JTextField();
                                                    //2 olmalı
            gbc.gridx = 2;  gbc.gridy = 1;  gbc.gridwidth = 3;
            this.add(tcTextField, gbc);


            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre :");

            gbc.gridx = 1;  gbc.gridy = 2;  gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);


            passwordTextField = new JPasswordField();
            passwordTextField.setEchoChar('*');
                                                    //2 olmalı
            gbc.gridx = 2;  gbc.gridy = 2;  gbc.gridwidth = 3;
            this.add(passwordTextField, gbc);

            //Şifreyi Göster
            JCheckBox showPasswordChkBox = new JCheckBox("Şifreyi Göster");
            showPasswordChkBox.setFocusable(false);
            showPasswordChkBox.addActionListener(e -> {
                if (showPasswordChkBox.isSelected()) {
                    passwordTextField.setEchoChar((char) 0); // Şifreyi düz metin olarak göster
                }
                else {
                    passwordTextField.setEchoChar('*');
                }
            });

            gbc.gridx = 2;  gbc.gridy = 3;  gbc.gridwidth = 3;
            this.add(showPasswordChkBox, gbc);

            //Fazlalık
            hackButton = new JButton();
            hackButton.setText("Hack");
            hackButton.setFocusable(false);
            hackButton.addActionListener(this);

            gbc.gridx = 4;
            gbc.gridy = 4; //3'tü
            gbc.gridwidth = 1;
            this.add(hackButton, gbc);
            //

            loginButton = new JButton();
            loginButton.setText("Giriş Yap");
            loginButton.setFocusable(false);
            loginButton.addActionListener(this);

            gbc.gridx = 3;  gbc.gridy = 4;  gbc.gridwidth = 1;
            this.add(loginButton, gbc);


            registerButton = new JButton();
            registerButton.setText("Kayıt Ol");
            registerButton.setFocusable(false);
            registerButton.addActionListener(this);

            gbc.gridx = 2;  gbc.gridy = 4;  gbc.gridwidth = 1;
            this.add(registerButton, gbc);


            goBackLoginButton = new JButton();
            goBackLoginButton.setText("Geri Dön");
            goBackLoginButton.setFocusable(false);
            goBackLoginButton.addActionListener(this);

            gbc.gridx = 1;  gbc.gridy = 4;  gbc.gridwidth = 1;
            this.add(goBackLoginButton, gbc);

            //Alt Boşluk
            gbc.gridx = 0;  gbc.gridy = 5;  gbc.gridwidth = 1;
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

            if(e.getSource() == loginButton){

                Customer customer = new Customer();

                char[] password = passwordTextField.getPassword();
                customer.password = String.valueOf(password);

                try {

                    customer.tc = Long.parseLong(tcTextField.getText());

                    Login login = new Login();

                    if(login.isCompleted(customer)){

                        frame.dispose();

                        // Şifre dizisini temizleme
                        java.util.Arrays.fill(password, '\0');

                        MainMenuPanel mainMenuPanel = MainMenuPanel.getInstance(customer);

                    }

                }catch (Exception exception){

                    JOptionPane.showMessageDialog(null,"TC boş bırakılamaz" ,
                            "Uyarı",JOptionPane.ERROR_MESSAGE);

                }
            }

            if(e.getSource() == hackButton){
                Customer volkan = new Customer();
                volkan.username = "volkan mutlu";
                volkan.password = "123456";
                volkan.tc = 12345678910L;
                volkan.id = 7;

                frame.dispose();
                MainMenuPanel mainMenuPanel = MainMenuPanel.getInstance(volkan);
            }
        }
    }



    private class RegisterPanel extends JPanel implements ActionListener{

        JTextField nameTextField;
        JTextField tcTextField;
        JTextField passwordTextField;
        JButton goBackButton;
        JButton registerButton;

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

                customer.username = nameTextField.getText();
                customer.password = passwordTextField.getText();

                try {

                    customer.tc = Long.parseLong(tcTextField.getText());

                    new Register(customer);

                }catch (Exception exception){

                    JOptionPane.showMessageDialog(null,"TC boş bırakılamaz" ,
                            "Uyarı",JOptionPane.ERROR_MESSAGE);

                }

            }
        }
    }
}


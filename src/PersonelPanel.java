import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelPanel {

    JFrame frame;

    // Hangi tip kullanıcı girişinin yapılacağını tut
    boolean admin = false;
    boolean doctor = false;
    //

    PersonelPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Personel Giriş");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        CardLayout cardLayout = new CardLayout();
        JPanel mainCardPanel = new JPanel(cardLayout); // Paneller arası ana bağlantıyı sağlar

        MainPanel mainPanel = new MainPanel(mainCardPanel, cardLayout);
        EmployeeChoicePanel choicePanel = new EmployeeChoicePanel(mainCardPanel, cardLayout);

        mainCardPanel.add(mainPanel, "Login");
        mainCardPanel.add(choicePanel, "Choice");

        cardLayout.show(mainCardPanel, "Choice");


        frame.add(mainCardPanel);

    }

    // Personel için giriş yapma ekranı
    private class MainPanel extends JPanel implements ActionListener{

        JTextField tcTextField;
        JPasswordField passwordTextField;
        JButton goBackButton;
        JButton loginButton;

        MainPanel(JPanel mainCardPanel, CardLayout cardLayout){
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

            JLabel tcLabel = new JLabel();
            tcLabel.setText("TC :");

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(tcLabel, gbc);

            tcTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            this.add(tcTextField, gbc);


            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre :");

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);


            passwordTextField = new JPasswordField();
            passwordTextField.setEchoChar('*');

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
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

            gbc.gridx = 2;  gbc.gridy = 3;  gbc.gridwidth = 2;
            this.add(showPasswordChkBox, gbc);


            loginButton = new JButton();
            loginButton.setText("Giriş Yap");
            loginButton.setFocusable(false);
            loginButton.addActionListener(this);

            gbc.gridx = 2;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            this.add(loginButton, gbc);


            goBackButton = new JButton();
            goBackButton.setText("Geri Dön");
            goBackButton.setFocusable(false);
            goBackButton.addActionListener(e -> cardLayout.show(mainCardPanel, "Choice"));

            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.gridwidth = 1;
            this.add(goBackButton, gbc);


            //Alt Boşluk
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == loginButton){

                if(admin){
                    Employee employee = new Admin();

                    char[] password = passwordTextField.getPassword(); // şifreyi güvenli bir şekilde tut
                    employee.password = String.valueOf(password);

                    try {

                        employee.tc = Long.parseLong(tcTextField.getText());

                        Login login = new Login(); // Giriş bilgilerini kontrol et

                        if(login.isCompleted(employee)){

                            frame.dispose();

                            // Şifre dizisini temizleme
                            java.util.Arrays.fill(password, '\0');

                            MainMenuPanel mainMenuPanel = MainMenuPanel.getInstance(employee);
                        }

                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"TC boş bırakılamaz" ,
                                "Uyarı",JOptionPane.ERROR_MESSAGE);
                    }
                }

                if(doctor){
                    Employee employee = new Doctor();

                    char[] password = passwordTextField.getPassword();
                    employee.password = String.valueOf(password);

                    try {

                        employee.tc = Long.parseLong(tcTextField.getText());

                        Login login = new Login();  // Giriş bilgilerini kontrol et

                        if(login.isCompleted(employee)){

                            frame.dispose();

                            // Şifre dizisini temizleme
                            java.util.Arrays.fill(password, '\0');

                            MainMenuPanel mainMenuPanel = MainMenuPanel.getInstance(employee);
                        }

                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(null,"TC boş bırakılamaz" ,
                                "Uyarı",JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
    }

    // Hangi personel için giriş yapılacağını seçtiğimiz ekran/panel
    private class EmployeeChoicePanel extends JPanel{

        EmployeeChoicePanel(JPanel mainCardPanel, CardLayout cardLayout) {
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Üst boşluk
            gbc.gridx = 0;  gbc.gridy = 0; gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            gbc.gridx = 2;  gbc.gridy = 0; gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);


            JButton docBtn = new JButton("Doktor Giriş");
            docBtn.setFocusable(false);
            docBtn.addActionListener(e -> {
                admin = false;
                doctor = true;


                cardLayout.show(mainCardPanel, "Login");
            });

            gbc.gridx = 1;  gbc.gridy = 1; gbc.gridwidth = 1;
            this.add(docBtn, gbc);


            JButton adminBtn = new JButton("Admin Giriş");
            adminBtn.setFocusable(false);
            adminBtn.addActionListener(e -> {
                admin = true;
                doctor = false;


                cardLayout.show(mainCardPanel, "Login");
            });

            gbc.gridx = 1;  gbc.gridy = 2; gbc.gridwidth = 1;
            this.add(adminBtn, gbc);

            //Boşluk
            gbc.gridx = 1;  gbc.gridy = 3; gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);

            JButton goBackBtn = new JButton("Geri Dön");
            goBackBtn.setFocusable(false);
            goBackBtn.addActionListener(e -> {
                frame.dispose();
                new LoginPanel();
            });

            gbc.gridx = 1;  gbc.gridy = 4; gbc.gridwidth = 1;
            this.add(goBackBtn, gbc);

            //Boşluk
            gbc.gridx = 1;  gbc.gridy = 5; gbc.gridwidth = 1;
            this.add(new JPanel(), gbc);
        }
    }
}

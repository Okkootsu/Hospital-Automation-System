import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelPanel {

    JFrame frame;
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

    private class MainPanel extends JPanel implements ActionListener{

        JTextField tcTextField;
        JTextField passwordTextField;
        JButton goBackButton;
        JButton loginButton;

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

            gbc.gridx = 3;
            this.add(new JPanel(), gbc);

            JLabel tcLabel = new JLabel();
            tcLabel.setText("TC :");

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(tcLabel, gbc);

            tcTextField = new JTextField();


            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            this.add(tcTextField, gbc);


            JLabel passwordLabel = new JLabel();
            passwordLabel.setText("Şifre :");

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(passwordLabel, gbc);


            passwordTextField = new JTextField();

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            this.add(passwordTextField, gbc);


            loginButton = new JButton();
            loginButton.setText("Giriş Yap");
            loginButton.setFocusable(false);
            loginButton.addActionListener(this);

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

            if(e.getSource() == loginButton){

                Employee employee = new Employee();

                employee.password = passwordTextField.getText();

                try {

                    employee.tc = Long.parseLong(tcTextField.getText());

                    Login login = new Login();

                    if(login.isCompleted(employee)){

                        frame.dispose();

                        MainMenuPanel mainMenuPanel = MainMenuPanel.getInstance(employee);
                    }

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"TC boş bırakılamaz" ,
                            "Uyarı",JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == goBackButton){
                frame.dispose();
                new LoginPanel();
            }
        }
    }
}

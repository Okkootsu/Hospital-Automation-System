import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyAccountPanel {

    // Hastalar girişi için hesap paneli
    public static class CustomerPanel extends JPanel implements IPanel, ActionListener {
        private final BaseUser customer;

        CustomerPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser customer){
            this.customer = customer; // Kullanıcı bilgisini sakla
            initializePanel(mainCardPanel, cardLayout);
        }

        @Override
        public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
            this.setBackground(new Color(203, 220, 235));
            this.setPreferredSize(new Dimension(100, 100));
            refreshContent(mainCardPanel, cardLayout);
        }

        JButton showPasswordBtn;
        JLabel password;
        StringBuilder censoredPassword;
        int count = 0; // Şifreyi gösterme butonu için sayaç -> tek sayılarda göster, çift sayılarda gösterme

        // Sayfayı güncelle
        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll();

            JPanel tempPanel = new JPanel();
            tempPanel.setPreferredSize(new Dimension(140, 450));
            tempPanel.setBackground(Color.lightGray);
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setOpaque(false);

            //sol ve sağ boşluklar (Hesap bilgilerini ortala)
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel accountPanel = new JPanel();

            westContainer.setBackground(Color.magenta);
            westContainer.setOpaque(false);
            eastContainer.setBackground(Color.blue);
            eastContainer.setOpaque(false);

            westContainer.setPreferredSize(new Dimension(150,50));
            eastContainer.setPreferredSize(new Dimension(150,50));

            tempPanel.add(westContainer, BorderLayout.WEST);
            tempPanel.add(eastContainer, BorderLayout.EAST);


            accountPanel.setOpaque(false);
            accountPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Boşluk
            gbc.gridx = 0;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(new JLabel(""), gbc);


            JLabel accInfoLabel = new JLabel("Hesap Bilgileri");
            accInfoLabel.setFont(new Font("Times New Roman",Font.BOLD,45));

            gbc.gridx = 1;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(accInfoLabel, gbc);

            //Boşluk
            gbc.gridx = 2;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(new JLabel(""), gbc);



            JLabel nameLabel = new JLabel("İsim : ");
            nameLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 1;  gbc.gridwidth = 1;
            accountPanel.add(nameLabel, gbc);


            JLabel name = new JLabel();
            name.setText(customer.username);
            name.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 1;  gbc.gridwidth = 2;
            accountPanel.add(name, gbc);



            JLabel tcLabel = new JLabel("TC : ");
            tcLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 2;  gbc.gridwidth = 1;
            accountPanel.add(tcLabel, gbc);



            JLabel tc = new JLabel();
            tc.setText(String.valueOf(customer.tc));
            tc.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 2;  gbc.gridwidth = 2;
            accountPanel.add(tc, gbc);



            JLabel passwordLabel = new JLabel("Şifre : ");
            passwordLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 3;  gbc.gridwidth = 1;
            accountPanel.add(passwordLabel, gbc);



            password = new JLabel();

            //Şifreyi gizle
            censoredPassword = new StringBuilder();
            int len = customer.password.length();
            for(int i=0; i < len; i++){
                censoredPassword.append('*');
            }
            password.setText(censoredPassword.toString());
            //

            password.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 3;  gbc.gridwidth = 1; //2 idi normalde
            accountPanel.add(password, gbc);


            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.black);
            buttonPanel.setOpaque(false);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,7));

            showPasswordBtn = new JButton("Şifreyi Göster");
            showPasswordBtn.setFocusable(false);
            showPasswordBtn.addActionListener(this);
            showPasswordBtn.setPreferredSize(new Dimension(115,50));

            buttonPanel.setPreferredSize(new Dimension(showPasswordBtn.getPreferredSize()));
            buttonPanel.add(showPasswordBtn);

            gbc.gridx = 2;  gbc.gridy = 3;  gbc.gridwidth = 1;
            accountPanel.add(buttonPanel, gbc);


            JButton delAccountBtn = new JButton("Hesabı Sil");
            delAccountBtn.setFocusable(false);
            delAccountBtn.addActionListener(e -> {
                int choice = JOptionPane.showOptionDialog(this,"Hesabınızı KALICI olarak " +
                                "silmek istediğinize emin misiniz?",
                        "Uyarı!",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,null,0);

                if(choice == 0){

                    customer.delThisUser();

                    JOptionPane.showMessageDialog(null,"Kullanıcı sistemden başarıyla silindi" ,
                            "Bilgilendirme",JOptionPane.INFORMATION_MESSAGE);

                    MainMenuPanel.getInstance(customer).dispose();
                    MainMenuPanel.resetInstance();


                    new LoginPanel();
                }
            });

            gbc.gridx = 2;  gbc.gridy = 4;  gbc.gridwidth = 1;
            accountPanel.add(delAccountBtn, gbc);


            JButton updateAccountBtn = new JButton("Şifreyi Güncelle");
            updateAccountBtn.setFocusable(false);
            updateAccountBtn.addActionListener(e -> {
                String newPass = JOptionPane.showInputDialog("Yeni Şifre:");

                if(newPass == null || newPass.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Şifre değiştirme işlemi iptal edildi" ,
                            "Uyarı",JOptionPane.ERROR_MESSAGE);
                }

                else {
                    customer.update("password",newPass);
                    customer.password = newPass;


                    this.refreshContent(mainCardPanel, cardLayout); // Güncel içeriği yükle
                }
            });

            gbc.gridx = 1;  gbc.gridy = 4;  gbc.gridwidth = 1;
            accountPanel.add(updateAccountBtn, gbc);


            JButton logOffBtn = new JButton("Çıkış Yap");
            logOffBtn.setFocusable(false);
            logOffBtn.addActionListener(e -> {
                int choice = JOptionPane.showOptionDialog(this,"Çıkış yapmak istediğinize emin misiniz?",
                        "Uyarı!",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,null,0);

                if(choice == 0){
                    MainMenuPanel.getInstance(customer).dispose();
                    MainMenuPanel.resetInstance();
                    new LoginPanel();
                }
            });

            gbc.gridx = 0;  gbc.gridy = 4;  gbc.gridwidth = 1;
            accountPanel.add(logOffBtn, gbc);


            tempPanel.add(accountPanel);

            this.setLayout(new BorderLayout());
            this.add(tempPanel, BorderLayout.NORTH);

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == showPasswordBtn){

                count++;

                if(count % 2 == 0){ // Şifreyi gizle
                    password.setText(censoredPassword.toString());

                    password.revalidate();
                    password.repaint();

                    showPasswordBtn.setText("Şifreyi Göster");

                    showPasswordBtn.revalidate();
                    showPasswordBtn.repaint();
                }

                else{ // Şifreyi göster
                    password.setText(customer.password);

                    password.revalidate();
                    password.repaint();

                    showPasswordBtn.setText("Şifreyi Gizle");

                    showPasswordBtn.revalidate();
                    showPasswordBtn.repaint();
                }
            }
        }
    }

    // Çalışanlar için hesap paneli
    public static class EmployeePanel extends JPanel implements IPanel, ActionListener{

        private final BaseUser employee;

        EmployeePanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser employee){
            this.employee = employee; // Kullanıcı bilgisini sakla
            initializePanel(mainCardPanel, cardLayout);
        }

        @Override
        public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
            this.setBackground(new Color(203, 220, 235));
            this.setPreferredSize(new Dimension(100, 100));
            refreshContent(mainCardPanel, cardLayout);
        }

        JButton showPasswordBtn;
        JLabel password;
        StringBuilder censoredPassword;
        int count = 0;

        // Sayfayı güncelle
        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll();

            JPanel tempPanel = new JPanel();
            tempPanel.setPreferredSize(new Dimension(140, 450));
            tempPanel.setBackground(Color.lightGray);
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setOpaque(false);

            //sol ve sağ boşluklar (Hesap bilgilerini ortala)
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel accountPanel = new JPanel();

            westContainer.setBackground(Color.magenta);
            westContainer.setOpaque(false);
            eastContainer.setBackground(Color.blue);
            eastContainer.setOpaque(false);

            westContainer.setPreferredSize(new Dimension(150,50));
            eastContainer.setPreferredSize(new Dimension(150,50));

            tempPanel.add(westContainer, BorderLayout.WEST);
            tempPanel.add(eastContainer, BorderLayout.EAST);


            accountPanel.setOpaque(false);
            accountPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Boşluk
            gbc.gridx = 0;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(new JLabel(""), gbc);


            JLabel accInfoLabel = new JLabel("Hesap Bilgileri");
            accInfoLabel.setFont(new Font("Times New Roman",Font.BOLD,45));

            gbc.gridx = 1;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(accInfoLabel, gbc);

            //Boşluk
            gbc.gridx = 2;  gbc.gridy = 0;  gbc.gridwidth = 1;
            accountPanel.add(new JLabel(""), gbc);



            JLabel nameLabel = new JLabel("İsim : ");
            nameLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 1;  gbc.gridwidth = 1;
            accountPanel.add(nameLabel, gbc);


            JLabel name = new JLabel();
            name.setText(employee.username);
            name.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 1;  gbc.gridwidth = 2;
            accountPanel.add(name, gbc);



            JLabel tcLabel = new JLabel("TC : ");
            tcLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 2;  gbc.gridwidth = 1;
            accountPanel.add(tcLabel, gbc);



            JLabel tc = new JLabel();
            tc.setText(String.valueOf(employee.tc));
            tc.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 2;  gbc.gridwidth = 2;
            accountPanel.add(tc, gbc);



            JLabel passwordLabel = new JLabel("Şifre : ");
            passwordLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 3;  gbc.gridwidth = 1;
            accountPanel.add(passwordLabel, gbc);



            password = new JLabel();

            //Şifreyi gizle
            censoredPassword = new StringBuilder();
            int len = employee.password.length();
            for(int i=0; i < len; i++){
                censoredPassword.append('*');
            }
            password.setText(censoredPassword.toString());
            //

            password.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 3;  gbc.gridwidth = 1; //2 idi normalde
            accountPanel.add(password, gbc);


            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.black);
            buttonPanel.setOpaque(false);
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,7));

            showPasswordBtn = new JButton("Şifreyi Göster");
            showPasswordBtn.setFocusable(false);
            showPasswordBtn.addActionListener(this);
            showPasswordBtn.setPreferredSize(new Dimension(115,42));

            buttonPanel.setPreferredSize(new Dimension(showPasswordBtn.getPreferredSize()));
            buttonPanel.add(showPasswordBtn);

            gbc.gridx = 2;  gbc.gridy = 3;  gbc.gridwidth = 1;
            accountPanel.add(buttonPanel, gbc);


            JLabel roleLabel = new JLabel("Meslek : ");
            roleLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 0;  gbc.gridy = 4;  gbc.gridwidth = 1;
            accountPanel.add(roleLabel, gbc);


            JLabel role = new JLabel();
            role.setText(employee.getUserType());
            role.setFont(new Font("Times New Roman",Font.PLAIN,30));

            gbc.gridx = 1;  gbc.gridy = 4;  gbc.gridwidth = 2;
            accountPanel.add(role, gbc);



            JButton delAccountBtn = new JButton("Hesabı Sil");
            delAccountBtn.setFocusable(false);
            delAccountBtn.addActionListener(e -> {
                int choice = JOptionPane.showOptionDialog(this,"Hesabınızı KALICI olarak silmek istediğinize emin misiniz?",
                        "Uyarı!",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,null,0);

                if(choice == 0){

                    employee.delThisUser();

                    JOptionPane.showMessageDialog(null,"Kullanıcı sistemden başarıyla silindi" ,
                            "Bilgilendirme",JOptionPane.INFORMATION_MESSAGE);

                    MainMenuPanel.getInstance(employee).dispose();
                    MainMenuPanel.resetInstance();


                    new LoginPanel();
                }
            });

            gbc.gridx = 2;  gbc.gridy = 5;  gbc.gridwidth = 1;
            accountPanel.add(delAccountBtn, gbc);


            JButton updateAccountBtn = new JButton("Şifreyi Güncelle");
            updateAccountBtn.setFocusable(false);
            updateAccountBtn.addActionListener(e -> {

                String newPass = JOptionPane.showInputDialog("Yeni Şifre:");

                if(newPass == null || newPass.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Şifre değiştirme işlemi iptal edildi" ,
                            "Uyarı",JOptionPane.ERROR_MESSAGE);
                }

                else {
                    employee.update("password",newPass);
                    employee.password = newPass;

                    this.refreshContent(mainCardPanel, cardLayout); // Güncel içeriği yükle
                }

            });

            gbc.gridx = 1;  gbc.gridy = 5;  gbc.gridwidth = 1;
            accountPanel.add(updateAccountBtn, gbc);


            JButton logOffBtn = new JButton("Çıkış Yap");
            logOffBtn.setFocusable(false);
            logOffBtn.addActionListener(e -> {
                int choice = JOptionPane.showOptionDialog(this,"Çıkış yapmak istediğinize emin misiniz?",
                        "Uyarı!",JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,null,null,0);

                if(choice == 0){
                    MainMenuPanel.getInstance(employee).dispose();
                    MainMenuPanel.resetInstance();
                    new LoginPanel();
                }
            });

            gbc.gridx = 0;  gbc.gridy = 5;  gbc.gridwidth = 1;
            accountPanel.add(logOffBtn, gbc);


            tempPanel.add(accountPanel);

            this.setLayout(new BorderLayout());
            this.add(tempPanel, BorderLayout.NORTH);

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == showPasswordBtn){

                count++;

                if(count % 2 == 0){ // Şifreyi gizle
                    password.setText(censoredPassword.toString());

                    password.revalidate();
                    password.repaint();

                    showPasswordBtn.setText("Şifreyi Göster");

                    showPasswordBtn.revalidate();
                    showPasswordBtn.repaint();
                }

                else{ // Şifreyi göster
                    password.setText(employee.password);

                    password.revalidate();
                    password.repaint();

                    showPasswordBtn.setText("Şifreyi Gizle");

                    showPasswordBtn.revalidate();
                    showPasswordBtn.repaint();
                }
            }
        }
    }

}

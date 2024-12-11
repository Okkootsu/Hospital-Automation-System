import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Objects;

public class UserConfigsPanel extends JPanel implements IPanel{

    private Admin admin;

    UserConfigsPanel(JPanel mainCardPanel, CardLayout cardLayout, Admin admin) {
        this.admin = admin;
        initializePanel(mainCardPanel, cardLayout);
    }

    @Override
    public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
        this.setBackground(new Color(203, 220, 235));
        this.setPreferredSize(new Dimension(100, 100));
        this.setLayout(new BorderLayout());
        refreshContent(mainCardPanel, cardLayout);
    }

    @Override
    public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
        this.removeAll();

        UserAddPanel userAddPanel = new UserAddPanel(mainCardPanel, cardLayout);
        UserDelPanel userDelPanel = new UserDelPanel(mainCardPanel, cardLayout);

        mainCardPanel.add(userAddPanel, "Kullanıcı Ekle");
        mainCardPanel.add(userDelPanel, "Kullanıcı Sil");

        JPanel tempPanel = new JPanel();
        tempPanel.setPreferredSize(new Dimension(300, 325));
        tempPanel.setBackground(Color.lightGray);
        tempPanel.setLayout(new GridBagLayout());
        tempPanel.setOpaque(false); //şeffaf

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
        gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
        gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
        gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

        //üst boşluk
        gbc.gridx = 0;  gbc.gridy = 0; gbc.gridwidth = 1;
        tempPanel.add(new JLabel(""), gbc);

        gbc.gridx = 2;  gbc.gridy = 0; gbc.gridwidth = 1;
        tempPanel.add(new JLabel(""), gbc);
        //

        JButton userAddBtn = new JButton("Kullanıcı Ekle");
        userAddBtn.setFocusable(false);
        userAddBtn.addActionListener(e -> {
            // Geri dönmeden önce güncelle
            if (mainCardPanel.getComponent(0) instanceof UserAddPanel userAddPanel1) {
                userAddPanel1.refreshContent(mainCardPanel, cardLayout);
            }

            cardLayout.show(mainCardPanel, "Kullanıcı Ekle");
        });

        gbc.gridx = 1;  gbc.gridy = 1; gbc.gridwidth = 1;
        tempPanel.add(userAddBtn, gbc);


        JButton userDelBtn = new JButton("Kullanıcı Sil");
        userDelBtn.setFocusable(false);
        userDelBtn.addActionListener(e -> {
            // Geri dönmeden önce güncelle
            if (mainCardPanel.getComponent(0) instanceof UserDelPanel userDelPanel1) {
                userDelPanel1.refreshContent(mainCardPanel, cardLayout);
            }

            cardLayout.show(mainCardPanel, "Kullanıcı Sil");
        });

        gbc.gridx = 1;  gbc.gridy = 2; gbc.gridwidth = 1;
        tempPanel.add(userDelBtn, gbc);

        //Alt boşluk
        gbc.gridx = 0;  gbc.gridy = 3; gbc.gridwidth = 1;
        tempPanel.add(new JLabel(""), gbc);


        this.add(tempPanel, BorderLayout.NORTH);

        this.revalidate(); // Bileşenleri yeniden düzenle
        this.repaint();
    }

    private class UserAddPanel extends JPanel implements IPanel{

        UserAddPanel(JPanel mainCardPanel, CardLayout cardLayout) {
            initializePanel(mainCardPanel, cardLayout);
        }

        @Override
        public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
            this.setBackground(new Color(203, 220, 235));
            this.setPreferredSize(new Dimension(100, 100));
            this.setLayout(new BorderLayout());
            refreshContent(mainCardPanel, cardLayout);
        }

        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll();

            JPanel tempPanel = new JPanel();
            tempPanel.setPreferredSize(new Dimension(300, 375));
            tempPanel.setBackground(Color.lightGray);
            tempPanel.setLayout(new GridBagLayout());
            tempPanel.setOpaque(false); //şeffaf

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla


            //Üst boşluk
            gbc.gridx = 0;  gbc.gridy = 0; gbc.gridwidth = 1;
            tempPanel.add(new JLabel(""), gbc);

            gbc.gridx = 3;  gbc.gridy = 0; gbc.gridwidth = 1;
            tempPanel.add(new JLabel(""), gbc);
            //

            Font font = new Font("Times New Roman",Font.PLAIN,30);


            JLabel nameLabel = new JLabel("Ad Soyad:");
            nameLabel.setFont(font);

            gbc.gridx = 1;  gbc.gridy = 1; gbc.gridwidth = 1;
            tempPanel.add(nameLabel, gbc);


            JTextField nameTxtField = new JTextField();

            gbc.gridx = 2;  gbc.gridy = 1; gbc.gridwidth = 1;
            tempPanel.add(nameTxtField, gbc);


            JLabel tcLabel = new JLabel("TC :");
            tcLabel.setFont(font);

            gbc.gridx = 1;  gbc.gridy = 2; gbc.gridwidth = 1;
            tempPanel.add(tcLabel, gbc);


            JTextField tcTxtField = new JTextField();

            gbc.gridx = 2;  gbc.gridy = 2; gbc.gridwidth = 1;
            tempPanel.add(tcTxtField, gbc);


            JLabel passLabel = new JLabel("Şifre :");
            passLabel.setFont(font);

            gbc.gridx = 1;  gbc.gridy = 3; gbc.gridwidth = 1;
            tempPanel.add(passLabel, gbc);


            JTextField passTxtField = new JTextField();

            gbc.gridx = 2;  gbc.gridy = 3; gbc.gridwidth = 1;
            tempPanel.add(passTxtField, gbc);


            JLabel roleLabel = new JLabel("Meslek :");
            roleLabel.setFont(font);

            gbc.gridx = 1;  gbc.gridy = 4; gbc.gridwidth = 1;
            tempPanel.add(roleLabel, gbc);



            String[] roles = new String[] {"Doktor","Lab Asistanı","Admin"};

            JComboBox<String> roleCBox = new JComboBox<>(roles);

            gbc.gridx = 2;  gbc.gridy = 4; gbc.gridwidth = 1;
            tempPanel.add(roleCBox, gbc);


            JButton goBackBtn = new JButton("Geri Dön");
            goBackBtn.setFocusable(false);
            goBackBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof UserConfigsPanel userConfigsPanel) {
                    userConfigsPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Admin Center");
            });

            gbc.gridx = 1;  gbc.gridy = 5; gbc.gridwidth = 1;
            tempPanel.add(goBackBtn, gbc);


            JButton saveBtn = new JButton("Kaydet");
            saveBtn.setFocusable(false);
            saveBtn.addActionListener(e -> {
                try {
                    String name = Objects.requireNonNull(nameTxtField.getText());
                    String tc = Objects.requireNonNull(tcTxtField.getText());
                    String pass = Objects.requireNonNull(passTxtField.getText());
                    String role = Objects.requireNonNull(roleCBox.getSelectedItem()).toString();

                    Employee employee = new Employee();

                    employee.username = name;
                    employee.password = pass;
                    employee.tc = Long.parseLong(tc);


                    admin.addEmployee(employee, role);

                    JOptionPane.showMessageDialog(this,"Kullanıcı sisteme eklendi" ,
                            "İşlem Başarılı",JOptionPane.INFORMATION_MESSAGE);

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"Hata kodu: "+exception.getMessage() ,
                            "Bir Hata Oluştu!",JOptionPane.ERROR_MESSAGE);
                }

            });

            gbc.gridx = 2;  gbc.gridy = 5; gbc.gridwidth = 1;
            tempPanel.add(saveBtn, gbc);


            this.add(tempPanel, BorderLayout.NORTH);

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();
        }
    }

    private class UserDelPanel extends JPanel implements IPanel {

        UserDelPanel(JPanel mainCardPanel, CardLayout cardLayout) {
            initializePanel(mainCardPanel, cardLayout);
        }

        @Override
        public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
            this.setBackground(new Color(203, 220, 235));
            this.setPreferredSize(new Dimension(100, 100));
            this.setLayout(new BorderLayout());
            refreshContent(mainCardPanel, cardLayout);
        }

        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll();

            JPanel tempPanel = new JPanel();
            tempPanel.setOpaque(false);
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setPreferredSize(new Dimension(100,375));
            tempPanel.setBackground(Color.cyan);

            JPanel westPanel = new JPanel();
            westPanel.setPreferredSize(new Dimension(200,100));
            westPanel.setOpaque(false);

            JPanel eastPanel = new JPanel();
            eastPanel.setPreferredSize(new Dimension(200,100));
            eastPanel.setOpaque(false);

            tempPanel.add(westPanel, BorderLayout.WEST);
            tempPanel.add(eastPanel, BorderLayout.EAST);

            JPanel empList = new RoundedPanel(30,30,Color.BLACK,3);
            empList.setBackground(new Color(245, 240, 205));
            empList.setPreferredSize(new Dimension(100,100));
            empList.setLayout(new GridBagLayout());

            Font font = new Font("Times New Roman",Font.PLAIN,35);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            gbc.gridx = 0;
            gbc.gridy = 0;

            try {
                ResultSet resultSet = admin.getUsers();

                while (resultSet.next()) {

                    int userID = resultSet.getInt("id");

                    JLabel userLabel = new JLabel(resultSet.getString("fullName") + "          " +
                            resultSet.getString("role"));
                    userLabel.setFont(font);
                    userLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            userLabel.setBackground(new Color(255, 232, 147));
                            userLabel.setOpaque(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            userLabel.setBackground(new Color(245, 240, 205));
                            userLabel.setOpaque(true);
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {

                            int choice = JOptionPane.showOptionDialog(null,"Bu kullanıcıyı " +
                                            "silmek isrediğinize emin misiniz?",
                                    "Uyarı!",JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,null,null,0);

                            if(choice == 0){
                                admin.delUser(userID);
                                empList.remove(userLabel);

                                empList.revalidate();
                                empList.repaint();
                            }
                        }

                    });

                    empList.add(userLabel, gbc);

                    gbc.gridy++;
                }

            }catch (Exception exception){
                JOptionPane.showMessageDialog(null,"Hata kodu: "+exception.getMessage() ,
                        "Bir Hata Oluştu!",JOptionPane.ERROR_MESSAGE);
            }

            tempPanel.add(empList, BorderLayout.CENTER);

            this.add(tempPanel, BorderLayout.NORTH);

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();
        }

    }
}

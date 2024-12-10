import javax.swing.*;
import java.awt.*;

public class UserConfigsPanel extends JPanel implements IPanel{

    private final BaseUser employee;

    UserConfigsPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser employee) {
        this.employee = employee;
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

        mainCardPanel.add(userAddPanel, "Kullanıcı Ekle");

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

        gbc.gridx = 0;  gbc.gridy = 2; gbc.gridwidth = 1;
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
        userDelBtn.addActionListener(null); //////////

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
            tempPanel.setPreferredSize(new Dimension(300, 325));
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

            gbc.gridx = 1;  gbc.gridy = 0; gbc.gridwidth = 1;
            tempPanel.add(new JLabel(""), gbc);
            //

            gbc.gridx = 0;  gbc.gridy = 1; gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Ad Soyad:"), gbc);


            JTextField nameTxtField = new JTextField();

            gbc.gridx = 1;  gbc.gridy = 1; gbc.gridwidth = 1;
            tempPanel.add(nameTxtField, gbc);


            gbc.gridx = 0;  gbc.gridy = 2; gbc.gridwidth = 1;
            tempPanel.add(new JLabel("TC :"), gbc);


            JTextField tcTxtField = new JTextField();

            gbc.gridx = 1;  gbc.gridy = 2; gbc.gridwidth = 1;
            tempPanel.add(tcTxtField, gbc);


            gbc.gridx = 0;  gbc.gridy = 3; gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Şifre :"), gbc);


            JTextField passTxtField = new JTextField();

            gbc.gridx = 1;  gbc.gridy = 3; gbc.gridwidth = 1;
            tempPanel.add(passTxtField, gbc);


            gbc.gridx = 0;  gbc.gridy = 4; gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Meslek :"), gbc);


            JTextField roleTxtField = new JTextField();

            gbc.gridx = 1;  gbc.gridy = 4; gbc.gridwidth = 1;
            tempPanel.add(roleTxtField, gbc);


            JButton goBackBtn = new JButton("Geri Dön");
            goBackBtn.setFocusable(false);
            goBackBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof UserConfigsPanel userConfigsPanel) {
                    userConfigsPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Admin Center");
            });

            gbc.gridx = 0;  gbc.gridy = 5; gbc.gridwidth = 1;
            tempPanel.add(goBackBtn, gbc);


            JButton saveBtn = new JButton("Kaydet");
            saveBtn.setFocusable(false);
            saveBtn.addActionListener(null); ////////

            gbc.gridx = 1;  gbc.gridy = 5; gbc.gridwidth = 1;
            tempPanel.add(saveBtn, gbc);


            this.add(tempPanel, BorderLayout.NORTH);

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();
        }
    }
}

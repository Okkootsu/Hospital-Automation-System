import javax.swing.*;
import java.awt.*;

public class MyAccountPanel extends JPanel implements IPanel {

    private final Customer customer;

    MyAccountPanel(JPanel mainCardPanel, CardLayout cardLayout, Customer customer){
        this.customer = customer;
        initializePanel(mainCardPanel, cardLayout);
    }

    @Override
    public void initializePanel(JPanel mainCardPanel, CardLayout cardLayout) {
        this.setBackground(new Color(203, 220, 235));
        this.setPreferredSize(new Dimension(100, 100));
        refreshContent(mainCardPanel, cardLayout);
    }

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



        JLabel password = new JLabel();
        password.setText(customer.password);
        password.setFont(new Font("Times New Roman",Font.PLAIN,30));

        gbc.gridx = 1;  gbc.gridy = 3;  gbc.gridwidth = 2;
        accountPanel.add(password, gbc);



        JLabel idLabel = new JLabel("ID : ");
        idLabel.setFont(new Font("Times New Roman",Font.PLAIN,30));

        gbc.gridx = 0;  gbc.gridy = 4;  gbc.gridwidth = 1;
        accountPanel.add(idLabel, gbc);



        JLabel id = new JLabel();
        id.setText(String.valueOf(customer.id));
        id.setFont(new Font("Times New Roman",Font.PLAIN,30));

        gbc.gridx = 1;  gbc.gridy = 4;  gbc.gridwidth = 2;
        accountPanel.add(id, gbc);


        JButton delAccountBtn = new JButton("Hesabı Sil");
        delAccountBtn.setFocusable(false);

        gbc.gridx = 2;  gbc.gridy = 5;  gbc.gridwidth = 1;
        accountPanel.add(delAccountBtn, gbc);


        JButton updateAccountBtn = new JButton("Hesap Bilgilerini Güncelle");
        updateAccountBtn.setFocusable(false);

        gbc.gridx = 1;  gbc.gridy = 5;  gbc.gridwidth = 1;
        accountPanel.add(updateAccountBtn, gbc);


        JButton logOffBtn = new JButton("Çıkış Yap");
        logOffBtn.setFocusable(false);

        gbc.gridx = 0;  gbc.gridy = 5;  gbc.gridwidth = 1;
        accountPanel.add(logOffBtn, gbc);


        tempPanel.add(accountPanel);

        this.setLayout(new BorderLayout());
        this.add(tempPanel, BorderLayout.NORTH);

        this.revalidate(); // Bileşenleri yeniden düzenle
        this.repaint();    // Paneli yeniden boya
    }
}

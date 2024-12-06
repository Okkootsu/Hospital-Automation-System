import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MainMenuPanel {

    JFrame frame;
    MainPanel mainPanel;
    CreateAptPanel createAptPanel;
    Customer pointerCustomer;
    JPanel tempPanel;

    MainMenuPanel(Customer customer){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ana Sayfa");
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        pointerCustomer = customer;
        mainPanel = new MainPanel(pointerCustomer);

        frame.add(mainPanel);
    }

    private class MainPanel extends JPanel {

        MainCenterPanel mainCenterPanel;

        MainPanel(Customer customer){
            this.setLayout(new BorderLayout(10,10));

            //BorderLayout sınırları
            //Center ayrı class'da oluşturulsun?
            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();
//            JPanel centerContainer = new JPanel();

            header.setBackground(new Color(31, 80, 154));
            westContainer.setBackground(Color.pink);
            eastContainer.setBackground(Color.green);
            footer.setBackground(Color.black);
//            centerContainer.setBackground(new Color(203, 220, 235));

            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(100,100));
            eastContainer.setPreferredSize(new Dimension(100,100));
            footer.setPreferredSize(new Dimension(100,75));
//            centerContainer.setPreferredSize(new Dimension(100,100));

            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);
//            this.add(centerContainer, BorderLayout.CENTER);
            //-------

            //header
            header.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 15));

            Dimension buttonSize = new Dimension(125,45);

            JButton mainMenuBtn = new JButton("Ana Sayfa");
            mainMenuBtn.setFocusable(false);
            mainMenuBtn.setPreferredSize(buttonSize);
            header.add(mainMenuBtn);


            JButton testResultsBtn = new JButton("Test Sonuçları");
            testResultsBtn.setFocusable(false);
            testResultsBtn.setPreferredSize(buttonSize);
            header.add(testResultsBtn);


            JButton illnessesBtn = new JButton("Tanılar");
            illnessesBtn.setFocusable(false);
            illnessesBtn.setPreferredSize(buttonSize);
            header.add(illnessesBtn);
            //-----------------

            //Center

            CardLayout cardLayout = new CardLayout();
            JPanel mainCardPanel = new JPanel(cardLayout); //sayfa geçişleri için ana bağlantıyı tutar

            MainCenterPanel mainCenterPanel = new MainCenterPanel(mainCardPanel, cardLayout, customer);
            CreateAptPanel createAptPanel = new CreateAptPanel(mainCardPanel, cardLayout, customer);

            mainCardPanel.add(mainCenterPanel, "Main Center");
            mainCardPanel.add(createAptPanel, "Create Apt");

            cardLayout.show(mainCardPanel, "Main Center");

            this.add(mainCardPanel, BorderLayout.CENTER);

            }

        }

        private class MainCenterPanel extends JPanel {

            JButton createAptBtn;

            MainCenterPanel(JPanel mainCardPanel, CardLayout cardLayout, Customer customer){

                this.setBackground(new Color(203, 220, 235));
                this.setPreferredSize(new Dimension(100,100));

                if(appointmentExists(customer)){
                    tempPanel = new JPanel();
                    tempPanel.setPreferredSize(new Dimension(50,50));
                    tempPanel.setBackground(Color.cyan);
                    tempPanel.setLayout(new BorderLayout());
                    tempPanel.add(new JLabel("Randevunuz bulunmaktadır."),BorderLayout.CENTER);

                    this.setLayout(new BorderLayout());
                    this.add(tempPanel, BorderLayout.NORTH);
                }
                else {
                    tempPanel = new JPanel();
                    tempPanel.setPreferredSize(new Dimension(140,140));
                    tempPanel.setBackground(Color.cyan);
                    tempPanel.setLayout(new GridBagLayout());
                    tempPanel.setOpaque(false);

                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
                    gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
                    gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
                    gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

                    JLabel tempLabel = new JLabel("Randevunuz bulunmamaktadır");
                    tempLabel.setFont(new Font("Comic Sans",Font.PLAIN,30));

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 4;
                    tempPanel.add(tempLabel, gbc);

                    for (int i = 1; i <= 7; i++){
                        gbc.gridx = i;
                        gbc.gridy = 0;
                        gbc.gridwidth = 1;
                        tempPanel.add(new JLabel(""), gbc);
                    }


                    createAptBtn = new JButton("Randevu Oluştur");
                    createAptBtn.setFocusable(false);
                    createAptBtn.addActionListener(e -> cardLayout.show(mainCardPanel, "Create Apt"));


                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 1;
                    tempPanel.add(createAptBtn, gbc);


                    this.setLayout(new BorderLayout());
                    this.add(tempPanel, BorderLayout.NORTH);
            }
        }

        private static boolean appointmentExists(Customer customer){

            try {
                ResultSet resultSet = customer.getApt(customer);

                if(resultSet.next()){
                    return true;
                }

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Hata kodu: "+e.getMessage() ,
                        "Bir hata oluştu (appointmentExists)",JOptionPane.ERROR_MESSAGE);
            }

            return false;
        }

        private static void createCells(){

        }

    }

    private class CreateAptPanel extends JPanel {

        JButton goBackBtn;
        JButton saveBtn;

        CreateAptPanel(JPanel mainCardPanel, CardLayout cardLayout, Customer customer){

            this.setBackground(new Color(203, 220, 235));
            this.setPreferredSize(new Dimension(100,100));


            JPanel tempPanel = new JPanel();
            tempPanel.setPreferredSize(new Dimension(300,300));
            tempPanel.setBackground(Color.cyan);
            tempPanel.setLayout(new GridBagLayout());
            tempPanel.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla


            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Klinik :"), gbc);

            String[] klinik = new String[] {"Kalp","Beyin","Akciğer","Karaciğer","Nefroloji"};

            JComboBox clinicCBox = new JComboBox(klinik);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            tempPanel.add(clinicCBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Doktor :"), gbc);

            String[] doktor = new String[] {"Ahmet","Mehmet","Nazif"};

            JComboBox doctorCBox = new JComboBox(doktor);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            tempPanel.add(doctorCBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Tarih :"), gbc);

            //Tarih için panel(kısım/cell)
            JPanel dateTempPanel = new JPanel();
            dateTempPanel.setOpaque(false);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            tempPanel.add(dateTempPanel, gbc);

            dateTempPanel.setLayout(new GridLayout(1,3));
            dateTempPanel.setOpaque(false);

            String[] days = new String[30];
            for (int i = 1; i <= 30; i++){
                days[i-1] = String.valueOf(i);
            }

            String[] months = new String[12];
            for (int i = 1; i <= 12; i++){
                months[i-1] = String.valueOf(i);
            }

            String[] years = new String[] {"2024","2025","2026"};

            JComboBox dayCBox = new JComboBox(days);
            dateTempPanel.add(dayCBox);

            JComboBox monthCBox = new JComboBox(months);
            dateTempPanel.add(monthCBox);

            JComboBox yearCBox = new JComboBox(years);
            dateTempPanel.add(yearCBox);
            //------------------

            goBackBtn = new JButton("Geri Dön");
            goBackBtn.setFocusable(false);
            goBackBtn.addActionListener(e -> cardLayout.show(mainCardPanel, "Main Center"));

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            tempPanel.add(goBackBtn, gbc);

            saveBtn = new JButton("Kaydet");
            saveBtn.setFocusable(false);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            tempPanel.add(saveBtn, gbc);

            this.setLayout(new BorderLayout());
            this.add(tempPanel, BorderLayout.NORTH);
        }

    }
}

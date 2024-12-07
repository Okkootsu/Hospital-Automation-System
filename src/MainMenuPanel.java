import javax.swing.*;
import java.awt.*;

public class MainMenuPanel {

    JFrame frame;
    MainPanel mainPanel;
    Customer pointerCustomer;

    MainMenuPanel(Customer customer){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ana Sayfa");
        frame.setBackground(new Color(203, 220, 235));
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        pointerCustomer = customer;
        mainPanel = new MainPanel(pointerCustomer);

        frame.add(mainPanel);
    }

    private class MainPanel extends JPanel {

        CardLayout cardLayout;
        JPanel mainCardPanel;

        MainPanel(Customer customer){
            this.setLayout(new BorderLayout()); // marginler (10,10)

            //BorderLayout sınırları
            //Center ayrı class'da oluşturulsun?
            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();
//            JPanel centerContainer = new JPanel();

            header.setBackground(new Color(31, 80, 154));
            westContainer.setBackground(new Color(203, 220, 235));
            eastContainer.setBackground(new Color(203, 220, 235));
            footer.setBackground(new Color(203, 220, 235));
//            centerContainer.setBackground(new Color(203, 220, 235));


            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(150,100)); //100
            eastContainer.setPreferredSize(new Dimension(150,100)); //100
            footer.setPreferredSize(new Dimension(100,75));
//            centerContainer.setPreferredSize(new Dimension(100,100));

            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);
//            this.add(centerContainer, BorderLayout.CENTER);
            //-------

            //Center

            CardLayout cardLayout = new CardLayout();
            JPanel mainCardPanel = new JPanel(cardLayout); //sayfa geçişleri için ana bağlantıyı tutar

            AppointmentPanel.MainCenterPanel mainCenterPanel = new AppointmentPanel.MainCenterPanel(mainCardPanel, cardLayout, customer);
            AppointmentPanel.CreateAptPanel createAptPanel = new AppointmentPanel.CreateAptPanel(mainCardPanel, cardLayout, customer);

            TestResultsPanel testResultsPanel = new TestResultsPanel(mainCardPanel, cardLayout, customer);
            DiagnosesPanel diagnosesPanel = new DiagnosesPanel(mainCardPanel, cardLayout, customer);

            MyAccountPanel myAccountPanel = new MyAccountPanel(mainCardPanel, cardLayout, customer);

            mainCardPanel.add(mainCenterPanel, "Main Center");
            mainCardPanel.add(createAptPanel, "Create Apt");
            mainCardPanel.add(testResultsPanel, "Test Results");
            mainCardPanel.add(diagnosesPanel, "Diagnoses");
            mainCardPanel.add(myAccountPanel, "My Account");

            cardLayout.show(mainCardPanel, "Main Center");

            this.add(mainCardPanel, BorderLayout.CENTER);

            //header
            header.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 15));

            Dimension buttonSize = new Dimension(125,45);

            JButton mainMenuBtn = new JButton("Ana Sayfa");
            mainMenuBtn.setFocusable(false);
            mainMenuBtn.setPreferredSize(buttonSize);

            mainMenuBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof AppointmentPanel.MainCenterPanel centerPanel) {
                    centerPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Main Center");
            });

            header.add(mainMenuBtn);


            JButton testResultsBtn = new JButton("Test Sonuçları");
            testResultsBtn.setFocusable(false);
            testResultsBtn.setPreferredSize(buttonSize);

            testResultsBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof TestResultsPanel resultsPanel) {
                    resultsPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Test Results");
            });

            header.add(testResultsBtn);


            JButton illnessesBtn = new JButton("Tanılar");
            illnessesBtn.setFocusable(false);
            illnessesBtn.setPreferredSize(buttonSize);

            illnessesBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof DiagnosesPanel diagnoses) {
                    diagnoses.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Diagnoses");
            });

            header.add(illnessesBtn);

            //Boş kısımlar

            JPanel accountPanel = new JPanel();
            accountPanel.setPreferredSize(new Dimension(760,45));
            accountPanel.setBackground(Color.black);
            accountPanel.setOpaque(false);
            accountPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));

            //Hesap butonu
            JButton accountBtn = new JButton("Hesabım");
            accountBtn.setFocusable(false);
            accountBtn.setPreferredSize(new Dimension(90,45));

            accountBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof MyAccountPanel myAccPanel) {
                    myAccPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "My Account");
            });

            accountPanel.add(accountBtn);

            header.add(accountPanel);

            //-----------------
        }
    }
}

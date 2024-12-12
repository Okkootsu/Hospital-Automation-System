import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JFrame {

    //Singleton tasarım deseni

    private static MainMenuPanel instance;
    private BaseUser user;
    JPanel mainPanel;

    private MainMenuPanel(BaseUser user){
        this.user = user;
        initUI();
    }

    public static MainMenuPanel getInstance(BaseUser user) {
        if (instance == null) {
            instance = new MainMenuPanel(user);
        }
        return instance;
    }

    private void initUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Ana Sayfa");
        this.setBackground(new Color(203, 220, 235));
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        mainPanel = user.getPanel();

        this.add(mainPanel);
    }

    public static void resetInstance() {
        instance = null;
    }

    //Customer için MainPanel
    public static class MainPanel extends JPanel {

        CardLayout cardLayout;
        JPanel mainCardPanel;

        MainPanel(BaseUser customer){
            this.setLayout(new BorderLayout()); // marginler (10,10)

            //BorderLayout sınırları

            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();


            header.setBackground(new Color(31, 80, 154));
            westContainer.setBackground(new Color(203, 220, 235));
            eastContainer.setBackground(new Color(203, 220, 235));
            footer.setBackground(new Color(203, 220, 235));


            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(150,100)); //100
            eastContainer.setPreferredSize(new Dimension(150,100)); //100
            footer.setPreferredSize(new Dimension(100,75));


            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);

            //-------

            //Center

            CardLayout cardLayout = new CardLayout();
            JPanel mainCardPanel = new JPanel(cardLayout); //sayfa geçişleri için ana bağlantıyı tutar

            AppointmentPanel.MainCenterPanel mainCenterPanel = new AppointmentPanel.MainCenterPanel(mainCardPanel, cardLayout, customer);
            AppointmentPanel.CreateAptPanel createAptPanel = new AppointmentPanel.CreateAptPanel(mainCardPanel, cardLayout, customer);

            TestResultsPanel testResultsPanel = new TestResultsPanel(mainCardPanel, cardLayout, customer);
            DiagnosesPanel.CustomerPanel diagnosesPanel = new DiagnosesPanel.CustomerPanel(mainCardPanel, cardLayout, customer);

            MyAccountPanel.CustomerPanel myAccountPanel = new MyAccountPanel.CustomerPanel(mainCardPanel, cardLayout, customer);

            mainCardPanel.add(mainCenterPanel, "Main Center");
            mainCardPanel.add(createAptPanel, "Create Apt");
            mainCardPanel.add(testResultsPanel, "Test Results");
            mainCardPanel.add(diagnosesPanel, "Diagnoses");
            mainCardPanel.add(myAccountPanel, "My Account");

            cardLayout.show(mainCardPanel, "Main Center");

            this.add(mainCardPanel, BorderLayout.CENTER);

            //header
            header.setLayout(new GridLayout(1,2)); //LEADING 10,15

            Dimension buttonSize = new Dimension(125,45);

            JPanel leftPanel = new JPanel();
            leftPanel.setOpaque(false);
            leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,15));

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

            leftPanel.add(mainMenuBtn);


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

            leftPanel.add(testResultsBtn);


            JButton illnessesBtn = new JButton("Tanılar");
            illnessesBtn.setFocusable(false);
            illnessesBtn.setPreferredSize(buttonSize);

            illnessesBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof DiagnosesPanel.CustomerPanel diagnoses) {
                    diagnoses.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Diagnoses");
            });

            leftPanel.add(illnessesBtn);

            JPanel rightPanel = new JPanel();
            rightPanel.setOpaque(false);
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,15));

            //Hesap butonu
            JButton accountBtn = new JButton("Hesabım");
            accountBtn.setFocusable(false);
            accountBtn.setPreferredSize(new Dimension(90,45));

            accountBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof MyAccountPanel.CustomerPanel myAccPanel) {
                    myAccPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "My Account");
            });

            rightPanel.add(accountBtn);

            header.add(leftPanel);
            header.add(rightPanel);

            //-----------------
        }
    }

    public static class MainAdminPanel extends JPanel{

        MainAdminPanel(Admin admin){
            this.setLayout(new BorderLayout()); // marginler (10,10)

            //BorderLayout sınırları

            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();


            header.setBackground(new Color(31, 80, 154));
            westContainer.setBackground(new Color(203, 220, 235));
            eastContainer.setBackground(new Color(203, 220, 235));
            footer.setBackground(new Color(203, 220, 235));


            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(150,100)); //100
            eastContainer.setPreferredSize(new Dimension(150,100)); //100
            footer.setPreferredSize(new Dimension(100,75));


            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);

            //-------

            //Center

            CardLayout cardLayout = new CardLayout();
            JPanel mainCardPanel = new JPanel(cardLayout); //sayfa geçişleri için ana bağlantıyı tutar

            UserConfigsPanel mainAdminPanel = new UserConfigsPanel(mainCardPanel, cardLayout, admin);

            MyAccountPanel.EmployeePanel myAccountPanel = new MyAccountPanel.EmployeePanel(mainCardPanel, cardLayout, admin);

            mainCardPanel.add(mainAdminPanel, "Admin Center");
            mainCardPanel.add(myAccountPanel, "My Account");

            cardLayout.show(mainCardPanel, "Admin Center");

            this.add(mainCardPanel, BorderLayout.CENTER);

            //header
            header.setLayout(new GridLayout(1,2)); // FlowLayout.LEADING, 10, 15

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,15));
            leftPanel.setOpaque(false);

            Dimension buttonSize = new Dimension(125,45);

            JButton mainMenuBtn = new JButton("Ana Sayfa");
            mainMenuBtn.setFocusable(false);
            mainMenuBtn.setPreferredSize(buttonSize);

            mainMenuBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof UserConfigsPanel userConfigsPanel) {
                    userConfigsPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Admin Center");
            });

            leftPanel.add(mainMenuBtn);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,15));
            rightPanel.setOpaque(false);

            //Hesap butonu
            JButton accountBtn = new JButton("Hesabım");
            accountBtn.setFocusable(false);
            accountBtn.setPreferredSize(new Dimension(90,45));

            accountBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof MyAccountPanel.EmployeePanel myAccPanel) {
                    myAccPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "My Account");
            });

            rightPanel.add(accountBtn);

            header.add(leftPanel);
            header.add(rightPanel);

            //-----------------
        }
    } // Admin kısmının sonu

    public static class MainDoctorPanel extends JPanel{

        MainDoctorPanel (Doctor doctor) {
            this.setLayout(new BorderLayout()); // marginler (10,10)

            //BorderLayout sınırları

            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();


            header.setBackground(new Color(31, 80, 154));
            westContainer.setBackground(new Color(203, 220, 235));
            eastContainer.setBackground(new Color(203, 220, 235));
            footer.setBackground(new Color(203, 220, 235));


            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(150,100)); //100
            eastContainer.setPreferredSize(new Dimension(150,100)); //100
            footer.setPreferredSize(new Dimension(100,75));


            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);

            //-------

            //Center

            CardLayout cardLayout = new CardLayout();
            JPanel mainCardPanel = new JPanel(cardLayout); //sayfa geçişleri için ana bağlantıyı tutar

            AppointmentPanel.DoctorAptPanel mainDoctorPanel =
                    new AppointmentPanel.DoctorAptPanel(mainCardPanel, cardLayout, doctor);

            DiagnosesPanel.DoctorPanel diagnosesPanel =
                    new DiagnosesPanel.DoctorPanel(mainCardPanel, cardLayout, doctor);

            MyAccountPanel.EmployeePanel myAccountPanel =
                    new MyAccountPanel.EmployeePanel(mainCardPanel, cardLayout, doctor);


            mainCardPanel.add(mainDoctorPanel, "Doctor Center");
            mainCardPanel.add(diagnosesPanel, "Diagnoses");
            mainCardPanel.add(myAccountPanel, "My Account");

            cardLayout.show(mainCardPanel, "Doctor Center");

            this.add(mainCardPanel, BorderLayout.CENTER);

            //header
            header.setLayout(new GridLayout(1,2)); // FlowLayout.LEADING, 10, 15

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,15));
            leftPanel.setOpaque(false);

            Dimension buttonSize = new Dimension(125,45);

            //Header'ın solu

            JButton mainMenuBtn = new JButton("Ana Sayfa");
            mainMenuBtn.setFocusable(false);
            mainMenuBtn.setPreferredSize(buttonSize);

            mainMenuBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof AppointmentPanel.DoctorAptPanel doctorAptPanel) {
                    doctorAptPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Doctor Center");
            });

            leftPanel.add(mainMenuBtn);


            JButton diagnosesBtn = new JButton("Teşhisler");
            diagnosesBtn.setFocusable(false);
            diagnosesBtn.setPreferredSize(buttonSize);

            diagnosesBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof DiagnosesPanel.DoctorPanel doctorPanel) {
                    doctorPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Diagnoses");
            });

            leftPanel.add(diagnosesBtn);

            //Header'ın sağı

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,15));
            rightPanel.setOpaque(false);

            //Hesap butonu
            JButton accountBtn = new JButton("Hesabım");
            accountBtn.setFocusable(false);
            accountBtn.setPreferredSize(new Dimension(90,45));

            accountBtn.addActionListener(e -> {
                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof MyAccountPanel.EmployeePanel myAccPanel) {
                    myAccPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "My Account");
            });

            rightPanel.add(accountBtn);

            header.add(leftPanel);
            header.add(rightPanel);
        }
    }


}

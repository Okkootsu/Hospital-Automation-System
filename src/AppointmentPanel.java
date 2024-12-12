import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AppointmentPanel {

    public static class MainCenterPanel extends JPanel implements IPanel {

        private JPanel tempPanel;
        private final BaseUser customer;

        MainCenterPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser customer) {
            this.customer = customer; // Müşteri bilgisini sakla
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
            this.removeAll(); // Mevcut tüm bileşenleri kaldır

            if (appointmentExists(customer)) {

                tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(300, 325));
                tempPanel.setBackground(Color.lightGray);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.setOpaque(false);

                //Hücreleri oluşturma
                JPanel cellPanel = createCells(customer);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING)); // Butonu ortala
                buttonPanel.setOpaque(false);
                buttonPanel.setBackground(Color.DARK_GRAY);

                JButton createAptBtn = new JButton("Randevu Oluştur");
                createAptBtn.setFocusable(false);
                createAptBtn.setPreferredSize(new Dimension(150, 40));
                createAptBtn.addActionListener(e -> cardLayout.show(mainCardPanel, "Create Apt"));

                buttonPanel.add(createAptBtn);

                assert cellPanel != null;
                tempPanel.add(cellPanel, BorderLayout.CENTER);
                tempPanel.add(buttonPanel, BorderLayout.SOUTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);
            } else {
                tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(140, 140));
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new GridBagLayout());
                tempPanel.setOpaque(false);

                GridBagConstraints gbc = new GridBagConstraints();

                gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
                gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
                gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
                gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

                JLabel tempLabel = new JLabel("Randevunuz bulunmamaktadır");
                tempLabel.setFont(new Font("Comic Sans", Font.PLAIN, 30));

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 4;
                tempPanel.add(tempLabel, gbc);

                for (int i = 1; i <= 7; i++) {
                    gbc.gridx = i;
                    gbc.gridy = 0;
                    gbc.gridwidth = 1;
                    tempPanel.add(new JLabel(""), gbc);
                }


                JButton createAptBtn = new JButton("Randevu Oluştur");
                createAptBtn.setFocusable(false);
                createAptBtn.addActionListener(e -> cardLayout.show(mainCardPanel, "Create Apt"));


                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                tempPanel.add(createAptBtn, gbc);


                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);
            }

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        } // void refreshContent sonu

        private static boolean appointmentExists(BaseUser customer) {

            try {
                ResultSet resultSet = customer.getApt();

                if (resultSet.next()) {
                    return true;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hata kodu: " + e.getMessage(),
                        "Bir hata oluştu (appointmentExists)", JOptionPane.ERROR_MESSAGE);
            }

            return false;
        }

        private JPanel createCells(BaseUser customer) {
            ResultSet resultSet = customer.getApt();
            String clinic;
            String doctor;
            String date;

            int x = 0;
            int y = 0;
            int totalCells = 0;

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 300));
            panel.setBackground(Color.red);
            panel.setOpaque(false);
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla


            try {
                while (resultSet.next()) {
                    clinic = resultSet.getString("clinic");
                    doctor = resultSet.getString("doctor");
                    date = resultSet.getString("apt_date");

                    int aptID = resultSet.getInt("apt_id");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel cellPanel = new MainCenterPanel.CellPanel(clinic, doctor, date);

                    cellPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int choice = JOptionPane.showOptionDialog(null,
                                    "Bu randevuyu silmek istediğinize emin misiniz?",
                                    "Uyarı!",JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,null,null,0);

                            if(choice == 0){
                                customer.delApt(aptID);
                                panel.remove(cellPanel);

                                panel.revalidate();
                                panel.repaint();
                            }

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            cellPanel.setBackground(new Color(255, 116, 139));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            cellPanel.setBackground(new Color(177, 240, 247));
                        }
                    });

                    panel.add(cellPanel, gbc);

                    x++;
                    totalCells++;

                    // 3 sütundan sonra yeni satıra geç
                    if (x == 3) {
                        x = 0;
                        y++;
                    }
                }

                while (totalCells < 6) {
                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel temp = new JPanel();
                    temp.setOpaque(false);
                    panel.add(temp, gbc); // Boş hücre

                    x++;
                    totalCells++;

                    if (x == 3) {
                        x = 0;
                        y++;
                    }
                }

                return panel;

            } catch (Exception exception) {
                return null;
            }
        }

        private class CellPanel extends RoundedPanel {
            CellPanel(String clinic, String doctor, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(30, 30, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                JLabel label1 = new JLabel(date);
                JLabel label2 = new JLabel(clinic);
                JLabel label3 = new JLabel("Dr. " + doctor);

                Font font = new Font("Times New Roman", Font.PLAIN, 25);

                label1.setFont(font);
                label2.setFont(font);
                label3.setFont(font);

                label1.setAlignmentX(CENTER_ALIGNMENT);
                label2.setAlignmentX(CENTER_ALIGNMENT);
                label3.setAlignmentX(CENTER_ALIGNMENT);

                this.add(label1);
                this.add(label2);
                this.add(label3);
            }
        }
    } //MainCenterPanel sonu

    public static class CreateAptPanel extends JPanel {

        JButton goBackBtn;
        JButton saveBtn;

        private final String[] doctors = Objects.requireNonNull(getDoctors()).toArray(new String[0]);

        CreateAptPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser customer){

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

            String[] klinik = new String[] {"Dahiliye","Kardiyoloji","Nöroloji","Ortopedi","Nefroloji"};

            JComboBox<String> clinicCBox = new JComboBox<>(klinik);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            tempPanel.add(clinicCBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Doktor :"), gbc);

            JComboBox<String> doctorCBox = new JComboBox<>(doctors);

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

            JComboBox<String> dayCBox = new JComboBox<>(days);
            dateTempPanel.add(dayCBox);

            JComboBox<String> monthCBox = new JComboBox<>(months);
            dateTempPanel.add(monthCBox);

            JComboBox<String> yearCBox = new JComboBox<>(years);
            dateTempPanel.add(yearCBox);
            //------------------

            goBackBtn = new JButton("Geri Dön");
            goBackBtn.setFocusable(false);
            goBackBtn.addActionListener(e -> {

                // Geri dönmeden önce güncelle
                if (mainCardPanel.getComponent(0) instanceof MainCenterPanel mainCenterPanel) {
                    mainCenterPanel.refreshContent(mainCardPanel, cardLayout);
                }

                cardLayout.show(mainCardPanel, "Main Center");
            });

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            tempPanel.add(goBackBtn, gbc);

            saveBtn = new JButton("Kaydet");
            saveBtn.setFocusable(false);
            saveBtn.addActionListener(e -> {
                String clinic = Objects.requireNonNull(clinicCBox.getSelectedItem()).toString();
                String doctor = Objects.requireNonNull(doctorCBox.getSelectedItem()).toString();

                String day = Objects.requireNonNull(dayCBox.getSelectedItem()).toString();
                String month = Objects.requireNonNull(monthCBox.getSelectedItem()).toString();
                String year = Objects.requireNonNull(yearCBox.getSelectedItem()).toString();

                String date = day+"-"+month+"-"+year;

                customer.createApt(customer.id, clinic, doctor, date);

                JOptionPane.showMessageDialog(this,"Randevu oluşturuldu" ,
                        "İşlem Başarılı",JOptionPane.INFORMATION_MESSAGE);
            });

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            tempPanel.add(saveBtn, gbc);

            this.setLayout(new BorderLayout());
            this.add(tempPanel, BorderLayout.NORTH);
        }

        private static Set<String> getDoctors() {

            try {
                MysqlDBManager mysqlDBManager = new MysqlDBManager();

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                        mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

                Statement statement = connection.createStatement();

                String query = "SELECT fullName FROM employee " +
                        "WHERE role = 'Doktor' ";

                ResultSet resultSet = statement.executeQuery(query);

                Set<String> doctorNames = new HashSet<>();

                while (resultSet.next()) {

                    doctorNames.add(resultSet.getString("fullName"));

                }

                return doctorNames;

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                        "Bir Hata Oluştu(getDoctors)",JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

    } //CreateAptPanel sonu

    public static class DoctorAptPanel extends JPanel implements IPanel {

        private final BaseUser doctor;

        DoctorAptPanel (JPanel mainCardPanel, CardLayout cardLayout, BaseUser doctor) {
            this.doctor = doctor; // Müşteri bilgisini sakla
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
            this.removeAll(); // Mevcut tüm bileşenleri kaldır

            if( MainCenterPanel.appointmentExists(doctor) ){

                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(100,350));
                tempPanel.setOpaque(false); //Şeffaf
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new BorderLayout());

                JPanel cellPanel = createCells(doctor);

                assert cellPanel != null;
                tempPanel.add(cellPanel, BorderLayout.NORTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);

            }else {
                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(100,100));
                tempPanel.setOpaque(false); //Şeffaf
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new BorderLayout());

                JLabel tempLabel = new JLabel("Randevunuz bulunmamaktadır.");
                tempLabel.setFont(new Font("Times New Roman",Font.PLAIN,35));

                tempPanel.add(tempLabel, BorderLayout.CENTER);

                this.setLayout(new BorderLayout());
                this.add(tempLabel, BorderLayout.NORTH);
            }

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        }

        private JPanel createCells(BaseUser doctor) {
            ResultSet resultSet = doctor.getApt();
            String customerName;
            String date;

            int x = 0;
            int y = 0;
            int totalCells = 0;

            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 300));
            panel.setBackground(Color.red);
            panel.setOpaque(false);
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla


            try {

                MysqlDBManager mysqlDBManager = new MysqlDBManager();

                while (resultSet.next()) {

                    int customerID = resultSet.getInt("cid");
                    customerName = mysqlDBManager.getUsername("customer",customerID);
                    date = resultSet.getString("apt_date");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    panel.add(new DoctorAptPanel.CellPanel(customerName, date), gbc);

                    x++;
                    totalCells++;

                    // 3 sütundan sonra yeni satıra geç
                    if (x == 3) {
                        x = 0;
                        y++;
                    }
                }

                while (totalCells < 9) {
                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel temp = new JPanel();
                    temp.setOpaque(false);
                    panel.add(temp, gbc); // Boş hücre

                    x++;
                    totalCells++;

                    if (x == 3) {
                        x = 0;
                        y++;
                    }
                }

                return panel;

            } catch (Exception exception) {
                return null;
            }
        }

        private class CellPanel extends RoundedPanel {
            CellPanel(String customer, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(30, 30, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                JLabel label1 = new JLabel(date);
                JLabel label2 = new JLabel(customer);

                Font font = new Font("Times New Roman", Font.PLAIN, 25);

                label1.setFont(font);
                label2.setFont(font);

                label1.setAlignmentX(CENTER_ALIGNMENT);
                label2.setAlignmentX(CENTER_ALIGNMENT);


                this.add(label1);
                this.add(label2);
            }
        }
    }
}

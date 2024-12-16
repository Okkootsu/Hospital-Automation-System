import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

//Randevular için GUI paneli -> Doktor veya hasta erişebilir

public class AppointmentPanel {

    //Hasta için GUI paneli
    public static class MainCenterPanel extends JPanel implements IPanel {

        private JPanel tempPanel;
        private final BaseUser customer;

        //Kullanıcı bilgisini saklamak için constructor
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

        //Panel bilgilerini güncelle
        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll(); // Mevcut tüm bileşenleri kaldır

            // Randevu varsa bu kısmı göster
            if (appointmentExists(customer)) {

                tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(300, 325)); //325 height
                tempPanel.setBackground(Color.lightGray);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.setOpaque(false);

                //Hücreleri oluşturma
                JPanel cellPanel = createCells(customer);
                assert cellPanel != null;
                cellPanel.setPreferredSize(new Dimension(100,375));

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                buttonPanel.setOpaque(false);
                buttonPanel.setBackground(Color.DARK_GRAY);
                buttonPanel.setPreferredSize(new Dimension(100,150));

                JButton createAptBtn = new JButton("Randevu Oluştur");
                createAptBtn.setFocusable(false);
                createAptBtn.setPreferredSize(new Dimension(150, 40));
                createAptBtn.addActionListener(e -> cardLayout.show(mainCardPanel, "Create Apt"));

                buttonPanel.add(createAptBtn);

                JPanel southPanel = new JPanel();
                southPanel.setPreferredSize(new Dimension(100,45));
                southPanel.setBackground(Color.cyan);
                southPanel.setOpaque(false);


                tempPanel.add(cellPanel, BorderLayout.NORTH);
                tempPanel.add(buttonPanel, BorderLayout.CENTER);
                tempPanel.add(southPanel, BorderLayout.SOUTH);


                String message = "<html> <b>Bilgilendirme :</b>  <br> " +
                        "Randevuların üstüne tıklayarak randevularınızı iptal edebilirsiniz. </html>";

                JLabel infoMessage = new JLabel(message);
                infoMessage.setFont(new Font("Times New Roman",Font.PLAIN,25));


                JPanel infoPanel = new RoundedPanel(30,30,Color.BLACK,3);
                infoPanel.setBackground(new Color(255, 232, 147));
                infoPanel.setPreferredSize(new Dimension(300,150));

                infoMessage.setPreferredSize(new Dimension(280, 140)); // İçerik boyutuna uygun bir alan ayarla
                infoPanel.add(Box.createHorizontalGlue()); // Yanlardan boşluk ekle
                infoPanel.add(infoMessage);
                infoPanel.add(Box.createVerticalGlue()); // Yukarıdan ve aşağıdan boşluk ekle


                JPanel messagePanel = new JPanel();
                messagePanel.setBackground(Color.red);
                messagePanel.setOpaque(false);
                messagePanel.setPreferredSize(new Dimension(100,160));
                messagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

                messagePanel.add(infoPanel);


                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.CENTER);
                this.add(messagePanel, BorderLayout.SOUTH);
            } else {    //Randevu yoksa bu paneli göster
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
                //Butonun büyüklüğünü ayarlamak için boş hücreler oluşturuldu


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

        //Randevu var mı diye kontrol et -> user'ın ilgili metodunu çağır
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

        //Kullanıcının sahip olduğu tüm randevuların gösterileceği paneli oluştur
        private JPanel createCells(BaseUser customer) {
            ResultSet resultSet = customer.getApt(); //Kullanıcının randevu bilgilerini al
            String clinic;
            String doctor;
            String date;

            int x = 0;
            int y = 0;
            int totalCells = 0; //Tasarımı daha düzenli yapmak için 6'lı hücre tablosu kullanmak için her hücrenin sayısını tutuyoruz

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
                while (resultSet.next()) {  //Her bir randevu arasında geziniyoruz ve gerekli bilgileri çekiyoruz

                    int startIndex = resultSet.getString("doctor").indexOf('(');
                    int endIndex = resultSet.getString("doctor").indexOf(')');

                    doctor = resultSet.getString("doctor").substring(0,startIndex);
                    date = resultSet.getString("apt_date");

                    clinic = resultSet.getString("doctor").substring(startIndex+1,endIndex);

                    int aptID = resultSet.getInt("apt_id");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel cellPanel = new MainCenterPanel.CellPanel(clinic, doctor, date); //Tek randevu için hücreyi oluştur

                    cellPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {    //Tıklandığında randevuyu silme işlemi
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
                        public void mouseEntered(MouseEvent e) {    //Tasarımsal olarak hücreye fare getirilince rengi değişiyor
                            cellPanel.setBackground(new Color(255, 116, 139));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) { //Tasarımsal olarak hücreye fare getirilince rengi değişiyor
                            cellPanel.setBackground(new Color(177, 240, 247));
                        }
                    });

                    panel.add(cellPanel, gbc);

                    x++;
                    totalCells++;
                    //Tasarımsal düzenlemeler
                    // 3 sütundan sonra yeni satıra geç
                    if (x == 3) {
                        x = 0;
                        y++;
                    }
                }
                //6 randevudan az varsa geri kalan kısımları boşluk yaparak 6'lı GridBagLayout oluşturuyoruz
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

        //Her bir randevu için bir hücre/panel oluştur
        private class CellPanel extends RoundedPanel {
            CellPanel(String clinic, String doctor, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(30, 30, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Yazıların birbirine yakın olmasını sağla

                JLabel label1 = new JLabel(date);
                JLabel label2 = new JLabel(clinic);
                JLabel label3 = new JLabel("Dr. " + doctor);

                Font font = new Font("Times New Roman", Font.PLAIN, 25);

                label1.setFont(font);
                label2.setFont(font);
                label3.setFont(font);

                label1.setAlignmentX(CENTER_ALIGNMENT); //Yazıların birbirine yakın olmasını sağla
                label2.setAlignmentX(CENTER_ALIGNMENT);
                label3.setAlignmentX(CENTER_ALIGNMENT);

                this.add(label1);
                this.add(label2);
                this.add(label3);
            }
        }
    } //MainCenterPanel sonu

    //Randevu oluşturma sayfası
    public static class CreateAptPanel extends JPanel {

        JButton goBackBtn;
        JButton saveBtn;

        //Sistemde bulunan doktorlar
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
            tempPanel.add(new JLabel("Doktor :"), gbc);

            //Doktor seçme Combobox'ı
            JComboBox<String> doctorCBox = new JComboBox<>(doctors);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            tempPanel.add(doctorCBox, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            tempPanel.add(new JLabel("Tarih :"), gbc);

            //Tarih için panel(kısım/cell)
            JPanel dateTempPanel = new JPanel();
            dateTempPanel.setOpaque(false);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            tempPanel.add(dateTempPanel, gbc);

            dateTempPanel.setLayout(new GridLayout(1,3));
            dateTempPanel.setOpaque(false);


            JComboBox<Integer> dayCBox = new JComboBox<>();
            JComboBox<Integer> monthCBox = new JComboBox<>();
            JComboBox<Integer> yearCBox = new JComboBox<>();

            //Tarih kısmını ayarlamak için geçici tarihler oluşturuyoruz
            for (int year = 2024; year <= 2025; year++) {
                yearCBox.addItem(year);
            }

            for (int month = 1; month <= 12; month++) {
                monthCBox.addItem(month);
            }

            for (int day = 1; day <= 31; day++) {
                dayCBox.addItem(day);
            }

            monthCBox.addItemListener(e -> updateDays(dayCBox, monthCBox, yearCBox));
            yearCBox.addItemListener(e -> updateDays(dayCBox, monthCBox, yearCBox));

            dateTempPanel.add(dayCBox);
            dateTempPanel.add(monthCBox);
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
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            tempPanel.add(goBackBtn, gbc);

            //Seçilen bilgilere göre randevu oluştur
            saveBtn = new JButton("Kaydet");
            saveBtn.setFocusable(false);
            saveBtn.addActionListener(e -> {
                String doctor = Objects.requireNonNull(doctorCBox.getSelectedItem()).toString();

                String day = Objects.requireNonNull(dayCBox.getSelectedItem()).toString();
                String month = Objects.requireNonNull(monthCBox.getSelectedItem()).toString();
                String year = Objects.requireNonNull(yearCBox.getSelectedItem()).toString();

                String date = day+"/"+month+"/"+year;

                customer.createApt(customer.id, doctor, date);

                JOptionPane.showMessageDialog(this,"Randevu oluşturuldu" ,
                        "İşlem Başarılı",JOptionPane.INFORMATION_MESSAGE);
            });

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            tempPanel.add(saveBtn, gbc);

            this.setLayout(new BorderLayout());
            this.add(tempPanel, BorderLayout.NORTH);
        }

        //Tarihi uygun hale getir
        private static void updateDays(JComboBox<Integer> dayCBox, JComboBox<Integer> monthCBox, JComboBox<Integer> yearCBox) {

            int selectedMonth = (int) monthCBox.getSelectedItem();
            int selectedYear = (int) yearCBox.getSelectedItem();


            int daysInMonth = LocalDate.of(selectedYear, selectedMonth, 1).lengthOfMonth();


            dayCBox.removeAllItems();
            for (int day = 1; day <= daysInMonth; day++) {
                dayCBox.addItem(day);
            }
        }

        //Sistemdeki doktorları al
        private static Set<String> getDoctors() {

            try {
                MysqlDBManager mysqlDBManager = new MysqlDBManager();

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(mysqlDBManager.getSqlUrl(),
                        mysqlDBManager.getSqlUsername(),mysqlDBManager.getSqlPassword());

                Statement statement = connection.createStatement();

                String query = "SELECT * FROM clinics " ;


                ResultSet resultSet = statement.executeQuery(query);

                Set<String> doctorNames = new HashSet<>();

                while (resultSet.next()) {

                    doctorNames.add(resultSet.getString("fullName") + " " +
                          "(" + resultSet.getString("clinic") + ")" );

                }

                return doctorNames;

            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Hata Kodu:"+e.getMessage(),
                        "Bir Hata Oluştu(getDoctors)",JOptionPane.ERROR_MESSAGE);
            }

            return null;
        }

    } //CreateAptPanel sonu

    //RAndevu sayfası için Doktor paneli
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

        //Sayfayı güncelle
        @Override
        public void refreshContent(JPanel mainCardPanel, CardLayout cardLayout) {
            this.removeAll(); // Mevcut tüm bileşenleri kaldır

            //Doktorun sahip olduğu randevuları kontrol et, varsa bu paneli göster
            if( MainCenterPanel.appointmentExists(doctor) ){

                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(100,350));
                tempPanel.setOpaque(false); //Şeffaf
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new BorderLayout());

                JPanel cellPanel = createCells(doctor);

                //Bilgilendirme paneli ve onun yerleştirme ayarlamaları
                String message = "<html> <b>Bilgilendirme :</b>  <br> " +
                        "Randevuların üstüne tıklayarak hastalarınıza teşhis koyabilirsiniz. </html>";

                JLabel infoMessage = new JLabel(message);
                infoMessage.setFont(new Font("Times New Roman",Font.PLAIN,25));


                JPanel infoPanel = new RoundedPanel(30,30,Color.BLACK,3);
                infoPanel.setBackground(new Color(255, 232, 147));
                infoPanel.setPreferredSize(new Dimension(300,150));


                infoMessage.setPreferredSize(new Dimension(280, 140)); // İçerik boyutuna uygun bir alan ayarla
                infoPanel.add(Box.createHorizontalGlue()); // Yanlardan boşluk ekle
                infoPanel.add(infoMessage);
                infoPanel.add(Box.createVerticalGlue()); // Yukarıdan ve aşağıdan boşluk ekle


                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
                messagePanel.setBackground(Color.red);
                messagePanel.setPreferredSize(new Dimension(100,160));
                messagePanel.setOpaque(false);

                messagePanel.add(infoPanel);
                //

                //null olma ihtimaline karşı önlem
                assert cellPanel != null;
                tempPanel.add(cellPanel, BorderLayout.CENTER);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.CENTER);
                this.add(messagePanel, BorderLayout.SOUTH);

            }else { //Randevu yoksa bu paneli göster
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

        //Doktorun her randevusunu gösterecek paneli oluştur
        private JPanel createCells(BaseUser doctor) {
            ResultSet resultSet = doctor.getApt();
            String customerName;

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

                while (resultSet.next()) {  //Her randevu bilgisi arasında dolaş, gerekli bilgileri al

                    int customerID = resultSet.getInt("cid");
                    customerName = mysqlDBManager.getUsername("customer",customerID);
                    String date = resultSet.getString("apt_date");
                    int aptID = resultSet.getInt("apt_id");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel cellPanel = new DoctorAptPanel.CellPanel(customerName, date);

                    cellPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) { //Teşhis koyma eventi
                            String diagnose = JOptionPane.showInputDialog("Teşhis: ");

                            if (diagnose != null) {
                                doctor.createDiagnose(aptID, customerID, diagnose, date);

                                JOptionPane.showMessageDialog(null,"Teşhis Oluşturuldu" ,
                                        "Bilgilendirme",JOptionPane.INFORMATION_MESSAGE);

                                doctor.delApt(aptID);


                                panel.remove(cellPanel);

                                panel.revalidate();
                                panel.repaint();
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) { //Panelin üstüne gelince arkaplanı kırmızı yap
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

                //Tasarımı düzeltmek için boş hücreler ekle
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

        //Tek bir randevu hücresi oluştur
        private class CellPanel extends RoundedPanel {
            CellPanel(String customer, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(30, 30, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //Yazılar birbirine yakın olsun

                JLabel label1 = new JLabel(date);
                JLabel label2 = new JLabel(customer);

                Font font = new Font("Times New Roman", Font.PLAIN, 25);

                label1.setFont(font);
                label2.setFont(font);

                label1.setAlignmentX(CENTER_ALIGNMENT); //Yazılar birbirine yakın olsun
                label2.setAlignmentX(CENTER_ALIGNMENT);


                this.add(label1);
                this.add(label2);
            }
        }
    }
}

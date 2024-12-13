import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DiagnosesPanel {

    public static class CustomerPanel extends JPanel implements IPanel {

        private final BaseUser customer;

        CustomerPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser customer){
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

            if ( DiagnosesPanel.DoctorPanel.diagnosesExists(customer) ) {

                JPanel tempPanel = new JPanel();
                tempPanel.setBackground(Color.BLACK);
                tempPanel.setOpaque(false); //Şeffaf
                tempPanel.setPreferredSize(new Dimension(100,350));
                tempPanel.setLayout(new BorderLayout());

                JPanel cells = createCells(customer);

                assert cells != null;
                tempPanel.add(cells, BorderLayout.NORTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);

            }else {
                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(140, 140));
                tempPanel.setBackground(Color.lightGray);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.setOpaque(false);

                JLabel label = new JLabel("Herhangi bir teşhis bulunmamaktadır.");
                label.setFont(new Font("Times New Roman",Font.PLAIN,30));

                tempPanel.add(label, BorderLayout.NORTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);
            }

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        }

        private JPanel createCells(BaseUser customer) {
            ResultSet resultSet = customer.getDiagnoses();
            String doctorName;
            String diagnose;
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
                    int doctorID = resultSet.getInt("pid");
                    doctorName = mysqlDBManager.getUsername("employee", doctorID);
                    diagnose = resultSet.getString("diagnose");
                    date = resultSet.getString("apt_date");

//                    int aptID = resultSet.getInt("apt_id");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel cellPanel = new CellPanel(doctorName, diagnose, date);

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
            CellPanel(String doctor, String diagnose, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(10, 10, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
//                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                JLabel label1 = new JLabel("Dr. "+doctor);
                JLabel label2 = new JLabel(diagnose);
                JLabel label3 = new JLabel(date);

                Font font = new Font("Times New Roman", Font.PLAIN, 25);

                label1.setFont(font);
                label2.setFont(font);
                label3.setFont(font);

                label1.setAlignmentX(CENTER_ALIGNMENT);
                label2.setAlignmentX(CENTER_ALIGNMENT);
                label3.setAlignmentX(CENTER_ALIGNMENT);

                this.add(label3);
                this.add(label1);
                this.add(label2);
            }
        }
    } //CustomerPanel Sonu

    public static class DoctorPanel extends JPanel implements IPanel {

        private final BaseUser doctor;

        DoctorPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser doctor) {
            this.doctor = doctor;
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

            if( diagnosesExists(doctor) ){

                JPanel tempPanel = new JPanel();
                tempPanel.setBackground(Color.BLACK);
                tempPanel.setOpaque(false); //Şeffaf
                tempPanel.setPreferredSize(new Dimension(100,350));
                tempPanel.setLayout(new BorderLayout());

                JPanel cells = createCells(doctor);

                assert cells != null;
                tempPanel.add(cells, BorderLayout.NORTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);
            }else {
                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(140, 140));
                tempPanel.setBackground(Color.lightGray);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.setOpaque(false);

                JLabel label = new JLabel("Herhangi bir teşhis bulunmamaktadır.");
                label.setFont(new Font("Times New Roman",Font.PLAIN,30));

                tempPanel.add(label, BorderLayout.NORTH);

                this.setLayout(new BorderLayout());
                this.add(tempPanel, BorderLayout.NORTH);
            }

            this.revalidate(); // Bileşenleri yeniden düzenle
            this.repaint();    // Paneli yeniden boya
        }

        private static boolean diagnosesExists(BaseUser doctor) {
            try {
                ResultSet resultSet = doctor.getDiagnoses();

                if (resultSet.next()) {
                    return true;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hata kodu: " + e.getMessage(),
                        "Bir hata oluştu (diagnosesExists)", JOptionPane.ERROR_MESSAGE);
            }

            return false;
        }

        private JPanel createCells(BaseUser doctor) {
            ResultSet resultSet = doctor.getDiagnoses();
            String customerName;
            String diagnose;
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
                    diagnose = resultSet.getString("diagnose");
                    date = resultSet.getString("apt_date");

//                    int aptID = resultSet.getInt("apt_id");

                    gbc.gridx = x;
                    gbc.gridy = y;

                    JPanel cellPanel = new DoctorPanel.CellPanel(customerName, diagnose, date);

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
            CellPanel(String customer, String diagnose, String date) {

                // RoundedPanel yapılandırıcısını çağır
                super(10, 10, Color.BLACK, 3);
                // 30x30 yuvarlatma, siyah kenar, 3px kalınlık


                this.setBackground(new Color(177, 240, 247));
//                this.setPreferredSize(new Dimension(15, 15));
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

                JLabel label1 = new JLabel(customer);
                JLabel label2 = new JLabel(diagnose);
                JLabel label3 = new JLabel("("+date+")");

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
    }
}

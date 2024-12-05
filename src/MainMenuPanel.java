import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MainMenuPanel {

    JFrame frame;
    JPanel mainPanel;

    MainMenuPanel(Customer customer){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ana Sayfa");
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel = new MainPanel(customer);

        frame.add(mainPanel);
    }

    private class MainPanel extends JPanel{
        MainPanel(Customer customer){
            this.setLayout(new BorderLayout(10,10));

            //BorderLayout sınırları
            JPanel header = new JPanel();
            JPanel westContainer = new JPanel();
            JPanel eastContainer = new JPanel();
            JPanel footer = new JPanel();
            JPanel centerContainer = new JPanel();

            header.setBackground(Color.blue);
            westContainer.setBackground(Color.pink);
            eastContainer.setBackground(Color.green);
            footer.setBackground(Color.black);
            centerContainer.setBackground(Color.darkGray);

            header.setPreferredSize(new Dimension(100,75));
            westContainer.setPreferredSize(new Dimension(100,100));
            eastContainer.setPreferredSize(new Dimension(100,100));
            footer.setPreferredSize(new Dimension(100,75));
            centerContainer.setPreferredSize(new Dimension(100,100));

            this.add(header, BorderLayout.NORTH);
            this.add(eastContainer, BorderLayout.EAST);
            this.add(westContainer, BorderLayout.WEST);
            this.add(footer, BorderLayout.SOUTH);
            this.add(centerContainer, BorderLayout.CENTER);
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
            if(appointmentExists(customer)){
                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(50,50));
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.add(new JLabel("Randevunuz bulunmaktadır."),BorderLayout.CENTER);

                centerContainer.setLayout(new BorderLayout());
                centerContainer.add(tempPanel, BorderLayout.NORTH);
            }
            else {
                JPanel tempPanel = new JPanel();
                tempPanel.setPreferredSize(new Dimension(50,50));
                tempPanel.setBackground(Color.cyan);
                tempPanel.setLayout(new BorderLayout());
                tempPanel.add(new JLabel("Randevunuz bulunmamaktadır."),BorderLayout.CENTER);

                centerContainer.setLayout(new BorderLayout());
                centerContainer.add(tempPanel, BorderLayout.NORTH);
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
}

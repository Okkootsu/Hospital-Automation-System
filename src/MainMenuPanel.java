import javax.swing.*;
import java.awt.*;

public class MainMenuPanel {

    JFrame frame;
    JPanel mainPanel;

    MainMenuPanel(BaseUser user){
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ana Sayfa");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel = new MainPanel();

        frame.add(mainPanel);
    }

    private class MainPanel extends JPanel{
        MainPanel(){
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.weightx = 1.0; // Yatayda boş alan paylaşımı
            gbc.weighty = 1.0; // Dikeyde boş alan paylaşımı
            gbc.fill = GridBagConstraints.BOTH; // Hem yatayda hem dikeyde genişle
            gbc.insets = new Insets(10, 10, 10, 10); // Boşlukları sıfırla

            //Üst boşluk
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth =1;
            this.add(new JPanel(), gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridwidth =1;
            this.add(new JPanel(), gbc);

            JLabel test = new JLabel("TEST");

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth =1;
            this.add(test, gbc);

            //Alt boşluk
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth =1;
            this.add(new JPanel(), gbc);
        }
    }
}

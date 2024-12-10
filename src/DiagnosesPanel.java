import javax.swing.*;
import java.awt.*;

public class DiagnosesPanel extends JPanel implements IPanel {

    private final BaseUser customer;

    DiagnosesPanel(JPanel mainCardPanel, CardLayout cardLayout, BaseUser customer){
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
        tempPanel.setPreferredSize(new Dimension(140, 140));
        tempPanel.setBackground(Color.lightGray);
        tempPanel.setLayout(new BorderLayout());
        tempPanel.setOpaque(false);

        JLabel label = new JLabel("Herhangi bir teşhis bulunmamaktadır.");
        label.setFont(new Font("Times New Roman",Font.PLAIN,30));

        tempPanel.add(label, BorderLayout.NORTH);

        this.setLayout(new BorderLayout());
        this.add(tempPanel, BorderLayout.NORTH);

        this.revalidate(); // Bileşenleri yeniden düzenle
        this.repaint();    // Paneli yeniden boya
    }
}

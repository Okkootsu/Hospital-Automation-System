import javax.swing.*;
import java.awt.*;

public interface IPanel {

    void initializePanel(JPanel mainCardPanel, CardLayout cardLayout);

    void refreshContent(JPanel mainCardPanel, CardLayout cardLayout);

}

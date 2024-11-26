import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel implements ActionListener {

    JFrame frame;
    JButton hastaButon;
    JButton personelButon;

    LoginPanel () {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hoş Geldiniz!");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        hastaButon = new JButton();
        personelButon = new JButton();

        hastaButon.setText("Hasta Giriş");
        hastaButon.setBounds(170,115,150,50);
        hastaButon.setFocusable(false);
        hastaButon.addActionListener(this);

        personelButon.setText("Personel Giriş");
        personelButon.setBounds(170,175,150,50);
        personelButon.setFocusable(false);
        personelButon.addActionListener(this);

        frame.add(hastaButon);
        frame.add(personelButon);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hastaButon){
            frame.dispose();
            HastaPanel hastaPanel = new HastaPanel();
        }

        if(e.getSource() == personelButon){
            frame.dispose();
            PersonelPanel personelPanel = new PersonelPanel();
        }
    }
}

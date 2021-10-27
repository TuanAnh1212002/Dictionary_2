import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
  JPanel titlePanel;
  JLabel dicLabel;
  JLabel subLabel;
  JLabel label;

  TitlePanel(){
    titlePanel = new JPanel();
    dicLabel = new JLabel();
    subLabel = new JLabel();
    label = new JLabel();

    dicLabel.setBounds(0,0,35,200);
    dicLabel.setText("Dictionary");
    dicLabel.setFont(new Font("Georgia" ,Font.BOLD, 50));
    dicLabel.setBounds(5,0,300,60);
    subLabel.setText("English-Vietnamese");
    subLabel.setBounds(280,30,200,30);
    label.setText("K-T-A");
    label.setFont(new Font("Georgia" ,Font.BOLD, 50));
    label.setBounds(720,40,200,60);

    this.add(dicLabel);
    this.add(subLabel);
    this.add(label);
    this.setBackground(new Color(0x9999CC));
    this.setPreferredSize(new Dimension(900,100));
    this.setLayout(null);
  }

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Footer extends JPanel {
  JButton addButton ;
  JButton quitButton ;

  Footer(JList<String> engList, List<String> meanList,DictionaryCommandLine dictionaryCommandLine , LookUpDisplay lookUpDisplay) {
    addButton = new JButton("Thêm từ");
    quitButton = new JButton("Thoát");

    addButton.setBounds(70,5,120,40);
    addButton.setFocusable(false);
    addButton.setFont(new Font("Dialog", Font.BOLD, 20));
    addButton.setForeground(new Color(0x708090));
    addButton.setBackground(new Color(0xCDC9A5));
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton) {
          new AddWord(engList, meanList,dictionaryCommandLine, lookUpDisplay);
          lookUpDisplay.setVisible(false);
        }
      }
    });

    quitButton.setBounds(710,5,120,40);
    quitButton.setFocusable(false);
    quitButton.setFont(new Font("Dialog", Font.BOLD, 20));
    quitButton.setForeground(new Color(0xE6E6FA));
    quitButton.setBackground(new Color(0x8B8989));
    quitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == quitButton){
          System.exit(0);
        }
      }
    });

    //--
    JLabel imgBackground = new JLabel(new ImageIcon("background.jpg"));
    imgBackground.setBounds(0,0,900,50);
    //--

    this.setBackground(new Color(0xE8E8E8));
    this.setPreferredSize(new Dimension(900,50));
    this.setLayout(null);
    this.add(addButton);
    this.add(quitButton);
    this.add(imgBackground);
  }


}

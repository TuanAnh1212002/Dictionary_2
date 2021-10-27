import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AddWord extends JFrame {
  JLabel wordLabel;
  JButton confirmButton;
  JButton cancelButton;
  JLabel explainLabel;
  JTextField wordTexField ;
  JEditorPane explainPane;
  AddWord(JList<String> engList, List<String> meanList ,DictionaryCommandLine dictionaryCommandLine, LookUpDisplay lookUpDisplay) {
    wordLabel = new JLabel();
    confirmButton = new JButton("Chấp nhận");
    explainLabel = new JLabel();
    wordTexField = new JTextField();
    explainPane = new JEditorPane();
    cancelButton = new JButton("Thoát");

    wordLabel.setText("Nhập từ: ");
    wordLabel.setBounds(10,50,90,25);
    wordLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    wordTexField.setBounds(120,35,350,60);
    wordTexField.setFont(new Font("Dialog", Font.PLAIN, 18));

    explainLabel.setText("Định nghĩa: ");
    explainLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    explainLabel.setBounds(5,140,110,25);
    //explainPane.setBounds(120,130,430,280);
    explainPane.setFont(new Font("Dialog", Font.PLAIN, 18));
    JScrollPane scrollPane = new JScrollPane(explainPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setBounds(120,130,430,280);

    confirmButton.setBounds(45,435,120,50);
    confirmButton.setFocusable(false);
    confirmButton.setFont(new Font("Dialog", Font.BOLD, 15));
    cancelButton.setBounds(485,435,120,50);
    cancelButton.setFocusable(false);
    cancelButton.setFont(new Font("Dialog", Font.BOLD, 15));

    confirmButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmButton) {
              if(!wordTexField.getText().equals("") && !explainPane.getText().equals("")){
                Word word = new Word(wordTexField.getText(), explainPane.getText());
                dictionaryCommandLine.getDictionaryManagement().dictionary.setList(word);

                DefaultListModel<String> defaultEngList = new DefaultListModel<>();
                DictionaryManagement dictionaryManagement = dictionaryCommandLine.getDictionaryManagement();
                //dictionaryManagement.selectAll();
                meanList.clear();
                for(int i =0 ; i<dictionaryManagement.dictionary.getSize();++i){
                  defaultEngList.addElement(dictionaryManagement.dictionary.getList(i).getWord_target());
                  meanList.add(dictionaryManagement.dictionary.getList(i).getWord_explain());
                }
                engList.setModel(defaultEngList);

                dictionaryCommandLine.getDictionaryManagement().insert(wordTexField.getText(), explainPane.getText());

                wordTexField.setText("");
                explainPane.setText("");
              }

            }
          }
        });

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelButton){
          lookUpDisplay.setVisible(true);
          dispose();
        }
      }
    });

    //--
    JLabel imgBackground = new JLabel(new ImageIcon("background.jpg"));
    imgBackground.setBounds(0,0,650,550);

    //--


    this.setSize(650,550);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.add(wordLabel);
    this.add(wordTexField);
//    this.add(explainPane);
    this.add(scrollPane);
    this.add(explainLabel);
    this.add(confirmButton);
    this.add(cancelButton);
    this.add(imgBackground);
    this.setTitle("Thêm từ");
    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    this.setVisible(true);
  }

}

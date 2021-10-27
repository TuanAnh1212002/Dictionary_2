import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainPanel extends JPanel implements KeyListener {
  JPanel wordPanel;
  JPanel explainPanel;
  JTextField typeTextField;
  JScrollPane scrollEngWordPane;
  JEditorPane explainEditorPane;
  JLabel choiceWordLabel;
  JButton removeButton;
  JButton soundButton;
  JTextField choiceTextField;

  MainPanel(JList engList, List<String> meanList, DictionaryCommandLine dictionaryCommandLine) {
    wordPanel = new JPanel();
    explainEditorPane = new JEditorPane();
    typeTextField = new JTextField();
    scrollEngWordPane = new JScrollPane();
    explainPanel = new JPanel();
    choiceWordLabel = new JLabel();
    removeButton = new JButton("Xóa");
    soundButton = new JButton();
    choiceTextField = new JTextField();

    typeTextField.setBounds(5,5,300,40);
    typeTextField.setFont(new Font("Georgia",Font.PLAIN,16));

    typeTextField.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

      }

      @Override
      public void keyPressed(KeyEvent e) {

      }

      @Override
      public void keyReleased(KeyEvent e) {
        List<Word> searchedList = new ArrayList<>();
        DefaultListModel<String> searchedDefaultList = new DefaultListModel<>();
        searchedList = dictionaryCommandLine.dictionarySearcher(typeTextField.getText());
        System.out.println(typeTextField.getText());
        meanList.clear();
        for(int i=0;i<searchedList.size();++i){
          searchedDefaultList.addElement(searchedList.get(i).getWord_target());
          meanList.add(searchedList.get(i).getWord_explain());
        }

        engList.setModel(searchedDefaultList);
      }
    });



    scrollEngWordPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollEngWordPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollEngWordPane.setBounds(5,47,300,365);
    removeButton.setBounds(310,365,120,40);
    removeButton.setFocusable(false);
    removeButton.setFont(new Font("Dialog", Font.BOLD, 20));
    removeButton.setForeground(new Color(0xF5F5F5));
    removeButton.setBackground(new Color(0x003366));

    //--
    JLabel imgBackground = new JLabel(new ImageIcon("background.jpg"));
    imgBackground.setBounds(0,0,500,500);

    //--

    updateEngList(engList);
    wordPanel.setLayout(null);
    wordPanel.add(typeTextField);
    wordPanel.add(scrollEngWordPane);
    wordPanel.add(removeButton);
    wordPanel.add(imgBackground);

    engList.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
          int index = engList.getSelectedIndex();
          explainEditorPane.setContentType("text/html");
          explainEditorPane.setText((String) meanList.get(index));
          typeTextField.setText(String.valueOf(engList.getSelectedValue()));
          choiceTextField.setText(String.valueOf(engList.getSelectedValue()));

        }
      }
    });

    /*
    removeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int index = engList.getSelectedIndex();
        dictionaryCommandLine.getDictionaryManagement().remove(
            String.valueOf(engList.getSelectedValue()));
        List<Word> searchedList = new ArrayList<>();
        DefaultListModel<String> searchedDefaultList = new DefaultListModel<>();
        searchedList = dictionaryCommandLine.dictionarySearcher(typeTextField.getText());
        //System.out.println(typeTextField.getText());
        meanList.clear();
        for(int i=0;i<searchedList.size();++i){
          searchedDefaultList.addElement(searchedList.get(i).getWord_target());
          meanList.add(searchedList.get(i).getWord_explain());
        }

        engList.setModel(searchedDefaultList);
        explainEditorPane.setText("");
        choiceTextField.setText("");
      }
    });

     */

    //----
    removeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeButton) {
          //int index = engList.getSelectedIndex();
          if( dictionaryCommandLine.getDictionaryManagement().remove(
              typeTextField.getText())) {
            dictionaryCommandLine.getDictionaryManagement().delete(
                typeTextField.getText());
            DefaultListModel<String> defaultEngList = new DefaultListModel<>();
            DictionaryManagement dictionaryManagement = dictionaryCommandLine.getDictionaryManagement();
            meanList.clear();
            for(int i =0 ; i<dictionaryManagement.dictionary.getSize();++i){
              defaultEngList.addElement(dictionaryManagement.dictionary.getList(i).getWord_target());
              meanList.add(dictionaryManagement.dictionary.getList(i).getWord_explain());
            }
            engList.setModel(defaultEngList);
          }

        }

      }
    });


    //--

    soundButton.setPreferredSize(new Dimension(40,40));
    ImageIcon soundIcon = new ImageIcon("sound.png");
    soundButton.setIcon(soundIcon);
    soundButton.setFocusable(false);
    soundButton.setBorderPainted(false);

    choiceTextField.setPreferredSize(new Dimension(230,40));
    choiceTextField.setEditable(false);
    choiceTextField.setBackground(new Color(0xEEEEEE));
    choiceTextField.setBorder(null);
    choiceTextField.setFont(new Font( "Georgia",Font.BOLD, 25));

    choiceWordLabel.setBounds(5,0,300,60);
    choiceWordLabel.setLayout(new FlowLayout());
    choiceWordLabel.add(soundButton);
    choiceWordLabel.add(choiceTextField);

    JScrollPane scrollExplainPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //explainEditorPane.setBounds(5,47,430,365);
    explainEditorPane.setSize(430,635);
    explainEditorPane.setEditable(false);
    explainEditorPane.setFont(new Font("Dialog",Font.PLAIN,16));
    scrollExplainPane.setViewportView(explainEditorPane);
    scrollExplainPane.setBounds(5,47,430,365);

    explainPanel.setLayout(null);
    explainPanel.add(choiceWordLabel);
    explainPanel.add(scrollExplainPane);


    //--



    //--
    this.setPreferredSize(new Dimension(900,450));
    this.setLayout(new GridLayout(1,2));
    this.add(wordPanel);
    this.add(explainPanel);

  }

  public void updateEngList(JList engList){
    scrollEngWordPane.setViewportView(engList);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}

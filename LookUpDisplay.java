import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class LookUpDisplay extends JFrame  {

    TitlePanel titlePanel;
    MainPanel mainPanel;
    Footer footer;
    DefaultListModel<String> defaultEngList;
    DefaultListModel<String> defaultMeanList;
    JList<String> engList;
    DictionaryCommandLine dictionaryCommandLine;
    List<String> meanList;

    LookUpDisplay() throws IOException {
        titlePanel = new TitlePanel();
        dictionaryCommandLine = new DictionaryCommandLine();
        defaultMeanList = new DefaultListModel<>();
        defaultEngList= new DefaultListModel<>();
        engList = new JList<>();
        meanList = new ArrayList<>();

        dictionaryCommandLine.dictionaryAdvanced(-1);

        DictionaryManagement dictionaryManagement = dictionaryCommandLine.getDictionaryManagement();
        //dictionaryManagement.selectAll();
        for(int i =0 ; i<dictionaryManagement.dictionary.getSize();++i){
            defaultEngList.addElement(dictionaryManagement.dictionary.getList(i).getWord_target());
            meanList.add(dictionaryManagement.dictionary.getList(i).getWord_explain());
        }
        engList.setModel(defaultEngList);
        engList.setFont(new Font("Georgia",Font.PLAIN,16));


        mainPanel = new MainPanel(engList, meanList, dictionaryCommandLine);
        footer = new Footer(engList,meanList,dictionaryCommandLine, this);

        //---

        //---


        this.setLayout(new BorderLayout());

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setResizable(false);
        this.setTitle("Dictionary");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}

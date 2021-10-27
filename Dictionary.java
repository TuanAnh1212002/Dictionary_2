import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> list;

    Dictionary(){
        list=new ArrayList<Word>();
    }

    List<Word> getAllList(){
        return list;
    }

    void setList(Word word){
        list.add(word);
    }
    void setList(Word word, int index){
        list.add(index,word);
    }
    Word getList(int index){
        return list.get(index);
    }
    int getSize(){
        return list.size();
    }

    void remove(int index){
        list.remove(index);
    }

    //---

    //---
}

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dictionary;
    final String nameFile="dictionary.txt";
    DictionaryManagement(){
        dictionary= new Dictionary();
    }

    void insertFromCommnandLine(){
        Scanner sc=new Scanner(System.in);
        System.out.print("type quantity of word: ");
        int n=sc.nextInt();
        String engWord;
        String meaningWord;
        for(int i=0;i<n;++i){
            engWord=sc.next();
            sc.nextLine();
            meaningWord=sc.nextLine();
            Word word=new Word(engWord, meaningWord);
            dictionary.setList(word);
        }
    }

    /*
    void insertFromFile() throws IOException {
        String  url="newdic.txt";
        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String engWord="";
        String meaningWord="";
        int check=0;
        try {
            String line= reader.readLine();
            while(line!=null){
                if(line.contains("@")==true){
                    Word word=new Word(engWord, meaningWord);
                    dictionary.setList(word);
                    engWord="";
                    meaningWord="";
                    String[] testArray = line.split(" /");
                    engWord=testArray[0];
                    engWord=engWord.replace("@","");
                    meaningWord+="/"+testArray[1];

                }
                else{
                    meaningWord+=line+"\n";
                    //System.out.println(meaningWord);
                }
                //System.out.println(line);
                line= reader.readLine();
            }

        } finally {
            try {
                reader.close();
                // file.close();
            } catch (IOException ex) {
//                Logger.getLogger(ReadFileWithBufferedReader.class.getName())
//                        .log(Level.SEVERE, null, ex);
            }
        }
    }

     */
    //----
    private Connection connect() {
    // SQLite connection string
    String url = "jdbc:sqlite:C:\\Users\\COMPUTER'TUANANH\\Downloads/dict_hh.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll(){
        String sql = "SELECT * FROM av";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            String engWord="";
            String meaningWord="";
            // loop through the result set
            while (rs.next()) {
//                System.out.println(rs.getString("word") +  "\t" +
//                        rs.getString("description")) ;
                engWord=rs.getString("word");

                meaningWord= rs.getString("html");
                Word word=new Word(engWord, meaningWord);
                dictionary.setList(word);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void insert(String newWord, String explain) {
        String sql = "INSERT INTO av(word, html) VALUES(?,?);";

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newWord);
            pstmt.setString(2, explain);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    //------

    void dictionaryLookUp(String engWord){
        for(int i=0;i< dictionary.getSize();++i){
            if(dictionary.getList(i).getWord_target().equals(engWord)){
                System.out.println(engWord+" "+dictionary.getList(i).getWord_explain());
                return;
            }
        }
/*
        try {
            FileReader reader=new FileReader(nameFile);
            BufferedReader br=new BufferedReader(reader);
            int i=br.read();
            String s="";
            while(i!=-1){
                while(i!=' ' && i!=-1){
                    s+=(char)i;
                    i=br.read();
                }
                if(s.equals(engWord)){
                    String meaningWord=br.readLine();
                    System.out.println(engWord+" "+meaningWord);
                    return;
                }
                br.readLine();
                s="";
                i=br.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        System.out.println("Not find!!");
    }

    boolean remove(String engWord){
        if(engWord.equals("")) return false;
        for(int i=0;i<dictionary.getSize();++i){
            if(dictionary.getList(i).getWord_target().equals(engWord)){
                dictionary.remove(i);
                return true;
            }
        }
        return false;
    }



    //---
    public void delete(String deteleWord){
        Connection conn = this.connect();
        PreparedStatement ps = null;
        try{
            String sql = "delete from av where word = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deteleWord);
            ps.execute();
            System.out.println("Success");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //---

    void change(String changedWord){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<dictionary.getSize();++i){
            if(dictionary.getList(i).getWord_target().equals(changedWord)){
                System.out.print("Change english word: ");
                String engWord=sc.next();
                System.out.print("Change meaning word: ");
                String meaningWord=sc.nextLine();
                meaningWord=sc.nextLine();
                Word word=new Word(engWord,meaningWord);
                dictionary.remove(i);
                dictionary.setList(word,i);
                return;
            }
        }
    }

    void dictionaryExportToFile(){
        try {
            FileWriter writer=new FileWriter("dictionary.txt");
            for(int i=0;i<dictionary.getSize();++i){
                writer.write(dictionary.getList(i).getWord_target()+" "+dictionary.getList(i).getWord_explain()+'\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
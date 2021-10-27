public class Word {
    private String word_target;
    private String word_explain;

    Word(){
        word_explain="";
        word_target="";
    }
    Word(String word_target, String word_explain){
        this.word_target=word_target;
        this.word_explain=word_explain;
    }

    void setWord_target(String word_target) {
        this.word_target=word_target;
    }
    String getWord_target(){
        return word_target;
    }
    void setWord_explain(String word_explain){
        this.word_explain=word_explain;
    }
    String getWord_explain(){
        return word_explain;
    }

    boolean equalAPart(String word){
        if(word_target.length()< word.length()) return false;
        for(int i=0;i<word.length();++i){
            if(word_target.charAt(i)!=word.charAt(i)) return false;
        }
        return true;
    }
}

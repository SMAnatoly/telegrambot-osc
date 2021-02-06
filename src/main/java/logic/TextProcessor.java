package logic;

public class TextProcessor {
    private Integer wordsQuantity;
    private String text;
    private String[] words;
    private Integer theLongestWord;
    private Object[] OSCValues;

    public Object[] getOSCValues(){
        return OSCValues;
    }
    public Integer getTheLongestWord() {
        return theLongestWord;
    }

    public Integer getWordsQuantity(){
        return wordsQuantity;
    }

    public void setText(String t){
        this.text = t;
        WordsQtt();
        theLongestWord();
        AssignParams();
    }

    private void WordsQtt(){
       words = text.split("\\s");
       wordsQuantity = words.length;
    }

    private void theLongestWord(){
        theLongestWord = words[0].length();
        for(int i = 1; i < words.length; i++){
            theLongestWord = (words[i].length() > theLongestWord)? words[i].length() : theLongestWord;
        }
    }

    private void AssignParams(){
        OSCValues = new Object[2];
        OSCValues[0] = (float) wordsQuantity;
        OSCValues[1] = (float) theLongestWord;
    }
}

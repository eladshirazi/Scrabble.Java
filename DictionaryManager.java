package test;

import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String, Dictionary> bookMap;
    private String[] bookList;
    private static DictionaryManager singleDM=null;


    public DictionaryManager() {
        this.bookMap=new HashMap<>();
    }

    public static DictionaryManager get(){
        if (singleDM==null)
            singleDM=new DictionaryManager();
        return singleDM;
    }

    public boolean query(String...args){
        boolean Found=false;
        String word=args[args.length-1];
        for (int i = 0; i < (args.length - 1); i++)
        {
            String Book=args[i];
            if(!bookMap.containsKey(Book))
                bookMap.put(Book,new Dictionary(Book));
        }
        for(Dictionary dic : bookMap.values())
            if (dic.query(word))
                Found = true;

        return Found;
    }

    public boolean challenge(String...args){
        boolean Found=false;
        String word=args[args.length-1];
        for (int i = 0; i < (args.length - 1); i++)
        {
            String Book=args[i];
            if(!bookMap.containsKey(Book))
                bookMap.put(Book,new Dictionary(Book));
        }
        for(Dictionary dic : bookMap.values())
            if (dic.challenge(word))
                Found = true;

        return Found;
    }

    public int getSize(){
        return bookMap.size();
    }






}

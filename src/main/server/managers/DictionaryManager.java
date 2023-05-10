package test;


import java.util.HashMap;

public class DictionaryManager {
    private static DictionaryManager dictionaryManager = null;
    HashMap<String,Dictionary> library;

    private DictionaryManager() {
        this.library = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (dictionaryManager == null){
            dictionaryManager = new DictionaryManager();
        }
        return dictionaryManager;
    }

    public boolean query(String...args) {
        String searchWord = args[args.length-1];
        for (int i = 0 ; i < args.length - 1 ; i ++) {
            if (!library.containsKey(args[i])){
                library.put(args[i],new Dictionary(args[i]));
            }
            if(library.get(args[i]).query(searchWord)){
                return true;
            }
        }
        return false;
    }

    public boolean challenge(String...args) {
        String searchWord = args[args.length-1];
        for (int i = 0 ; i < args.length - 1 ; i ++) {
            if (!library.containsKey(args[i])){
                library.put(args[i],new Dictionary(args[i]));
            }
            if(library.get(args[i]).challenge(searchWord)){
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return library.size();
    }
}

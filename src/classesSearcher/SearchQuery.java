package classesSearcher;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created on 27.08.17.
 */
public class SearchQuery implements Runnable {
    private String searchParam;
    private String[] result;

    public SearchQuery(String searchParam){
        this.searchParam = searchParam;
    }
    public String[] getResult(){
        return this.result;
    }
    @Override
    public void run() {
//        long startTime = System.currentTimeMillis();
        int count = 0;

        TreeSet<ClassComparator> choosenFiles = new TreeSet<>();
        ArrayList<String> files = new ArrayList<>();

        for(Map.Entry<String, Long> e : Utils.getFilesStorage().entrySet()){
            if(e.getKey().startsWith(searchParam))
                choosenFiles.add(new ClassComparator(e.getKey(), e.getValue()));
        }

        if(choosenFiles.size() > 12)
            this.result = new String[12];
        else
            this.result = new String[files.size()];

        for(ClassComparator col : choosenFiles){
            if(count < result.length)
                files.add(col.getClassName());
            else break;
            count++;
        }

        for(int i = 0; i < result.length; i++){
            this.result[i] = files.get(i);
        }

//        long totalTime = System.currentTimeMillis() - startTime;
//        System.out.println("Поиск занял: " + totalTime + " мс");

    }
}


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
//        System.out.println("Старт поиска");
//        long startTime = System.currentTimeMillis();

        TreeSet<ClassComparator> choosenFiles = new TreeSet<>();
        ArrayList<String> files = new ArrayList<>();

        for(Map.Entry<String, Long> e : Utils.getFilesStorage().entrySet()){
            if(e.getKey().startsWith(searchParam))
                choosenFiles.add(new ClassComparator(e.getKey(), e.getValue()));
        }

        for(ClassComparator col : choosenFiles){
            files.add(col.getClassName());
        }

        if(files.size() > 12)
            this.result = new String[12];
        else
            this.result = new String[files.size()];

        for(int i = 0; i < result.length; i++){
            this.result[i] = files.get(i);
        }
        Utils.tmpThreadCounter++;
//        long totalTime = System.currentTimeMillis() - startTime;
//        System.out.println("Поиск занял: " + totalTime + " мс");

    }
}


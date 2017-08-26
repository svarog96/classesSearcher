package classesSearcher;


import java.util.*;

/**
 * Created by glebus on 24.08.17.
 * реализует интерфейс ISearcher
 *
 */
public class Searcher implements ISearcher {

    static {
        long startTime = System.currentTimeMillis();
        DataIndexing.doDataIndexing();
        long time = System.currentTimeMillis() - startTime;
        System.out.println("Инициализация заняла: " + time + " ms");
    }

    @Override
    public void refresh (String[] classNames, long[] modificationDates) {
        for(int i = 0; i < classNames.length; i++) {
            if(classNames[i].endsWith(Utils.getFileExtension()))
                Utils.setFileStorage(classNames[i], modificationDates[i]);
            else
                System.out.println("неизвестный файл " + classNames[i] + " позиция " + i);
        }
    }

    @Override
    public String[] guess(String start) {
        TreeSet<ClassComparator> choosenFiles = new TreeSet<>();
        List<String> files = new ArrayList<>();
        String[] result;

        for(Map.Entry<String, Long> e : Utils.getFilesStorage().entrySet()){
            if(e.getKey().startsWith(start))
               choosenFiles.add(new ClassComparator(e.getKey(), e.getValue()));
        }

        for(ClassComparator col : choosenFiles){
            files.add(col.getClassName());
        }

        if(files.size() > 12)
            result = new String[12];
        else
            result = new String[files.size()];

        for(int i = 0; i < result.length; i++){
            result[i] = files.get(i);
        }

        return result;
    }
}

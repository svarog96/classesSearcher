package classesSearcher;

import java.io.File;
import java.util.*;

/**
 * Created by glebus on 24.08.17.
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
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < classNames.length; i++) {
            if(classNames[i].endsWith(Utils.getFileExtension()))
                Utils.setFileStorage(classNames[i], modificationDates[i]);
            else
                System.out.println("неизвестный файл " + classNames[i] + " позиция " + i);
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Обновление заняло: " + totalTime + " мс");
    }

    @Override
    public String[] guess(String start) {

        System.out.println("Старт поиска");
        long startTime = System.currentTimeMillis();

        TreeSet<ClassComparator> choosenFiles = new TreeSet<>();
        List<String> files = new ArrayList<>();
        String[] result;

        long cutoff1 = System.currentTimeMillis() - startTime;
        System.out.println("Прошло: " + cutoff1 + " - создали map, arrayLisr, String[]");

        for(Map.Entry<String, Long> e : Utils.getFilesStorage().entrySet()){
            if(e.getKey().startsWith(start))
               choosenFiles.add(new ClassComparator(e.getKey(), e.getValue()));
        }

        long cutoff2 = System.currentTimeMillis() - startTime;
        System.out.println("Прошло: " + cutoff2 + " - заполнили HashMap значениями");

        for(ClassComparator col : choosenFiles){
            files.add(col.getClassName());
        }

        long cutoff3 = System.currentTimeMillis() - startTime;
        System.out.println("Прошло: " + cutoff3 + " - заполнили ArrayList");

        if(files.size() > 12)
            result = new String[12];
        else
            result = new String[files.size()];

        for(int i = 0; i < result.length; i++){
            result[i] = files.get(i);
        }
        long cutoff4 = System.currentTimeMillis() - startTime;
        System.out.println("Прошло: " + cutoff4 + " - заполнили String[]");

//        for(Map.Entry<File, Long> e : choosenFiles.entrySet()){
//            if(e.getKey().getName().startsWith(start))
//                files.add(e.getKey());
//
//        }



//        Collections.sort(files, Utils.MODIFIED_DATE_FILE_COMPARATOR);





//        for(int i = 0; i < result.length; i++) {
//            result[i] = files.get(i).getName();
//        }
//
//        long cutoff5 = System.currentTimeMillis() - startTime;
//        System.out.println("Прошло: " + cutoff5 + " - Заполнили String[]");


//        TreeSet<ClassComparator> chosenClasses = new TreeSet<>();
//        ArrayList<String> classesList = new ArrayList<>();

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Поиск занял: " + totalTime + " мс");
        return result;
    }
}

package classesSearcher;
import java.util.Arrays;

/**
 * Created on 25.08.17
 */
public class Main {
    public static void main(String[] args) {
        String[] names = new String[100_000];
        long[] times = new long[100_000];

        Searcher searcher = new Searcher();
            for(int i = 0; i < 100_000; i++) {
                times[i] = System.currentTimeMillis() + ((long) (Math.random() * 10000) + 1);
                if(i%2 == 0)
                    names[i] = "Test_1_" + i + ".java";
                 else
                    names[i] = "Best_1_" + i + ".java";
            }
        searcher.refresh(names, times);

        System.out.println("Найдено " + Utils.getFilesStorage().size() + " '" + Utils.getFileExtension() + "' " + " файлов");
        System.out.println(Arrays.toString(searcher.guess("Test_1_1")));


    }

}

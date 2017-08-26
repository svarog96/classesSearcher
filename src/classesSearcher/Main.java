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
                if(i%2 == 0)
                    names[i] = "Test" + i + ".java";
                else
                    names[i] = "Best" + i + ".java";

                times[i] = System.currentTimeMillis() + ((int)Math.random() * 100 + 1);
            }

        searcher.refresh(names, times);
        System.out.println("Найдено " + Utils.getFilesStorage().size() + " java файлов");

        String[] result = searcher.guess("B");
        System.out.println(Arrays.toString(result));

//        for(int j = 0; j < 5; j++){
//            String name = "Test" + j;
//            System.out.println(Arrays.toString(searcher.guess(name)));
//        }

    }

}

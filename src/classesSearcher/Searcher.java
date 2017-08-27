package classesSearcher;
/**
 * Created by glebus on 24.08.17.
 * реализует интерфейс ISearcher
 *  @method refresh() - update data in indexing collection in the Utils class;
 *  @method gues() - get some part of String of the class name wich you whant find and return the Array[] of class names in which contains 12 results
 *
 */
public class Searcher implements ISearcher {

    static {
        long startTime = System.currentTimeMillis();
        DataIndexing.doDataIndexing();
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Индексация заняла: " + totalTime + " мс");
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
    public String[] guess(String start){

        SearchQuery query = new SearchQuery(start);
        Thread t1 = new Thread(query);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return query.getResult();
    }
}

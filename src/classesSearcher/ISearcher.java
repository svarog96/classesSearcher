package classesSearcher;

/**
 * Created by glebus on 24.08.17.
 */
public interface ISearcher {
    public void refresh(String[] classNames, long[] modificationDates);
    public String[] guess(String start);
}

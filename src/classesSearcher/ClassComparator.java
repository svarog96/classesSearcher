package classesSearcher;

/**
 * Created on 26.08.17.
 */
public class ClassComparator implements Comparable {
    private String className;
    private Long modificationDate;

    public ClassComparator(String className, Long modificationDate){
        this.className = className;
        this.modificationDate = modificationDate;
    }

    @Override
    public int compareTo(Object o) {
        ClassComparator entry = (ClassComparator) o;
        int result = modificationDate.compareTo(entry.modificationDate);
        if(result != 0)
            return result*(-1);
        else
            return result = className.compareTo(entry.className);

    }

    public String getClassName(){
        return this.className;
    }

    public long getModificationDate(){
        return this.modificationDate;
    }


}


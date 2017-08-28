package classesSearcher;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created on 26.08.17.
 * contains the main parameters of search
 * @Param FILE_EXTENSION - searching file extension
 * @Param ROOT_PROJECT_DIRECTORY - root directory searching start
 * @Param FILES_STORAGE - collection in wich stored findin files
 */
public abstract class Utils {

    private static String FILE_EXTENSION = ".java";
    private static Path ROOT_PROJECT_DIRECTORY = Paths.get(new File("src").getAbsolutePath()).getParent();
    private static Map<String, Long> FILES_STORAGE =  new HashMap<>();

    public static volatile int  tmpThreadCounter = 0;

    public static void setFileStorage(String fileName, Long modificationDate) {FILES_STORAGE.put(fileName, modificationDate);}
    public static String getFileExtension(){return FILE_EXTENSION;}
    public static Map<String, Long> getFilesStorage(){
        return FILES_STORAGE;
    }
    public static Path getRootProjectDirectory() {
        return ROOT_PROJECT_DIRECTORY;
    }
}

package classesSearcher;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 26.08.17.
 * содержит основные параметры поиска
 * @Param FILE_EXTENSION - расширение искомых файлов
 * @Param ROOT_PROJECT_DIRECTORY - корневая директория с которой начинается поиск
 * @Param FILES_STORAGE - коллекция в которой хранятся найденные файлы и время их модификации
 */
public abstract class Utils {
    private static String FILE_EXTENSION = ".java";
    private static Path ROOT_PROJECT_DIRECTORY = Paths.get(new File("src").getAbsolutePath()).getParent();
    private static Map<String, Long> FILES_STORAGE =  new HashMap<>();

    static final Comparator<File> MODIFIED_DATE_FILE_COMPARATOR = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            if (o1.lastModified() > o2.lastModified()) {
                return -1;
            } else {
                if (o1.lastModified() == o2.lastModified()) {
                    return o1.getAbsolutePath().compareToIgnoreCase(o2.getAbsolutePath());

                }
            }
            return 1;
        }
    };

    public static void setFileStorage(String fileName, Long modificationDate) {
        FILES_STORAGE.put(fileName, modificationDate);
    }

    public static String getFileExtension(){
        return FILE_EXTENSION;
    }
    public static Map<String, Long> getFilesStorage(){
        return FILES_STORAGE;
    }
    public static Path getRootProjectDirectory() {
        return ROOT_PROJECT_DIRECTORY;
    }


}

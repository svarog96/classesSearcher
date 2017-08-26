package classesSearcher;

import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by glebus on 25.08.17.
 */
public abstract class DataIndexing {
    public static void doDataIndexing(){
        try {
            Files.walkFileTree(Utils.getRootProjectDirectory(), new ProjectDirectoryVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

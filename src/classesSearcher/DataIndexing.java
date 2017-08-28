package classesSearcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by glebus on 25.08.17.
 * start indexing data before start searching
 */
abstract class DataIndexing {
    static void doDataIndexing(){
        for(Iterator<Map.Entry<String, Long>> it = Utils.getFilesStorage().entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Long> entry = it.next();
                it.remove();
        }

        try {
            Files.walkFileTree(Utils.getRootProjectDirectory(), new ProjectDirectoryVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class ProjectDirectoryVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(dir.endsWith("classesSearcher"))
                return FileVisitResult.SKIP_SUBTREE;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            if(path.toString().endsWith(Utils.getFileExtension())){
                Utils.setFileStorage(String.valueOf(path.toFile().getName()), attrs.lastModifiedTime().toMillis());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}

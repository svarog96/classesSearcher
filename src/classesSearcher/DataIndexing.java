package classesSearcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by glebus on 25.08.17.
 * start indexing data before start searching
 */
abstract class DataIndexing {
    static void doDataIndexing(){
        try {
            Files.walkFileTree(Utils.getRootProjectDirectory(), new ProjectDirectoryVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static class ProjectDirectoryVisitor extends SimpleFileVisitor<Path> {

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

package classesSearcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * Created by glebus on 25.08.17.
 * visit all directories in this project and save all files with .java extension in the collection
 * ignored the package in which locate
 */
public class ProjectDirectoryVisitor extends SimpleFileVisitor<Path> {
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

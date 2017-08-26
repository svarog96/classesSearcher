package classesSearcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * Created by glebus on 25.08.17.
 */
public class ProjectDirectoryVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(dir.endsWith("classesSearcher"))
            return FileVisitResult.SKIP_SUBTREE;
        return FileVisitResult.CONTINUE;
    }

    //    private DataIndex dataIndex = DataIndex.getInstance();
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if(path.toString().endsWith(Utils.getFileExtension())){
            Utils.setFileStorage(String.valueOf(path.toFile().getName()), attrs.lastModifiedTime().toMillis());
        }
        return FileVisitResult.CONTINUE;
    }
}

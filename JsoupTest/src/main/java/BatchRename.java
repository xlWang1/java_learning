
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BatchRename {

    public static void main(String[] args) throws IOException {
        // 指定要处理的目录
        File directory = new File("C:\\Users\\Administrator\\Desktop\\转码视频\\4K_折返跑_3.7m");

        // 检查目录是否存在并且是一个目录
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory path");
            return;
        }

        // 使用try-with-resources来自动关闭Stream
        try (Stream<Path> paths = Files.walk(Paths.get(directory.getPath()))) {
            // 过滤出所有文件，按文件名排序，并重命名
            paths.filter(Files::isRegularFile)
                    .sorted()
                    .forEach(BatchRename::renameFile);
        }
    }

    private static int counter = 1; // 静态计数器，从1开始

    private static void renameFile(Path filePath) {
        File file = filePath.toFile();
        File parentDir = file.getParentFile();

        // 构造新的文件名，只使用父目录名称+顺序编号
        String newName = parentDir.getName() + "_" + counter++ + ".mp4";
        Path newPath = filePath.resolveSibling(newName);

        // 重命名文件
        try {
            Files.move(filePath, newPath);
            System.out.println("Renamed: " + filePath + " -> " + newPath);
        } catch (IOException e) {
            System.err.println("Failed to rename file: " + filePath);
            e.printStackTrace();
        }
    }
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        String basePath = "C://homework/Games";

        List<String> directories = List.of(
                basePath + "/src",
                basePath + "/res",
                basePath + "/savegames",
                basePath + "/temp",
                basePath + "/src/main",
                basePath + "/src/test",
                basePath + "/res/drawables",
                basePath + "/res/vectors",
                basePath + "/res/icons"
        );

        List<String> files = List.of(
                basePath + "/src/main/Main.java",
                basePath + "/src/main/Utils.java",
                basePath + "/temp/temp.txt"
        );

        for (String dirPath : directories) {
            createDirectory(dirPath);
        }

        for (String filePath : files) {
            createFile(filePath);
        }

        writeLogToFile(basePath + "/temp/temp.txt", log.toString());

        System.out.println(log.toString());
    }

    private static void createDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (dir.mkdir()) {
            log.append("Директория создана: ").append(dir.getAbsolutePath()).append("\n");
        } else {
            if (dir.exists()) {
                log.append("Директория уже существует: ").append(dir.getAbsolutePath()).append("\n");
            } else {
                log.append("Ошибка при создании директории: ").append(dir.getAbsolutePath()).append("\n");
            }
        }
    }

    private static void createFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                log.append("Файл создан: ").append(file.getAbsolutePath()).append("\n");
            } else {
                if (file.exists()) {
                    log.append("Файл уже существует: ").append(file.getAbsolutePath()).append("\n");
                } else {
                    log.append("Ошибка при создании файла: ").append(file.getAbsolutePath()).append("\n");
                }
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(file.getAbsolutePath())
                    .append(": ").append(e.getMessage()).append("\n");
        }
    }

    private static void writeLogToFile(String filePath, String logContent) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(logContent);
            log.append("Лог успешно записан в файл: ").append(filePath).append("\n");
            System.out.println("Лог успешно записан в файл: " + filePath);
        } catch (IOException e) {
            String errorMsg = "Ошибка при записи лога в файл: " + e.getMessage();
            log.append(errorMsg).append("\n");
            System.err.println(errorMsg);
        }
    }
}
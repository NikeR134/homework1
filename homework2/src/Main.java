import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String basePath = "C://homework/Games";

        StringBuilder log = new StringBuilder();


        File srcDir = new File(basePath + "/src");
        File resDir = new File(basePath + "/res");
        File savegamesDir = new File(basePath + "/savegames");
        File tempDir = new File(basePath + "/temp");

        log.append(createDirectory(srcDir));
        log.append(createDirectory(resDir));
        log.append(createDirectory(savegamesDir));
        log.append(createDirectory(tempDir));

        File srcMainDir = new File(srcDir, "main");
        File srcTestDir = new File(srcDir, "test");

        log.append(createDirectory(srcMainDir));
        log.append(createDirectory(srcTestDir));

        File mainJava = new File(srcMainDir, "Main.java");
        File utilsJava = new File(srcMainDir, "Utils.java");

        log.append(createFile(mainJava));
        log.append(createFile(utilsJava));

        File resDrawablesDir = new File(resDir, "drawables");
        File resVectorsDir = new File(resDir, "vectors");
        File resIconsDir = new File(resDir, "icons");

        log.append(createDirectory(resDrawablesDir));
        log.append(createDirectory(resVectorsDir));
        log.append(createDirectory(resIconsDir));

        File tempTxt = new File(tempDir, "temp.txt");
        log.append(createFile(tempTxt));

        writeLogToFile(tempTxt, log.toString());

        System.out.println(log.toString());
    }

    private static String createDirectory(File dir) {
        if (dir.mkdir()) {
            return "Директория создана: " + dir.getAbsolutePath() + "\n";
        } else {
            if (dir.exists()) {
                return "Директория уже существует: " + dir.getAbsolutePath() + "\n";
            }
            return "Ошибка при создании директории: " + dir.getAbsolutePath() + "\n";
        }
    }

    private static String createFile(File file) {
        try {
            if (file.createNewFile()) {
                return "Файл создан: " + file.getAbsolutePath() + "\n";
            } else {
                if (file.exists()) {
                    return "Файл уже существует: " + file.getAbsolutePath() + "\n";
                }
                return "Ошибка при создании файла: " + file.getAbsolutePath() + "\n";
            }
        } catch (IOException e) {
            return "Ошибка при создании файла " + file.getAbsolutePath() + ": " + e.getMessage() + "\n";
        }
    }

    private static void writeLogToFile(File tempFile, String logContent) {
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(logContent);
            System.out.println("Лог успешно записан в файл: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога в файл: " + e.getMessage());
        }
    }
}
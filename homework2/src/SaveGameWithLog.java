import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveGameWithLog {
    private static StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        String savegamesPath = "C://homework/Games/savegames";

        GameProgress progress1 = new GameProgress(100, 5, 1, 10.5);
        GameProgress progress2 = new GameProgress(80, 8, 2, 25.3);
        GameProgress progress3 = new GameProgress(50, 12, 3, 47.8);

        String saveFile1 = savegamesPath + "/save1.dat";
        String saveFile2 = savegamesPath + "/save2.dat";
        String saveFile3 = savegamesPath + "/save3.dat";

        saveGame(saveFile1, progress1);
        saveGame(saveFile2, progress2);
        saveGame(saveFile3, progress3);

        List<String> filesToZip = new ArrayList<>();
        filesToZip.add(saveFile1);
        filesToZip.add(saveFile2);
        filesToZip.add(saveFile3);

        String zipPath = savegamesPath + "/saves.zip";
        zipFiles(zipPath, filesToZip);
        deleteFiles(filesToZip);

        System.out.println(log.toString());
    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(gameProgress);
            log.append("Сохранение создано: ").append(filePath).append("\n");

        } catch (IOException e) {
            String errorMsg = "Ошибка при сохранении: " + e.getMessage();
            log.append(errorMsg).append("\n");
            System.err.println(errorMsg);
        }
    }

    public static void zipFiles(String zipPath, List<String> filesToZip) {
        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String filePath : filesToZip) {
                File fileToZip = new File(filePath);

                if (!fileToZip.exists()) {
                    log.append("Файл не найден: ").append(filePath).append("\n");
                    continue;
                }

                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zos.putNextEntry(zipEntry);

                try (FileInputStream fis = new FileInputStream(fileToZip)) {
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                }

                zos.closeEntry();
                log.append("Добавлен в архив: ").append(fileToZip.getName()).append("\n");
            }

            log.append("Архив создан: ").append(zipPath).append("\n");

        } catch (IOException e) {
            String errorMsg = "Ошибка при создании архива: " + e.getMessage();
            log.append(errorMsg).append("\n");
            System.err.println(errorMsg);
        }
    }

    public static void deleteFiles(List<String> filesToDelete) {
        for (String filePath : filesToDelete) {
            File file = new File(filePath);
            if (file.exists()) {
                if (file.delete()) {
                    log.append("Файл удалён: ").append(filePath).append("\n");
                } else {
                    log.append("Не удалось удалить: ").append(filePath).append("\n");
                }
            }
        }
    }
}
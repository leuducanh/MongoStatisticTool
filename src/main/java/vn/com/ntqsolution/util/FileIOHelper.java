package vn.com.ntqsolution.util;

import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Leu Duc Anh
 * 04/10/2018
 * 21:59
 */
public class FileIOHelper {

    public static String FOLDER_NAME = "statistic_api";
    public static File folder;

    public static void initFolder() {
        folder = Paths.get(Paths.get(FOLDER_NAME).toFile().getAbsolutePath()).toFile();
        if (!folder.exists()) folder.mkdir();
    }

    public static void createFile(String fileName) {
        File file = Paths.get(Paths.get(FOLDER_NAME + "/" + fileName + ".txt").toFile().getAbsolutePath()).toFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> boolean write(List<T> list, String fileName) {

        try (PrintWriter writer = new PrintWriter(Paths.get(Paths.get(FOLDER_NAME + "/" + fileName + ".txt").toFile().getAbsolutePath()).toFile(), "UTF-8")) {
            for (int i = 0; i < list.size(); i++) {
                writer.println((i + 1) + " " + list.get(i).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

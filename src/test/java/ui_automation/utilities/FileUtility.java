package ui_automation.utilities;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Arrays;

public class FileUtility {
    private static final Logger logger = LogManager.getLogger(FileUtility.class);

    /**
     * Check if a file exists.
     *
     * @param filePath The path of the file to check.
     * @return true if the file exists, false otherwise.
     */
    public static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        logger.info("Check if the file exist in directory: "+filePath);
        return file.exists();
    }

    /**
     * Get the path of a file.
     *
     * @param file The file for which to get the path.
     * @return The path of the file as a string.
     */
    public static String getFilePath(File file) {
        logger.info("Get absolute path for file: "+file+"\n"+file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    /**
     * Delete a file.
     *
     * @param filePath The path of the file to delete.
     * @return true if the file was successfully deleted, false otherwise.
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        logger.info("Check if the file exist: "+filePath+" "+file.exists()+". Attempting to delete");
        if (file.exists()) {

            return file.delete();
        }
        return false;
    }

    /**
     * Delete all files within a directory.
     *
     * @param directoryPath The path of the directory.
     * @return true if all files were successfully deleted, false otherwise.
     */
    public static boolean deleteFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory path: " + directoryPath);
            return false;
        }

        // Get all files in the directory
        File[] files = directory.listFiles();

        logger.info("Getting list of existing files in directory: "+directoryPath+"\n"+ Arrays.toString(files));


        // Check if there are any files in the directory
        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory: " + directoryPath);
            return false;
        }
        logger.info("Deleting all existing files");
        // Iterate over each file and delete it
        for (File file : files) {
            try {
                if (file.isFile()) {
                    if (file.delete()) {
                        System.out.println("Deleted file: " + file.getAbsolutePath());
                    } else {
                        System.err.println("Failed to delete file: " + file.getAbsolutePath());
                    }
                }
            } catch (SecurityException e) {
                logger.error("Security exception occurred while deleting file: " + file.getAbsolutePath());
                System.err.println("Security exception occurred while deleting file: " + file.getAbsolutePath());
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * Check if a file is a directory.
     *
     * @param filePath The path of the file to check.
     * @return true if the file is a directory, false otherwise.
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }
}

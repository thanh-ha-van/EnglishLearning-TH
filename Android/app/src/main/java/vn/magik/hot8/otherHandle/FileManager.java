package vn.magik.hot8.otherHandle;

import android.os.Environment;

import java.io.File;

import vn.magik.hot8.constants.Constants;

/**
 * Created by ADMIN on 3/18/2016.
 */
public class FileManager {

    private static final String ROOT_FOLDER = "/hot8/";

    public static final String APP_FOLDER_DATA = Environment.getExternalStorageDirectory().getPath() + ROOT_FOLDER;

    public static String getLinkLocalFile(String fileName) {
        makeAppFolderIfNotExist();
        return APP_FOLDER_DATA + fileName;
    }


    public static String getLinkServer(String fileName) {
        return Constants.FILE_CONFIG + fileName;
    }

    public static boolean checkFileExists(String fileName) {
        File file = new File(getLinkLocalFile(fileName));
        return file.exists();
    }

    public static void makeAppFolderIfNotExist() {
        File appFolder = new File(APP_FOLDER_DATA);
        if (!appFolder.exists())
            appFolder.mkdir();

    }
}

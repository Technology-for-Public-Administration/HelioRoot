package tech.feily.unistarts.heliostration.helioroot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import tech.feily.unistarts.heliostration.helioroot.model.FileModel;

public class FileUtils {

    public static FileModel open(String fileName) {
        File file = new File(fileName);
        FileModel fm = new FileModel();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fm.setFos(new FileOutputStream(file));
            fm.setOsw(new OutputStreamWriter(fm.getFos(), "utf-8"));
            fm.setBw(new BufferedWriter(fm.getOsw()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fm;
    }
    
    public static void write(String content, FileModel fm) {
        try {
            fm.getBw().write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(FileModel fm) {
        try {
            fm.getBw().close();
            fm.getOsw().close();
            fm.getFos().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

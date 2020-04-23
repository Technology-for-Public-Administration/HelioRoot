package tech.feily.unistarts.heliostration.helioroot.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileModel {

    private FileOutputStream fos;
    private OutputStreamWriter osw;
    private BufferedWriter bw;
    
    public void setFos(FileOutputStream fos) {
        this.fos = fos;
    }
    public void setOsw(OutputStreamWriter osw) {
        this.osw = osw;
    }
    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    public FileOutputStream getFos() {
        return fos;
    }
    public OutputStreamWriter getOsw() {
        return osw;
    }
    public BufferedWriter getBw() {
        return bw;
    }
    
}

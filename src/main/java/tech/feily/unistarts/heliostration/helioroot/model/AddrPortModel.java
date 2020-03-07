package tech.feily.unistarts.heliostration.helioroot.model;

public class AddrPortModel {

    private String addr;
    private int port;
    
    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }
    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[AddrPortModel] addr = " + addr + ", port = " + port);
        return str.toString();
    }
    
}

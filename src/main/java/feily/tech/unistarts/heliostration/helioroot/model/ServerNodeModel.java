package feily.tech.unistarts.heliostration.helioroot.model;

public class ServerNodeModel {

    private String serverId;
    private String serverKey;
    private String accessKey;
    
    /**
     * @return the serverId
     */
    public String getServerId() {
        return serverId;
    }
    /**
     * @param serverId the serverId to set
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
    
    /**
     * @return the serverKey
     */
    public String getServerKey() {
        return serverKey;
    }
    /**
     * @param serverKey the serverKey to set
     */
    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }
    
    /**
     * @return the accessKey
     */
    public String getAccessKey() {
        return accessKey;
    }
    /**
     * @param accessKey the accessKey to set
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ServerNodeModel] serverId = " + serverId + ", serverKey = " + serverKey + ", accessKey = " + accessKey);
        return str.toString();
    }
    
}

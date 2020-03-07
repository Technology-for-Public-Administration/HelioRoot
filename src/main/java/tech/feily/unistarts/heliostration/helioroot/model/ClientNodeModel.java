package tech.feily.unistarts.heliostration.helioroot.model;

public class ClientNodeModel {

    private String clientId;
    private String clientKey;
    private String accessKey;
    
    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }
    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return the clientKey
     */
    public String getClientKey() {
        return clientKey;
    }
    /**
     * @param clientKey the clientKey to set
     */
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
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
        str.append("[ClientNodeModel] clientId = " + clientId + ", clientKey = " + clientKey + ", accessKey = " + accessKey);
        return str.toString();
    }
    
}

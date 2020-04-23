package tech.feily.unistarts.heliostration.helioroot.model;

/**
 * Transaction entity assembled according to pbft message of root node.
 * That is, the modeling of client data.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class ContentInfoModel {

    private String from;
    private String to;
    private String type;
    private String content;
    private long timestamp;
    
    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }
    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }
    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[ContentInfoModel] from = " + from + ", to = " + to + ", type = " + type
                + ", content = " + content + ", timestamp = " + timestamp);
        return str.toString();
    }
    
}

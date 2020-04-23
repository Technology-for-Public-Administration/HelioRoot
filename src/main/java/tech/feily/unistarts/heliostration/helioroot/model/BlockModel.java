package tech.feily.unistarts.heliostration.helioroot.model;

/**
 * Block Model.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class BlockModel {

    private BlockHeaderModel header;
    private BlockBodyModel body;
    private String blockHash;
    
    /**
     * @return the header
     */
    public BlockHeaderModel getHeader() {
        return header;
    }
    /**
     * @param header the header to set
     */
    public void setHeader(BlockHeaderModel header) {
        this.header = header;
    }
    
    /**
     * @return the body
     */
    public BlockBodyModel getBody() {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(BlockBodyModel body) {
        this.body = body;
    }
    
    /**
     * @return the blockHash
     */
    public String getBlockHash() {
        return blockHash;
    }
    /**
     * @param blockHash the blockHash to set
     */
    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[BlockModel] header = " + header.toString() + ", body = " + body.toString()
            + ", blockHash = " + blockHash);
        return str.toString();
    }
    
}

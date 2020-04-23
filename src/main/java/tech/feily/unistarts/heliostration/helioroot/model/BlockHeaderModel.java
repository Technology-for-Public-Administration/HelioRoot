package tech.feily.unistarts.heliostration.helioroot.model;

/**
 * Block head model.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class BlockHeaderModel {

    // Current block version.
    private int version;
    // Current block number.
    private int number;
    // Hash value of previous block.
    private String hashPreviousBlock;
    
    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    /**
     * @return the hashPreviousBlock
     */
    public String getHashPreviousBlock() {
        return hashPreviousBlock;
    }
    /**
     * @param hashPreviousBlock the hashPreviousBlock to set
     */
    public void setHashPreviousBlock(String hashPreviousBlock) {
        this.hashPreviousBlock = hashPreviousBlock;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[BlockHeaderModel] version = " + version + ", number = " + number + ", hashPreviousBlock"
                + hashPreviousBlock);
        return str.toString();
    }
    
}

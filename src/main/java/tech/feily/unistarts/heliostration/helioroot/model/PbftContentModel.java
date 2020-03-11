package tech.feily.unistarts.heliostration.helioroot.model;

import java.util.List;

/**
 * Data model of pbft consensus stage.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class PbftContentModel {

    private int viewNum;
    private int reqNum;
    private List<String> transaction;
    private String digest;
    private AddrPortModel ap;
    
    /**
     * @return the viewNum
     */
    public int getViewNum() {
        return viewNum;
    }
    /**
     * @param viewNum the viewNum to set
     */
    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }
    
    /**
     * @return the reqNum
     */
    public int getReqNum() {
        return reqNum;
    }
    /**
     * @param reqNum the reqNum to set
     */
    public void setReqNum(int reqNum) {
        this.reqNum = reqNum;
    }
    
    /**
     * @return the transaction
     */
    public List<String> getTransaction() {
        return transaction;
    }
    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(List<String> transaction) {
        this.transaction = transaction;
    }
    
    /**
     * @return the digest
     */
    public String getDigest() {
        return digest;
    }
    /**
     * @param digest the digest to set
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }
    
    /**
     * @return the ap
     */
    public AddrPortModel getAp() {
        return ap;
    }
    /**
     * @param ap the ap to set
     */
    public void setAp(AddrPortModel ap) {
        this.ap = ap;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[PbftContentModel] viewNum = " + viewNum + ", reqNum = " + reqNum + ", transaction = "
                + transaction.toString() + ", digest = " + digest + ", ap = " + ap.toString());
        return str.toString();
    }

}

package tech.feily.unistarts.heliostration.helioroot.utils;

/**
 * Definition of nodes in the Merkle tree.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class MerkleTreeNode {

    /**
     * Left child node of current node, and leaf node has no child node.
     */
    private MerkleTreeNode left;
    
    /**
     * Right child node of current node, and leaf node has no child node.
     */
    private MerkleTreeNode right;
    
    /**
     * Data of current node, and the field data and hash of non leaf nodes are the same.
     */
    private String data;
    
    /**
     * The hash value of the current node.
     */
    private String hash;
    private String name;
    
    public MerkleTreeNode() {
        
    }
    
    public MerkleTreeNode(String data) {
        this.data = data;
        this.hash = SHAUtil.sha256BasedHutool(data);
        this.name = "[Node : " + data + "]";
    }
    
    /**
     * Regular course of official duties.
     * @param left
     */
    public void setLeft(MerkleTreeNode left) {
        this.left = left;
    }
    
    public void setRight(MerkleTreeNode right) {
        this.right = right;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public MerkleTreeNode getLeft() {
        return left;
    }
    
    public MerkleTreeNode getRight() {
        return right;
    }
    
    public String getData() {
        return data;
    }
    
    public String getHash() {
        return hash;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("left : " + (left != null ? left.getName() : null) + "\n");
        sb.append("right : " + (right != null ? right.getName() : null) + "\n");
        sb.append("data : " + data + "\n");
        sb.append("hash : " + hash + "\n");
        sb.append("name : " + name + "\n");
        return sb.toString();
    }
    
}

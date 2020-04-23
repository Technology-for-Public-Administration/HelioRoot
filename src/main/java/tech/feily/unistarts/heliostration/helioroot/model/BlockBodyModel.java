package tech.feily.unistarts.heliostration.helioroot.model;

import java.util.List;

import tech.feily.unistarts.heliostration.helioroot.utils.MerkleTreeNode;

/**
 * Model of transaction entity.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class BlockBodyModel {
    
    private List<ContentInfoModel> contentlist;
    private List<MerkleTreeNode> merkleTree;
    
    /**
     * @return the contentlist
     */
    public List<ContentInfoModel> getContentlist() {
        return contentlist;
    }
    /**
     * @param contentlist the contentlist to set
     */
    public void setContentlist(List<ContentInfoModel> contentlist) {
        this.contentlist = contentlist;
    }

    /**
     * @return the merkleTree
     */
    public List<MerkleTreeNode> getMerkleTree() {
        return merkleTree;
    }
    /**
     * @param merkleTree the merkleTree to set
     */
    public void setMerkleTree(List<MerkleTreeNode> merkleTree) {
        this.merkleTree = merkleTree;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[BlockBodyModel] contentlist = " + contentlist.toString() + ", merkleTree = " + merkleTree.toString());
        return str.toString();
    }
    
}

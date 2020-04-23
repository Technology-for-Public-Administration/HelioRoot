package tech.feily.unistarts.heliostration.helioroot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class builds Merkle tree according to the transaction list.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class MerkleTree {

    /**
     * The node list of the Merkle tree.
     */
    private List<MerkleTreeNode> list;
    /**
     * The root node of the Merkle tree.
     */
    private MerkleTreeNode root;
    
    public MerkleTree(List<String> contents) {
        createMerkleTree(contents);
    }
    
    /**
     * Building the leaf node of Merkle tree according to the transaction list.
     * The leaf node has no child node.
     * 
     * @param contents the transaction list.
     * @return leaf node.
     */
    private List<MerkleTreeNode> createLeafList(List<String> contents) {
        List<MerkleTreeNode> leafList = new ArrayList<MerkleTreeNode>();
        if (contents == null || contents.size() == 0) {
            return leafList;
        }
        for (String content : contents) {
            MerkleTreeNode node = new MerkleTreeNode(content);
            leafList.add(node);
        }
        return leafList;
    }
    
    /**
     * Building the parent node according to the left and right node.
     * 
     * @param left - the left node.
     * @param right - the right node.
     * @return the parent node.
     */
    private MerkleTreeNode createParentNode(MerkleTreeNode left, MerkleTreeNode right) {
        MerkleTreeNode parent = new MerkleTreeNode();
        parent.setLeft(left);
        parent.setRight(right);
        String hash = left.getHash();
        if (right != null) {
            hash = SHAUtil.sha256BasedHutool(left.getHash() + right.getHash());
        }
        parent.setData(hash);
        parent.setHash(hash);
        if (right != null) {
            parent.setName("(The parent node of " + left.getName() + " and " + right.getName() + ")");
        } else {
            parent.setName("(Inherit node {" + left.getName() + "} becomes parent node)");
        }
        return parent;
    }
    
    /**
     * Building the parent node list of Merkle tree according to the sub-node list.
     * 
     * @param leafList the sub-node list.
     * @return the parent node list.
     */
    private List<MerkleTreeNode> createParentList(List<MerkleTreeNode> leafList) {
        List<MerkleTreeNode> parents = new ArrayList<MerkleTreeNode>();
        if (leafList == null || leafList.size() == 0) {
            return parents;
        }
        int len = leafList.size();
        for (int i = 0; i < len - 1; i += 2) {
            MerkleTreeNode parent = createParentNode(leafList.get(i), leafList.get(i + 1));
            parents.add(parent);
        }
        if (len % 2 != 0) {
            MerkleTreeNode parent = createParentNode(leafList.get(len - 1), null);
            parents.add(parent);
        }
        return parents;
    }
    
    /**
     * Building a Merkle tree。
     * 
     * @param contents
     */
    private void createMerkleTree(List<String> contents) {
        if (contents == null || contents.size() == 0) {
            return;
        }
        /**
         * Initializes the entire node list to null firstly.
         */
        list = new ArrayList<MerkleTreeNode>();
        /**
         * Then build the leaf node list according to the transaction list, and add it into the final node list.
         */
        List<MerkleTreeNode> leafList = createLeafList(contents);
        list.addAll(leafList);
        /**
         * Build the parent node of the leaf node, and add it into the final node list.
         */
        List<MerkleTreeNode> parents = createParentList(leafList);
        list.addAll(parents);
        /**
         * Build the parent node list of the current node list cyclically, until the root node.
         */
        while (parents.size() > 1) {
            List<MerkleTreeNode> temp = createParentList(parents);
            list.addAll(temp);
            parents = temp;
        }

        /**
         * Now the Merkle tree has been built, then set the root node of the Merkle tree.
         */
        root = parents.get(0);
    }
    
    /**
     * Traverse the Merkle tree.
     */
    public void traverseTreeNodes() {
        Collections.reverse(list);
        MerkleTreeNode root = list.get(0);
        traverseTreeNodes(root);
    }
    
    /**
     * Traverse the left child node first, then right.
     * 
     * @param node
     */
    private void traverseTreeNodes(MerkleTreeNode node) {
        System.out.println(node.toString());
        if (node.getLeft() != null) {
            traverseTreeNodes(node.getLeft());
        }
        if (node.getRight() != null) {
            traverseTreeNodes(node.getRight());
        }
    }
    
    /**
     * Regular course of official duties.
     * 
     * @return
     */
    public List<MerkleTreeNode> getList() {
        if (list == null) {
            return list;
        }
        Collections.reverse(list);
        return list;
    }
    
    public void setList(List<MerkleTreeNode> list) {
        this.list = list;
    }
    
    public MerkleTreeNode getRoot() {
        return root;
    }
    
    public void setRoot(MerkleTreeNode root) {
        this.root = root;
    }
    
}

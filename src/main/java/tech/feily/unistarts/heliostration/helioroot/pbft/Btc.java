package tech.feily.unistarts.heliostration.helioroot.pbft;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import tech.feily.unistarts.heliostration.helioroot.model.BlockBodyModel;
import tech.feily.unistarts.heliostration.helioroot.model.BlockHeaderModel;
import tech.feily.unistarts.heliostration.helioroot.model.BlockModel;
import tech.feily.unistarts.heliostration.helioroot.model.ContentInfoModel;
import tech.feily.unistarts.heliostration.helioroot.utils.MerkleTree;
import tech.feily.unistarts.heliostration.helioroot.utils.SHAUtil;

/**
 * Data assembly as the core class of blockchain.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class Btc {

    private static Gson gson = new Gson();
    
    /**
     * Assembly block head.
     * 
     * @param version
     * @param number
     * @param hashPreviousBlock
     * @param hashMerkleRoot
     * @param timestamp
     * @return
     */
    public static BlockHeaderModel beHeader(int version, int number, 
            String hashPreviousBlock) {
        BlockHeaderModel header = new BlockHeaderModel();
        header.setHashPreviousBlock(hashPreviousBlock);
        header.setNumber(number);
        header.setVersion(version);
        return header;
    }
    
    /**
     * Assembling data entities.
     * 
     * @param jsonList
     * @return
     */
    public static List<ContentInfoModel> beContentInfo(List<String> jsonlist) {
        List<ContentInfoModel> contents = new ArrayList<ContentInfoModel>();
        for (String json : jsonlist) {
            contents.add(gson.fromJson(json, ContentInfoModel.class));
        }
        return contents;
    }
    
    /**
     * Assembly block body.
     * 
     * @param bodyList
     * @return
     */
    public static BlockBodyModel beBody(List<ContentInfoModel> contentlist, List<String> jsonlist) {
        MerkleTree merkle = new MerkleTree(jsonlist);
        BlockBodyModel body = new BlockBodyModel();
        body.setContentlist(contentlist);
        body.setMerkleTree(merkle.getList());
        return body;
    }
    
    /**
     * Assembly block.
     * 
     * @param header
     * @param body
     * @return
     */
    public static BlockModel beBlock(BlockHeaderModel header, BlockBodyModel body) {
        BlockModel block = new BlockModel();
        block.setBody(body);
        block.setHeader(header);
        block.setBlockHash(SHAUtil.sha256BasedHutool(header.toString() + body.toString()));
        return block;
    }
    
    public static BlockModel beBlock(int version, int number, 
            String hashPreviousBlock, List<String> jsonlist) {
        BlockHeaderModel header = beHeader(version, number, hashPreviousBlock);
        BlockBodyModel body = beBody(beContentInfo(jsonlist), jsonlist);
        return beBlock(header, body);
    }
    
}

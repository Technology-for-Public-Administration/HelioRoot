package tech.feily.unistarts.heliostration.helioroot.model;

import java.util.List;

public class PbftMsgModel {

    private MsgEnum msgType;
    private ClientNodeModel client;
    private ServerNodeModel server;
    private AddrPortModel ap;
    private MetaModel meta;
    private List<ServerNodeModel> listServer;
    
    /**
     * @return the msgType
     */
    public MsgEnum getMsgType() {
        return msgType;
    }
    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(MsgEnum msgType) {
        this.msgType = msgType;
    }
    
    /**
     * @return the client
     */
    public ClientNodeModel getClient() {
        return client;
    }
    /**
     * @param client the client to set
     */
    public void setClient(ClientNodeModel client) {
        this.client = client;
    }
    
    /**
     * @return the server
     */
    public ServerNodeModel getServer() {
        return server;
    }
    /**
     * @param server the server to set
     */
    public void setServer(ServerNodeModel server) {
        this.server = server;
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

    /**
     * @return the meta
     */
    public MetaModel getMeta() {
        return meta;
    }
    /**
     * @param meta the meta to set
     */
    public void setMeta(MetaModel meta) {
        this.meta = meta;
    }
    
    /**
     * @return the listServer
     */
    public List<ServerNodeModel> getListServer() {
        return listServer;
    }
    /**
     * @param listServer the listServer to set
     */
    public void setListServer(List<ServerNodeModel> listServer) {
        this.listServer = listServer;
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[PbftMsg] msgType = " + msgType.toString() + ", client = " + client.toString()
                + ", server = " + server.toString() + ", ap = " + ap.toString() + ", meta = " + meta.toString()
                + ", listServer = " + listServer.toString());
        return str.toString();
    }
    
}

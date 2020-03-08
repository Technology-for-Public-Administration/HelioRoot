package tech.feily.unistarts.heliostration.helioroot.pbft;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;

import com.google.gson.Gson;

import tech.feily.unistarts.heliostration.helioroot.model.AddrPortModel;
import tech.feily.unistarts.heliostration.helioroot.model.ClientNodeModel;
import tech.feily.unistarts.heliostration.helioroot.model.MsgEnum;
import tech.feily.unistarts.heliostration.helioroot.model.PbftMsgModel;
import tech.feily.unistarts.heliostration.helioroot.model.ServerNodeModel;
import tech.feily.unistarts.heliostration.helioroot.p2p.P2pClientEnd;
import tech.feily.unistarts.heliostration.helioroot.p2p.P2pServerEnd;
import tech.feily.unistarts.heliostration.helioroot.p2p.SocketCache;

public class Pbft {

    private Logger log = Logger.getLogger(Pbft.class);
    private Gson gson = new Gson();
    private int port;
    
    /**
     * When the root node is started, the cache information of the root node is initialized here.
     */
    public Pbft(int port) {
        this.port = port;
        /**
         * Initialize SocketCache.
         */
        SocketCache.initMeta();
        SocketCache.initServers();
        SocketCache.initClients();
        SocketCache.initlistServer();
    }
    
    public void handle(WebSocket ws, String msg) {
        log.info("From " + ws.getRemoteSocketAddress().getAddress().toString() + ":"
                + ws.getRemoteSocketAddress().getPort() + ", message is " + msg);
        PbftMsgModel msgs = gson.fromJson(msg, PbftMsgModel.class);
        switch (msgs.getMsgType()) {
            case hello :
                onHello(ws, msgs);
                break;
            case init :
                onInit(ws, msgs);
                break;
            case service :
                onService(ws, msgs);
                break;
            case confirm :
                onConfirm(ws, msgs);
                break;
            default:
                break;
        }
    }

    private void onConfirm(WebSocket ws, PbftMsgModel msgs) {
        /**
         * Nothing to do, because we just want to acquire ws of client.
         * When the client requests this node through the p2pclientend class, we have obtained the WS of the client.
         */
        System.out.println("Current P2P network metadata: " + SocketCache.get().toString());
    }

    /**
     * Reply P2P network metadata to service node.
     * 
     * @param ws
     * @param msgs
     */
    private void onService(WebSocket ws, PbftMsgModel msgs) {
        if (!isValid(msgs.getServer())) {
            return;
        }
        /**
         * Broadcast new P2P network state in the whole network.
         */
        PbftMsgModel toServer = new PbftMsgModel();
        toServer.setMsgType(MsgEnum.service);
        toServer.setMeta(SocketCache.increAndGet());
        toServer.setListServer(SocketCache.listServer);
        P2pServerEnd.broadcasts(gson.toJson(toServer));
        /**
         * Broadcast the address of the newly added node to all nodes,
         * so that other nodes can obtain ws of the node.
         */
        PbftMsgModel toAll = new PbftMsgModel();
        toAll.setMsgType(MsgEnum.note);
        AddrPortModel ap = new AddrPortModel();
        ap.setAddr(msgs.getAp().getAddr());
        ap.setPort(msgs.getAp().getPort());
        toAll.setAp(ap);
        P2pServerEnd.broadcasts(gson.toJson(toAll));
        /**
         * Send a probe message to the service node server.
         * so that the service node server can save ws of the root node.
         */
        PbftMsgModel toThis = new PbftMsgModel();
        toThis.setMsgType(MsgEnum.detective);
        ap.setAddr(ws.getLocalSocketAddress().getAddress().toString());
        ap.setPort(port);
        toThis.setAp(ap);
        P2pClientEnd.connect(this, "ws:/" + msgs.getAp().getAddr() + ":" + msgs.getAp().getPort(), gson.toJson(toThis), port);
    }

    /**
     * Verify whether the service node request is legal.
     * 
     * @param ser
     * @return
     */
    private boolean isValid(ServerNodeModel ser) {
        return SocketCache.servers.containsKey(ser.getServerId())
                && SocketCache.servers.get(ser.getServerId()).getAccessKey().equals(ser.getAccessKey());
    }

    /**
     * 
     * 
     * @param ws
     * @param msgs
     */
    private void onInit(WebSocket ws, PbftMsgModel msgs) {
        if (!containServer(msgs.getServer())) {
            return;
        }
        /**
         * Assemble accessKey to reply to server.
         */
        PbftMsgModel toServer = new PbftMsgModel();
        toServer.setMsgType(MsgEnum.init);
        ServerNodeModel serModel = new ServerNodeModel();
        serModel.setAccessKey(SocketCache.servers.get(msgs.getServer().getServerId()).getAccessKey());
        serModel.setServerId(msgs.getServer().getServerId());
        toServer.setServer(serModel);
        P2pServerEnd.sendMsg(ws, gson.toJson(toServer));
        /**
         * The address of the service node cannot be broadcasted in the whole network,
         * because it does not have the service capability.
         */
        
    }

    /**
     * Whether the current client is included in the cache.
     * 
     * @param server
     * @return
     */
    private boolean containServer(ServerNodeModel ser) {
        return SocketCache.servers.containsKey(ser.getServerId())
                && (SocketCache.servers.get(ser.getServerId())).getServerKey().equals(ser.getServerKey());
    }

    /**
     * Receive the Hello message from the client and respond to the client and the whole network node
     * 
     * @param ws
     * @param msgs
     */
    private void onHello(WebSocket ws, PbftMsgModel msgs) {
        if (!containClient(msgs.getClient())) {
            return;
        }
        /**
         * Assemble accessKey to reply to client.
         */
        PbftMsgModel toClient = new PbftMsgModel();
        toClient.setMsgType(MsgEnum.hello);
        ClientNodeModel cliModel = new ClientNodeModel();
        cliModel.setAccessKey(SocketCache.clients.get(msgs.getClient().getClientId()).getAccessKey());
        cliModel.setClientId(msgs.getClient().getClientId());
        toClient.setClient(cliModel);
        P2pServerEnd.sendMsg(ws, gson.toJson(toClient));
        /**
         * Broadcast the address and port of the client to the service node.
         * The purpose is for the client to receive the receipt finally.
         */
        PbftMsgModel toAllServer = new PbftMsgModel();
        toAllServer.setMsgType(MsgEnum.note);
        AddrPortModel ap = new AddrPortModel();
        ap.setAddr(msgs.getAp().getAddr());
        ap.setPort(msgs.getAp().getPort());
        toAllServer.setAp(ap);
        P2pServerEnd.broadcasts(gson.toJson(toAllServer));
    }

    /**
     * Whether the current client is included in the cache.
     * 
     * @param cli
     * @return
     */
    private boolean containClient(ClientNodeModel cli) {
        return SocketCache.clients.containsKey(cli.getClientId())
                && SocketCache.clients.get(cli.getClientId()).getClientKey().equals(cli.getClientKey());
    }
    
}

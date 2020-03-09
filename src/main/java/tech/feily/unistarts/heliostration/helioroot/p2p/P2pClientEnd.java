package tech.feily.unistarts.heliostration.helioroot.p2p;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;

import tech.feily.unistarts.heliostration.helioroot.model.MsgEnum;
import tech.feily.unistarts.heliostration.helioroot.model.PbftMsgModel;
import tech.feily.unistarts.heliostration.helioroot.pbft.Pbft;
import tech.feily.unistarts.heliostration.helioroot.utils.SystemUtil;

/**
 * The client program of P2P node.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class P2pClientEnd {
    
    private static Gson gson = new Gson();
    
    /**
     * Client connects to a server.
     * 
     * @param pbft
     * @param wsUrl
     * @param msg
     * @param port
     */
    public static void connect(final Pbft pbft, final String wsUrl, final String msg, final PbftMsgModel pm) {
        try {
            final WebSocketClient socketClient = new WebSocketClient(new URI(wsUrl)) {

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    sendMsg(this, msg, pm);
                }

                @Override
                public void onMessage(String msg) {
                    pbft.handle(this, msg);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    /**
                     * Remove disconnected connections and broadcast status.
                     */
                    if (SocketCache.contain(wsUrl)) {
                        SocketCache.remove(wsUrl);
                        SocketCache.minusAndGet();
                    }
                    PbftMsgModel msg = new PbftMsgModel();
                    msg.setMsgType(MsgEnum.update);
                    msg.setMeta(SocketCache.get());
                    PbftMsgModel pm = new PbftMsgModel();
                    pm.setMsgType(MsgEnum.update);
                    P2pServerEnd.broadcasts(gson.toJson(msg), pm);

                    PbftMsgModel psm = new PbftMsgModel();
                    psm.setMsgType(MsgEnum.close);
                    SystemUtil.printlnClientCloseOrError(psm, wsUrl);
                }

                @Override
                public void onError(Exception ex) {
                    /**
                     * Remove disconnected connections and broadcast status.
                     */
                    if (SocketCache.contain(wsUrl)) {
                        SocketCache.remove(wsUrl);
                        SocketCache.minusAndGet();
                    }
                    PbftMsgModel msg = new PbftMsgModel();
                    msg.setMsgType(MsgEnum.update);
                    msg.setMeta(SocketCache.get());
                    PbftMsgModel pm = new PbftMsgModel();
                    pm.setMsgType(MsgEnum.update);
                    P2pServerEnd.broadcasts(gson.toJson(msg), pm);
                    
                    PbftMsgModel psm = new PbftMsgModel();
                    psm.setMsgType(MsgEnum.error);
                    SystemUtil.printlnClientCloseOrError(psm, wsUrl);
                }
            };
            socketClient.connect();
        } catch (URISyntaxException e) {
            PbftMsgModel psm = new PbftMsgModel();
            psm.setMsgType(MsgEnum.exception);
            SystemUtil.printlnClientCloseOrError(psm, wsUrl);
        }
    }

    /**
     * The method of sending a message to a server.
     * 
     * @param ws
     * @param msg
     */
    public static void sendMsg(WebSocket ws, String msg, PbftMsgModel pm) {
        ws.send(msg);
        SystemUtil.printlnOut(pm);
    }


    /**
     * The method of broadcasting a massage to all server.
     * 
     * @param msg - Messages to send.
     */
    public static void broadcasts(String msg, PbftMsgModel pm) {
        if (SocketCache.wss.size() == 0 || msg == null || msg.equals("")) {
            return;
        }
        for (WebSocket ws : SocketCache.wss) {
            sendMsg(ws, msg, pm);
        }
    }
    
}

package tech.feily.unistarts.heliostration.helioroot.p2p;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import tech.feily.unistarts.heliostration.helioroot.model.PbftMsgModel;
import tech.feily.unistarts.heliostration.helioroot.pbft.Pbft;
import tech.feily.unistarts.heliostration.helioroot.utils.SystemUtil;

/**
 * The server program of P2P node.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class P2pServerEnd {

    //private static Logger log = Logger.getLogger(P2pServerEnd.class);
    //private static Gson gson = new Gson();
    
    /**
     * The method of starting node service in P2P network(as server).
     * 
     * @param pbft
     * @param port
     */
    public static void run(final Pbft pbft, int port) {
        final WebSocketServer socketServer = new WebSocketServer(new InetSocketAddress(port)) {

            @Override
            public void onOpen(WebSocket ws, ClientHandshake handshake) {
                if (!SocketCache.wss.contains(ws)) {
                    SocketCache.wss.add(ws);
                }
            }

            @Override
            public void onClose(WebSocket ws, int code, String reason, boolean remote) {
                /**
                 * Active node minus one after disconnection.
                 */
                /**
                 * 
                if (SocketCache.wss.contains(ws)) {
                    SocketCache.minusAndGet();
                    SocketCache.wss.remove(ws);
                }
                PbftMsgModel msg = new PbftMsgModel();
                msg.setMsgType(MsgEnum.update);
                msg.setMeta(SocketCache.get());
                PbftMsgModel pm = new PbftMsgModel();
                pm.setMsgType(MsgEnum.update);
                broadcasts(gson.toJson(msg), pm);
                 */
                //log.info("Client close!");
            }

            @Override
            public void onMessage(WebSocket ws, String msg) {
                pbft.handle(ws, msg);
            }

            @Override
            public void onError(WebSocket ws, Exception ex) {
                /**
                 * Active node minus one after occuring error.
                 */
                /**
                 * 
                if (SocketCache.wss.contains(ws)) {
                    SocketCache.minusAndGet();
                    SocketCache.wss.remove(ws);
                }
                PbftMsgModel msg = new PbftMsgModel();
                msg.setMsgType(MsgEnum.update);
                msg.setMeta(SocketCache.get());
                PbftMsgModel pm = new PbftMsgModel();
                pm.setMsgType(MsgEnum.update);
                broadcasts(gson.toJson(msg), pm);
                 */
                //log.info("Client connection error!");
            }

            @Override
            public void onStart() {
                //log.info("Server start successfully!");
                System.out.println("Server start successfully!");
                System.out.println("------------------------------------------------------------------------------------");
                SystemUtil.printHead();
            }
            
        };
        socketServer.start();
        //log.info("server listen port " + port);
        System.out.println("Root node starting...");
        System.out.println("server listen port " + port);
    }
    
    /**
     * The method of sending a message to a node.
     * 
     * @param ws - websocket
     * @param msg - Messages to send.
     */
    public static void sendMsg(WebSocket ws, String msg, PbftMsgModel pm) {
        //System.out.println(msg);
        //log.info("To " + ws.getRemoteSocketAddress().getAddress().toString() + ":"
                //+ ws.getRemoteSocketAddress().getPort() + " : " + msg.toString());
        ws.send(msg);
        SystemUtil.printlnOut(pm);
    }
    
    /**
     * The method of broadcasting a massage to all nodes.
     * 
     * @param msg - Messages to send.
     */
    public static void broadcasts(String msg, PbftMsgModel pm) {
        if (SocketCache.wss.size() == 0 || msg == null || msg.equals("")) {
            return;
        }
        //log.info("Glad to say broadcast to clients being startted!");
        for (WebSocket ws : SocketCache.wss) {
            sendMsg(ws, msg, pm);
        }
        //log.info("Glad to say broadcast to clients being overred!");
    }

}

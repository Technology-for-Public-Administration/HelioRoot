package tech.feily.unistarts.heliostration.helioroot.p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.java_websocket.WebSocket;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.hutool.crypto.digest.DigestUtil;
import tech.feily.unistarts.heliostration.helioroot.model.ClientNodeModel;
import tech.feily.unistarts.heliostration.helioroot.model.MetaModel;
import tech.feily.unistarts.heliostration.helioroot.model.ServerNodeModel;

/**
 * Thread safe cache static class.
 * 
 * @author Feily zhang
 * @version v.01
 */
public class SocketCache {
    
    /**
     * Cache all ws connected to this root node
     */
    public static Set<WebSocket> wss = Sets.newConcurrentHashSet();

    /**
     * Caching metadata for the entire network, direct initialization.
     */
    private static MetaModel metaModel = null;
    
    /**
     * Cache all service and user node permissions connected to this root node.
     */
    public static Map<String, ServerNodeModel> servers = Maps.newConcurrentMap();
    public static Map<String, ClientNodeModel> clients = Maps.newConcurrentMap();
    
    /**
     * The simplified version of servers field only keeps the current session credentials.
     * Send it to all active service nodes to verify the qualification of the remaining service nodes.
     */
    public static List<ServerNodeModel> listServer = Lists.newArrayList();
    
    public static synchronized boolean contain(String wsUrl) {
        if (wss.isEmpty()) {
            return false;
        }
        InetSocketAddress isa = null;
        Integer itg = null;
        String ipAddr = null;
        for (WebSocket ws : wss) {
            isa = ws.getRemoteSocketAddress();
            if (isa == null) {
                return false;
            }
            ipAddr = isa.getAddress().toString();
            itg = Integer.valueOf(wsUrl.substring(4).split(":")[1]);
            if (ipAddr.equals(wsUrl.substring(4).split(":")[0])
                    && itg == isa.getPort()) {
                return true;
            }
        }
        return false;
    }
    
    public static synchronized void remove(String wsUrl) {
        InetSocketAddress isa = null;
        Integer itg = null;
        String ipAddr = null;
        for (WebSocket ws : wss) {
            isa = ws.getRemoteSocketAddress();
            ipAddr = isa.getAddress().toString();
            itg = Integer.valueOf(wsUrl.substring(4).split(":")[1]);
            if (ipAddr.equals(wsUrl.substring(4).split(":")[0])
                    && itg == isa.getPort()) {
                wss.remove(ws);
            }
        }
    }
    
    /**
     * Initialize meta based on files or databases.
     */
    public static void initMeta() {
        SocketCache.metaModel = new MetaModel(0, 0, 0, 0, true);
    }
    
    /**
     * Initialize servers based on files or databases.
     */
    @SuppressWarnings("resource")
    public static void initServers() {
        String file = System.getProperty("user.dir") + "\\src\\main\\java\\serverKey.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            String strline = null;
            while ((strline = br.readLine()) != null) {
                ServerNodeModel server = new ServerNodeModel();
                server.setServerId(strline.split("&")[0].split("=")[1]);
                server.setServerKey(strline.split("&")[1].split("=")[1]);
                server.setAccessKey(DigestUtil.md5Hex16(server.getServerId()
                        + server.getServerKey() + System.currentTimeMillis()));
                servers.put(server.getServerId(), server);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize clients based on files or databases.
     */
    @SuppressWarnings("resource")
    public static void initClients() {
        String file = System.getProperty("user.dir") + "\\src\\main\\java\\clientKey.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            String strline = null;
            while ((strline = br.readLine()) != null) {
                ClientNodeModel client = new ClientNodeModel();
                client.setClientId(strline.split("&")[0].split("=")[1]);
                client.setClientKey(strline.split("&")[1].split("=")[1]);
                client.setAccessKey(DigestUtil.md5Hex16(client.getClientId()
                        + client.getClientKey() + System.currentTimeMillis()));
                clients.put(client.getClientId(), client);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize servers based on servers.
     */
    public static void initlistServer() {
        for (String key : servers.keySet()) {
            ServerNodeModel ser = new ServerNodeModel();
            ser.setAccessKey(servers.get(key).getAccessKey());
            ser.setServerId(key);
            listServer.add(ser);
        }
    }
    
    /**
     * The following implements atomic operations for all MetaModels.
     */
    public static MetaModel get() {
        synchronized (SocketCache.class) {
            return metaModel;
        }
    }
    
    public static MetaModel getAndIncre() {
        synchronized (SocketCache.class) {
            MetaModel meta = metaModel;
            metaModel.setIndex(metaModel.getIndex() + 1);
            metaModel.setSize(metaModel.getSize() + 1);
            metaModel.setMaxf((metaModel.getSize() - 1) / 3);
            return meta;
        }
    }
    
    public static MetaModel getAndMinus() {
        synchronized (SocketCache.class) {
            MetaModel meta = metaModel;
            metaModel.setSize(metaModel.getSize() - 1);
            metaModel.setMaxf((metaModel.getSize() - 1) / 3);
            return meta;
        }
    }

    
    public static MetaModel increAndGet() {
        synchronized (SocketCache.class) {
            metaModel.setIndex(metaModel.getIndex() + 1);
            metaModel.setSize(metaModel.getSize() + 1);
            metaModel.setMaxf((metaModel.getSize() - 1) / 3);
            return metaModel;
        }
    }
    
    public static MetaModel minusAndGet() {
        synchronized (SocketCache.class) {
            metaModel.setSize(metaModel.getSize() - 1);
            metaModel.setMaxf((metaModel.getSize() - 1) / 3);
            return metaModel;
        }
    }
}

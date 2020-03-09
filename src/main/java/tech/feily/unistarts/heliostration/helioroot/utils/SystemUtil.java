package tech.feily.unistarts.heliostration.helioroot.utils;

import java.util.Date;

import tech.feily.unistarts.heliostration.helioroot.model.PbftMsgModel;

public class SystemUtil {
    
    public static void printHead() {
        System.out.println("  Time   |      MsgType      | Bc  |                     Details                     ");
        System.out.println("-------------------------------------------------------------------------------------");
    }
    
    public static void printlnIn(PbftMsgModel msg) {
        switch (msg.getMsgType()) {
            case hello :
                println("in  - [hello]     | no  | client node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ request accessKey.");
                break;
            case init :
                println("in  - [init]      | no  | service node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ request accessKey.");
                break;
            case service :
                println("in  - [service]   | no  | service node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ request metadata & session credentials of all service nodes.");
                break;
            case confirm :
                println("in  - [confirm]   | no  | service node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ WebSocket of root node has been saved.");
                break;
            case request :
                println("in  - [request]   | yes | client node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ request package transaction.");
                break;
            case prepare :
                println("in  - [prepare]   | yes | service node@" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ receive prepare msg.");
                break;
            case commit :
                println("in  - [commit]    | yes | service node@" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ receive commit msg.");
                break;
            default:
                break;
        }
    }

    public static void printlnOut(PbftMsgModel msg) {
        switch (msg.getMsgType()) {
            case hello :
                println("out - [hello]     | no  | client node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                        + " $ response accessKey.");
                break;
            case init :
                println("out - [init]      | no  | service node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                         + " $ response accessKey.");
                break;
            case service :
                println("out - [service]   | no  | @all" + " $ broadcast metadata & session credentials of all service nodes.");
                break;
            case note :
                println("out - [note]      | no  | @all" + " $ broadcast the address of the newly added node.");
                break;
            case detective :
                println("out - [detective] | no  | service node @" + msg.getAp().getAddr() + ":" + msg.getAp().getPort()
                        + " $ send detection packet.");
                break;
            case update :
                println("out - [update]    | no  | @all" + " $ update their metadata.");
                break;
            case prePrepare :
                println("out - [prepre]    | yes | @all" + " $ broadcast pre-prepare msg.");
                break;
            default:
                break;
        }
    }
    
    public static void printlnClientCloseOrError(PbftMsgModel msg, String wsUrl) {
        switch (msg.getMsgType()) {
            case close :
                println("    - [close]     | no  | node @" + wsUrl.substring(4) + " $ closed.");
                break;
            case error :
                println("    - [error]     | no  | node @" + wsUrl.substring(4) + " $ occurs error.");
                break;
            case exception :
                println("    - [exception] | no  | node @" + wsUrl.substring(4) + " $ exception.");
                break;
            default:
                break;
        }
    }

    
    @SuppressWarnings("deprecation")
    public static void println(String line) {
        Date date = new Date();
        System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + " | " + line);
    }
    
}

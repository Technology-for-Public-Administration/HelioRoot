package tech.feily.unistarts.heliostration.helioroot;

import tech.feily.unistarts.heliostration.helioroot.p2p.P2pServerEnd;
import tech.feily.unistarts.heliostration.helioroot.pbft.Pbft;
import tech.feily.unistarts.heliostration.helioroot.utils.PreCmd;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the HelioChain platform(Root Node).");
        System.out.println("Current application version : Alpha 0.0.1.0423");
        System.out.println("This application is licensed through GNU General Public License version 3 (GPLv3).");
        System.out.println("Copyright (c)2020 tpastd.com. All rights reserved.\n");
        System.out.println("First, you need to add some configuration information to use.");
        System.out.println("------------------------------------------------------------------");
        PreCmd.run();
        int port = 9090;
        Pbft pbft = new Pbft(port);
        P2pServerEnd.run(pbft, port);
    }
}

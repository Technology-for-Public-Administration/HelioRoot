package tech.feily.unistarts.heliostration.helioroot;

import tech.feily.unistarts.heliostration.helioroot.p2p.P2pServerEnd;
import tech.feily.unistarts.heliostration.helioroot.pbft.Pbft;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int port = 7001;
        Pbft pbft = new Pbft(port);
        P2pServerEnd.run(pbft, port);
    }
}

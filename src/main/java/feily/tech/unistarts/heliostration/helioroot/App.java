package feily.tech.unistarts.heliostration.helioroot;

import feily.tech.unistarts.heliostration.helioroot.p2p.P2pServerEnd;
import feily.tech.unistarts.heliostration.helioroot.pbft.Pbft;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Pbft pbft = new Pbft();
        P2pServerEnd.run(pbft, 7001);
    }
}

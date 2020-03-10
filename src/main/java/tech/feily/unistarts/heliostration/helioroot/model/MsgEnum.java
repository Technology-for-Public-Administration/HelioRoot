package tech.feily.unistarts.heliostration.helioroot.model;

/**
 * Enumeration of P2P network states.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public enum MsgEnum {

    hello, detective, confirm,
    init, service, note,
    update, close, error,
    exception, request,
    prePrepare, prepare,
    commit, start;
    
}

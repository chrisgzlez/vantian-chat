package com.vantian.core.communication;

/**
 * ICommunicate
 */
public interface ICommunicate {
    
    public void sendTo(ICommunicate dest, IMessage mssg);

    public IMessage receiveFrom(ICommunicate sender);
    
}

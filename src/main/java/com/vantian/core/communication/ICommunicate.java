package com.vantian.core.communication;

/**
 * ICommunicate
 */
public interface ICommunicate {
    
    public void send(ICommunicate sender, IMessage mssg);

    public IMessage receive(ICommunicate sender);

    public String getId();
    
}

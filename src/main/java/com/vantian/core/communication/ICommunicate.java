package com.vantian.core.communication;

/**
 * ICommunicate
 */
public interface ICommunicate {
    
    public void send(IMessage mssg);

    public IMessage receive(ICommunicate sender);

    public String getId();
    
}

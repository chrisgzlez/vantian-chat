package com.vantian.core.communication;

/**
 * ICommunicate
 */
public interface ICommunicate {
    
    public send(IMessage mssg);

    public receive(IMessage mssg);
    
}

package com.vantian.core;

/**
 * ICommunicate
 */
public interface ICommunicate {
    
    public send(IMessage mssg);

    public receive(IMessage mssg);
    
}

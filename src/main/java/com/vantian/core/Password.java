package com.vantian.core;

/**
 * Password
 */
public class Password implements IPassword {
    private String passwd;
    public Password(String plainPassword) {
        this.passwd = plainPassword;

    }

    public String get() {
        return this.passwd;

    }

    
}

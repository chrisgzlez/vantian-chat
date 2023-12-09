package com.vantian.gui.windows.chatComponents.event;

import com.vantian.core.communication.IMessage;

public interface EventChat {

    public void sendMessage(IMessage mssg);
    public void receiveMessage();
}

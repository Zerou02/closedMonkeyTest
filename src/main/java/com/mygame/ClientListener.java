package com.mygame;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.mygame.messages.SetColourMessage;

public class ClientListener implements MessageListener<Client> {
    GameState state;

    public ClientListener(GameState state) {
        this.state = state;
    }

    public void messageReceived(Client source, Message message) {
        if (message instanceof SetColourMessage) {
            this.state.sliderColour = ((SetColourMessage) message).getVal();
        }
    }
}

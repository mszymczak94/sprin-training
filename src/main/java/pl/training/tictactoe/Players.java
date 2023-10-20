package pl.training.tictactoe;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class Players {
    private final Map<String,Player> players = new HashMap<>();


    public synchronized void registerPlayer(String socketId) {
        if (isFull()) {
            players.clear();
        }
        if (players.isEmpty()) {
            players.put(socketId, Player.CROSS);
        } else {
            players.put(socketId, Player.CIRCLE);
        }
    }

    public Player getPlayer(String socketId) {
        return players.get(socketId);
    }

    private boolean isFull() {
        return players.size() == 2;
    }
}

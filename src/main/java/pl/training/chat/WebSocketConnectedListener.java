package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import static pl.training.chat.WebSocketUtils.*;

@Component
@RequiredArgsConstructor
@Log
public class WebSocketConnectedListener /* implements ApplicationListener<SessionConnectedEvent>*/ {

    private static final String USER_HEADER = "username";

    private final SystemMessageSender systemMessageSender;

    @EventListener
    public void onApplicationEvent(SessionConnectedEvent event) {
        var socketId = getSocketId(event);
        var username = getNativeHeader(event, USER_HEADER);
        getNativeAttributes(event).put(socketId, username);
        log.info("Socket with id %s connected (user: %s)".formatted(socketId, username));
        systemMessageSender.send("User %s is now connected".formatted(username));
    }

}

package pl.training.tictactoe;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log
public class TicTacToeController {
    private final TicTacToe ticTacToe;
    private final Players players;
    @MessageMapping("/game-room")
    @SendTo("/game-channel")
    public TicTacToeDto onMessage(@Payload MoveDto moveDto,
                                  @Header("simpSessionId") String socketId) {

        Player player = players.getPlayer(socketId);

        if (!ticTacToe.getCurrentPlayer().equals(player)) {
            return TicTacToeDto.builder()
                    .crossFields(ticTacToe.getCrossFields())
                    .circleFields(ticTacToe.getCircleFields())
                    .moveWasValid(false)
                    .currentPlayer(ticTacToe.getCurrentPlayer())
                    .winner(ticTacToe.getWinner().orElse(Player.NONE))
                    .build();
        }

        boolean isValid = ticTacToe.takeField(moveDto.getFieldNumber());

        return TicTacToeDto.builder()
                .crossFields(ticTacToe.getCrossFields())
                .circleFields(ticTacToe.getCircleFields())
                .moveWasValid(isValid)
                .currentPlayer(ticTacToe.getCurrentPlayer())
                .winner(ticTacToe.getWinner().orElse(Player.NONE))
                .build();
    }
}

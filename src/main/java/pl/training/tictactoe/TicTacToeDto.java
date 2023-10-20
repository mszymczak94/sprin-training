package pl.training.tictactoe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicTacToeDto {
    private Set<Integer> crossFields;
    private Set<Integer> circleFields;
    private boolean moveWasValid;
    private Player currentPlayer;
    private Player winner;

}

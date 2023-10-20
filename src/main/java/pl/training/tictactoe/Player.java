package pl.training.tictactoe;

public enum Player {

    CIRCLE, CROSS, NONE;

    public Player reverse() {
        return this == CIRCLE ? CROSS : CIRCLE;
    }

}

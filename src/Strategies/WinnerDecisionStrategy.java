package Strategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinnerDecisionStrategy {
    public Player getWinner(Board board, Move move);
}

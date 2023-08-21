package Strategies;

import models.Board;
import models.Game;
import models.Move;
import models.Player;

public interface BotPlayingStrategy {
    public Move getNextMove(Player player, Board board);
}

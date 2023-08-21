package Controllers;

import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {


    public Game createGame(int dimension, List<Player> players)
    {
        return Game.getBuilder().setBoard(dimension).setPlayers(players).build();

    }

    public void displayBoard(Game game)
    {
        game.getBoard().display();
    }

    public void setGameStatus(Game game, GameStatus gameStatus) {
        game.setGameStatus(gameStatus);
    }

    public void makeNextMove(Game game) {
        game.executeNextMove();
    }

}

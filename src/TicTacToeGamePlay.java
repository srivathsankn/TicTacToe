import Controllers.GameController;
import Strategies.FillNextSpotBotPlayingStrategy;
import Strategies.StrategyDecider;
import models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGamePlay {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("The Game of Tic Tac Toe is starting....");

        System.out.println("Enter the dimensions of the board.");
        int dimension = s.nextInt();

        System.out.println("Enter the number of Players.");
        int playersCount = s.nextInt();

        System.out.println("Is there a bot? (y/n)");
        char isBotPresentResponse = s.next().charAt(0);

        boolean isBotPresent = isBotPresentResponse == 'y' || isBotPresentResponse == 'Y';

        int humanPlayersCount = playersCount;

        List<Player> players = new ArrayList<Player>();

        if (isBotPresent)
        {
            humanPlayersCount--;
            System.out.println("Enter the name of the Bot ");
            String name = s.next();
            System.out.println("Enter the symbol for the Bot ");
            char symbol = s.next().charAt(0);
            System.out.println("Enter the Bot Difficulty level as 0,1 or 2 (0:EASY, 1:MEDIUM, 2:HARD)");
            int botDifficultyLevel = s.nextInt();
            Bot b = new Bot(name,symbol, PlayerType.BOT, DifficultyLevel.difficultyLevelMap.get(botDifficultyLevel));
            b.setPlayingStrategy(StrategyDecider.assignStrategy(DifficultyLevel.difficultyLevelMap.get(botDifficultyLevel)));
            players.add(b);

        }


        for (int i=0; i<humanPlayersCount; i++)
        {
            System.out.println("Enter the name of the player "+ (i+1));
            String name = s.next();
            System.out.println("Enter the symbol of the player "+ (i+1));
            char symbol = s.next().charAt(0);
            players.add(new Player(name, symbol, PlayerType.HUMAN));
        }

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);
        gameController.setGameStatus (game, GameStatus.ONGOING);


        while (game.getGameStatus() == GameStatus.ONGOING) {
            System.out.println("Current Board is as below");
            gameController.displayBoard(game);

            gameController.makeNextMove(game);


            //if (game.getMoves().size() > 0)  // Ask for UNDO only if there are some moves

        }

        // Handle Ended or Draw status

        if (game.getGameStatus() == GameStatus.ENDED)
        {
            System.out.println (game.getWinner().getName() + " has won the game. Congrats!!");
        }
        else if (game.getGameStatus() == GameStatus.DRAW)
        {
            System.out.println("Match ended in a Draw. Please play again!!");
        }

    }
}
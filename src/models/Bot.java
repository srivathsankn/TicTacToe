package models;

import Strategies.BotPlayingStrategy;

public class Bot extends Player{
    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy playingStrategy;

    public Bot(String name, char symbol, PlayerType playerType, DifficultyLevel difficultyLevel)
    {
        super(name, symbol ,playerType);
        this.difficultyLevel = difficultyLevel;
    }

    public void setPlayingStrategy(BotPlayingStrategy playingStrategy)
    {
        this.playingStrategy = playingStrategy;
    }

    public Move decideMove(Game game)
    {
        Move move = playingStrategy.getNextMove(this,game.getBoard());
        if (move != null)
        {
            System.out.println( this.getName() +" has decided to keep a coin at ( "+
                    move.getCell().getRow() + "," + move.getCell().getCol() + " )"  );
        }
        return move;
    }
}

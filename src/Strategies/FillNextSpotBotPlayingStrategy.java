package Strategies;

import models.*;

public class FillNextSpotBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move getNextMove(Player player, Board board) {

        for (int i=0; i< board.getCells().size(); i++)
        {
            for (int j=0; j<board.getCells().get(i).size(); j++)
            {
                if (board.getCells().get(i).get(j).getCellStatus() == CellStatus.EMPTY)
                    return new Move(player, new Cell(i,j,player, CellStatus.FILLED));
            }
        }

        return null;
    }
}

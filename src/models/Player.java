package models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType)
    {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move decideMove(Game game) {
        Scanner s = new Scanner(System.in);
        boolean isErrorMove = true;
        int chosenRow =0, chosenCol=0;
        while (isErrorMove)
        {
            System.out.println(this.name+"! Enter the row and column index (0 - "+(game.getBoard().getDimension()-1)+") where you would like to place your coin");
            chosenRow = s.nextInt();
            chosenCol = s.nextInt();
            if (chosenRow <0 || chosenRow >=game.getBoard().getDimension() )
            {
                System.out.println("ERROR!! Row value should be between (0 - "+(game.getBoard().getDimension()-1)+")");
                //isErrorMove = true;
                continue;
            }
            if (chosenCol<0 || chosenCol >=game.getBoard().getDimension())
            {
                System.out.println("ERROR!! Col value should be between (0 - "+(game.getBoard().getDimension()-1)+")");
                //isErrorMove = true;
                continue;
            }

            if (game.getBoard().getCells().get(chosenRow).get(chosenCol).getCellStatus() != CellStatus.EMPTY)
            {
                System.out.println("ERROR!! You can place your Coin only on a Empty Cell");
                //isErrorMove = true;
                continue;
            }
            isErrorMove = false; // Just for Understanding, Marking that it is not an error move;
        }
        return new Move(this,new Cell(chosenRow, chosenCol)) ;

    }
}

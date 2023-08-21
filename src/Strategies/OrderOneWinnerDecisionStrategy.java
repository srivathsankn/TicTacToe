package Strategies;

import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class OrderOneWinnerDecisionStrategy implements WinnerDecisionStrategy {

    int dimension;
    List<HashMap<Character,Integer>> rowWiseSymbolCounts = new ArrayList<>();
    List<HashMap<Character,Integer>> colWiseSymbolCounts = new ArrayList<>();
    Map<Character,Integer> leftDiagonalSymbolCounts = new HashMap<>();
    Map<Character,Integer> rightDiagonalSymbolCounts = new HashMap<>();

    public OrderOneWinnerDecisionStrategy(int dimension) {
        this.dimension = dimension;
        for (int i=0; i<dimension; i++)
        {
            rowWiseSymbolCounts.add(new HashMap<>());
            colWiseSymbolCounts.add(new HashMap<>());
        }
    }

    @Override
    public Player getWinner(Board board, Move move) {
        populateCounts (move);
        int r = move.getCell().getRow();
        int c = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

//        System.out.println(rowWiseSymbolCounts);
//        System.out.println(colWiseSymbolCounts);
//        System.out.println(leftDiagonalSymbolCounts);
//        System.out.println(rightDiagonalSymbolCounts);

        if  (   (rowWiseSymbolCounts.get(r).containsKey(symbol) && rowWiseSymbolCounts.get(r).get(symbol) == dimension) ||
                (colWiseSymbolCounts.get(c).containsKey(symbol) && colWiseSymbolCounts.get(c).get(symbol) == dimension) ||
                (leftDiagonalSymbolCounts.containsKey(symbol) && leftDiagonalSymbolCounts.get(symbol) == dimension) ||
                (rightDiagonalSymbolCounts.containsKey(symbol) && rightDiagonalSymbolCounts.get(symbol) == dimension))
            return move.getPlayer();
        else
            return null;
    }

    private void populateCounts(Move move)
    {
        Player player = move.getPlayer();
        char symbol = player.getSymbol();
        int r = move.getCell().getRow();
        int c = move.getCell().getCol();

        HashMap<Character,Integer> rowHashMap = rowWiseSymbolCounts.get(r);
        HashMap<Character,Integer> colHashMap = colWiseSymbolCounts.get(c);

        rowHashMap.put(symbol, rowHashMap.getOrDefault(symbol,0)+1  );
        colHashMap.put(symbol, colHashMap.getOrDefault(symbol,0)+1  );
        if (isleftdiagonal(r,c))
            leftDiagonalSymbolCounts.put(symbol, leftDiagonalSymbolCounts.getOrDefault(symbol,0)+1);
        if (isrighttdiagonal(r,c))
            rightDiagonalSymbolCounts.put(symbol, rightDiagonalSymbolCounts.getOrDefault(symbol,0)+1);
    }

    private boolean isrighttdiagonal(int r, int c) {
        return r+c == dimension-1;
    }

    private boolean isleftdiagonal(int r, int c) {
        return r==c;
    }
}

package models;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> cells;

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public Board(int dimension)
    {
        this.dimension = dimension;
        cells = new LinkedList<>();
        for(int i=0; i<dimension; i++)
        {
            cells.add(new LinkedList<>());
            for (int j=0; j<dimension; j++)
            {
                cells.get(i).add(new Cell(i,j));
            }
        }
    }

    public void display() {
        for(List<Cell> cellsInARow : cells)
        {
            for (Cell cell : cellsInARow)
            {
                if (cell.getCellStatus() == CellStatus.EMPTY)
                    System.out.print("| |");
                else
                    System.out.print("|" + cell.getPlayer().getSymbol() + "|");
            }
            System.out.println();
        }
    }

    public void applyMove(Move currentMove) {
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();

        cells.get(row).get(col).setPlayer(currentMove.getPlayer());
        cells.get(row).get(col).setCellStatus(CellStatus.FILLED);
    }

    public boolean isFull() {
        for (List<Cell> cellr : cells )
        {
            for (Cell cell: cellr)
            {
                if (cell.getCellStatus() == CellStatus.EMPTY)
                    return false;
            }
        }
        return true;
    }
}

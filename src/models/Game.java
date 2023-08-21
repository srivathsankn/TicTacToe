package models;

import Strategies.OrderOneWinnerDecisionStrategy;
import Strategies.WinnerDecisionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameStatus gameStatus;
    private Player winner;
    WinnerDecisionStrategy winnerDecisionStrategy;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public WinnerDecisionStrategy getWinnerDecisionStrategy() {
        return winnerDecisionStrategy;
    }

    public void setWinnerDecisionStrategy(WinnerDecisionStrategy winnerDecisionStrategy) {
        this.winnerDecisionStrategy = winnerDecisionStrategy;
    }

    private Game() { } // Making constructor private to make builder responsible for creating a Game instance.

    public static GameBuilder getBuilder()
    {
        return new GameBuilder();
    }

    public void executeNextMove() {
        Player nextMovePlayer = this.getPlayers().get(this.getNextPlayerIndex());
        System.out.println("It is "+ nextMovePlayer.getName()+ "'s turn");

//        System.out.println("Hi " + nextMovePlayer.getName() + " Please enter the row and col where you want to place your next coin");
//        Scanner s = new Scanner(System.in);
//        int r = s.nextInt();
//        int c = s.nextInt();

        Move currentMove = nextMovePlayer.decideMove(this); // Player is making the move.
        this.getMoves().add(currentMove);
        this.board.applyMove(currentMove);
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        //Determine Winner
        Player winningPlayer = winnerDecisionStrategy.getWinner(this.board, currentMove);
        if(winningPlayer != null)
        {
            this.winner = winningPlayer;
            setGameStatus(GameStatus.ENDED);
        }
        else
        {
            //check if the board is full that the game may have ended in draw
            if (board.isFull())
                setGameStatus(GameStatus.DRAW);
        }

    }



    public Player getWinner() {
        return this.winner;
    }

    public static class GameBuilder
    {
        private Board board;
        private List<Player> players;
//        private List<Move> moves;  // This field takes only default value hence not required to keep here
//        private int nextPlayerIndex; // This field takes only default value hence not required to keep here
//        private GameStatus gameStatus; // This field takes only default value hence not required to keep here

        public GameBuilder setBoard(int dimension)
        {
            this.board = new Board(dimension);
            return this;
        }

        public GameBuilder setPlayers(List<Player> players)
        {
            this.players = players;
            return this;
        }

        public Game build()
        {
            Game game = new Game();
            game.setBoard(this.board);
            game.setPlayers(this.players);
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            game.gameStatus = GameStatus.ONGOING;
            game.setWinnerDecisionStrategy(new OrderOneWinnerDecisionStrategy(this.board.getDimension()));
            return game;
        }

    }
}

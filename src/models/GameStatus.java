package models;

public enum GameStatus {
    ONGOING(1),
    ENDED(2),
    DRAW(3);

    private int status;

    GameStatus(int x)
    {
        status=x;
    }
}

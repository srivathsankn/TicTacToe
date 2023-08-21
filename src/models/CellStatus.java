package models;

public enum CellStatus {
    EMPTY(0), FILLED(1), BLOCKED(2);

    private int status;
    CellStatus(int status)
    {
        this.status = status;
    }
}

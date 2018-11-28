package com.example.indrabayu.myapplication.model;

public class Board {
    private Cell[][] cell = new Cell[3][3];

    private Player currentPlayer;
    private Player win;
    private int cellsChoosen = 0;

    public Board() {
        restart();
    }

    public int getCellsChoosen() {
        return cellsChoosen;
    }

    public int setCellsChoosen(int value) {
        return this.cellsChoosen = value;
    }

    public void restart() {
        emptyCells();
        currentPlayer = Player.X;
        win = null;
    }

    private void emptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cell[i][j] = new Cell();
            }
        }
    }

    public Player getWinner() {
        return win;
    }

    public Player markCellOnBoard(int row, int column) {

        Player playerPlaying = null;

        if (validateCoordinate(row, column)) {
            cell[row][column].setCellValue(currentPlayer);
            playerPlaying = currentPlayer;
            this.cellsChoosen = this.cellsChoosen + 1;
            if (checkWin(currentPlayer, row, column)) {
                win = currentPlayer;
            } else {
                changePlayer();
            }
        } else {
            cell[row][column].setCellValue(currentPlayer);
        }

        return playerPlaying;
    }

    private void changePlayer() {
        if (currentPlayer == Player.X) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    }

    private Boolean validateCoordinate(int row, int column) {
        if (cell[row][column].getCellValue() != null) {
            return false;
        } else if (outBoundCoordinate(row) || outBoundCoordinate(column)) {
            return false;
        } else if (finishGame()) {
            return false;
        }
        return true;
    }

    private Boolean outBoundCoordinate(int coord) {
        return coord < 0 || coord > 2;
    }

    private Boolean finishGame() {
        if (getWinner() != null && getCellsChoosen() == 9) {
            return true;
        }

        return false;
    }

    private Boolean checkWin(Player nowPlaying, int row, int col) {
        if ((cell[row][0].getCellValue() == nowPlaying &&
             cell[row][1].getCellValue() == nowPlaying &&
             cell[row][2].getCellValue() == nowPlaying) ||
            (cell[0][col].getCellValue() == nowPlaying &&
             cell[1][col].getCellValue() == nowPlaying &&
             cell[2][col].getCellValue() == nowPlaying) ||
            (row == col &&
             cell[0][0].getCellValue() == nowPlaying &&
             cell[1][1].getCellValue() == nowPlaying &&
             cell[2][2].getCellValue() == nowPlaying) ||
            (row + col == 2 &&
             cell[0][2].getCellValue() == nowPlaying &&
             cell[1][1].getCellValue() == nowPlaying &&
             cell[2][0].getCellValue() == nowPlaying)){
            return true;
        }
        return false;
    }

}

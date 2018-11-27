package com.example.indrabayu.myapplication.presenter;

import com.example.indrabayu.myapplication.model.Board;
import com.example.indrabayu.myapplication.model.Cell;
import com.example.indrabayu.myapplication.model.Player;
import com.example.indrabayu.myapplication.view.MainActivity;

public class TicTacToePresenter {
    // Manggil View
    public MainActivity activity;

    // Manggil Model
    public Board board;
    public Cell cell;
    public Player player;
}

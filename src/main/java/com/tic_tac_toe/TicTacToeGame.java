package com.tic_tac_toe;

import com.tic_tac_toe.models.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board board;

    public void initializeGame(){
        players=new LinkedList<>();

        PlayingPiece xPiece=new PlayingPieceX();
        Player xPlayer=new Player("Player1",xPiece);

        PlayingPiece oPiece=new PlayingPieceO();
        Player oPlayer=new Player("Player2",oPiece);

        players.add(xPlayer);
        players.add(oPlayer);
        board=new Board(3);
    }
    public String startGame(){
        boolean noWinner=true;
        while (noWinner){
            Player playerTurn=players.removeFirst();
            //print board
            board.printBoard();

            //check for free cells
            List<Pair<Integer,Integer>> freecells=board.getFreeCells();
            if (freecells.isEmpty()){
                noWinner=false;
                continue;
            }

            //read Player input
            System.out.println("Player : "+playerTurn.getName()+" enter row,column :");
            Scanner sc=new Scanner(System.in);
            String input=sc.nextLine();
            String[] pos=input.split(",");
            int row=Integer.valueOf(pos[0]);
            int col=Integer.valueOf(pos[1]);

            boolean pieceAdded=board.addPiece(row,col,playerTurn.getPlayingPiece());
            if(!pieceAdded){
                System.out.println("incorrect position choosen !!!");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner=isThereWinner(row,col,playerTurn.getPlayingPiece().getPieceType());
            if (winner){
                return "Player "+playerTurn.getName()+" wins!!!";
            }
        }
        return "TIE!!!";
    }

    public boolean isThereWinner(int row,int col,PieceType pieceType){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<board.getSize();i++) {

            if(board.getBoard()[row][i] == null || board.getBoard()[row][i].getPieceType() != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<board.getSize();i++) {

            if(board.getBoard()[i][col] == null || board.getBoard()[i][col].getPieceType() != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<board.getSize();i++,j++) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getPieceType() != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=board.getSize()-1; i<board.getSize();i++,j--) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getPieceType() != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}

package com.tic_tac_toe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicTacToeApplication.class, args);

		TicTacToeGame ticTacToeGame=new TicTacToeGame();
		ticTacToeGame.initializeGame();
		System.out.println(ticTacToeGame.startGame());
		System.out.println("******************** GAME ENDS *************************");

		shutdown();
	}
	public static void shutdown() {
		Runtime.getRuntime().halt(0);
	}
}

package controller;

import model.Gameboard;
import view.GameView;

public class Controller {
	private Gameboard board;
	private GameView view;

	public Controller() {
		super();
	}
	//abc
	public Controller(Gameboard board, GameView view) {
		this.board = board;
		this.view = view;

		// add listener
		view.addListener(e -> {
			if (e.getSource() == view.getBtnStart()) {
				board.genetic();

			}
			if (e.getSource() == view.getBtnRestart()) {
				board.restart();
			}
		});

	}

	public void showGame() {
		view.setVisible(true);
		board.genetic();
	}

	public static void main(String[] args) {
		Gameboard board = new Gameboard();
		GameView view = new GameView(board);
		Controller ctrl = new Controller(view.getGameboard(), view);
		ctrl.showGame();

	}

	public Gameboard getBoard() {
		return board;
	}

	public void setBoard(Gameboard board) {
		this.board = board;
	}

	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}
}

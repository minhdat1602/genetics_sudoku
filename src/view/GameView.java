package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import model.Gameboard;
import model.Observable;
import model.comunity.Individual;

public class GameView extends JFrame implements GameObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePlay gameplay;
	private Gameboard gameboard;

	private JButton btnRestart;
	private JButton btnStart;
	private JLabel lbFitness;
	private JLabel lbGeneration;

	public GameView(Gameboard gameboard) {
		setFocusable(true);

		this.gameboard = gameboard;
		gameplay = new GamePlay(gameboard.getCurrentGeneration().getBest());

		// dang ky observer
		 gameboard.registerObserver((GameObserver) this);
		UI();

//		btnStart.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (btnStart.hasFocus()) {
//					btnStart.setFocusable(false);
//					btnStart.setFocusPainted(false);
//					btnStart.getRootPane().requestFocus();
//					gameboard.genetic();
//				}
//			}
//		});

	}

	public void UI() {

		setLayout(null);

		// add component
		initUI();

		// visible
		setTitle("SUDOKU ALGORITHM SOLVE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(350, 60);
		setSize(870, 666);
		setResizable(false);
	}

	public void initUI() {

		gameplay.setBounds(0, 0, GamePlay.WIDTH, GamePlay.HEIGHT);
		this.add(gameplay);

		lbFitness = new JLabel("Fitnesss: " + gameboard.getBest().fitness());
		lbFitness.setForeground(Color.GREEN);
		lbFitness.setFont(new Font("RESTART", Font.CENTER_BASELINE, 20));
		lbFitness.setBounds(650, 70, 170, 55);
		this.add(lbFitness);

		lbGeneration = new JLabel("Generation: " + 0);
		lbGeneration.setForeground(Color.GREEN);
		lbGeneration.setFont(new Font("RESTART", Font.CENTER_BASELINE, 20));
		lbGeneration.setBounds(650, 110, 170, 55);
		this.add(lbGeneration);

		btnRestart = new JButton("Restart");
		btnRestart.setBorderPainted(false);
		btnRestart.setBackground(Color.darkGray);
		btnRestart.setForeground(Color.LIGHT_GRAY);
		btnRestart.setBorder(new LineBorder(Color.BLACK));
		btnRestart.setFont(new Font("RESTART", Font.CENTER_BASELINE, 30));
		btnRestart.setBounds(650, 300, 170, 55);

		btnStart = new JButton("START");
		btnStart.setBorderPainted(false);
		btnStart.setBackground(Color.darkGray);
		btnStart.setForeground(Color.LIGHT_GRAY);
		btnStart.setBorder(new LineBorder(Color.BLACK));
		btnStart.setFont(new Font("RESTART", Font.CENTER_BASELINE, 30));
		btnStart.setBounds(650, 220, 170, 55);

		this.add(btnRestart);
		this.add(btnStart);
	}

	@Override
	public void update(Observable bo) {

		Gameboard g = (Gameboard) bo;
		Individual best = g.getBest();
		int generation = g.getGeneration();

		gameplay.setBest(best);
		lbFitness.setText("Fitness: " + best.fitness());
		lbGeneration.setText("Generation: " + generation);

		gameplay.repaint();
		repaint();
	}

	public static void main(String[] args) {
		Gameboard board = new Gameboard();
		GameView view = new GameView(board);
		view.setVisible(true);
	}

	public GamePlay getGameplay() {
		return gameplay;
	}

	public void setGameplay(GamePlay gameplay) {
		this.gameplay = gameplay;
	}

	public Gameboard getGameboard() {
		return gameboard;
	}

	public void setGameboard(Gameboard gameboard) {
		this.gameboard = gameboard;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public void setBtnRestart(JButton btnRestart) {
		this.btnRestart = btnRestart;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
		this.btnStart = btnStart;
	}

	public void addListener(ActionListener click) {
		btnStart.setRequestFocusEnabled(false);
		btnStart.addActionListener(click);
		btnRestart.addActionListener(click);
	}

	public JLabel getLbFitness() {
		return lbFitness;
	}

	public void setLbFitness(JLabel lbFitness) {
		this.lbFitness = lbFitness;
	}

	public JLabel getLbGeneration() {
		return lbGeneration;
	}

	public void setLbGeneration(JLabel lbGeneration) {
		this.lbGeneration = lbGeneration;
	}

}

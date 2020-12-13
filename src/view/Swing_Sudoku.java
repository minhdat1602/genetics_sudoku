package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Swing_Sudoku extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lb1 = new JLabel("SUDOKU");
	private JButton btn1 = new JButton("");
	private JButton btn2 = new JButton("");

	public Swing_Sudoku(String tilte) {
		super(tilte);
		int[] arr = new int[9];

		setLayout(new BorderLayout());

//		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p1 = new JPanel();

		p1.setLayout(new GridLayout(9, 7, 0, 0));
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j < arr.length; j++) {
				JLabel lb = new JLabel();
				lb.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
				Random rd = new Random();
				arr[j] = rd.nextInt(arr.length) + 1;

				lb.setText("     " + arr[j] + "     ");
				p1.add(lb);
			}
		}
		p2.setLayout(new GridLayout(5, 1, 10, 10));
		p2.add(lb1);
		lb1.setFont(new Font("Helvetica", Font.PLAIN, 30));
		p2.add(btn1);
		btn1.setBounds(200, 200, 10, 20);
		btn1.setIcon(new ImageIcon("src/images/newgame.png"));
		btn2.setIcon(new ImageIcon("src/images/exit.png"));
		btn1.setBackground(Color.GREEN);
		btn2.setBackground(Color.GREEN);
		btn1.setSize(10, 10);
		p2.add(btn2);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Swing_Sudoku("SUDOKU");
			}
		});
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		p1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(new JLabel(" "), BorderLayout.NORTH);
		add(new JLabel("       "), BorderLayout.WEST);
		add(new JLabel(" "), BorderLayout.SOUTH);
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.EAST);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Swing_Sudoku("SUDOKU");
	}
}

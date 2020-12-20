package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.comunity.Individual;

public class GamePlay extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color BG_COLOR = new Color(0xfdebb9);
	private static final String FONT_NAME = "Arial";
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 5;
	static final int WIDTH = 626;
	static final int HEIGHT = 626;

	private Individual best;

	public GamePlay(Individual best) {
		this.best = best;

		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				drawTile(g, best.getGenes()[x].getChromosomes()[y], y, x);
			}
		}
		g.dispose();
	}

	private void drawTile(Graphics g2, int chromosome, int x, int y) {
		Graphics2D g = ((Graphics2D) g2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		int value = chromosome;
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(new Color(0xe3cccc));
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 10, 10);
		g.setColor(new Color(0x332c2c));
		final int size = 40;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

	}

	private static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}

	public static void main(String[] args) {
		// add component
		JFrame fm = new JFrame();
		Individual best = new Individual();
		GamePlay gp = new GamePlay(best);

		gp.setLayout(null);
		gp.setBounds(10, 10, 720, 720);
		fm.add(gp);

		// visible
		fm.setTitle("Game 2048");
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fm.setLocation(550, 220);
		fm.setSize(650, 670);
		fm.setResizable(false);
		fm.setVisible(true);
	}

	public Individual getBest() {
		return best;
	}

	public void setBest(Individual best) {
		this.best = best;
	}

}

package model;

import java.util.Arrays;
import java.util.Random;

import model.comunity.Individual;
import model.comunity.Population;
import model.crossover.Crossover;
import model.crossover.SinglePointCrossover;
import model.mutation.FlipBitMutation;
import model.mutation.Mutation;
import model.selection.RankSelection;
import model.selection.Selection;

public class Gameboard {

	private Population currentGeneration;
	private Population nextGeneration;

	private Crossover crossover;
	private Mutation mutation;
	private Selection selection;

	private boolean found;
	private int generation;

	public Gameboard() {
		restart();
	}

	public void restart() {
		found = false;
		generation = 0;

		currentGeneration = new Population(200);
		currentGeneration.initPopulation();

		crossover = new SinglePointCrossover();
		mutation = new FlipBitMutation();
		selection = new RankSelection();
	}

	// Lai tạo quần thể tạo ra n cá thể con.
	// Lại tạo vòng.
	public Individual[] cycleCrossover(Individual[] individuals) {
		long st = System.currentTimeMillis();
		// PopCross: mảng lưu số cá thể con sau lai tạo.
		int individualNum = individuals.length;
		Individual[] popCross = new Individual[individualNum];

		// Khai báo cá thể con.
		Individual offspring;
		// Lai tạo
		for (int i = 0; i < individualNum; i++) {

			if (i == (individualNum - 1)) // cá thể cuối cùng thì đem lai với cá thể đầu tiên.
				offspring = crossover.cross(individuals[i], individuals[0]);
			else
				offspring = crossover.cross(individuals[i], individuals[i + 1]);

			popCross[i] = offspring;
		}
		// sắp xếp cho nó đẹp
		long et = System.currentTimeMillis();
		System.out.println("Crossover in: " + (et - st));
		return popCross;
	}

	/*
	 * Đột biến 1/2 số cá thể con. Đột biến xen kẽ: A B C D... + Đột biến A hoặc B +
	 * Đột biến C hoặc D
	 */
	public Individual[] largeScaleMutations(Individual[] individuals) {
		long st = System.currentTimeMillis();
		Random rd = new Random();
		// popMu: mảng lưu số cá thể con sau khi đột biến.
		int sizeOfPop = individuals.length;
		Individual[] popMu = new Individual[sizeOfPop / 2];

		// Khai báo cá thể con.
		Individual offspring;
		// Đột biến
		int begin = 0;
		for (int i = 0; i < sizeOfPop; i += 2) {
			offspring = mutation.cellLevel(individuals[i + rd.nextInt(1)]);
			popMu[begin++] = offspring;
		}
		// sắp xếp cho nó đẹp nà.
		// Arrays.sort(popMu);
		long et = System.currentTimeMillis();
		System.out.println("Mutation in: " + (et - st));
		return popMu;
	}

	public void initNewGenetation() {
		// Lai tạo được một số cá thể
		Individual[] popCross = cycleCrossover(currentGeneration.getIndividuals());
		// Sắp xếp các cá thể theo thứ tự xấu dần.

		// Lấy 1 nữa cá thể xấu nhất vừa lại tạo đi đột biến.
		Individual[] popMu = largeScaleMutations(popCross);

		// Chọn số lượng cá thể tốt vừa đột biến và lai tạo thành thế hệ mới
		Individual[] newIndividuals = selection.rankSelection(currentGeneration.getIndividuals().length, false,
				popCross, popMu);

		nextGeneration = new Population();
		nextGeneration.setIndividuals(newIndividuals);
	}

	public void genetic() {
		while (!found) {
			System.out.println("Generation: " + generation++);

			// Khởi tạo thế hệ mới.
			initNewGenetation();

			System.out.println("Old:  " + currentGeneration.tostring());
			System.out.println("New:  " + nextGeneration.tostring());

			// Kiểm tra thế hệ mới có cá thể cần tìm không?
			if (found = checkGoal(nextGeneration.getIndividuals()))
				break;

			// Chọn lọc số lượng cá thể ok nhất.
			Individual[] nextIn = selection.rankSelection(currentGeneration.getIndividuals().length, true,
					nextGeneration.getIndividuals(), currentGeneration.getIndividuals());
			// Khởi tạo quần thể dùng để tiếp tục di truyền.
			currentGeneration = new Population();
			currentGeneration.setIndividuals(nextIn);

			System.out.println("Next: " + currentGeneration.tostring());
			System.out.println("-------------------------------------------------------------------------"
					+ "-----------------------------------------------------------------------");
		}
	}

	public boolean checkGoal(Individual[] individuals) {
		for (Individual in : individuals) {
			if (in.fitness() == 0) {
				System.out.println("Destination individual is:" + in.toString());
				System.out.println("------------END----------------");
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Gameboard gb = new Gameboard();
		long st = System.currentTimeMillis();
		gb.genetic();
		long et = System.currentTimeMillis();
		System.out.println("Time sovle: " + (et - st) / 1000 + "s");

	}
}

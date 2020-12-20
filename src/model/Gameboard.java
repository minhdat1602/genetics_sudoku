package model;

import java.util.ArrayList;
import java.util.Random;

import model.comunity.Individual;
import model.comunity.Population;
import model.crossover.Crossover;
import model.crossover.SinglePointCrossover;
import model.mutation.FlipBitMutation;
import model.mutation.Mutation;
import model.selection.RankSelection;
import model.selection.Selection;
import view.GameObserver;

public class Gameboard implements Observable {

	private Population currentGeneration;
	private Population nextGeneration;

	private Crossover crossover;
	private Mutation mutation;
	private Selection selection;

	private Individual best;

	private ArrayList observers;

	private boolean found;
	private int generation;

	private Individual defaultIn;

	public Gameboard() {
		int[][] chromosomes = new int[][] { { 0, 0, 7, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 6, 8, 0, 3, 0, 2 },
				{ 0, 0, 0, 2, 0, 4, 0, 5, 7 }, { 0, 3, 2, 4, 7, 9, 6, 8, 5 }, { 0, 0, 0, 1, 6, 0, 0, 0, 4 },
				{ 0, 6, 0, 0, 0, 0, 0, 1, 9 }, { 0, 7, 0, 0, 4, 0, 0, 0, 0 }, { 3, 0, 9, 0, 2, 0, 8, 5, 1 },
				{ 0, 5, 6, 8, 0, 1, 0, 7, 0 } };
		defaultIn = new Individual(chromosomes);

		observers = new ArrayList();

		restart();
	}

	public Gameboard(Individual defaultIn) {
		this.defaultIn = defaultIn;
	}

	public void restart() {
		found = false;
		generation = 0;

		currentGeneration = new Population(5000);
		currentGeneration.initPopulation(defaultIn);

		crossover = new SinglePointCrossover();
		mutation = new FlipBitMutation();
		selection = new RankSelection();

		best = currentGeneration.getBest();
		changeBest(best);
	}

	// Lai tạo quần thể tạo ra n cá thể con.
	// Lại tạo vòng.
	public Individual[] cycleCrossover(Individual[] individuals) {
		long st = System.currentTimeMillis();
		// PopCross: mảng lưu số cá thể con sau lai tạo.
		int inNum = individuals.length;
		Individual[] popCross = new Individual[inNum];

		// Khai báo cá thể con.
		Individual offspring;
		// Lai tạo
		for (int i = 0; i < inNum; i++) {

			if (i == (inNum - 1)) // cá thể cuối cùng thì đem lai với cá thể đầu tiên.
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
//			try {
//				Thread.sleep(400);
//			} catch (InterruptedException ex) {
//				Thread.currentThread().interrupt();
//			}

			// Khởi tạo thế hệ mới.
			initNewGenetation();

			// notify observer
			best = this.nextGeneration.getBest();
			changeBest(best);

			System.out.println("Old:  " + currentGeneration.tostring());
			System.out.println("New:  " + nextGeneration.tostring());
			System.out.println("Generation: " + generation++ + " th");

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

	// notify observer when tiles change

	public void changeBest(Individual best) {
		this.best = best;

		notifyObservers();
	}

	public void changeGeneration(int generation) {
		generation = this.generation;
	}

	public void change(Gameboard gb) {
		gb = this;

		notifyObservers();
	}

	public static void main(String[] args) {
		Gameboard gb = new Gameboard();
		long st = System.currentTimeMillis();
		gb.genetic();
		long et = System.currentTimeMillis();
		System.out.println("Time sovle: " + (et - st) / 1000 + "s");
	}

	public void removeObserver(GameObserver go) {
		int i = observers.indexOf(go);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			GameObserver go = (GameObserver) observers.get(i);
			go.update(this);
		}
	}

	public void registerObserver(GameObserver go) {
		observers.add(go);
	}

	public Population getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(Population currentGeneration) {
		this.currentGeneration = currentGeneration;
	}

	public Population getNextGeneration() {
		return nextGeneration;
	}

	public void setNextGeneration(Population nextGeneration) {
		this.nextGeneration = nextGeneration;
	}

	public Crossover getCrossover() {
		return crossover;
	}

	public void setCrossover(Crossover crossover) {
		this.crossover = crossover;
	}

	public Mutation getMutation() {
		return mutation;
	}

	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}

	public Selection getSelection() {
		return selection;
	}

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public Individual getBest() {
		return best;
	}

	public void setBest(Individual best) {
		this.best = best;
	}

	public ArrayList getObservers() {
		return observers;
	}

	public void setObservers(ArrayList observers) {
		this.observers = observers;
	}

	public Individual getDefaultIn() {
		return defaultIn;
	}

	public void setDefaultIn(Individual defaultIn) {
		this.defaultIn = defaultIn;
	}

}

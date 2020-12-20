package model.selection;

import java.util.ArrayList;
import java.util.Collections;

import model.comunity.Individual;
import model.comunity.Population;

public class RankSelection implements Selection {

	public RankSelection() {
	}

	public Individual[] rankSelection(int rank, boolean check, Individual[]... in) {
		long st = System.currentTimeMillis();

		// Mảng lưu tổng số cá thể đột biến và lai tạo.
		ArrayList<Individual> inSum = new ArrayList<Individual>();
		for (int i = 0; i < in.length; i++) {
			Individual[] inElement = in[i];
			for (int j = 0; j < inElement.length; j++) {
				// Kiểm tra cá thể được chọn hay chưa (Cá thể giống nhau thì chọn 1)
				if (check) {
					if (isConstaint(inSum, inElement[j]))
						continue;
				}
//				for (int x = 0; x < 9; x++) {
//					System.out.println("Gene: " + x);
//					System.out.println("Index " + inElement[j].getGenes()[x].getDefaultIndexs());
//					System.out.println("Value" + inElement[j].getGenes()[x].getDefaultValues());
//					System.out.println("-----------------------------------------");
//				}
				inSum.add(inElement[j]);
			//	System.out.println("--------------------------");
			//	System.out.println(inElement[j].toString());
			}
		}
		// Xếp hàng, cá thể nào ngon đứng trước.
		Collections.sort(inSum);

		Individual[] inRank = new Individual[rank];
		// Chọn số rank cá thể ok nhất.
		for (int i = 0; i < rank; i++) {
			inRank[i] = inSum.get(i);
		}

		long et = System.currentTimeMillis();
		System.out.println("Selection in: " + (et - st));

		return inRank;
	}

	public boolean isConstaint(ArrayList<Individual> ins, Individual in) {
		for (int i = 0; i < ins.size(); i++) {
			if ((ins.get(i).equals(in))) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		RankSelection selection = new RankSelection();

		Population oldp = new Population(100);
		oldp.initPopulation();
		System.out.println("Old:  " + oldp.tostring());

		Population newp = new Population(100);
		newp.initPopulation();
		System.out.println("New:  " + newp.tostring());

		Population nextp = new Population();
		long st = System.currentTimeMillis();
		Individual[] nextIn = selection.rankSelection(oldp.getIndividuals().length, true, newp.getIndividuals(),
				oldp.getIndividuals());
		long et = System.currentTimeMillis();
		System.out.println("Time in: " + (et - st));

		nextp.setIndividuals(nextIn);

		System.out.println("Next: " + nextp.tostring());
	}
}

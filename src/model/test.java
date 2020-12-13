package model;

public class test {
	public static void main(String[] arg) {
		long st = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
		}
		long et = System.currentTimeMillis();

		System.out.println("Time in: " + (et - st));
	}
}

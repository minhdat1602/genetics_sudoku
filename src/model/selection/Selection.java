package model.selection;

import model.Individual;

public interface Selection {

	public Individual[] rankSelection(int rank, Individual[]... in);

}

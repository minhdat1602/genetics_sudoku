package model.selection;

import model.comunity.Individual;

public interface Selection {

	public Individual[] rankSelection(int rank, Individual[]... in);

}

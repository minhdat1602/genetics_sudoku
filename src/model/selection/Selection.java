package model.selection;

import model.comunity.Individual;

public interface Selection {

	public Individual[] rankSelection(int rank, boolean check, Individual[]... in);

}

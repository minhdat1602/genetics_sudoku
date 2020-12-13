package model.crossover;

import model.comunity.Individual;

public interface Crossover {

	public Individual cross(Individual dad, Individual mom);

}

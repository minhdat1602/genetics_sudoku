package model.crossover;

import model.comunity.Individual;
import model.comunity.Population;

public interface Crossover {

	public Individual cross(Population population);

}

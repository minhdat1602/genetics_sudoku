package model.crossover;

import model.Individual;
import model.Population;

public interface Crossover {

	public Individual cross(Population population);

}

package model.mutation;

import model.comunity.Individual;

public interface Mutation {

	public Individual oneGeneMutation(Individual individual);

	public Individual twoGeneMutation(Individual individual);
}

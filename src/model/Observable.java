package model;

import view.GameObserver;

public interface Observable {
	public void removeObserver(GameObserver go);

	public void notifyObservers();

	public void registerObserver(GameObserver go);
}

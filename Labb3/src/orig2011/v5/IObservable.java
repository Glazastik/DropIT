package orig2011.v5;

import java.beans.PropertyChangeListener;

/**
 * An interface for observing
 */
public interface IObservable {
	
	/**
	 * Add an observer to the list
	 * @param observer the observer you want to add 
	 */
	void addObserver(PropertyChangeListener observer);
	
	/**
	 * Remove observer
	 * @param observer the observer to be removed
	 */
	void removeObserver(PropertyChangeListener observer);
}

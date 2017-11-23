package project.othello.breton.util;

import project.othello.breton.model.OthelloImpl;

/**
 * Interface to implement the observer/observable pattern.
 * 
 * @author Gabriel Breton - 43397
 */
public interface Observer {
    
    /**
     * Updates an observer.
     */
    public void update();      
}

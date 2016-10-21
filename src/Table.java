/**
 * The table class
 * 
 * @author Bhavik Tailor 100973983
 */

import java.util.*;

public class Table
{
	private List<Ingredient> ingredients = new ArrayList<>();
	
	/**
	 * Puts a list of ingredients on the table if the table is not already full
	 * 
	 * @param i: the list of ingredients to be added
	 */
	public synchronized void put(List<Ingredient> i){
		while(isFull()){
			try{
				wait();
			} catch (Exception e){
				return;
			}
		}
		ingredients.addAll(i);
		notifyAll();
	}
	
	/**
	 * Returns the items on the table if the ingredient passed is not within the list or it's not empty
	 * 
	 * @param i: the ingredient to be checked if it's not on the list
	 * @return: a list containing items on the table
	 */
	public synchronized List<Ingredient> get(Ingredient i){
		//checks if the list is empty or if it contains the passed item
		while(ingredients.isEmpty() || ingredients.contains(i)){
			try{
				wait();
			} catch (Exception e){
				return null;
			}
		}
		
		List<Ingredient> returnList = new ArrayList<>(ingredients);
		ingredients.clear();
		notifyAll();
		return returnList;
	}
	
	/**
	 * Checks if the list contains 2 or more items to check if it is full
	 * 
	 * @return: Boolean saying if it is full or not
	 */
	private boolean isFull(){
		if (ingredients.size() >= 2){
			return true;
		}
		return false;
	}

}

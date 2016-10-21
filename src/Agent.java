/**
 * The agent class
 * 
 * @author Bhavik Tailor 100973983
 */

import java.util.*;

public class Agent extends Thread {
	private Random random;
	private Ingredient ingredient1;
	private Ingredient ingredient2;
	private List<Ingredient> ingredientsArr;
	private Table table;
	
	/**
	 * Creates an agent
	 * 
	 * @param t: the table the agent will be put ingredients on
	 */
	public Agent(Table t){
		this.random = new Random();
		this.ingredientsArr = new ArrayList<>();
		this.table = t;
		
	}
	
	/**
	 * The agent thread's run method
	 */
	@Override
	public void run(){
		for(int i = 0; i < 20; i++){
			boolean same = true;
			this.ingredient1 = randomEnum(Ingredient.class); //takes a random ingredient from the Ingredient enum
			ingredientsArr.add(ingredient1); //adds it to the list of ingredients
			while(same){ //makes sure the second ingredient isn't the same 
				ingredient2 = randomEnum(Ingredient.class);
				if (ingredient2 != ingredient1){
					same = false;
					ingredientsArr.add(ingredient2);
				}
			}
			System.out.println("Sandwich: " + (i + 1) + " The table has " + ingredient1.toString() + " and " + ingredient2.toString());
			table.put(ingredientsArr); //puts the ingredient on the table
			try{
				Thread.sleep(400); //sleeps the thread for processor time
			} catch (Exception e){
				e.printStackTrace();
			}
			ingredientsArr.clear(); //clears the ingredients array for next set of ingredients to be added
		}
	}
	
	/**
	 * method to get a random enum from the ingredient enum
	 * 
	 * @param enumClass: the enum passed as a class
	 * @return: the random enum
	 */
	private <T extends Enum<?>> T randomEnum(Class<T> enumClass){
		int x = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[x];
	}
}

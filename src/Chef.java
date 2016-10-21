/**
 * The chef class
 * 
 * @author Bhavik Tailor 100973983
 *
 */

public class Chef extends Thread{
	private Ingredient ingredient;
	public String identity;
	private Table table;
	
	/**
	 * creates a new chef
	 * 
	 * @param i: The chef's ingredient
	 * @param t: the table they are receiving info from
	 * @param identity: the name of the chef
	 */
	public Chef(Ingredient i, Table t, String identity){
		this.ingredient = i;
		this.table = t;
		this.identity = identity;
	}
	
	/**
	 * The run method for the chef threads
	 */
	@Override
	public void run(){
		while(true){
			table.get(ingredient); //gets the ingredient from the table, will not get if the ingredient is already on the table
			System.out.println(identity + " has the missing ingredient, " + ingredient.toString() + "."); 
			System.out.println(identity + " has eaten the sandwich. \n");
			try {//sleeps the thread
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * gets the ingredient 
	 * 
	 * @return: the ingredient
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public static void main(String args[]){
		Table table = new Table();
		Agent agent = new Agent(table);
		Chef chef1 = new Chef(Ingredient.Bread, table, "Bobbo");
		Chef chef2 = new Chef(Ingredient.Cheese, table, "Stevo-o");
		Chef chef3 = new Chef(Ingredient.Ham, table, "Alex");
		
		agent.start();
		chef1.start();
		chef2.start();
		chef3.start();
	}
}

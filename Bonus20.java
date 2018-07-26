import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bonus20 {
	String universalVariable = "Hello World";	
	static HashMap<String, Double> inventory = new HashMap<>();		
	
	public static void main(String[] args) {				
		
		Scanner scnr = new Scanner(System.in);
		ArrayList<String> shoppingCart = new ArrayList<String>();
		boolean shouldContinue = true;
		
		// key is item as a String and value is price as a double		
		// Fills the inventory
		inventory = fillInventory(inventory);				
				
		//scanner, inventory, prompt
		// Could return a String, then add to ShoppingCart
		
		while (shouldContinue == true) {
			// Display inventory ();
			displayInventory(inventory);
			
			String prompt = "What would you like to order? ";
			String newItem = enterItem(scnr, inventory, prompt);
			
			//If newItem is null, we need to skip one loop
			if (newItem == null) {				
				continue;
			}
			
			shoppingCart.add(newItem);
			System.out.println("Add another item? (y/n)");
			String userAnswer = scnr.nextLine();
			if (userAnswer.equals("n")) {
				
				System.out.println("This is your cart");
				System.out.println(shoppingCart);
				
				// average(); this displays average price of all items
				average(shoppingCart);
				//highest (); this displays highest price item
				highest(shoppingCart);
				//lowest (); this displays lowest price item
				lowest(shoppingCart);
				//eventually break loop to end program
			}
		}			
	}

	private static void lowest(ArrayList<String> shoppingCart) {
		double min = 0;
		String leastExpensiveItem = "";
		
		for (String cartItem : shoppingCart) {
			// sum get from the inventory,
			// the value of the key in this case fruit
			double cost = inventory.get(cartItem);
			if (cost < min) {
				min = cost;
				leastExpensiveItem = cartItem;
			}				
		}
		
		System.out.println("The least expensive item is " + leastExpensiveItem);			
	}

	private static void highest(ArrayList<String> shoppingCart) {		
		double max = 0;
		String mostExpensiveItem = "";
		
		for (String cartItem : shoppingCart) {
			// sum get from the inventory,
			// the value of the key in this case fruit
			double cost = inventory.get(cartItem);
			if (cost > max) {
				max = cost;
				mostExpensiveItem = cartItem;
			}				
		}
		
		System.out.println("The most expensive item is " + mostExpensiveItem);		
	}

	private static void average(ArrayList<String> shoppingCart) {
		
		double sum = 0; // the double price		
		double count = 0; // the number of individual items 
		for (String cartItem : shoppingCart) {
			// sum get from the inventory,
			// the value of the key in this case fruit
			double cost = inventory.get(cartItem);
			sum += cost;
			count++;			
		}
		
		System.out.println("Average price per item in order was " + sum/count);
	}

	private static String enterItem(Scanner scnr, HashMap<String, Double> inventory, String prompt) {
		
		String userInput;		
		System.out.println(prompt);		
		userInput = scnr.nextLine();
		
		//if userInout is in our inventory, then return userInput
		if (inventory.containsKey(userInput) == true) { // true is redundant 
			Double cost = inventory.get(userInput);
			System.out.println("Adding " + userInput + " to cart at $" + cost); 			
			// else, repeat the method
		} else {
			System.out.println("Sorry, we don't have those. Please try again.");	
			userInput = null;
		}
		return userInput;
	}		

	private static void displayInventory(HashMap<String, Double> inventory) {
		// we want to use a for loop!
		String format = "%s\t\t%s";
		String format2 = "%-10s     %s";
		System.out.printf(format, "Item" , "Price");
		System.out.println();
		System.out.println("===========================");
		
		
		//loop through our inventory, by key (which is the list of fruits
		for (String fruit : inventory.keySet() ) {
			// this line prints out for each individual fruit
			System.out.printf(format2,  fruit, inventory.get(fruit) + "\n");
//			System.out.println(fruit + " " + inventory.get(fruit));
		}
	}
	
	private static HashMap<String, Double> fillInventory(HashMap<String, Double> inventory) {		
		inventory.put("apple", 0.99);
		inventory.put("banana", 0.59);
		inventory.put("cauliflower", 1.59);
		inventory.put("dragonfruit", 2.19);
		inventory.put("figs", 2.09);
		inventory.put("grapefruit", 1.99);				
		
		return inventory;
	}
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bonus20 {
	static HashMap<String, Double> inventory = new HashMap<>();		
	
	public static void main(String[] args) {				
		
		Scanner scnr = new Scanner(System.in);
		ArrayList<String> shoppingCart = new ArrayList<String>();		
		
		// First we must create and fill out an inventory of available items in our little market
		inventory = fillInventory(inventory);				
	
		boolean shouldContinue = true;
		// This loop will run as long as the user wishes to do so
		while (shouldContinue == true) {
			// Now displaying the created inventory so the user has choices
			displayInventory(inventory);
			
			String prompt = "What would you like to order? ";
			// Calling method enterItem so user can 
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
				
				// This displays average price of all items
				average(shoppingCart);
				// This displays the  most expensive item
				highest(shoppingCart);
				// This displays the cheapest item
				lowest(shoppingCart);
				
				shouldContinue = false;
			}
		}	
		System.out.println("Thanks for buying at 'Ye Olde Store'");		
	}

	private static void lowest(ArrayList<String> shoppingCart) {
		double min = 9999;
		String leastExpensiveItem = "";
		
		for (String cartItem : shoppingCart) {
			// Storing cartItem value inside of double variable 'cost'
			double cost = inventory.get(cartItem);
			// Determining that as long as the cost is less than the minimum, we keep storing until there are no more values
			if (cost < min) {
				min = cost;
				leastExpensiveItem = cartItem;
			}				
		}
		
		System.out.println("The least expensive item is " + leastExpensiveItem + ".");			
	}

	private static void highest(ArrayList<String> shoppingCart) {		
		double max = 0;
		String mostExpensiveItem = "";
		
		for (String cartItem : shoppingCart) {
			// Storing cartItem value inside of double variable 'cost'
			double cost = inventory.get(cartItem);
			// Determining that as long as the cost is more than the maximum,  we keep storing until there are no more values
			if (cost > max) {
				max = cost;
				mostExpensiveItem = cartItem;
			}				
		}
		
		System.out.println("The most expensive item is " + mostExpensiveItem + ".");		
	}

	private static void average(ArrayList<String> shoppingCart) {
		
		double sum = 0; // Price variable needs to be a double to print out cents.	
		double count = 0; 
		for (String cartItem : shoppingCart) {

			double cost = inventory.get(cartItem);
			// Storing the addition of each item inside sum
			sum += cost; 
			count++; // To count each item.			
		}
		
		System.out.println("Average price per item in order was $" + sum/count);
	}

	private static String enterItem(Scanner scnr, HashMap<String, Double> inventory, String prompt) {
		
		String userInput;		
		System.out.println(prompt);		
		userInput = scnr.nextLine();
		
		// If userInput is in our inventory, then return userInput
		if (inventory.containsKey(userInput)) {
			// Storing the cost (value called with key) from inventory in a variable for legibility
			Double cost = inventory.get(userInput);
			System.out.println("Adding " + userInput + " to cart at $" + cost); 			
			// Else, if userInput is not in the inventory, display error message and return null (so we can return to the loop
		} else {
			System.out.println("Sorry, we don't have those. Please try again.");	
			userInput = null;
		}
		return userInput;
	}		

	private static void displayInventory(HashMap<String, Double> inventory) {
		
		String format = "%s\t\t\t\t%s";
		String format2 = "%-20s             %s";
		System.out.println("~~~~~~~Welcome to 'Ye Olde Store'~~~~~~\nThis is our available produce for today:");
		System.out.println("=====================================");
		System.out.printf(format, "Item" , "Price");
		System.out.println();
		System.out.println("=====================================");
		
		// This will loop through our inventory by key (list of available produce)
		for (String cartItem : inventory.keySet() ) {
			// This line prints out for each individual item
			System.out.printf(format2,  cartItem, inventory.get(cartItem) + "\n");
		}
	}
	
	private static HashMap<String, Double> fillInventory(HashMap<String, Double> inventory) {
		// This fills out the inventory hashmap collection
		
		inventory.put("apple", 0.99);
		inventory.put("banana", 0.59);
		inventory.put("cauliflower", 1.59);
		inventory.put("dragonfruit", 2.19);
		inventory.put("figs", 2.09);
		inventory.put("grapefruit", 1.99);				
		inventory.put("horseradish", 1.79);	
		inventory.put("ginger", 1.15);
		inventory.put("tomatoes", 2.39);
		inventory.put("spinach", .89);
		
		return inventory;
	}
}

package chatDelivery;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterpreterDemo {
	
	public String accesRegex="(?i)^give me.*list of products.*$";
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        String input = sc.nextLine();
	        String regex = "(?i)^give me.*list of products.*$"; // case-insensitive regex pattern
	        if (input.matches(regex)) {
	            // input matches the regex pattern
	            // do something here
	            //Expression listItemsExpression = new ListItemsExpression();


	            //List<String> items = listItemsExpression.interpret(input);

	            /*if (items.isEmpty()) {
	                System.out.println("Sorry, I don't understand.");
	            } else {
	                System.out.println("products that exist:");
	                for (String item : items) {
	                    System.out.println(item);
	                }
	            }
	        } else {
	            // input does not match the regex pattern
	            // do something else here
	            System.out.println("Sorry, I don't understand.");
	        }*/

	        /*Scanner sc = new Scanner(System.in);
	        String input =sc.next();*/
	        //String input = "give me list of items that exist";

	    }

	
	}
}


   

	interface Expression {
	    List<String> interpret(String context);
	}
	
	
	
	class ListItemsExpression implements Expression {
		connectionDatabase con = new connectionDatabase();
		public String accesRegex="(?i)^give me.*list of products.*$";
	    @Override
	    public List<String> interpret(String context) {
	        List<String> items = new ArrayList<>();
	        String allProducts = con.selectAllProducts();
	        String[] productsArray = allProducts.split("\n");
	        for (String product : productsArray) {
	            items.add(product);
	        }

	        String specificProduct = con.getSpecificProduct(accesRegex);
	        items.add(specificProduct);

	        return items;
	    }
	}

	
	/*class ListItemsExpression implements Expression {
	    private List<String> items;

	    @Override
	    public List<String> interpret(String context) {
	        items = new ArrayList<>();
	        if (context.contains("exist")) {
	            // Fetch items from the database
	            items.add("Creme hydratante 100ml");
	            items.add("Serum vitamin C");
	        }
	        return items;
	    }
	}*/


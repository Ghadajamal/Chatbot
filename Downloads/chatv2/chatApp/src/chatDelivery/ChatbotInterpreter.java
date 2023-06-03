package chatDelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Expression {
    String interpret(String input);
}

	class RegexExpression implements Expression {
	    private Pattern pattern;
	    private String response;
	    private String regex;
		//public String getResponse;

	    public RegexExpression(String regex, String response) {
	        pattern = Pattern.compile(regex);
	        this.response = response;
	        this.regex = regex;
	    }
	    
	    //GETTERS AND SETTERS
	    public String getResponse() {
	        return response;
	    }

	    public void setResponse(String response) {
	        this.response = response;
	    }
	    
	    public String getRegex() {
	        return regex;
	    }

	    public void setRegex(String regex) {
	        this.regex = regex;
	        pattern = Pattern.compile(regex);
	    }
	    
	    

	    public String interpret(String input) {
	        Matcher matcher = pattern.matcher(input);
	        if (matcher.matches()) {
	            // Check if the regex has capturing groups
	            int numGroups = matcher.groupCount();
	            if (numGroups > 0) {
	            	System.out.println("test");
	                // Extract the first capturing group (group 1)
	                String productName = matcher.group(1);
	                return response.replaceAll("product name", productName);
	            } else {
	            	System.out.println("hola");
	                return response;
	            }
	        }
	        return null;
	    }
	}


class OrExpression implements Expression {
    private List<Expression> expressions;

    public OrExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public String interpret(String input) {
        for (Expression expression : expressions) {
            String response = expression.interpret(input);
            if (response != null) {
                return response;
            }
        }
        System.out.println("ha anaaa");
        return "Sorry, I didn't understand.";
    }
}

// Example usage
public class ChatbotInterpreter {
    public static void main(String[] args) {
        // Define regular expression patterns and responses
        List<RegexExpression> regexExpressions = new ArrayList<>();
        regexExpressions.add(new RegexExpression("(?i)^hi.*", "Hello! How can I assist you?"));
        regexExpressions.add(new RegexExpression("(?i)^hello.*", "Hello! How can I assist you?"));
        regexExpressions.add(new RegexExpression("(?i).*I want.*", "The price is $10."));
      //  regexExpressions.add(new RegexExpression("(?i)^want.*", "Yes, we have that item in stock."));

        // Combine expressions with logical operators
        List<Expression> expressions = new ArrayList<>();
        expressions.addAll(regexExpressions);
        Expression orExpression = new OrExpression(expressions);

        // Interpret inputs based on the defined expressions
        String message1 = "hi";
        String message2 = "How much is this item?";
        String message3 = "Do you have this item in stock?";
        String message4 = "I want something special";

        System.out.println(orExpression.interpret(message1)); // Hello! How can I assist you?
        System.out.println(orExpression.interpret(message2)); // The price is $10.
        System.out.println(orExpression.interpret(message3)); // Yes, we have that item in stock.
        System.out.println(orExpression.interpret(message4)); // Sorry, I didn't understand.
    }
}

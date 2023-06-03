package chatDelivery;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import raven.chat.component.ChatArea;
import raven.chat.component.ChatBox;
import raven.chat.model.ModelMessage;
import java.util.*;

import java.util.Observable;
import java.util.Observer;


public class Communication {

//Adding a constructor to use it in the decorator class
	/*public Communication(Communication communication) {
		// TODO Auto-generated constructor stub
	}*/

	public void generateTheReponseForThis(String message ,ChatArea chatArea) {
		//connection database 
		connectionDatabase con = new connectionDatabase();
		//Observer observer = new Observer();
		 Observable observable = new Observable();
		 EmailNotifier emailNotifier = new EmailNotifier();
		
		
		// Define regular expression patterns and responses
        List<RegexExpression> regexExpressions = new ArrayList<>();
        //HEY & BYE STATEMENTS
        regexExpressions.add(new RegexExpression("(?i)^hey.*$", "Hello! how can I assist you today?"));
        regexExpressions.add(new RegexExpression("(?i)^I have some questions.*$", "Please feel free to ask !"));
        regexExpressions.add(new RegexExpression("(?i)^Good bye.*$", "I hope that was helpful see you later"));
        regexExpressions.add(new RegexExpression("(?i)^Good bye.*$", "I hope that was helpful see you later"));
        //GIVE ME THE LIST OF PRODUCTS THAT EXIST
        regexExpressions.add(new RegexExpression("(?i)^give me.*list of products.*$", con.selectAllProducts()));
        regexExpressions.add(new RegexExpression("(?i)^what.*products.*have.*in store.*$", con.selectAllProducts()));
        //GIVE THE LIST OF ONLY AVAILABLE PRODUCTS
        regexExpressions.add(new RegexExpression("(?i)^give me.*list of available products.*$", con.selectOnlyAvailableProducts()));
        regexExpressions.add(new RegexExpression("(?i)^i want.*see.*available.*products.*$", con.selectOnlyAvailableProducts()));
        //I WANT TO KNOW THE STATE ABOUT MY COMMAND
        regexExpressions.add(new RegexExpression("(?i)^i want.*know.*state.*my order.*$", "I see that you want to pursue the state of your command. is that right?"));
        //regexExpressions.add(new RegexExpression("(?i)^exactly.*$", "alright would you please provide us with the id of your command ?"));
        
        //GET SPECIFIC CITY
        
       
        
        //GET SPECIFIC PRODUCT 
        String product = null;
        for (String word : message.split(" ")) {
            if (con.selectAllProducts().contains(word)) {
                product = word;
                break;
            }
        }
   
        if (product != null) {
            regexExpressions.add(new RegexExpression("(?i)^does.*" + product + ".*exist in stock\\?$", con.getSpecificProduct(product)));
           
        }
        //implementation of observer design pattern
        regexExpressions.add(new RegexExpression("(?i)^yes.*$", con.approve()));
        //String email = "jamalghada85@gmail.com";
        //regexExpressions.add(new RegexExpression(email, " We will notify you at" + email + "to confirm your request"));
       // observable.addObserver(emailNotifier);
        //observable.notifyObservers(email);
        //FIN TEST 
        //NEW TEST 
     // Start the chatbot loop
        regexExpressions.add(new RegexExpression("^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b$", con.sendToEmail(message)));
        
        		// Create the observable and add the email notifier as observer
   
        		observable.addObserver(new EmailNotifier());

        		// Get the email address from the regex expression and notify the observer
        		for (RegexExpression expression : regexExpressions) {
        			//message="jamalghada85@gmail.com";
        		    if (message.matches(expression.getRegex())) {
        		        String output = expression.getResponse();
        		        System.out.println("hey this is communication");
        		        //observable.notifyObservers(output);
        		        emailNotifier.update( observable, message);
        		        break;
        		    }
        		}
        		
        		
        		
                List<Expression> expressions = new ArrayList<>();
                expressions.addAll(regexExpressions);
                Expression orExpression = new OrExpression(expressions);
                

                 makeTheReponse(message,"Guess",chatArea);
        	   	 String answer = (String) orExpression.interpret(message);
        	   	 makeTheReponse(answer,"Bot",chatArea);
        }
        
       /* while (true) {
            System.out.print("User: ");
            String input = scanner.nextLine();

            // Check if the input requires notification
            if (input.matches("^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b$")) {
                message = input.substring(0);
                System.out.println("Chatbot: " + "Okay, I'll notify you at " + message + " to confirm your request.");
                observable.notifyObservers(message);
                continue;
            }*/

            // Handle other input
            /*System.out.println("Chatbot: " + "I'm sorry, I didn't understand your request.");*/
        
        
        
        
        //TO KEEP
        /*
        String product = "";  // Initialize the product variable

        // Check if the message contains a product name
        if (message.contains("Electric")) {
            // Extract the product name from the message and assign it to the product variable
            product = "Electric";  // Replace "product name" with the actual product name
        }

        
        regexExpressions.add(new RegexExpression("(?i)^does.*"+product+".*exist in stock\\?$", con.getSpecificProduct(product)));*/

    
	
	
	public String splittheword(String sentence) {
		String[] words = sentence.split(" "); // split the sentence into an array of words
		String wordAfterDoes = null;

		for (int i = 0; i < words.length - 1; i++) {
		    if (words[i].equalsIgnoreCase("does")) {
		        wordAfterDoes = words[i + 1];
		        break;
		    }
		}

		return wordAfterDoes;
	}
	
	// to display the reponse of the chatbot
    public void makeTheReponse(String textToAdd  , String user ,ChatArea chatArea) {
    	
        Icon iconUser = new ImageIcon(getClass().getResource("../p1.png"));
        Icon iconBot = new ImageIcon(getClass().getResource("../bot.png"));
    	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy, hh:mmaa");
        String date = df.format(new Date());
    	String pending ="Chatbot is writing...";

		if (user == "Bot" ) {
			ModelMessage mymsg = new ModelMessage(iconBot, "Bot", date, pending);
	    	ChatBox MyChatbox = new ChatBox(ChatBox.BoxType.LEFT,mymsg);
	        chatArea.addChatBox(mymsg, ChatBox.BoxType.LEFT);
	        Timer timer = new Timer(1300, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // remove the Chatbot writing bubble from the chatPanel
	                chatArea.removeChatBox(MyChatbox);
	                chatArea.addChatBox(new ModelMessage(iconBot, "Bot", date, textToAdd), ChatBox.BoxType.LEFT);
	                chatArea.clearTextAndGrabFocus();

	            }
	        });
	        timer.setRepeats(false);
	        timer.start();
		}else {
			chatArea.addChatBox(new ModelMessage(iconUser, "Guess", date, textToAdd), ChatBox.BoxType.RIGHT);
	        chatArea.clearTextAndGrabFocus();
		}
    	
        
    }
}


//TEST BEGIN 
// if (checkRegx1(message) {
// 	makeTheReponse("hi there","Bot",chatArea);
// }
//my quetion : does this product (ex :iphone )  exist in stock??
 //String product = "Electric";
//TEST END 
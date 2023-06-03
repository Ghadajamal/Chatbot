package chatDelivery;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connectionDatabase {
	public static boolean enable = false;
	public static boolean enable1 = false;
	
    public static Connection connectionDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/DeliveryChatbot", "root", ""
            );
            System.out.println("connected successfully");
            return connection;

        } catch (Exception e) {
        	System.out.println("test");
            System.out.println(e);
        }
		return null;
    }
    

    
    public static String selectAllProducts() {
        try {
        	
        	Connection connection = connectionDb();
        	String allProducts ="";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");

            while (resultSet.next()) {
                // retrieve data from result set
                int id = resultSet.getInt("id_product");
                String name = resultSet.getString("name");
                allProducts +=  name + "\n";
               //System.out.println(id + " " + name + " " );

                // print out the data
            }
            

            // close the connections
            resultSet.close();
            statement.close();
            connection.close();
            return allProducts ;
           
        } catch (Exception e) {
            System.out.println(e);
        }
	
       return null; 
    }
    
    public static String selectOnlyAvailableProducts() {
        try {
        	
        	Connection connection = connectionDb();
        	String available ="";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product WHERE stock > 0");

            while (resultSet.next()) {
                // retrieve data from result set
                int id = resultSet.getInt("id_product");
                String name = resultSet.getString("name");
                
                available +=  name + "\n";

                // print out the data
            }

            // close the connections
            resultSet.close();
            statement.close();
            connection.close();
            return available;
        } catch (Exception e) {
            System.out.println(e);
        }
		return null;
        
    }
    public static String getSpecificCity(String city) {
    	String in = city;
    	String response = "";
    	try {
            Connection connection = connectionDb();
            PreparedStatement statement = connection.prepareStatement("SELECT city FROM delivery_address where city = ? ");
            statement.setString(1, in);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String existcity = resultSet.getString("city");
            	if (existcity.equals(in)) {
                    response = "Exactly! We actually do ship to "+ in + " feel free  to pass your order in the  website !";
                } else {
                	enable = true;
                    response = "We are sorry, Delivery isn't currently available to your place." + in  +" Try with the cities nearby?";  
                }
            } else {
                response = "Could not find " + in + " in the database.";
            }
            resultSet.close();
            statement.close();
            connection.close();
    	 } catch (Exception e) {
             System.out.println(e);
         }
         return response;
    	
    }
    
    public static String getSpecificProduct(String product) {
    String intent = product;
        String response = "";
        
        try {
            Connection connection = connectionDb();
            PreparedStatement statement = connection.prepareStatement("SELECT stock FROM Product WHERE name = ?");
            statement.setString(1, intent);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int numberOfThisProduct = resultSet.getInt("stock");
                if (numberOfThisProduct > 0) {
                    response = "Exactly " + intent + " that you asked for is still available in stock !";
                } else {
                	enable = true;
                    response = "We are sorry, " + intent + " isn't currently available. Would you like to be notified once back in stock?";  
                }
            } else {
                response = "Could not find " + intent + " in the database.";
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
        
    }
    

    public static String approve() {
    	if(enable==true) {
    		enable1=true;
    		return "Please provide your email so you'll get notified .";
    	}
    	
    	return "Sorry I didnt understand. you can refrase your request ";
    }




    public static String sendToEmail(String msg) {
    	if(enable1==true) {
    		return "We will notify you at " + msg + " to confirm your request";
    	}
    	
    	return "Sorry I didnt understand. please refrase your request ";
    }
    
    public static String provideUserCommandeInfo() {
    	
    	try {
        	
        	Connection connection = connectionDb();
        	String message ="";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product WHERE stock > 0");

            while (resultSet.next()) {
                // retrieve data from result set
                int id = resultSet.getInt("id_product");
                String name = resultSet.getString("name");
                
                message += "ID: " + id + "\n" +
                        "name: " + name + "\n" ;

                //System.out.println(id + " " + name + " \n" );

                // print out the data
            }

            // close the connections
            resultSet.close();
            statement.close();
            connection.close();
            return message ;

        } catch (Exception e) {
            System.out.println(e);
        }
    	return null;
    	
    }
    
        public static void main(String[] args) {
            try {
            	selectOnlyAvailableProducts();
            } catch (Exception e) {
                System.out.println(e);
            }
            //Connection con = connectionDb();
        }

}
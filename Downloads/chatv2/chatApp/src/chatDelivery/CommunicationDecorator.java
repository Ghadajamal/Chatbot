package chatDelivery;

import raven.chat.component.ChatArea;

public abstract class CommunicationDecorator extends Communication {
    protected Communication communication;

    public CommunicationDecorator(Communication communication) {
        this.communication = communication;
    }

    @Override
    public void generateTheReponseForThis(String message, ChatArea chatArea) {
        // Log the user message to a file
        

        // Delegate the method call to the wrapped Communication object
        communication.generateTheReponseForThis(message, chatArea);
    }
    
public static void main() {
	Communication communication = new CommunicationImpl();
	communication = new CommunicationDecorator(communication);

	// Now we can use the decorated Communication object
	communication.generateTheReponseForThis("Hello", chatArea);

}
    //public abstract String generateTheReponseForThis(String message);
    public abstract void generateTheReponseForThis(String message , ChatArea chatArea);
}
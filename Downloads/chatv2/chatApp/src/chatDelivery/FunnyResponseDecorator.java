package chatDelivery;
import chatDelivery.Communication;


import raven.chat.component.ChatArea;

public class FunnyResponseDecorator extends CommunicationDecorator {

    public FunnyResponseDecorator(Communication communication) {
        super(communication);
    }

    
    @Override
    public void generateTheReponseForThis(String message, ChatArea chatArea) {
        String response = communication.generateTheReponseForThis(message, chatArea);
        String funnyResponse = "Haha, that's funny! " + response;
        makeTheReponse(funnyResponse, "Bot", chatArea);
    }


  
}


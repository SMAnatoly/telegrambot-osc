package telegram;
import logic.TextProcessor;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import osc.OSC;

import java.util.logging.Level;

public class Bot extends TelegramLongPollingBot {
    
    private String name;
    private String token;
    OSC osc = new OSC();
    TextProcessor textProcessor = new TextProcessor();

    public  Bot(String name, String token){
         this.name = name;
         this.token = token;
    }
    
    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        /*
        System.out.println(message);
         */
        String message = update.getMessage().getText();
        textProcessor.setText(message);
        osc.updateOSC(textProcessor.getOSCValues());
        sendMsg(update.getMessage().getChatId().toString(), String.valueOf(message.length()));
    }

    public synchronized void sendMsg(String cid, String message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(cid);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println(Level.SEVERE.toString() + "Exception: " + e.toString());
        }
    }
}

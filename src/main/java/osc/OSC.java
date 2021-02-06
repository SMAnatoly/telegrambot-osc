package osc;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCSerializeException;
import com.illposed.osc.transport.udp.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

public class OSC {
    private OSCPortOut sender;
    private int sendPort = 6448;
    private String message = "/wek/inputs";

    public OSC() {
        try {
            sender = new OSCPortOut(InetAddress.getLocalHost(), sendPort);
        } catch (SocketException ex) {
            System.out.println("Error: Socket exception");
        } catch (UnknownHostException ex) {
            System.out.println("Error: Unknown Host Exception");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOSC(Object[] o) {
        //Object[] o = new Object[2];
        //o[0] = (float) slider1.getValue();
        //o[1] = (float) slider2.getValue();
        OSCMessage msg = new OSCMessage(message, Arrays.asList(o));
        try {
            sender.send(msg);
        } catch (IOException | OSCSerializeException ex) {
            System.out.println("Could not send message: " + ex.getLocalizedMessage());
        }
    }
}

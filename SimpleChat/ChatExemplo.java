import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class ChatExemplo extends ReceiverAdapter {
    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");
    final List<String> state=new LinkedList<String>();

    public void viewAccepted(View new_view) {
         System.out.println("Existem " +  new_view.size() + " membros no cluster (grupo) " + channel.getClusterName());
         System.out.println("View ID " + new_view.getViewId());
         System.out.println("Coordenador " + new_view.getMembers().get(0));
         for (int i=0;i<new_view.size();i++) {
            System.out.println("  Membro " + i + " " + new_view.getMembers().get(i));
         }
         System.out.print("> "); System.out.flush();
    }

    public void receive(Message msg) {
        String line= "De: " + msg.getSrc() + " Mensagem: " + msg.getObject();
        System.out.println(msg.getSrc() +  " " + channel.getAddressAsString());
        System.out.println(line);
        System.out.print("> "); System.out.flush();
        synchronized(state) {
            state.add(line);
        }
    }

    public void getState(OutputStream output) throws Exception {
        synchronized(state) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }
    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        List<String> list=(List<String>)Util.objectFromStream(new DataInputStream(input));
        synchronized(state) {
            state.clear();
            state.addAll(list);
        }
        System.out.println("state recebido (" + list.size() + " mensagens no historico de chat):");
        for(String str: list) {
            System.out.println(str);
        }
        System.out.print("> "); System.out.flush();
    }


    private void start(String nome, String canal) throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.setName(nome);
        channel.setDiscardOwnMessages(true);
        channel.connect(canal);
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                line="[" + user_name + "] " + line;
                Message msg=new Message(null, line);
                channel.send(msg);
            }
            catch(Exception e) {
            }
        }
    }


    public static void main(String[] args) throws Exception {
        new ChatExemplo().start(args[0],args[1]);
    }
}

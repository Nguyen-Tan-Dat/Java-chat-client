public class Controller extends Thread {
    public void run() {
        while (true) {
            WaitView waitView = new WaitView();
            waitView.confirmLink();
            ChatView chatView = new ChatView();
            String receive = "";
            while (!receive.equals("disconnected")) {
                receive = Client.receive();
                chatView.receiveMessage(receive);
            }
            chatView.dispose();
            Client.start();
            Client.send(Client.name);
            Client.receive();
        }
    }
}

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String host= "localhost";
    private static final int port = 1605;
    public static String name;
    private static PrintWriter writer;
    private static BufferedReader reader;

    public Client() {
        start();
    }
    public static void start(){
        try {
            Socket socket = new Socket(host, port);
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException e) {
            System.out.println("Không thê kết nối tới máy chủ");
            System.exit(0);
        }
    }

    public static void send(String info) {
        System.out.println("Gửi:" + info);
        writer.println(info);
    }

    public static String receive() {
        try {
            System.out.println("Chờ server");
            String info = reader.readLine();
            System.out.println("Nhận:" + info);
            return info;
        } catch (IOException e) {
            System.out.println("Không thể kết nối tới máy chủ");
            System.exit(0);
        }
        return null;
    }
}
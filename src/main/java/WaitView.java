import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WaitView extends JFrame {
    private JPanel panelRoot;
    private JLabel labelStatus;

    public WaitView() {
        super("Phòng chờ kết nối");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/Icon/logo.png")));
        this.setIconImage(logo.getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        this.setResizable(false);
        this.add(panelRoot);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void confirmLink() {
        while (true) {
            String info = Client.receive();
            if (info.equals("isConnecting")) Client.send("TRUE");
            else if (info.equals("startChat"))break;
            else if (info.equals("No")) labelStatus.setText("Người kia không đồng ý. Đang tiếp tục chờ");
            else {
                String name = info.substring(1, info.length() - 1);
                JOptionPane jop = new JOptionPane("Xác nhận kết nối với " + name, JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
                JDialog dialog = jop.createDialog(getContentPane(), "Xác nhận kết nối");
                new Thread(() -> {
                    try {
                        Thread.sleep(10000);
                    } catch (Exception ignored) {
                    }
                    dialog.dispose();
                }).start();
                dialog.setVisible(true);
                boolean ok = false;
                try {
                    ok = jop.getValue().equals(0);
                } catch (Exception ignored) {
                }
                if (ok) Client.send("OK");
                else {
                    Client.send("No");
                    labelStatus.setText("Bạn đã bỏ qua kết nối với: " + name);
                }
            }
        }
        dispose();
    }
}

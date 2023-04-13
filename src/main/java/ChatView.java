import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ChatView extends JFrame  {
    private JTextArea textAreaMessage;
    private JTextField textFieldInput;
    private JButton btnSend;
    private JPanel panelChat;
    private JButton btnBack;

    public ChatView() {
        super("Nickname: "+Client.name);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/logo.png")));
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setResizable(false);
        add(panelChat);
        pack();
        setLocationRelativeTo(null);
        btnBack.addActionListener(e ->Client.send("/disconnect"));
        btnSend.addActionListener(e -> controllerSend());
        textFieldInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    controllerSend();
                }
            }
        });
        setVisible(true);
    }

    private void controllerSend() {
        if (!textFieldInput.getText().isEmpty()) {
            String meMessage = textFieldInput.getText();
            textAreaMessage.append("[Tôi]: " + meMessage+"\n");
            Client.send(textFieldInput.getText());
            textFieldInput.setText("");
        }
    }

    public void receiveMessage(String message) {
        textAreaMessage.append(message+"\n");
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        btnSend=new JButton("");
        btnSend.setFocusPainted(false);
        btnBack = new JButton();
        btnBack.setBackground(new Color(0,0,0, 0));
//        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(new LineBorder(new Color(0,0,0, 0)));
    }
}

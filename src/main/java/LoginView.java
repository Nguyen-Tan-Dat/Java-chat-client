import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class LoginView extends JFrame {
    private JPanel panelRoot;
    private JButton buttonOK;
    private JTextField textFieldName;

    public LoginView() {
        super("Chọn nickname");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Icon/logo.png")));
        setIconImage(logo.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        setResizable(false);
        add(panelRoot);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        textFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) btnOKAction();
            }
        });
        buttonOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnOKAction();
            }
        });
    }

    private void btnOKAction() {
        Client.name = textFieldName.getText();
        if (Client.name.isEmpty()) {
            JOptionPane.showMessageDialog(panelRoot, "Vui lòng nhập tên");
            return;
        }
        Client.send(Client.name);
        if (!Client.receive().equals("NO")) {
            dispose();
            new Controller().start();
        } else JOptionPane.showMessageDialog(panelRoot, "Tên đã được sử dụng");
    }

    private void createUIComponents() {
        buttonOK=new JButton();
        buttonOK.setFocusPainted(false);
    }
}

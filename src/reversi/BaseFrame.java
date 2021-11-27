package reversi;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame() {
        super("Reversi");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("reversi_logo.png");
        this.setIconImage(icon);

        this.setMinimumSize(new Dimension(900, 600));
        this.setResizable(false);
        this.setVisible(true);
    }
}

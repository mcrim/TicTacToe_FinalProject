import javax.swing.JFrame;

public class Frame extends JFrame {
    //The frame class that extends JFrame to create a window for the game
    public Frame() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLocationRelativeTo(null);
        Panel panel = new Panel();
        add(panel);
    }
}
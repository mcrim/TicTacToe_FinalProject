import javax.swing.JFrame;

public class Main {
    //Main class creates a Frame object and displays it.
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
/*
The program uses a 2D array to represent the game board, and each button on the board corresponds to an element in the array. The aiMove() method generates random moves for the AI player, while the getWinner() method checks for a winning condition.

When the game is over, a dialog box is displayed to show the result. The program also switches between the human and AI players, depending on whose turn it is.
 */
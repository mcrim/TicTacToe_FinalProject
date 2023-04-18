//Importing packages
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener {
    // Initialization of the values used to establish the game board and the user input values for turns and moves.
    private static final int BOARD_SIZE = 3;

    private JButton[][] buttons;
    private char[][] board;
    private char humanPlayer;
    private char aiPlayer;
    private char currentPlayer;
    private boolean isGameOver;

    //Panel is a class that extends JPanel and creates the game board and handles user input.
    public Panel() {
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton button = new JButton();
                button.addActionListener(this);
                add(button);
                buttons[row][col] = button;
                board[row][col] = ' ';
            }
        }

        //Allows the user to choose if they want to be X or O
        int choice = JOptionPane.showOptionDialog(
                null,
                "Do you want to be X or O?",
                "Choose Your Symbol",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Character[]{'X', 'O'},
                'X'
        );

        humanPlayer = (choice == 0) ? 'X' : 'O';
        aiPlayer = (humanPlayer == 'X') ? 'O' : 'X';

        if (Math.random() < 0.5) {
            currentPlayer = humanPlayer;
            JOptionPane.showMessageDialog(null, "You go first.");
        } else {
            currentPlayer = aiPlayer;
            JOptionPane.showMessageDialog(null, "The computer goes first.");
            aiMove(); // Returns to the ai's move
        }
    }
}

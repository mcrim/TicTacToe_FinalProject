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
    // When ai is called to make a move
    private void aiMove() {
        int row, col;
        do {
            row = (int) (Math.random() * BOARD_SIZE);
            col = (int) (Math.random() * BOARD_SIZE);
        } while (board[row][col] != ' ');

        buttons[row][col].setText(Character.toString(currentPlayer));
        board[row][col] = currentPlayer;

        checkGameOver();
        switchPlayers();
    }

    //Will check to see if there is a winning exception towards the board players from humanplayer and aiplayer
    private void checkGameOver() {
        char winner = getWinner();
        boolean isTie = true;

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == ' ') {
                    isTie = false;
                }
            }
        }
        //Decalres a winner
        if (winner != ' ') {
            isGameOver = true;
            showWinnerDialog(winner);
        } else if (isTie) { //Declares a tie
            isGameOver = true;
            showTieDialog();
        }
    }

    //When a move is made, it will then switch the players choice and move either to humanPlayer or aiPlayer
    private void switchPlayers() {
        if (currentPlayer == humanPlayer) {
            currentPlayer = aiPlayer;
            aiMove();
        } else {
            currentPlayer = humanPlayer;
        }
    }

    //When there is a winner established via checkGameover, it will then gather to see who won
    //Checks continously to see if there is a winner condition with if statements and for loops to see if anything matches. This was annoying.
    private char getWinner() {
        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != ' ') {
                return board[row][0];
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != ' ') {
                return board[0][col];
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        return ' ';
    }
    //When there is a winner, it will print the following dialog via GUI printing
    private void showWinnerDialog(char winner) {
        String message = (winner == humanPlayer) ? "Congratulations! You win." : "Sorry, you lose.";
        JOptionPane.showMessageDialog(null, message);
    }

    //If there is a tie, it will print this dialog instead
    private void showTieDialog() {
        JOptionPane.showMessageDialog(null, "It's a tie.");
    }
    // When an Action is performed from parameter "e" of the ActionEvent object listed,
    // Basically, when a player either hunman or ai makes a move, this action is called.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) {
            return;
        }

        JButton button = (JButton) e.getSource();
        int row = -1, col = -1;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (buttons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        if (row == -1 || col == -1 || board[row][col] != ' ') {
            return;
        }

        button.setText(Character.toString(currentPlayer));
        board[row][col] = currentPlayer;

        checkGameOver();
        switchPlayers();
    }
}

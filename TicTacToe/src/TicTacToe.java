import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;

    public TicTacToe() {
        setTitle("3 en Raya");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        if (playerXTurn) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        playerXTurn = !playerXTurn;

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this, "¡" + (playerXTurn ? "O" : "X") + " ha ganado!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            resetBoard();
        }
    }

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(buttons[i][0], buttons[i][1], buttons[i][2]) ||
                    checkRowCol(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return true;
            }
        }
        // Check diagonals
        return (checkRowCol(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                checkRowCol(buttons[0][2], buttons[1][1], buttons[2][0]));
    }

    private boolean checkRowCol(JButton b1, JButton b2, JButton b3) {
        return (!b1.getText().equals("") &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText()));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}

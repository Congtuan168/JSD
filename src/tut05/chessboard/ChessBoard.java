package tut05.chessboard;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JFrame {
    public ChessBoard() {
        setTitle("Chess Game");
        setSize(1080, 880);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chessboard panel
        JPanel chessBoardPanel = new JPanel(new GridLayout(8, 8));
        boolean isWhite = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel();
                if (isWhite) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.GRAY);
                }
                chessBoardPanel.add(square);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        // Side panel
        JPanel sidePanel = new JPanel();

        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, 600));


        JLabel guidanceLabel = new JLabel("White's turn");
        JTextArea instructionsArea = new JTextArea("Instructions:\n- Move pieces by dragging.\n- Capture opponent's pieces.");
        instructionsArea.setEditable(false);
        JTextArea moveHistoryArea = new JTextArea("Move History:\n");
        moveHistoryArea.setEditable(false);
        JButton startButton = new JButton("Start");
        JButton surrenderButton = new JButton("Surrender");

        sidePanel.add(guidanceLabel);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(new JScrollPane(instructionsArea));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(new JScrollPane(moveHistoryArea));
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(startButton);
        sidePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        sidePanel.add(surrenderButton);

        add(chessBoardPanel, BorderLayout.CENTER);

        add(sidePanel, BorderLayout.EAST);


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoard frame = new ChessBoard();
            frame.setVisible(true);
        });
    }
}

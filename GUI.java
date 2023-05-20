package src;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {
    JFrame frame;
    JTable table;
    Label Result;
    Label score_display;

    GUI(ActionListener listener) {

        frame = new JFrame();


        String column[] = {"", "", "", ""};
        String data[][] = {{"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", "", ""}};

        table = new JTable(data, column);
        table.setShowGrid(true);
        table.setGridColor(Color.black);
        table.setBorder(new LineBorder(Color.black, 4));
        table.setFont(new Font("arial", Font.BOLD, 36));
        table.setDefaultEditor(Object.class, null);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.setBounds(50, 100, 300, 300);
        table.setRowHeight(75);

        table.setTableHeader(null);
        table.setVisible(true);


        Label gameLabel = new Label("2048");
        Label scoreLabel = new Label("Score:");
        score_display = new Label();
        Result = new Label();


        Button leftB = new Button("<");
        Button rightB = new Button(">");
        Button upB = new Button("^");
        Button downB = new Button("v");
        Button restart = new Button("Restart");

        // setting button position on screen
        gameLabel.setBounds(240, 20, 200, 50);
        gameLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        restart.setBounds(425, 100, 100, 40);
        scoreLabel.setBounds(50, 350, 80, 40);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        score_display.setBounds(130, 350, 100, 40);
        score_display.setFont(new Font("Arial", Font.PLAIN, 25));
        Result.setBounds(375, 350, 200, 40);
        Result.setFont(new Font("Arial", Font.PLAIN, 30));

        leftB.setBounds(400, 200, 50, 50);
        rightB.setBounds(500, 200, 50, 50);
        upB.setBounds(450, 150, 50, 50);
        downB.setBounds(450, 250, 50, 50);

        frame.setLayout(null);
        frame.setSize(600, 500);
        frame.setTitle("2048");
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(table);
        frame.add(gameLabel);
        frame.add(restart);
        frame.add(scoreLabel);
        frame.add(score_display);
        frame.add(Result);
        Result.setVisible(false);
        frame.add(leftB);
        frame.add(rightB);
        frame.add(upB);
        frame.add(downB);
        leftB.addActionListener(listener);
        rightB.addActionListener(listener);
        upB.addActionListener(listener);
        downB.addActionListener(listener);
        restart.addActionListener(listener);



    }

        public void update(int[][] board){
            for (int i = 0; i < board.length; i++){
                for (int j = 0; j < board.length; j++){
                    if (! (board[i][j] == 0)){
                        table.setValueAt(String.valueOf(board[i][j]), i, j);
                    } else {
                        table.setValueAt("", i, j);
                    }
                    table.repaint();
                }
            }
            score_display.setText(String.valueOf(Board.score));
    }

    public void Game_Result(boolean result){
        Result.setVisible(true);
        if (result){
            Result.setText("You Won");
        } else {
            Result.setText("You Lost");
        }
    }


}


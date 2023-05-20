package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class main {
    private static GUI Window;

    public static void updateBoard() {
        Window.update(Board.game_board);

        if (Board.won()) {
            Window.Game_Result(true);
        } 
    }

    public static void main(String[] args) {
        Board.new_board();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "<") {
                    Board.left(Board.game_board);
                } else if (e.getActionCommand() == ">") {
                    Board.right(Board.game_board);
                } else if (e.getActionCommand() == "^") {
                    Board.up(Board.game_board);
                } else if (e.getActionCommand() == "v") {
                    Board.down(Board.game_board);
                } else if (e.getActionCommand() == "Restart") {
                    Board.new_game();
                }
                updateBoard();
            }
        };



        // KeyEventPostProcessor kepp = new KeyEventPostProcessor() {
        //     @Override
        //     public boolean postProcessKeyEvent(KeyEvent e) {
        //         int input = e.getKeyCode();
        //         if (input == 37) {
        //             Board.left(Board.game_board);
        //         } else if (input == 38) {
        //             Board.up(Board.game_board);
        //         } else if (input == 39) {
        //             Board.right(Board.game_board);
        //         } else if (input == 40) {
        //             Board.down(Board.game_board);
        //         }
        //         updateBoard();
        //         return false;
        //     }
        // };
        // KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        // kfm.addKeyEventPostProcessor(kepp);

        Window = new GUI(listener);
        Window.update(Board.game_board);
    }
}

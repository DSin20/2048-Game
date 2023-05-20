package src;
import java.util.*;


public class Board {
    protected static int[][] game_board;
    protected static int score = 0;

    public static void new_board() {
        game_board = new int[4][4];
        for (int i = 0; i < game_board.length; i++){
            Arrays.fill(game_board[i], 0);
        }
        add_tile(game_board);
        add_tile(game_board);
    }

    protected static int empty_tiles_counter(int[][] board){
        int counter = 0;
        for (int[] i : board){
            for (int j : i){
                if (j == 0){
                    counter++;
                }
            }
        }
        return counter;
    }

    protected static void add_tile(int[][] board) {
        Random rand = new Random();
        if(empty_tiles_counter(board) == 0) {
            return;
        }
        else {
            int a = rand.nextInt(empty_tiles_counter(board));
            int aux = 0;
            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++) {
                    if(a == aux && (board[i][j] == 0)) {
                        board[i][j] = rand.nextInt(2) == 0 ? 4 : 2;
                        return;
                    }
                    if(board[i][j] == 0) {
                        aux++;
                    }
                }
            }

        }
    }

    private static boolean can_slide(int[] row) {
        for (int i = 0; i < row.length; i++){
            if (row[i] == 0) {
                for (int j = i + 1; j < row.length; j++){
                    if (row[j] != 0){
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    private static boolean can_merge(int[] row) {
        for(int i = 0; i < row.length - 1; i++) {
                if (row[i] != 0 && row[i] == row[i + 1]){
                    return true;
                }
            }
            return false;
        }

    private static int[] slide(int[] row) {
        // `k` stores the index of the next available position
        int k = 0;
        for (int i: row) {
            if (i != 0) {
                row[k++] = i;
            }
        }
        for (int i = k; i < row.length; i++) {
            row[i] = 0;
        }
        return row;
    }


    private static int[] merge(int[] row) {
        for(int i = 0; i < row.length - 1; i++) {
            if(row[i] == row[i + 1]) {
                row[i] += row[i + 1];
                row[i + 1] = 0;
                score += 2*row[i];
            }
        }
        return row;
    }

    //For left, I slide and merge the array.
    public static void left(int[][] board) {
        boolean Changed = false;
        for (int i = 0; i < board.length; i++){
            if (can_slide(board[i])){
                board[i] = slide(board[i]);
                Changed = true;
            }
            if (can_merge(board[i])){
                board[i] = merge(board[i]);
                Changed = true;
            }
            if (can_slide(board[i])){
                board[i] = slide(board[i]);
                Changed = true;
            }
        }
        //If there is any change, I add another 2 or 4 tile in a empty spot
        if (Changed){
            add_tile(board);
        }
    }

    //For right, I flip the array, slide and merge the array, then flip back.
    public static void right(int[][] board) {
        boolean Changed = false;
        for (int k = 0; k < board.length; k++){
            //flip now
            int[] flipped = new int[board[k].length];
            for (int j = 0; j < board.length; j++){
                flipped[board.length - j - 1] = board[k][j];
            }
            if (can_slide(flipped)){
                flipped = slide(flipped);
                for (int j = 0; j < board.length; j++) {
                    board[k][j] = flipped[board.length - j - 1];
                }
                Changed = true;
            }
            if (can_merge(flipped)){
                flipped = merge(flipped);
                for (int j = 0; j < board.length; j++) {
                    board[k][j] = flipped[board.length - j - 1];
                }
                Changed = true;
            }
            if (can_slide(flipped)){
                flipped = slide(flipped);
                for (int j = 0; j < board.length; j++) {
                    board[k][j] = flipped[board.length - j - 1];
                }
                Changed = true;
            }
        }
        //If there is any change, I add another 2 or 4 tile in a empty spot
        if (Changed){
            add_tile(board);
        }
    }
    //For Up, I transpose the array, slide and merge the array, then transpose back.
    public static void up(int[][] board){
        boolean Changed = false;
        for (int i = 0; i < board.length; i++){
            int[] transposed = new int[board[i].length];
            for (int j = 0; j < board.length; j++){
                transposed[j] = board[j][i];
            }
            if (can_slide(transposed)){
                transposed = slide(transposed);
                for (int j = 0; j < transposed.length; j++){
                    board[j][i] = transposed[j];
                }
                Changed = true;
            }
            if (can_merge(transposed)){
                transposed = merge(transposed);
                for (int j = 0; j < transposed.length; j++) {
                    board[j][i] = transposed[j];
                }
                Changed = true;
            }
            if (can_slide(transposed)){
                transposed = slide(transposed);
                for (int j = 0; j < transposed.length; j++){
                    board[j][i] = transposed[j];
                }
                Changed = true;
            }
        }
        if (Changed){
            add_tile(board);
        }
    }
    //For down, I transpose and flip the array, slide and merge the array, then transpose and flip back.
    public static void down(int[][] board){
        boolean Changed = false;
        for (int i = 0; i < board.length; i++){
            int[] trans_flipped = new int[board[i].length];
            for (int j = 0; j < board.length; j++){
                trans_flipped[j] = board[board.length - j - 1][i];
            }
            if (can_slide(trans_flipped)){
                trans_flipped = slide(trans_flipped);
                for (int j = 0; j < trans_flipped.length; j++){
                    board[board.length - j - 1][i] = trans_flipped[j];
                }
                Changed = true;
            }
            if (can_merge(trans_flipped)){
                trans_flipped = merge(trans_flipped);
                for (int j = 0; j < trans_flipped.length; j++) {
                    board[board.length - j - 1][i] = trans_flipped[j];
                }
                Changed = true;
            }
            if (can_slide(trans_flipped)){
                trans_flipped = slide(trans_flipped);
                for (int j = 0; j < trans_flipped.length; j++){
                    board[board.length - j - 1][i] = trans_flipped[j];
                }
                Changed = true;
            }
        }
        if (Changed){
            add_tile(board);
        }
    }

    public static void new_game(){
        new_board();
    }

    public static boolean won(){
        for (int[] row : game_board){
            for (int num : row){
                if (num == 2048){
                    return true;
                }
            }
        }
        return false;
    }
}


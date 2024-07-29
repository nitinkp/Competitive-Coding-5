import java.util.HashSet;

//https://leetcode.com/problems/valid-sudoku/
public class ValidSudoku {
    //O(1) T.C since max iterations are only 9*9=81 which is constant
    //O(1) S.C since max space in each hashset is 9 which is constant
    public boolean isValidSudoku(char[][] board) {
        //row check
        for(int i=0; i<9; i++) {
            HashSet<Character> set = new HashSet<>();
            char[] row = board[i];
            for(int j=0; j<9; j++) {
                char val = row[j];
                if(val != '.') {
                    if(set.contains(val)) return false;
                    else set.add(val);
                }
            }
        }

        //col check
        for(int i=0; i<9; i++) {
            HashSet<Character> set2 = new HashSet<>();
            for(int j=0; j<9; j++) {
                char val = board[j][i];
                if(val != '.') {
                    if(set2.contains(val)) return false;
                    else set2.add(val);
                }
            }
        }

        //grid check
        for(int i = 0; i<9; i+=3) { //iterate row only 3 times
            for(int j=0; j<9; j+=3) { //iterate col only 3 times
                HashSet<Character> set3 = new HashSet<>();
                for(int k=i; k<i+3; k++) { //actual iteration of each 3 elements in row
                    for(int l=j; l<j+3; l++) { //actual iteration of each 3 elements in col
                        char val = board[k][l];
                        if(val != '.') {
                            if(set3.contains(val)) return false;
                            else set3.add(val);
                        }
                    }
                }
            }
        }
        return true;
    }
}

public class SudokuSolve {
    private static final int taille = 9;
    //horizental axis verification
    private static boolean isInRow(int[][] tab,int numero, int row){
        for(int i=0;i<taille;i++){
            if(tab[row][i]==numero)
                return true;
        }
        return false;
    }

    //vertical axis verification
    private static boolean isInColumn(int[][] tab,int numero, int column){
        for(int i=0;i<taille;i++){
            if(tab[i][column]==numero)
                return true;
        }
        return false;
    }

    //check the 3*3 inner sub-square
    private static boolean isInBox(int[][] tab,int numero,int row, int column){
        int startFromRow = row - row % 3;
        int startFromColumn = column - column % 3;
        for(int i=startFromRow;i < startFromRow+3;i++){
            for(int j=startFromColumn;j<startFromColumn+3;j++){
                if(tab[i][j]==numero)
                    return true;
            }
        }
        return false;
    }

    public static boolean isWellPlaced(int[][] tab, int numero, int row, int column){
        return (!isInRow(tab,numero,row) && !isInColumn(tab,numero,column) && !isInBox(tab,numero,row,column));
    }

    private static boolean solve(int[][] tab){
        for(int i=0;i<taille;i++){
            for(int j=0;j<taille;j++){
                if(tab[i][j]==0){
                    for(int testCase = 1; testCase<=taille;testCase++){
                        if(isWellPlaced(tab,testCase,i,j)){
                            tab[i][j] = testCase;
                            if(solve(tab)){
                                return true;
                            }else{
                                tab[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args){
        //https://www.researchgate.net/profile/Paul-Roach-4/publication/264572573/figure/fig1/AS:295976259604480@1447577647208/An-Example-Sudoku-Puzzle-and-its-Solution-1.png
        int[][] tab = {
                {0,0,0,0,0,0,2,0,0},
                {0,8,0,0,0,7,0,9,0},
                {6,0,2,0,0,0,5,0,0},
                {0,7,0,0,6,0,0,0,0},
                {0,0,0,9,0,1,0,0,0},
                {0,0,0,0,2,0,0,4,0},
                {0,0,5,0,0,0,6,0,3},
                {0,9,0,4,0,0,0,7,0},
                {0,0,6,0,0,0,0,0,0}
        };
        if(solve(tab)){
            System.out.println("solved");
        }else{
            System.out.println("unsolvable");
        }

        printtab(tab);

    }

    private static void printtab(int[][] tab) {
        for(int i=0;i<taille;i++){
            if(i%3==0 && i !=0){
                System.out.println("------------------");
            }
            for(int j=0;j <taille;j++){
                System.out.print(tab[i][j]+"|");
            }
            System.out.println();
        }
    }
}

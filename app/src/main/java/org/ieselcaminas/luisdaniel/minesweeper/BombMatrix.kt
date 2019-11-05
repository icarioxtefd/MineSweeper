package org.ieselcaminas.luisdaniel.minesweeper

class BombMatrix (var nrow: Int, var ncol: Int, mineCount: Int){

    val listOfRows = (0..nrow-1) //using this for picking a random cell for planting the bombs in
    val listOfCols = (0..ncol-1) //using this for picking a random cell for planting the bombs in

    //randomizing the bombs -> 0 = clear | -1 = bomb

    var bombArray: Array<IntArray>

    init {
        bombArray = Array(nrow) {IntArray(ncol)}
        plantBombs(mineCount)
        calculateNumbers()

    }

    private fun plantBombs(mineCount: Int) { //random bomb planting for ; inicializacion
        var bombsCounter = 0
        var randRow: Int
        var randCol: Int

        while(bombsCounter < mineCount){
            randRow = listOfRows.shuffled().take(1)[0] //picks a random numbers
            randCol = listOfCols.shuffled().take(1)[0] //picks a random numbers

            if (bombArray[randRow][randCol] == -1){ //changing the cell if alr has a bomb
                randRow = listOfRows.shuffled().take(1)[0]
                randCol = listOfCols.shuffled().take(1)[0]
            }

            bombArray[randRow][randCol] = -1 //plants bomb
            bombsCounter++ //increases counter
        }
    }


    fun printMatrix(){
        for(i in 0 until nrow){
            for(j in 0 until ncol){

                if(bombArray[i][j] != -1)
                    print(" " + (bombArray[i][j]).toString()) //just one more space; decorative purpose
                else
                    print( (bombArray[i][j]).toString() )

                print(" | ")
            }
            println() //linea necesaria para que imprima
        }
    }

    private fun calculateNumbers(){ //the numbers means the adjacent bombs
        for (row in 0 until nrow){
            for(col in 0 until ncol){
                if (bombArray[row][col] != -1){ //not a bomb
                    calculateNumbers2(row, col)
                }
            }
        }
    }

    private fun calculateNumbers2(row: Int, col: Int){
        var numCounter = 0
        for(i in col-1 .. col+1){
            for(j in row-1 .. row+1) {
                if (!(i == col && j == row)) {
                    if(isValid(j,i)) {
                        if (bombArray[j][i] == -1) { //too many nesting; but yes
                            numCounter++
                        }
                    }
                }
            }
        }
        bombArray[row][col] = numCounter //number of adjacent bombs
    }


    fun isValid(row: Int, col: Int): Boolean{ //calculates whether the cell is a valid cell (inside the board); useful for avoiding arrayIndexOutOfBoundEx
        if(row < 0 || row >= nrow){
            return false
        }
        if(col < 0 || col >= ncol){
            return false
        }
        return true
    }

}
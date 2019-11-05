package org.ieselcaminas.luisdaniel.minesweeper


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.ieselcaminas.luisdaniel.minesweeper.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    lateinit var board: List<List<MineButton>> //instantiation of 2d object matrix
            //= List(height) { i -> List(width) { j -> IndexedTile(i, j, Tile()) } }
    lateinit var bombMatrix: BombMatrix

    var numRows: Int = 0
    var numCols: Int = 0
    var mineCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater, R.layout.fragment_game, container, false)

        binding.buttonWin.setOnClickListener {
            it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToWonFrag())
        }

        binding.buttonLose.setOnClickListener {
            it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFrag())
        }

        var args = GameFragmentArgs.fromBundle(arguments!!)

        numRows = args.numRows //I messed it up kay don't judge me
        numCols = args.numCols
        mineCount = (args.numRows * args.numCols) / 6

        Toast.makeText(context, "Rows: $numRows Cols $numCols", Toast.LENGTH_SHORT).show()

        //////////////////////////////////

        createButtons() //creates the array of buttons
        binding.gridLayout.columnCount = numCols //grid con el nº exacto de columnas especificado
        binding.gridLayout.rowCount = numRows

        for(row in 0 until numRows){
            for (col in 0 until numCols){
                binding.gridLayout.addView(board[row][col])
                board[row][col].setOnClickListener(){
                    Toast.makeText(context, "row: ${board[row][col].row} col: ${board[row][col].col}",Toast.LENGTH_SHORT).show()
                }
            }
        }

        bombMatrix = BombMatrix(numRows, numCols, mineCount) //instantiation
        bombMatrix.printMatrix()




        return binding.root
    }

    private fun createButtons(){
        board = List(numRows) { i -> List(numCols) { j -> MineButton(context!!, i, j) } } //inicialization of 2d object matrix
    }


}

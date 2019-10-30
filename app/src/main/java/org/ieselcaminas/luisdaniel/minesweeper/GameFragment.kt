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

    var numRows: Int = 0
    var numCols: Int = 0

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

        numRows = args.numRows
        numCols = args.numCols

        Toast.makeText(context, "Rows: $numRows Cols $numCols", Toast.LENGTH_SHORT).show()

        //////////////////////////////////


        createButtons()


        return binding.root
    }

    private fun createButtons(){
        board = List(numRows) { i -> List(numCols) { j -> MineButton(context!!, i, j) } } //inicialization of 2d object matrix
    }


}

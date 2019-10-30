package org.ieselcaminas.luisdaniel.minesweeper


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_config.view.*
import org.ieselcaminas.luisdaniel.minesweeper.databinding.FragmentConfigBinding
import org.ieselcaminas.luisdaniel.minesweeper.databinding.FragmentGameBinding
import java.lang.NumberFormatException

/**
 * A simple [Fragment] subclass.
 */
class ConfigFragment : Fragment() {

    data class ConfigData(
        var cols: Int,
        var rows: Int
    )


    var config = ConfigData(10, 10)
    lateinit var binding: FragmentConfigBinding //importante para usar binding.  fuera del onCreate


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentConfigBinding>(
            inflater, R.layout.fragment_config, container, false
        )

        binding.buttnGoToGame.setOnClickListener {
            //this  ->  Navigation.findNavController(it).navigate(R.id.action_configFragment_to_gameFragment)   <- is the same as below
            it.findNavController().navigate(
                ConfigFragmentDirections.actionConfigFragmentToGameFragment(
                    config.cols,
                    config.rows
                )
            ) //   <-- safeArgs
        }

        setHasOptionsMenu(true) //menu


        /////////////////CambiarTextosListeners---------------------------------------

        binding.editTxtCols.addTextChangedListener() {
            try {
                config.cols = it.toString().toInt()

            } catch (ex: NumberFormatException) {
                config.cols = 0
                //editTxtCols.setText("")
            }
            binding.invalidateAll()
        }

        binding.editTxtRows.addTextChangedListener() {
            try {
                config.rows = it.toString().toInt()

            } catch (ex: NumberFormatException) {
                config.rows = 0
                //editTxtCols.setText("")
            }
            binding.invalidateAll()
        }

        /////////////////CambiarTextosListeners---------------------------------------


        setSpinner() //sets the spinner with its adapter


        ////////////////////

        binding.config = this //enlaza con la variable del xml

        return binding.root
    }

    fun setSpinner() {

        //string array
        val optionsArr = arrayOf("Easy", "Medium", "Hard")

        //arrayAdapter
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, optionsArr)

        //drop down resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //data bind the spinner with the adapter
        binding.spinnerSelDiff.adapter = adapter

        //set on item selected for spinner object
        binding.spinnerSelDiff.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            config.cols = 10
                            config.rows = 10
                        }
                        1 -> {
                            config.cols = 20
                            config.rows = 20
                        }
                        2 -> {
                            config.cols = 30
                            config.rows = 30
                        }
                    }
                    binding.invalidateAll()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //nothing
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_about, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
    }






}






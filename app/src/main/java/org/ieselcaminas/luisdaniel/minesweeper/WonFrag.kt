package org.ieselcaminas.luisdaniel.minesweeper


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class WonFrag : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true) //menu







        return inflater.inflate(R.layout.fragment_won, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.share -> shareSuccess() //si el idenficador del menuItem es share, hacemos nuestar funcion de tal
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent {
        //var args = GameWonFragmentArgs.fromBundle(arguments!!) //pasados para usarlos
        val shareIntent = Intent(Intent.ACTION_SEND) //para hacer share
        shareIntent.type = "text/plain" //tipo MIME de lo que va a sharear
        shareIntent.putExtra(Intent.EXTRA_TEXT, "bitch I won") //getString se necesita para coger el valor del string que hay en values/strings
        return shareIntent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }



















}

package org.ieselcaminas.luisdaniel.minesweeper

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getDrawable

class MineButton(context: Context, var row: Int, var col: Int) : ImageButton(context) {
    val size = 50
    var state: ButtonState = ButtonState.CLOSED

    init{ //equivalente al constructor del boton
        layoutParams = LinearLayout.LayoutParams(size,size)

        //Para ajustar las imagenes----------------------------
        setPadding(0,0,0,0)
        scaleType = ImageView.ScaleType.CENTER
        adjustViewBounds = true
        //-----------------------------------------------------

        setBackgroundResource(R.drawable.boton)


        setOnTouchListener() { view: View, event: MotionEvent ->
            val button: MineButton = view as MineButton //casting al view

            if(event.action == MotionEvent.ACTION_DOWN){ //al pulsar
                button.background = getDrawable(context,R.drawable.boton_pressed)
            }
            else{
                if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL){ //al levantar o al salir
                    button.background = getDrawable(context, R.drawable.boton)
                }
            }


            false
            //el false es el return del touchlistener
        }

        setOnLongClickListener {view: View ->
            val button: MineButton = view as MineButton

            when(button.state){
                ButtonState.CLOSED -> {
                    button.state = ButtonState.FLAG
                    button.setImageResource(R.drawable.flag)
                }

                ButtonState.FLAG -> {
                    button.state = ButtonState.QUESTION
                    button.setImageResource(R.drawable.question)
                }

                ButtonState.QUESTION -> {
                    button.state = ButtonState.CLOSED
                    button.setImageDrawable(null)
                }
            }
            true
        }









    }
}


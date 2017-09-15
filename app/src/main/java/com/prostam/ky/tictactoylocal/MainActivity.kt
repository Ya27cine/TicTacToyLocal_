package com.prostam.ky.tictactoylocal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    // attr game :
    var playerPlay = 1
    var playerAndr = 2
    var player01 = ArrayList<Int>()
    var player02 = ArrayList<Int>()
    var activePlayer = 1
    var typeGame = playerAndr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ***********************
        val simpleAlert = AlertDialog.Builder(this@MainActivity).create()
            simpleAlert.setTitle("choose a game ")
            simpleAlert.setMessage(" what do you want to play ? ")

            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Player Vs Player", {
                dialogInterface, i ->
                Toast.makeText(applicationContext, "Player Vs Player :) ",
                        Toast.LENGTH_SHORT).show()
                //type game :
                typeGame = playerPlay
        })
        simpleAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Player Vs Android", {
            dialogInterface, i ->
            Toast.makeText(applicationContext, "Player Vs Android :) ",
                    Toast.LENGTH_SHORT).show()
            //type game :
            typeGame = playerAndr
        })
        simpleAlert.show()

    }


    /** ==============================                  ============================**/
    fun clickBox(view : View){
        var boxSelect = view as Button
        var idSelect = -1
        when( boxSelect.id ){
            R.id.bt1-> idSelect = 1
            R.id.bt2-> idSelect = 2
            R.id.bt3-> idSelect = 3
            R.id.bt4-> idSelect = 4
            R.id.bt5-> idSelect = 5
            R.id.bt6-> idSelect = 6
            R.id.bt7-> idSelect = 7
            R.id.bt8-> idSelect = 8
            R.id.bt9-> idSelect = 9
        }
        //Toast.makeText(this,"ID select : "+idSelect,Toast.LENGTH_LONG).show()
        // fct game  :
          playing(idSelect, boxSelect)

    }


    /** ==============================                  ============================**/
    fun playing(idSelect:Int, boxSelect:Button){

        if(activePlayer == 1){
            player01.add( idSelect )
            boxSelect.setBackgroundResource(R.color.colorX)
            boxSelect.text = "X"
            activePlayer = 2
            if(checkWinner() == -1 && typeGame == playerAndr)
                    autoPlay()
        }else{
            player02.add( idSelect )
            boxSelect.setBackgroundResource(R.color.colorO)
            boxSelect.text = "O"
            activePlayer = 1
        }
        boxSelect.isEnabled = false
        checkWinner()
    }


    /** ==============================                  ============================**/
    fun checkWinner():Int{
        var winner = -1

        // row 01
         if(player01.contains(1) && player01.contains(2) && player01.contains(3)) winner = 1
         else if(player02.contains(1) && player02.contains(2) && player02.contains(3)) winner = 2

        // row 02
        if(player01.contains(4) && player01.contains(5) && player01.contains(6)) winner = 1
        else if(player02.contains(4) && player02.contains(5) && player02.contains(6)) winner = 2

        // row 03
        if(player01.contains(7) && player01.contains(8) && player01.contains(9)) winner = 1
        else if(player02.contains(7) && player02.contains(8) && player02.contains(9)) winner = 2


        // col 01
        if(player01.contains(1) && player01.contains(4) && player01.contains(7)) winner = 1
        else if(player02.contains(1) && player02.contains(4) && player02.contains(7)) winner = 2

        // col 02
        if(player01.contains(2) && player01.contains(5) && player01.contains(8)) winner = 1
        else if(player02.contains(2) && player02.contains(5) && player02.contains(8)) winner = 2

        // col 03
        if(player01.contains(3) && player01.contains(6) && player01.contains(9)) winner = 1
        else if(player02.contains(3) && player02.contains(6) && player02.contains(9)) winner = 2


        // diagonal
        if(player01.contains(1) && player01.contains(5) && player01.contains(9)) winner = 1
        else if(player02.contains(1) && player02.contains(5) && player02.contains(9)) winner = 2

        // reverse diagonal
        if(player01.contains(3) && player01.contains(5) && player01.contains(7)) winner = 1
        else if(player02.contains(3) && player02.contains(5) && player02.contains(7)) winner = 2


        // *********************************
        if( winner != -1){
                val simpleAlert = AlertDialog.Builder(this@MainActivity).create()
                simpleAlert.setTitle("Game Over")

                if( winner == 1 ){
                    simpleAlert.setMessage(" Player 01  wins the game ")
                }else{
                    if(typeGame == playerPlay)
                        simpleAlert.setMessage(" Player 02  wins the game ")
                    else
                        simpleAlert.setMessage(" Android wins the game ")
                }

                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Re-Play", {
                    dialogInterface, i ->
                    Toast.makeText(applicationContext, "New Game :) ",
                            Toast.LENGTH_SHORT).show()
                   //start new game :
                    newGame()
                })
                simpleAlert.show()
        }else{
            //**********************************************
            if( scannEmptyCells().isEmpty()){
                // the game is finished &&  there is no winner
                val simpleAlert = AlertDialog.Builder(this@MainActivity).create()
                simpleAlert.setTitle("Game Over")
                simpleAlert.setMessage("no one is winner ")
                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "Re-Play", {
                    dialogInterface, i ->
                    Toast.makeText(applicationContext, "New Game :) ",
                            Toast.LENGTH_SHORT).show()
                    //start new game :
                    newGame()
                })
                simpleAlert.show()
            }
        }
        return  winner
    }



    /** ==============================                  ============================**/
    fun  newGame(){
         val intent = Intent(this, MainActivity::class.java)
         startActivity(intent)
     }



    /** ==============================                  ============================**/
    fun autoPlay() {

        var emptyCells = scannEmptyCells()
        if (!emptyCells.isEmpty()) {

            // select rand CellID
            var indexRandom = Random().nextInt(emptyCells.size)
            var cellID = emptyCells[indexRandom]

            var seleBt: Button
            when (cellID) {
                1 -> seleBt = bt1
                2 -> seleBt = bt2
                3 -> seleBt = bt3
                4 -> seleBt = bt4
                5 -> seleBt = bt5
                6 -> seleBt = bt6
                7 -> seleBt = bt7
                8 -> seleBt = bt8
                9 -> seleBt = bt9
                else -> seleBt = bt1
            }
            //
            playing(cellID, seleBt)
        }else
            newGame()
    }


    /** ==============================                  ============================**/
    fun scannEmptyCells():ArrayList<Int>{
        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9) {
            if (!(player01.contains(cellID) || player02.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }
        return  emptyCells
    }


    override fun onBackPressed() {
        //super.onBackPressed()
    }
}

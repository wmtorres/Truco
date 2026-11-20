package br.weslley.truco

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigationevent.NavigationEventHistory

class ContagemTentos : AppCompatActivity() {
    lateinit var btnAddPlusOne: LinearLayout
    lateinit var btnAddPlusThree: LinearLayout
    lateinit var btnAddPlusSix: LinearLayout
    lateinit var btnAddPlusNine: LinearLayout
    lateinit var btnAddPlusTwelve: LinearLayout
    lateinit var btnMinusOne: TextView

    lateinit var btnAddPlusOneP2: LinearLayout
    lateinit var btnAddPlusThreeP2: LinearLayout
    lateinit var btnAddPlusSixP2: LinearLayout
    lateinit var btnAddPlusNineP2: LinearLayout
    lateinit var btnAddPlusTwelveP2: LinearLayout
    lateinit var btnMinusOneP2: TextView

    lateinit var tvPlayer1Score: TextView
    lateinit var tvPlayer2Score: TextView

    lateinit var btnRstHistory: LinearLayout

    lateinit var btnGameHistory: LinearLayout

    lateinit var btnEditPlayerNames: LinearLayout
    var numberPointsP1 = 0
    var numberPointsP2 = 0

    var numberGamesP1 = 0

    var numberGamesP2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contagem_tentos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val jogadorUm = intent.getStringExtra("NOME_J1")
        val jogadorDois = intent.getStringExtra("NOME_J2")

        val tvPlayer1NameScore = findViewById<TextView>(R.id.tvPlayer1NameScore)
        val tvPlayer2NameScore = findViewById<TextView>(R.id.tvPlayer2NameScore)
        btnAddPlusOne = findViewById(R.id.btnP1Add1)
        btnAddPlusThree = findViewById(R.id.btnP1Add3)
        btnAddPlusSix = findViewById(R.id.btnP1Add6)
        btnAddPlusNine = findViewById(R.id.btnP1Add9)
        btnAddPlusTwelve = findViewById(R.id.btnP1Add12)
        btnMinusOne =  findViewById(R.id.btnP1Minus1)
        btnAddPlusOneP2 = findViewById(R.id.btnP2Add1)
        btnAddPlusThreeP2 = findViewById(R.id.btnP2Add3)
        btnAddPlusSixP2 = findViewById(R.id.btnP2Add6)
        btnAddPlusNineP2 = findViewById(R.id.btnP2Add9)
        btnAddPlusTwelveP2 = findViewById(R.id.btnP2Add12)
        btnMinusOneP2 =  findViewById(R.id.btnP2Minus1)
        tvPlayer1Score = findViewById(R.id.tvScoreP1)
        tvPlayer2Score = findViewById(R.id.tvScoreP2)
        btnRstHistory = findViewById(R.id.btnResetHistory)
        btnGameHistory = findViewById(R.id.btnHistoryPlays)
        btnEditPlayerNames = findViewById(R.id.btnEditNames)
        if (!jogadorUm.isNullOrEmpty()) {
            tvPlayer1NameScore.text = jogadorUm
        }
        if (!jogadorDois.isNullOrEmpty()) {
            tvPlayer2NameScore.text = jogadorDois
        }

        btnAddPlusOne.setOnClickListener {
            incrementScore("PlayerOne", ONE_POINT)
        }
        btnAddPlusThree.setOnClickListener {
            incrementScore("PlayerOne", THREE_POINTS)
        }
        btnAddPlusSix.setOnClickListener {
            incrementScore("PlayerOne", SIX_POINTS)
        }
        btnAddPlusNine.setOnClickListener {
            incrementScore("PlayerOne", NINE_POINTS)
        }
        btnAddPlusTwelve.setOnClickListener {
            incrementScore("PlayerOne", TWELVE_POINTS)
        }
        btnMinusOne.setOnClickListener {
            decrementScore("PlayerOne", MINUS_ONE_POINT)
        }
        btnAddPlusOneP2.setOnClickListener {
            incrementScore("PlayerTwo", ONE_POINT)
        }
        btnAddPlusThreeP2.setOnClickListener {
            incrementScore("PlayerTwo", THREE_POINTS)
        }
        btnAddPlusSixP2.setOnClickListener {
            incrementScore("PlayerTwo", SIX_POINTS)
        }
        btnAddPlusNineP2.setOnClickListener {
            incrementScore("PlayerTwo", NINE_POINTS)
        }
        btnAddPlusTwelveP2.setOnClickListener {
            incrementScore("PlayerTwo", TWELVE_POINTS)
        }
        btnMinusOneP2.setOnClickListener {
            decrementScore("PlayerTwo", MINUS_ONE_POINT)
        }
        btnRstHistory.setOnClickListener {
            resetHistory()
        }
        btnGameHistory.setOnClickListener {
            Log.d("TAG", "btnGameHistory")
            val intent = Intent(this, HistoricoPartidas::class.java).apply {
                putExtra("NOME_J1", tvPlayer1NameScore.text.toString())
                putExtra("NOME_J2", tvPlayer2NameScore.text.toString())
                putExtra("VITORIAS_J1", numberGamesP1)
                putExtra("VITORIAS_J2", numberGamesP2)
            }
            startActivity(intent)
        }

        btnEditPlayerNames.setOnClickListener {
            Log.d("TAG", "btnEditPlayerNames")
            resetHistory()
            val intent = Intent(this, NomeJogadores::class.java)
            startActivity(intent)

        }
    }
    fun incrementScore(player: String, points: Int) {
        if (player == "PlayerOne") {
            if (numberPointsP1 < 12) {
                numberPointsP1 = numberPointsP1 + points
                tvPlayer1Score.text = numberPointsP1.toString()
                if(numberPointsP1 >=12){
                    Toast.makeText(this, "Jogador 1 venceu!", Toast.LENGTH_SHORT).show()
                    resetScore("PlayerOne")
                }
            } else {
                Log.d("TAG", "Jogador 1 atingiu 12 pontos - Parabéns")
            }
        }else{
            if (numberPointsP2 < 12) {
                numberPointsP2 = numberPointsP2 + points
                tvPlayer2Score.text = numberPointsP2.toString()
                if(numberPointsP2 >=12){
                    Toast.makeText(this, "Jogador 2 venceu!", Toast.LENGTH_SHORT).show()
                    resetScore("PlayerTwo")
                }
            } else {
                Log.d("TAG", "Jogador 2 atingiu 12 pontos - Parabéns")
            }
        }
    }
    fun decrementScore(player: String, points: Int) {
        if (player == "PlayerOne") {
            if (numberPointsP1 > 0) {
                numberPointsP1 = numberPointsP1 + points
                tvPlayer1Score.text = numberPointsP1.toString()
            } else {
                Log.d("TAG", "Jogador 1 ainda não tem pontos para descontar")
            }
        }else{
            if (numberPointsP2 > 0) {
                numberPointsP2 = numberPointsP2 + points
                tvPlayer2Score.text = numberPointsP2.toString()
            } else {
                Log.d("TAG", "Jogador 2 ainda não tem pontos para descontar")
            }
        }
    }

    fun resetScore(player: String){
        numberPointsP1 = 0
        numberPointsP2 = 0
        tvPlayer1Score.text = numberPointsP1.toString()
        tvPlayer2Score.text = numberPointsP2.toString()

        if(player == "PlayerOne"){
            numberGamesP1++;
        }else{
            numberGamesP2++;
        }
    }

    fun resetHistory(){
        numberPointsP1 = 0
        numberPointsP2 = 0
        numberGamesP1 = 0
        numberGamesP2 = 0
        tvPlayer1Score.text = numberPointsP1.toString()
        tvPlayer2Score.text = numberPointsP2.toString()
        Toast.makeText(this, "Histórico de partidas resetado!", Toast.LENGTH_SHORT).show()

    }
    companion object  {
        private const val TAG = "ContagemTentos"
        private const val ONE_POINT = 1
        private const val THREE_POINTS = 3
        private const val SIX_POINTS = 6
        private const val NINE_POINTS = 9
        private const val TWELVE_POINTS = 12
        private const val MINUS_ONE_POINT = -1
    }
}
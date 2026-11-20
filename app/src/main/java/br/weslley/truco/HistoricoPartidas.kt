package br.weslley.truco

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoricoPartidas : AppCompatActivity() {
    lateinit var btnVoltar: LinearLayout
    lateinit var btnZeroHist: LinearLayout
    lateinit var tvLeaderName: TextView
   lateinit var tvP1GamesWon: TextView

    lateinit var tvChallengerName: TextView
    lateinit var tvP2GamesWon: TextView

    lateinit var tvNumberOfMatches: TextView
    lateinit var tvLeaderDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico_partidas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val playerOneName = intent.getStringExtra("NOME_J1")
        val playerTwoName = intent.getStringExtra("NOME_J2")
        val playerOneWins = intent.getIntExtra("VITORIAS_J1", 0)
        val playerTwoWins = intent.getIntExtra("VITORIAS_J2", 0)
        tvP1GamesWon = findViewById(R.id.tvLeaderWinsCount)
        tvLeaderName = findViewById(R.id.tvLeaderName)
        tvChallengerName = findViewById(R.id.tvChallengerName)
        tvP2GamesWon = findViewById(R.id.tvChallengerWinsCount)
        tvNumberOfMatches = findViewById(R.id.tvTotalMatchesCount)
        tvLeaderDifference = findViewById(R.id.tvLeaderWinRate)
        btnVoltar = findViewById(R.id.btnHistoryBack)
        btnZeroHist = findViewById(R.id.btnClearHistory)

        if (playerOneWins > playerTwoWins) {
            tvLeaderName.text = playerOneName
            tvChallengerName.text = playerTwoName
            tvP1GamesWon.text = playerOneWins.toString()
            tvP2GamesWon.text = playerTwoWins.toString()
            tvNumberOfMatches.text = (playerOneWins + playerTwoWins).toString()
        } else {
            tvLeaderName.text = playerTwoName
            tvChallengerName.text = playerOneName
            tvP1GamesWon.text = playerTwoWins.toString()
            tvP2GamesWon.text = playerOneWins.toString()
            tvNumberOfMatches.text = (playerOneWins + playerTwoWins).toString()

        }
        btnVoltar.setOnClickListener {
            finish()
        }

        btnZeroHist.setOnClickListener {
            finish()
        }
    }

}

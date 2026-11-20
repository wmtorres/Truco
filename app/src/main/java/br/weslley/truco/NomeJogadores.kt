package br.weslley.truco

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NomeJogadores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nome_jogadores)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnConfirmDuel = findViewById<AppCompatButton>(R.id.btnConfirmDuel)
        val etPlayer1Name = findViewById<EditText>(R.id.etPlayer1Name)
        val etPlayer2Name = findViewById<EditText>(R.id.etPlayer2Name)
        btnConfirmDuel.setOnClickListener {
            val intent = Intent(this, ContagemTentos::class.java).apply {
                putExtra("NOME_J1", etPlayer1Name.text.toString())
                putExtra("NOME_J2", etPlayer2Name.text.toString())
            }
            startActivity(intent)
        }
    }
}
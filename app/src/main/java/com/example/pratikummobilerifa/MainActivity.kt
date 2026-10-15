package com.example.pratikummobilerifa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratikummobilerifa.databinding.ActivityMainBinding
import com.example.pratikummobilerifa.pertemuan_4.FourthActivity
import com.example.pratikummobilerifa.pertemuan_5.FifthActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnToFourth.setOnClickListener {
            val i = Intent(this@MainActivity, FourthActivity::class.java)

            // Masukkan data extra terlebih dahulu
            i.putExtra("name", "Politeknik Caltex Riau")
            i.putExtra("from", "Rumbai")
            i.putExtra("age", 25)

            // Panggil startActivity HANYA SATU KALI di sini
            startActivity(i)
        }

        binding.btnToFifth.setOnClickListener {
            val i = Intent(this@MainActivity, FifthActivity::class.java)
            startActivity(i)
        }

        binding.btnToAuth.setOnClickListener {
            val i = Intent(this@MainActivity, AuthActivity::class.java)
            startActivity(i)
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}

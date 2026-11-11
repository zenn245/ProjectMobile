package com.example.pratikummobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratikummobile.databinding.ActivityMainBinding
import com.example.pratikummobile.pertemuan_4.FourthActivity
import com.example.pratikummobile.pertemuan_5.FifthActivity
import com.example.pratikummobile.pertemuan_7.SeventhActivity

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

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", null)
        if (!username.isNullOrEmpty()) {
            binding.tvUserHeader.text = "Selamat Datang, $username!"
        }

        binding.btnToFourth.setOnClickListener {
            val i = Intent(this@MainActivity, FourthActivity::class.java)
            i.putExtra("name", "Politeknik Caltex Riau")
            i.putExtra("from", "Rumbai")
            i.putExtra("age", 25)
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

        binding.btnToSeventh.setOnClickListener {
            val i = Intent(this@MainActivity, SeventhActivity::class.java)
            startActivity(i)
        }

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    val intent = Intent(this@MainActivity, AuthActivity::class.java)
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
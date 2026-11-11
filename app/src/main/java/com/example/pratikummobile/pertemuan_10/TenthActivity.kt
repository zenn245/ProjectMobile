package com.example.pratikummobile.pertemuan_10

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pratikummobile.R
import com.example.pratikummobile.databinding.ActivityTenthBinding

// Latihan Pertemuan 10 (ListView + ArrayAdapter dengan simple_list_item_1).
// Versi SimpleAdapter dan Custom Adapter ada di tab More dan Message (BaseActivity).
class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    private val dataList = listOf(
        "Kotlin", "Java", "Python", "C++", "JavaScript", "Dart", "Swift", "Go", "Ruby",
        "R", "PHP", "C#", "TypeScript", "Shell", "SQL", "Perl", "Rust", "Scala",
        "Haskell", "Lua", "Erlang", "Prolog", "Assembly", "Objective-C", "VBA"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Tenth"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        binding.listViewItems.adapter = adapter
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Kamu memilih: ${dataList[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

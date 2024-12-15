package com.frodzy.nestednavigationftbottomnavigationview.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.frodzy.nestednavigationftbottomnavigationview.R
import com.frodzy.nestednavigationftbottomnavigationview.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpEventListeners()
    }

    private fun setUpEventListeners(){
        binding.apply {
            btnFilter.setOnClickListener {
                val filterIntent = Intent(this@SearchActivity, FilterActivity::class.java)
                startActivity(filterIntent)
            }
        }
    }
}
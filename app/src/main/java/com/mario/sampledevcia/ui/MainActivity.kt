package com.mario.sampledevcia.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.ceep.ui.adapter.NoteAdapter
import com.mario.sampledevcia.R
import com.mario.sampledevcia.dao.NoteDAO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dao by lazy {
        NoteDAO()
    }
    private val adapter by lazy {
        NoteAdapter(
            this,
            dao.findAll(),
            this::gotoDetails
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun setupRecycleView() {
        list_notes.adapter = adapter
    }

    private fun initListeners() {
        fab_add.setOnClickListener {
            val intent = Intent(this, FormNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gotoDetails(position: Int) {
        val intent = Intent(this, DetailNoteActivity::class.java)
        intent.putExtra(POSITION_NOTE, position)
        startActivity(intent)
    }
}
package com.mario.sampledevcia.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mario.sampledevcia.R
import com.mario.sampledevcia.dao.NoteDAO
import kotlinx.android.synthetic.main.activity_details_note.*

class DetailNoteActivity : AppCompatActivity() {

    private val positionNote: Int? by lazy {
        val extras = intent.extras
        if (extras != null && extras.containsKey(POSITION_NOTE)) {
            return@lazy extras.getInt(POSITION_NOTE)
        }
        return@lazy null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_note)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        findNote()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> gotoForm()
            R.id.menu_remove -> remove()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun remove() {
        val dao = NoteDAO()
        positionNote?.let {
            dao.remove(it)
            finish()
        }
    }

    private fun gotoForm() {
        val intent = Intent(this, FormNoteActivity::class.java)
        intent.putExtra(POSITION_NOTE, positionNote)
        startActivity(intent)
    }

    private fun findNote() {
        positionNote?.let {
            val dao = NoteDAO()
            val nota = dao.findByPosition(it)
            detail_note_title.text = nota.title
            detail_note_description.text = nota.description
        } ?: finish()
    }
}
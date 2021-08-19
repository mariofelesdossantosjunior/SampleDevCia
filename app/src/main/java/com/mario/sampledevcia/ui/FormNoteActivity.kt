package com.mario.sampledevcia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mario.sampledevcia.R
import com.mario.sampledevcia.dao.NoteDAO
import com.mario.sampledevcia.domain.Note
import com.mario.sampledevcia.helper.POSITION_NOTE
import kotlinx.android.synthetic.main.activity_form_note.*

class FormNoteActivity : AppCompatActivity() {

    private val positionNote: Int? by lazy {
        val extras = intent.extras
        if (extras != null && extras.containsKey(POSITION_NOTE)) {
            return@lazy extras.getInt(POSITION_NOTE)
        }
        return@lazy null
    }
    private val dao by lazy {
        NoteDAO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_note)
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        findNoteByPosition()
    }

    private fun initListeners() {
        //val buttonSave = findViewById<Button>(R.id.form_note_button_save)
        form_note_button_save.setOnClickListener {
            val note = createNote()
            save(note)
        }
    }

    private fun createNote(): Note {
        val title = form_note_edit_title.text.toString()
        val description = form_note_edit_description.text.toString()
        return Note(title, description)
    }

    private fun save(note: Note) {
        //Podemos ou não ter o lambda nomeado
        positionNote?.let { position ->
            dao.update(note, position)
        } ?: dao.save(note)

        finish()
    }

    private fun findNoteByPosition() {
        positionNote?.let {
            val note = dao.findByPosition(it)
            form_note_edit_title.setText(note.title)
            form_note_edit_description.setText(note.description)
        }
    }
}
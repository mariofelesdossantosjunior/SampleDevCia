package com.mario.sampledevcia.dao

import com.mario.sampledevcia.domain.Note

class NoteDAO {

    fun findAll(): List<Note> {
        return notes
    }

    fun save(vararg notes: Note) {
        Companion.notes.addAll(notes)
    }

    fun findByPosition(position: Int): Note {
        return notes[position]
    }

    fun update(note: Note, position: Int) {
        notes[position] = note
    }

    fun remove(position: Int) {
        notes.removeAt(position)
    }

    fun clear(){
        notes.clear()
    }

    companion object {
        private val notes = mutableListOf<Note>()
    }

}
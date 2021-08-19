package com.mario.sampledevcia.dao

import com.mario.sampledevcia.domain.Note
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteDAOTest {

    lateinit var dao: NoteDAO

    @Before
    fun setup() {
        dao = NoteDAO()
    }

    @After
    fun after() {
        dao.clear()
    }

    @Test
    fun `should return empty notes when find all is call`() {
        val findAll = dao.findAll()
        Assert.assertTrue(findAll.isEmpty())
    }

    @Test
    fun `should return all notes when find all is call`() {
        val fakeNote1 = Note(
            title = "fakeTitle",
            description = "fakeDescription"
        )
        val fakeNote2 = Note(
            title = "fakeTitle",
            description = "fakeDescription"
        )
        dao.save(fakeNote1, fakeNote2)

        val findAll = dao.findAll()
        Assert.assertEquals(2, findAll.size)
    }

    @Test
    fun `should return one item when save is call`() {
        val fakeNote = Note(
            title = "fakeTitle",
            description = "fakeDescription"
        )
        dao.save(fakeNote)
        val findAll = dao.findAll()

        Assert.assertTrue(findAll.isNotEmpty())
        Assert.assertEquals(fakeNote, findAll.first())
    }

    @Test
    fun `should update item by position when invoke update is call`() {
        val fakeNote = Note(
            title = "fakeTitle",
            description = "fakeDescription"
        )
        dao.save(fakeNote)


        val noteUpdated = fakeNote.apply {
            title = "aaaa"
            description = "bbbb"
        }

        dao.update(noteUpdated, 0)

        val find = dao.findByPosition(0)
        Assert.assertEquals(noteUpdated, find)
    }

    @Test
    fun `should remove item by position when invoke remove is call`() {
        val fakeNote = Note(
            title = "fakeTitle",
            description = "fakeDescription"
        )
        dao.save(fakeNote)
        dao.remove(0)

        val findAll = dao.findAll()
        Assert.assertTrue(findAll.isEmpty())
    }

}
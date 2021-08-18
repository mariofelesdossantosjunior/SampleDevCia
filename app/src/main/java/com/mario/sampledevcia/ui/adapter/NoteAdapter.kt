package br.com.alura.ceep.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mario.sampledevcia.R
import com.mario.sampledevcia.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(
    private val context: Context,
    private val notes: List<Note>,
    val callBack: (position: Int) -> Unit
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var note: Note

        init {
            itemView.setOnClickListener {
                callBack(adapterPosition)
            }
        }

        fun bind(note: Note) {
            this.note = note
            itemView.note_item_title.text = note.title
            itemView.note_item_description.text = note.description
        }

    }

}

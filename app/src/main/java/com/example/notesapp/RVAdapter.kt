package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val context:Context,val noteClickInterface: NoteClickInterface,val noteClickDeleteInterface: NoteClickDeleteInterface):RecyclerView.Adapter<RVAdapter.NoteViewHolder>() {


    private val allNotes=ArrayList<Note>()

    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val noteTV:TextView=itemView.findViewById(R.id.NoteTitle)
        val timeTV:TextView=itemView.findViewById(R.id.TimeStamp)
        val deleteTV:ImageView=itemView.findViewById(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NoteViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteTV.setText(allNotes[position].noteTitle)
        holder.timeTV.setText("Last Updated :"+allNotes[position].timeStamp)
        holder.deleteTV.setOnClickListener{
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))

        }
        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(allNotes.get(position))
        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()


    }


}

interface NoteClickDeleteInterface{
    fun onDeleteIconClick(note:Note)
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}
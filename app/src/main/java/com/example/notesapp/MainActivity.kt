package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var addFAB:FloatingActionButton
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerView=findViewById(R.id.recyclerView)
        addFAB=findViewById(R.id.addFAB)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val rvAdapter=RVAdapter(this,this,this)
        recyclerView.adapter=rvAdapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this , {list->
            list?.let {
                rvAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener{
            val intent= Intent(this@MainActivity,AddEditActivity::class.java)
            startActivity(intent)
            this.finish()


        }


    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} deleted",Toast.LENGTH_SHORT).show()

    }

    override fun onNoteClick(note: Note) {
        val intent= Intent(this,AddEditActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }
}
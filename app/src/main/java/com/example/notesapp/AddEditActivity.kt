package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditActivity : AppCompatActivity() {
    lateinit var noteTitleEdit:EditText
    lateinit var noteDescriptionEdit:EditText
    lateinit var viewModel: NoteViewModel
    lateinit var addUpdateButton:Button
    var noteID=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)


        noteTitleEdit=findViewById(R.id.EditNoteTitle)
        noteDescriptionEdit=findViewById(R.id.EditNoteDescription)
        addUpdateButton=findViewById(R.id.button)
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if(noteType.equals("Edit")){

            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDes=intent.getStringExtra("noteDescription")
            noteID=intent.getIntExtra("noteType",-1)
            addUpdateButton.setText("Update Note")
            noteTitleEdit.setText(noteTitle)
            noteDescriptionEdit.setText(noteDes)
        }
        addUpdateButton.setOnClickListener{

            val noteTitle=noteTitleEdit.text.toString()
            val noteDescription=noteDescriptionEdit.text.toString()
            if (noteType.equals("Edit")){
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf= SimpleDateFormat("dd MMM,yyyy- HH:mm")
                    val currentDate:String=sdf.format(Date())

                    val updatedNote=Note(noteTitle,noteDescription,currentDate)
                    updatedNote.id=noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(this,"Note updated..!",Toast.LENGTH_LONG).show()
                }



            }
            else{
                if(noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf= SimpleDateFormat("dd MMM,yyyy- HH:mm")
                    val currentDate:String=sdf.format(Date())


                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this,"Note added!!",Toast.LENGTH_LONG).show()
                }


            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }


    }
}
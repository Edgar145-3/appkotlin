package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_datos.*

class datos : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)
        btnguardar.setOnClickListener {
            validacion();
        }
    }

   private fun validacion(){
       if(nombreip.text.isEmpty()){
            nombreip.setError("Required");
       }
       else if(ocupacionip.text.isEmpty()){
           ocupacionip.setError("Required");
       }else{
           val homeIntent= Intent(this,homeActivity::class.java);
           startActivity(homeIntent);
           base();
       }
   }
    //private lateinit var database: DatabaseReference
    //var p:person=person();
    //database = Firebase.database.reference;
    fun base(){
        val valores:String=""+nombreip.text+""+ocupacionip.text;
        val database = Firebase.database;
        val myRef = database.getReference("usuario");

        myRef.setValue(valores);
    }
}



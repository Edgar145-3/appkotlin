package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC;
}
class homeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundle = intent.extras;
        val email= bundle?.getString("email");
        val provider=bundle?.getString("provider");
        val tipo=bundle?.getString("tipo");
        setup(email ?: "",provider ?: "",tipo ?: "");

    }

    fun setup(email: String, provider: String,tipo: String){
        title = "Inicio";
        memail.text= email;
        mprovedor.text=provider;

        if (tipo == "reg"){
            btnmodificar.isEnabled=false;
            btnagregar.setOnClickListener {
                val homeIntent= Intent(this,datos::class.java);
                startActivity(homeIntent);
            }
        }else{
            btnagregar.isEnabled=false;
            btnmodificar.setOnClickListener {
                val homeIntent= Intent(this,datos::class.java);
                startActivity(homeIntent);
            }
        }

        btncerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            onBackPressed();
            val homeIntent= Intent(this,AuthActivity::class.java);
            startActivity(homeIntent);
        }
    }
}
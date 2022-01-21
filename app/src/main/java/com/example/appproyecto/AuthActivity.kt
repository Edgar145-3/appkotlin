package com.example.appproyecto

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    @SuppressLint("InvalidAnalyticsName")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Analitycs event
        val analytics=FirebaseAnalytics.getInstance(this);
        val bundle=Bundle();
        bundle.putString("messge","Integracion de firebase completa");
        analytics.logEvent("Init Screen",bundle);

        //setup
        setup();
    }
    private fun setup(){
        title= "Acceder";

        btnregistrar.setOnClickListener{
            if (email.text.isNotEmpty()&& password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),
                    password.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?:"", provider = ProviderType.BASIC,"reg");

                        }else{
                            alerta();
                        }
                }
            }
        }//Fin Registrar

        btniniciar.setOnClickListener{
            if (email.text.isNotEmpty()&& password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),
                    password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        showHome(it.result?.user?.email ?:"", provider = ProviderType.BASIC,"ini");
                    }else{
                        alerta();
                    }
                }
            }
        }//Fin Iniciar
    }

    private fun alerta(){
        val builder= AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Error de Auhtenticacion");
        builder.setPositiveButton("Aceptar",null);
        val dialog: AlertDialog= builder.create();
        dialog.show();
    }
    private fun showHome(email: String, provider: ProviderType,tipo: String){
        val homeIntent= Intent(this,homeActivity::class.java).apply {
            putExtra("email",email);
            putExtra("provider",provider.name);
            putExtra("tipo",tipo);
        }
        startActivity(homeIntent);
    }
}
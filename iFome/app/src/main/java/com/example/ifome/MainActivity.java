package com.example.ifome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference usuarios = referencia.child("usuarios").child("004");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Firebase", snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Salvar Dados no Firebase
        /* DatabaseReference usuarios = referencia.child("usuarios");
        usuarios.child("003").child("nome").setValue("Wesley");
        referencia.child("usuarios").child("001").child("nome").setValue("Clara");

        Usuario usuario = new Usuario();
        usuario.setNome("Luiz");
        usuario.setSobrenome("Eduardo");
        usuario.setEmail("dudu@gmail.com");

        usuarios.child("004").setValue(usuario);*/



    }
}
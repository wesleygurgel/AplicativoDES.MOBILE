package com.example.ifome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth usuario = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Deslogar usuario */
        usuario.signOut();

        /* Logar Usuario */
        usuario.signInWithEmailAndPassword("wesleygurgel27@gmail.com", "wesley123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("LoginUser", "Sucesso ao logar o usuário " + usuario.getCurrentUser().getEmail());
                        }else{
                            Log.i("LoginUser", "Falha ao logar o usuário" + usuario.getCurrentUser());
                        }
                    }
                });

        /* Verificar se o usuário está logado*/

        if ( usuario.getCurrentUser() != null) {
            Log.i("Usuario", "Usuario Logado");
        }else{
            Log.i("Usuario", "Usuario desLogado");

        }

        /* Cadastro de Usuário
        usuario.createUserWithEmailAndPassword("wesleygurgel27@gmail.com", "wesley123456")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("CreateUser", "Sucesso ao cadastrar o usuário");
                        }else{
                            Log.i("CreateUser", "Falha ao cadastrar o usuário");
                        }
                    }
                });*/

        /* DatabaseReference usuarios = referencia.child("usuarios").child("004");

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
        DatabaseReference usuarios = referencia.child("usuarios");
        usuarios.child("003").child("nome").setValue("Wesley");
        referencia.child("usuarios").child("001").child("nome").setValue("Clara");

        Usuario usuario = new Usuario();
        usuario.setNome("Luiz");
        usuario.setSobrenome("Eduardo");
        usuario.setEmail("dudu@gmail.com");

        usuarios.child("004").setValue(usuario);*/



    }
}
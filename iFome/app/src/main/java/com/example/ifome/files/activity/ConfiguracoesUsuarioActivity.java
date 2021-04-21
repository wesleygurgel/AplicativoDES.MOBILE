package com.example.ifome.files.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ifome.R;
import com.example.ifome.files.helper.ConfiguracaoFirebase;
import com.example.ifome.files.helper.UsuarioFirebase;
import com.example.ifome.files.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguracoesUsuarioActivity extends AppCompatActivity {

    private EditText editUsuarioNome, editUsuarioEndereco;
    private String idUsuario;
    private DatabaseReference firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_usuario);

        //Configurações iniciais
        inicializarComponentes();
        idUsuario = UsuarioFirebase.getIdUsuario();
        firebaseRef = ConfiguracaoFirebase.getFirebase();

        //Configurações Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Configurações usuário");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recupera dados do usuário
        recuperarDadosUsuario();

    }

    private void recuperarDadosUsuario(){

        DatabaseReference usuarioRef = firebaseRef
                .child("usuarios")
                .child( idUsuario );

        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if( dataSnapshot.getValue() != null ){

                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    editUsuarioNome.setText( usuario.getNome() );
                    editUsuarioEndereco.setText( usuario.getEndereco() );

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void validarDadosUsuario(View view){

        //Valida se os campos foram preenchidos
        String nome = editUsuarioNome.getText().toString();
        String endereco = editUsuarioEndereco.getText().toString();

        if( !nome.isEmpty()){
            if( !endereco.isEmpty()){

                Usuario usuario = new Usuario();
                usuario.setIdUsuario( idUsuario );
                usuario.setNome( nome );
                usuario.setEndereco( endereco );
                usuario.salvar();

                exibirMensagem("Dados atualizados com sucesso!");
                finish();

            }else{
                exibirMensagem("Digite seu endereço completo!");
            }
        }else{
            exibirMensagem("Digite seu nome!");
        }

    }

    private void exibirMensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT)
                .show();
    }

    private void inicializarComponentes(){
        editUsuarioNome = findViewById(R.id.editUsuarioNome);
        editUsuarioEndereco = findViewById(R.id.editUsuarioEndereco);
    }

}
package bike.douglas.com.bikejanu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Activity.AlertarFurtoRoubo;
import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Activity.DadosBike;
import bike.douglas.com.bikejanu.Activity.EditarBike;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

import static android.view.View.OnClickListener;


public class UsuarioAdapter extends ArrayAdapter<Usuarios>  {


    public Context context;
    public List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();

    int cont=0;
    private ProgressBar progressBar;

  //  public  int recebeQtd=0;
  //  public static int quantidadeBikesRoubadas=0;



    DatabaseReference databaseReference;
    FirebaseDatabase  firebaseDatabase;





    public UsuarioAdapter (Context c, ArrayList<Usuarios> objects) {
        super(c, 0,objects);

        this.context = c;
        this.listaUsuarios = objects;

    }





    @Override
    public int getCount() {

        return super.getCount();

    }






    @Nullable
    @Override
    public Usuarios getItem(int position) {

        return super.getItem(position);


    }

    @Override
    public long getItemId(int position) {


        return super.getItemId(position);


    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {






        View view = null;







            //verifica se a lista está vazia
           if(listaUsuarios!=null) {




            // inicializa objetos para a montagem da view

            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_usuarios, parent, false);






            final TextView txtNome  = (TextView) view.findViewById(R.id.txtNomeID);









            final Usuarios usuario123;

             usuario123 = listaUsuarios.get(position);


          //  quantidadeBikesRoubadas = recebeQtd;





               firebaseDatabase = FirebaseDatabase.getInstance();
                   // firebaseDatabase.setPersistenceEnabled(true);
                databaseReference = firebaseDatabase.getReference();


                 final Usuarios usuarios = listaUsuarios.get(position);












            // recupera usuario
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user !=null){


                Usuarios usuarioselecao = new Usuarios();

                usuarioselecao = listaUsuarios.get(position);

                // passa dados  para a tela dados BIKE

                Bundle params = new Bundle();
                params.putString("nomeUsuario",          user.getDisplayName());
                params.putString("emailUsuario",         user.getEmail());



                //   params.putString("dadosmarca",        bikeselecao.getMarca());



               Intent intent = new Intent(UsuarioAdapter.super.getContext(), CadastroUsuario.class);
               intent.putExtras(params);

               context.startActivity(intent);

          }

        }

        return view;





    }




}

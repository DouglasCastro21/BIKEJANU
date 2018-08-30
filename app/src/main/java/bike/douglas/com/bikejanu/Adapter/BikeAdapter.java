package bike.douglas.com.bikejanu.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bike.douglas.com.bikejanu.Activity.AlertarFurtoRoubo;
import bike.douglas.com.bikejanu.Activity.CadastroBike;
import bike.douglas.com.bikejanu.Activity.DadosBike;

import bike.douglas.com.bikejanu.Activity.Dialogo_Personalizado;
import bike.douglas.com.bikejanu.Activity.ImagemBike;
import bike.douglas.com.bikejanu.Activity.MainActivity;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Fragments.Entrar;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Entidades.Bike;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.view.View.*;




public class BikeAdapter extends ArrayAdapter<Bike>  {


    private Context context;
    private List<Bike> listabikes = new ArrayList<Bike>();


    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;




    public BikeAdapter( Context c, ArrayList<Bike> objects) {
        super(c, 0,objects);

        this.context = c;
       this.listabikes = objects;

    }





    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        View view = null;


        // verifica se a lista está vazia
        if(listabikes!=null) {

            // inicializa objetos para a montagem da view

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_bikes, parent, false);


            final TextView txtViewNumeroSerie = (TextView) view.findViewById(R.id.txtViewNumeroSerie);
            final TextView txtViewMarca = (TextView) view.findViewById(R.id.txtViewMarca);
            TextView txtViewCaixaDescricao = (TextView) view.findViewById(R.id.txtCaixaDescricaoID);
            final ImageView imagem = (ImageView) view.findViewById(R.id.imagemListaID);
            final TextView txtViewModelo = (TextView) view.findViewById(R.id.textViewModeloID);
            final TextView txtViewCor = (TextView) view.findViewById(R.id.textViewCorID);
            final TextView alertaNumero = (TextView) view.findViewById(R.id.alertaNumeroID);

            firebaseDatabase = FirebaseDatabase.getInstance();
//          firebaseDatabase.setPersistenceEnabled(true);
            databaseReference = firebaseDatabase.getReference();


            final Bike bike1 = listabikes.get(position);

            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewCaixaDescricao.setText(bike1.getDescricao());
            txtViewModelo.setText(bike1.getModelo());
            txtViewCor.setText(bike1.getCor());
         ///  alertaNumero.setText(bike1.getAlertaNumero());


            imagem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bike b = new Bike();

                    // recupera posição da bike


                    Bike bikeselecao = new Bike();
                    bikeselecao = listabikes.get(position);

                    // passa dados  para a tela dados usuarios e Cadastro Bike

                    Bundle params = new Bundle();
                    params.putString("dadosmodelo", bikeselecao.getModelo());
                    params.putString("dadosmarca", bikeselecao.getMarca());
                    params.putString("dadosnumero_serie", bikeselecao.getNumero_serie());
                    params.putString("dadosdescricao", bikeselecao.getDescricao());
                    params.putString("dadoscor", bikeselecao.getCor());


                    Intent intent = new Intent(BikeAdapter.super.getContext(), DadosBike.class);
                    intent.putExtras(params);

                    context.startActivity(intent);


                }
            });


            // recupera usuario
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user !=null){ /// se o usuario não estiver logado .. não aparece isso nas opçoes da listagem das bikes
                // no inicio do app
                // o usuario logado não podera voltar sem está desconetado


                txtViewCaixaDescricao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final CharSequence[] opcoes = {"Alertar Furto/Roubo", "Editar", "Remover"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(BikeAdapter.super.getContext());
                        builder.setTitle("");
                        builder.setItems(opcoes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                Bike b = new Bike();

                                // recupera posição da bike


                                Bike bikeselecao = new Bike();
                                bikeselecao = listabikes.get(position);

                                // recupera usuario pelo email
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String email = user.getEmail();


                                // converte o email pra base 64
                                String identificadorUsuario = Base64Custom.codificarBase64(email);


                                if (opcoes[i].equals("Alertar Furto/Roubo")) {


                                    // passa dados  para a tela dados usuarios e Cadastro Bike

                                    Bundle params = new Bundle();
                                    params.putString("Alertamodelo", bikeselecao.getAlertaNumero());
                                   // params.putString("marca", bikeselecao.getMarca());
                                    //params.putString("numero_serie", bikeselecao.getNumero_serie());
                                  //  params.putString("descricao", bikeselecao.getDescricao());
                                  //  params.putString("cor", bikeselecao.getCor());


                                    Intent intent = new Intent(BikeAdapter.super.getContext(), AlertarFurtoRoubo.class);
                                    intent.putExtras(params);

                                    context.startActivity(intent);




                                } else if (opcoes[i].equals("Editar")) {


                                    // passa dados  para a tela dados usuarios e Cadastro Bike

                                    Bundle params = new Bundle();
                                    params.putString("modelo", bikeselecao.getModelo());
                                    params.putString("marca", bikeselecao.getMarca());
                                    params.putString("numero_serie", bikeselecao.getNumero_serie());
                                    params.putString("descricao", bikeselecao.getDescricao());
                                    params.putString("cor", bikeselecao.getCor());


                                    Intent intent = new Intent(BikeAdapter.super.getContext(), CadastroBike.class);
                                    intent.putExtras(params);

                                    context.startActivity(intent);

                                    //Editar campos

                                    b.setNumero_serie(bikeselecao.getNumero_serie());
                                    b.setModelo(txtViewModelo.getText().toString().trim());
                                    b.setMarca(txtViewMarca.getText().toString().trim());
                                    b.setCor(txtViewCor.getText().toString().trim());
                                    b.setNumero_serie(txtViewNumeroSerie.getText().toString().trim());


                                    // edita a bike no nó todas as bikes
                                    databaseReference = Configuracao_Firebase.getFirebase().child("TodasBikes");
                                    databaseReference.child(b.getNumero_serie()).setValue(b);

                                    // edita a bike no nó Bikes
                                    databaseReference = Configuracao_Firebase.getFirebase().child("Bikes").child(identificadorUsuario);
                                    databaseReference.child(b.getNumero_serie()).setValue(b);


                                    //// até aq


                                    //   Toast.makeText(BikeAdapter.super.getContext(), "As alterações foram salvas", Toast.LENGTH_LONG).show();


                                } else if (opcoes[i].equals("Remover")) {


                                    // Remove a bike no nó todas as bikes
                                    databaseReference = Configuracao_Firebase.getFirebase().child("TodasBikes");
                                    databaseReference.child(bikeselecao.getNumero_serie()).removeValue();

                                    //EXCLUI A BIKE
                                    databaseReference = Configuracao_Firebase.getFirebase().child("Bikes").child(identificadorUsuario);
                                    databaseReference.child(bikeselecao.getNumero_serie()).removeValue();

                                    Toast.makeText(BikeAdapter.super.getContext(), " Sua Bicicleta foi Exluida", Toast.LENGTH_LONG).show();

                                }

                            }
                        });
                        builder.show();

                    }
                });

        }
        }
        return view;
    }






}

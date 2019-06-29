package bike.douglas.com.bikejanu.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import bike.douglas.com.bikejanu.Activity.AlertarFurtoRoubo;
import bike.douglas.com.bikejanu.Activity.CadastroUsuario;
import bike.douglas.com.bikejanu.Activity.DadosBike;

import bike.douglas.com.bikejanu.Activity.EditarBike;
import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bike.douglas.com.bikejanu.Model.Bike;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.view.View.*;




public class BikeAdapter extends ArrayAdapter<Bike>  {




    public Context context;
    public List<Bike> listabikes = new ArrayList<Bike>();


    //  public  int recebeQtd=0;
   //  public static int quantidadeBikesRoubadas=0;

    DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference();

    DatabaseReference databaseReference;
    FirebaseDatabase  firebaseDatabase;





    public BikeAdapter( Context c, ArrayList<Bike> objects) {
        super(c, 0,objects);

        this.context = c;
        this.listabikes = objects;

    }







    @Override
    public int getCount() {



        return super.getCount();

    }






    @Nullable
    @Override
    public Bike getItem(int position) {







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
           if(listabikes!=null) {


            // inicializa objetos para a montagem da view

            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_bikes, parent, false);




            final TextView txtViewNome  = (TextView) view.findViewById(R.id.NumeroID);

            final TextView txtViewNumeroSerie        = (TextView) view.findViewById(R.id.txtViewNumeroSerie);
            final TextView txtViewMarca              = (TextView) view.findViewById(R.id.txtViewMarca);
            final TextView txtViewCaixaDescricao     = (TextView) view.findViewById(R.id.CaixaDescricaoID);
            final CircleImageView imagem             = (CircleImageView) view.findViewById(R.id.listaImagemID);
            final TextView txtViewModelo             = (TextView) view.findViewById(R.id.textViewModeloID);
            final TextView txtViewCor                = (TextView) view.findViewById(R.id.textViewCorID);
            final TextView txtViewStatus             = (TextView) view.findViewById(R.id.textStatusID);



            final TextView txtViewAlertaEstado       = (TextView) view.findViewById(R.id.cadastroCidadeID);
            final TextView txtViewAlertaCidade       = (TextView) view.findViewById(R.id.alertacidadeID);
            final TextView txtViewAlertaRua          = (TextView) view.findViewById(R.id.cadastroBairroID);
            final TextView txtViewAlertaBairro       = (TextView) view.findViewById(R.id.cadastroBairroID);
            final TextView txtViewAlertaData         = (TextView) view.findViewById(R.id.alertaDataID);
            final TextView txtViewAlertaHora         = (TextView) view.findViewById(R.id.alertaHoraID);
            final TextView txtViewAlertaBoletim      = (TextView) view.findViewById(R.id.BoletimID);
            final TextView txtViewAlertaDescricao    = (TextView) view.findViewById(R.id.alertaDescricaoID);
            final Bike bikeRoubadaFurtada;


            ImageView novaNotificacao                =  (ImageView)view.findViewById(R.id.novaNotificacaoID);
        //    final String clicada                        =  (TextView) view.findViewById(R.id.clicadaID);




               bikeRoubadaFurtada = listabikes.get(position);






               SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
               // OU
               SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm");

               Date data = new Date();

               Calendar cal = Calendar.getInstance();
               cal.setTime(data);

               Date data_atual = cal.getTime();

               String  data_completa = dateFormat.format(data_atual);

               String hora_atual = dateFormat_hora.format(data_atual);

               Log.i("data_completa", data_completa);
               Log.i("data_atual", data_atual.toString());
               Log.i("hora_atual", hora_atual);




               if(bikeRoubadaFurtada.getStatus().equals("Roubada")) {


       view.setBackgroundColor(Color.RED);

            if(bikeRoubadaFurtada.getAlertaDate().equals(data_completa)){

                novaNotificacao.setVisibility(VISIBLE);

            }else
            {

               novaNotificacao.setVisibility(GONE);


            }






    }











    if( bikeRoubadaFurtada.getStatus().equals("Furtada")){


        view.setBackgroundColor(Color.YELLOW);

        if(bikeRoubadaFurtada.getAlertaDate().equals(data_completa)){

            novaNotificacao.setVisibility(VISIBLE);

        }else
        {

            novaNotificacao.setVisibility(GONE);


        }





    }

   //  quantidadeBikesRoubadas = recebeQtd;




               firebaseDatabase = FirebaseDatabase.getInstance();
                   // firebaseDatabase.setPersistenceEnabled(true);
                databaseReference = firebaseDatabase.getReference();



                final Bike bike1 = listabikes.get(position);






            txtViewNumeroSerie.setText(bike1.getNumero_serie());
            txtViewMarca.setText(bike1.getMarca());
            txtViewCaixaDescricao.setText(bike1.getDescricao());
            txtViewModelo.setText(bike1.getModelo());
            txtViewCor.setText(bike1.getCor());
            txtViewStatus.setText(bike1.getStatus());


            Glide.with(BikeAdapter.super.getContext()).load(bike1.getFotoBikeUrl1()).into(imagem);







               imagem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bike b = new Bike();

                    // recupera posição da bike




                    Bike bikeselecao = new Bike();

                    bikeselecao = listabikes.get(position);

                    // passa dados  para a tela dados BIKE








                    Bundle params = new Bundle();
                    params.putString("dadosmodelo",       bikeselecao.getModelo());
                    params.putString("dadosmarca",        bikeselecao.getMarca());
                    params.putString("dadosnumero_serie", bikeselecao.getNumero_serie());
                    params.putString("dadosdescricao",    bikeselecao.getDescricao());
                    params.putString("dadoscor",          bikeselecao.getCor());
                    params.putString("dadosImagem1",      bikeselecao.getFotoBikeUrl1());



                    params.putString("alertaEstado",      bikeselecao.getAlertaEstado());
                    params.putString("alertaCidade",      bikeselecao.getAlertaCidade());
                    params.putString("alertaBairro",      bikeselecao.getAlertaBairro());
                    params.putString("alertaRua",         bikeselecao.getAlertaRua());
                    params.putString("alertaData",        bikeselecao.getAlertaDate());
                    params.putString("alertaHora",        bikeselecao.getAlertaHora());
                    params.putString("alertaBoletim",     bikeselecao.getBoletim());
                    params.putString("alertadescricao",   bikeselecao.getAlertaDescricao());
                    params.putString("status",            bikeselecao.getStatus());
                    params.putString("proprietario",      bikeselecao.getProprietario());


                    params.putString("Numero_serie",       bikeselecao.getNumero_serie());






                    Intent intent = new Intent(BikeAdapter.super.getContext(), DadosBike.class);
                    intent.putExtras(params);

                    context.startActivity(intent);


                }
            });


               AreaUsuario deOndeVenho = new AreaUsuario();
               String resposta="0";


              resposta = AreaUsuario.paraOndeVou;


             //  Toast.makeText(BikeAdapter.super.getContext(), "A bike foi marcada como :" +resposta, Toast.LENGTH_LONG).show();


               // recupera usuario
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            if ((user !=null ) &&( resposta.equals("0"))){ /// se o usuario não estiver logado .. não aparece isso nas opçoes da listagem das bikes
                // no inicio do app
                // o usuario logado não podera voltar sem está desconetado


                txtViewCaixaDescricao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final CharSequence[] opcoes = {"Mudar status da bike", "Editar", "Remover"};

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


                                if (opcoes[i].equals("Mudar status da bike")) {


                                    // passa dados  a tela alerta Furto/roubo

                                    Bundle params = new Bundle();

                                    params.putString("alertaEstado",   bikeselecao.getAlertaEstado());
                                    params.putString("alertaCidade",   bikeselecao.getAlertaCidade());
                                    params.putString("alertaBairro",   bikeselecao.getAlertaBairro());
                                    params.putString("alertaRua",      bikeselecao.getAlertaRua());
                                    params.putString("alertaHora",     bikeselecao.getAlertaHora());
                                    params.putString("alertaData",     bikeselecao.getAlertaDate());
                                    params.putString("alertaBoletim",  bikeselecao.getBoletim());
                                    params.putString("alertadescricao",bikeselecao.getAlertaDescricao());


                                    /// DADOS QUE NÃO vão ficar visiveis na tela Alerta furto e roubo

                                    params.putString("modelo",          bikeselecao.getModelo());
                                    params.putString("marca",           bikeselecao.getMarca());
                                    params.putString("numero_serie",    bikeselecao.getNumero_serie());
                                    params.putString("descricao",       bikeselecao.getDescricao());
                                    params.putString("cor",             bikeselecao.getCor());
                                    params.putString("status",          bikeselecao.getStatus());
                                    params.putString("Imagem1",         bikeselecao.getFotoBikeUrl1());
                                    params.putString("Imagem2",         bikeselecao.getFotoBikeUrl2());
                                    params.putString("Imagem3",         bikeselecao.getFotoBikeUrl3());
                                    params.putString("Imagem4",         bikeselecao.getFotoBikeUrl4());
                                    params.putString("Imagem5",         bikeselecao.getFotoBikeUrl5());
                                    params.putString("latitude",        bikeselecao.getLatitude() );
                                    params.putString("longitude",       bikeselecao.getLongitude());
                                    params.putString("proprietario",      bikeselecao.getProprietario());
                                    params.putString("proprietarioID",      bikeselecao.getProprietarioID());


                                    Intent intent = new Intent(BikeAdapter.super.getContext(), AlertarFurtoRoubo.class);
                                   intent.putExtras(params);

                                   context.startActivity(intent);




                                } else if (opcoes[i].equals("Editar")) {


                                    // passa dados  para a tela dados usuarios e Cadastro Bike

                                    Bundle params = new Bundle();
                                    params.putString("modelo",         bikeselecao.getModelo());
                                    params.putString("marca",          bikeselecao.getMarca());
                                    params.putString("numero_serie",   bikeselecao.getNumero_serie());
                                    params.putString("descricao",      bikeselecao.getDescricao());
                                    params.putString("cor",            bikeselecao.getCor());


                                    ///// DADOS QUE NÃO vão ficar envisiveis na tela editar

                                    params.putString("alertaEstado",    bikeselecao.getAlertaEstado());
                                    params.putString("alertaCidade",    bikeselecao.getAlertaCidade());
                                    params.putString("alertaRua",       bikeselecao.getAlertaRua());

                                    params.putString("alertaBairro",    bikeselecao.getAlertaBairro());
                                    params.putString("alertaHora",      bikeselecao.getAlertaHora());
                                    params.putString("alertaData",      bikeselecao.getAlertaDate());
                                    params.putString("alertaBoletim",   bikeselecao.getBoletim());
                                    params.putString("alertadescricao", bikeselecao.getAlertaDescricao());
                                    params.putString("status",          bikeselecao.getStatus());

                                    params.putString("Imagem1",         bikeselecao.getFotoBikeUrl1());
                                    params.putString("Imagem2",         bikeselecao.getFotoBikeUrl2());
                                    params.putString("Imagem3",         bikeselecao.getFotoBikeUrl3());
                                    params.putString("Imagem4",         bikeselecao.getFotoBikeUrl4());
                                    params.putString("Imagem5",         bikeselecao.getFotoBikeUrl5());

                                    params.putString("latitude",        bikeselecao.getLatitude() );
                                    params.putString("longitude",       bikeselecao.getLongitude());
                                    params.putString("proprietario",      bikeselecao.getProprietario());
                                    params.putString("proprietarioID",      bikeselecao.getProprietarioID());


                                    Intent intent = new Intent(BikeAdapter.super.getContext(), EditarBike.class);
                                  intent.putExtras(params);

                                  context.startActivity(intent);




                                } else if (opcoes[i].equals("Remover")) {


                                    AlertDialog.Builder alertaDialog = new AlertDialog.Builder(BikeAdapter.super.getContext());

                                    // configurando dialogo

                                    alertaDialog.setTitle("Excluir");


                                    alertaDialog.setMessage("Deseja Realmente Excluir a Bike ?");
                                    // alertaDialog.setCancelable(false);


                                    //conf botões
                                    alertaDialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {


                                            Bike bikeselecao = new Bike();
                                            bikeselecao = listabikes.get(position);

                                            // recupera usuario pelo email
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            String email = user.getEmail();


                                            // converte o email pra base 64
                                            String identificadorUsuario = Base64Custom.codificarBase64(email);

                                            // Remove a bike no nó todas as bikes
                                            databaseReference = Configuracao_Firebase.getFirebase().child("TodasBikes");
                                            databaseReference.child(bikeselecao.getNumero_serie()).removeValue();

                                            //EXCLUI A BIKE
                                            databaseReference = Configuracao_Firebase.getFirebase().child("Bikes").child(identificadorUsuario);
                                            databaseReference.child(bikeselecao.getNumero_serie()).removeValue();

                                            Toast.makeText(BikeAdapter.super.getContext(), " Sua Bicicleta foi Exluida", Toast.LENGTH_LONG).show();


                                        }
                                    });

                                    alertaDialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Toast.makeText(BikeAdapter.super.getContext(), " Operação Cancelada", Toast.LENGTH_LONG).show();

                                        }
                                    });

                                    alertaDialog.create();
                                    alertaDialog.show();



                                }

                            }
                        });
                        builder.show();

                    }
                });


                AreaUsuario.paraOndeVou="0";

        }







               FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

               if(user1 !=null){




                   String email = user1.getEmail();

                   // converte o email pra base 64
                   String identificadorUsuario= Base64Custom.codificarBase64(email);



                   DatabaseReference UsuarioReference = databaseReferenceUsuario.child("Usuarios").child(identificadorUsuario);




                   UsuarioReference.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {



                           final Usuarios dados = dataSnapshot.getValue(Usuarios.class);

                           if(dados.getDigitoValidador().equals("01")) {



                               ///aparece apenas a opção remover editar para o usuario que possui digito validador igual a 01


                               txtViewCaixaDescricao.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {


                                       final CharSequence[] opcoes = {"Mudar status da bike"};

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


                                               if (opcoes[i].equals("Mudar status da bike")) {


                                                   // passa dados  a tela alerta Furto/roubo

                                                   Bundle params = new Bundle();

                                                   params.putString("alertaEstado",   bikeselecao.getAlertaEstado());
                                                   params.putString("alertaCidade",   bikeselecao.getAlertaCidade());
                                                   params.putString("alertaBairro",   bikeselecao.getAlertaBairro());
                                                   params.putString("alertaRua",      bikeselecao.getAlertaRua());
                                                   params.putString("alertaHora",     bikeselecao.getAlertaHora());
                                                   params.putString("alertaData",     bikeselecao.getAlertaDate());
                                                   params.putString("alertaBoletim",  bikeselecao.getBoletim());
                                                   params.putString("alertadescricao",bikeselecao.getAlertaDescricao());


                                                   /// DADOS QUE NÃO vão ficar visiveis na tela Alerta furto e roubo

                                                   params.putString("modelo",          bikeselecao.getModelo());
                                                   params.putString("marca",           bikeselecao.getMarca());
                                                   params.putString("numero_serie",    bikeselecao.getNumero_serie());
                                                   params.putString("descricao",       bikeselecao.getDescricao());
                                                   params.putString("cor",             bikeselecao.getCor());
                                                   params.putString("status",          bikeselecao.getStatus());


                                                   params.putString("Imagem1",         bikeselecao.getFotoBikeUrl1());
                                                   params.putString("Imagem2",         bikeselecao.getFotoBikeUrl2());
                                                   params.putString("Imagem3",         bikeselecao.getFotoBikeUrl3());
                                                   params.putString("Imagem4",         bikeselecao.getFotoBikeUrl4());
                                                   params.putString("Imagem5",         bikeselecao.getFotoBikeUrl5());



                                                   params.putString("latitude",        bikeselecao.getLatitude() );
                                                   params.putString("longitude",       bikeselecao.getLongitude());
                                                   params.putString("digitoValidador",   dados.getDigitoValidador());
                                                   params.putString("proprietario",      bikeselecao.getProprietario());

                                                   params.putString("proprietarioID",      bikeselecao.getProprietarioID());

                                                   Intent intent = new Intent(BikeAdapter.super.getContext(), AlertarFurtoRoubo.class);
                                                   intent.putExtras(params);

                                                   context.startActivity(intent);


                                               }

                                           }
                                       });
                                       builder.show();

                                   }
                               });










                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });






               }







        }

        return view;


    }

}

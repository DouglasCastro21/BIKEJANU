package bike.douglas.com.bikejanu.Activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import bike.douglas.com.bikejanu.DAO.Configuracao_Firebase;
import bike.douglas.com.bikejanu.Model.Bike;
import bike.douglas.com.bikejanu.Fragments.AreaUsuario;
import bike.douglas.com.bikejanu.Helper.Base64Custom;
import bike.douglas.com.bikejanu.Model.Usuarios;
import bike.douglas.com.bikejanu.R;

public class EditarBike extends AppCompatActivity {


    public Bike bike;
    private DatabaseReference firebase;
    private Button btnEditar;
    public  TextView numero_serie;
    private EditText marca;
    private EditText modelo;
    private EditText cor;
    private EditText descricao;

    // se não repetir os dados da tela cadastro os dados são exluidos
    private TextView alertaNumero;

    private TextView alertaRua;
    private TextView alertaCidade;
    private TextView alertaEstado;
    private TextView alertaBairro;
    private TextView alertaDate;
    private TextView alertaHora;

    private  TextView proprietario;
    private  TextView proprietarioID;

    private TextView latitude;
    private TextView longitude;
    private TextView alertaBoletim;
    private TextView alertaDescricao;
    private ImageView spinnerImagem;
    private RadioButton status;
    private TextView txtStatus;




    private ImageView imagem1;
    private ImageView imagem2;
    private ImageView imagem3;
    private ImageView imagem4;
    private ImageView imagem5;




    private Uri uriImagem1;
    private Uri uriImagem2;
    private Uri uriImagem3;
    private Uri uriImagem4;
    private Uri uriImagem5;


    String dadosImagem1;
    String dadosImagem2;
    String dadosImagem3;
    String dadosImagem4;
    String dadosImagem5;
    String statusBike;



    private static  final int TIMER_RUNTINME = 300000;
    private boolean mbActive;
    private ProgressBar progressBar;
    private TextView carregando;

    private StorageReference  storageReference;
    private String marcasBike[] = new  String[] {"BIANCHI","COLNAGO","CALOY","DROPP ","GARELLI","GALO","HOUSTON","KSW","GÖRICKE","MONARK","MALAGA","ORBEA","ÓRBITA","SIRLA","SOUTH","SUTTON","OUTRA"};
    private Spinner spinner;



    private FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    String nomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_bike);


        storageReference = FirebaseStorage.getInstance().getReference("ImagensBikes");

        FirebaseApp.initializeApp(this);





        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();



        progressBar = (ProgressBar) findViewById(R.id.progressBarEditarID) ;
        carregando  =(TextView)findViewById(R.id.carregandoEditarID);

    btnEditar = (Button) findViewById(R.id.finalizarID);

    numero_serie     =  (TextView) findViewById(R.id.NumeroID);
               // marca          =  (EditText) findViewById(R.id.spinnerMarcaID);
    modelo           =  (EditText) findViewById(R.id.modeloID);
    cor              =  (EditText) findViewById(R.id.corID);
    descricao        =  (EditText) findViewById(R.id.descricaoID);


         // se não repetir os dados da tela cadastro os dados são exluidos
        // alertaEstado     =  (TextView) findViewById(R.id.);
       //   alertaCidade       =  (TextView) findViewById(R.id.teste2ID);
    alertaNumero     =  (TextView) findViewById(R.id.teste1ID);
    alertaRua        =  (TextView) findViewById(R.id.teste2ID);
    alertaBairro     =  (TextView) findViewById(R.id.teste3ID);
    alertaDate       =  (TextView) findViewById(R.id.teste4ID);
    alertaHora       =  (TextView) findViewById(R.id.teste5ID);
    alertaBoletim    =  (TextView) findViewById(R.id.teste6ID);
    alertaDescricao  =  (TextView) findViewById(R.id.teste7ID);
    alertaEstado     =  (TextView) findViewById(R.id.editarEstadoID);
    alertaCidade     =  (TextView) findViewById(R.id.editarCidadeID);
    txtStatus        =  (TextView) findViewById(R.id.statusID);
    latitude         =  (TextView) findViewById(R.id.txtEditarLatitudeID);
    longitude        =  (TextView) findViewById(R.id.txtEditarLongitudeID);

    proprietario     =  (TextView) findViewById(R.id.EditeProprietarioID);

    proprietarioID     =  (TextView) findViewById(R.id.chaveProprietarioID);

        imagem1    =  (ImageView) findViewById(R.id.editarImagem1ID);
        imagem2    =  (ImageView) findViewById(R.id.editarImagem2ID);
        imagem3    =  (ImageView) findViewById(R.id.editarImagem3ID);
        imagem4    =  (ImageView) findViewById(R.id.editarImagem4ID);
        imagem5    =  (ImageView) findViewById(R.id.editarImagem5ID);





        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {


            String email = user.getEmail();

            // converte o email pra base 64
            final String identificadorUsuario1 = Base64Custom.codificarBase64(email);


            DatabaseReference reference = databaseReference.child("Usuarios").child(identificadorUsuario1);


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if (dataSnapshot.exists()) {

                        Usuarios dados = dataSnapshot.getValue(Usuarios.class);


                        nomeUsuario = dados.getNome().toString();


                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }





        //carrega os spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(EditarBike.this, android.R.layout.simple_spinner_dropdown_item, marcasBike);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImagem = (ImageView) findViewById(R.id.spinerButtonID);



        status = (RadioButton)findViewById(R.id.alertaNadaConstaID);
        spinner = (Spinner)   findViewById(R.id.spinnerMarcaID);
        spinner.setAdapter(arrayAdapter);


            openGaleria();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnEditar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


                inicializarElementos();
                Upload();

               // recuperarDadosUsuarioConectadoECadastra();
          }
      });




        // rebece o dados do Bike Adapter por parametro
        Intent intent = getIntent();

        if(intent !=null){

            Bundle params = intent.getExtras();

            if (params !=null){

                //dados do modelo
                String modelo = params.getString("modelo");
                TextView modeloText = (TextView) findViewById(R.id.modeloID);
                modeloText.setText(modelo);

                //dados da marca
                String marca = params.getString("marca");
                Spinner marcaText = (Spinner) findViewById(R.id.spinnerMarcaID);


                    marcaText.getItemAtPosition(0);


                // dados do numero serie
                String numero_serie = params.getString("numero_serie");
                TextView numero_serieText = (TextView) findViewById(R.id.NumeroID);
                numero_serieText.setText(numero_serie);


                // dados da cor
                String cor = params.getString("cor");
                TextView corText = (TextView) findViewById(R.id.corID);
                corText.setText(cor);

                // dados do numero serie
                String descricao = params.getString("descricao");
                TextView descricaoText = (TextView) findViewById(R.id.descricaoID);
                descricaoText.setText(descricao);



                /// DADOS QUE NÃO vão ficar envisiveis na tela editar


                //dados do alertaNumero
                String alertaNumero = params.getString("alertaNumero");
                TextView alertaNumeroText = (TextView) findViewById(R.id.teste1ID);
                alertaNumeroText.setText(alertaNumero);




                // dados do alertaEstado
                String alertaEstado = params.getString("alertaEstado");
                TextView alertaEstadoText = (TextView) findViewById(R.id.editarEstadoID);
                alertaEstadoText.setText(alertaEstado);


                // dados do alertaCidade
                String alertaCidade = params.getString("alertaCidade");
                TextView alertaCidadeText = (TextView) findViewById(R.id.editarCidadeID);
                alertaCidadeText.setText(alertaCidade);

                //dados do alertaRua
                String alertaRua = params.getString("alertaRua");
                TextView alertaRuaText = (TextView) findViewById(R.id.teste2ID);
                alertaRuaText.setText(alertaRua);



                // dados do alertaBairro
                String alertaBairro = params.getString("alertaBairro");
                TextView alertaBairroText = (TextView)findViewById(R.id.teste3ID) ;
                alertaBairroText.setText(alertaBairro);



                // dados do alertaDate
                String alertaDate = params.getString("alertaData");
                TextView alertaDateText = (TextView) findViewById(R.id.teste4ID);
                alertaDateText.setText(alertaDate);


                // dados do alertaHora
                String alertaHora = params.getString("alertaHora");
                TextView alertaHoraText = (TextView) findViewById(R.id.teste5ID);
                alertaHoraText.setText(alertaHora);

                // dados do alertaHora
                String alertaBoletim = params.getString("alertaBoletim");
                TextView alertaBoletimText = (TextView) findViewById(R.id.teste6ID);
                alertaBoletimText.setText(alertaBoletim);


                // dados do alertaBairro
                String alertaDescricao = params.getString("alertadescricao");
                TextView alertaDescricaoText = (TextView) findViewById(R.id.teste7ID);
                alertaDescricaoText.setText(alertaDescricao);


                String statuss = params.getString("status");
                TextView statussText = (TextView) findViewById(R.id.statusID);
                statussText.setText(statuss);


                statusBike = statuss;


                String latitude = params.getString("latitude");
                TextView latitudeText = (TextView) findViewById(R.id.txtEditarLatitudeID);
                latitudeText.setText(latitude);


                String longitude = params.getString("longitude");
                TextView longitudeText = (TextView) findViewById(R.id.txtEditarLongitudeID);
                longitudeText.setText(longitude);



                String proprietario = params.getString("proprietario");
                TextView proprietarioText = (TextView) findViewById(R.id.EditeProprietarioID);
                proprietarioText.setText(proprietario);



                String proprietarioID = params.getString("proprietarioID");
                TextView proprietarioIDText = (TextView) findViewById(R.id.chaveProprietarioID);
                proprietarioIDText.setText(proprietarioID);






                // dadosda IMAGEM 1

                 dadosImagem1 = params.getString("Imagem1");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
                // dadosImagem1Text.setText(dadosImagem1);




                if(dadosImagem1.equals("") || dadosImagem1 == null ){


                    Glide.with(EditarBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagem1);




                }else{


                    Glide.with(EditarBike.this).load(dadosImagem1).into(imagem1);


                }


                // dadosda IMAGEM 2

              dadosImagem2 = params.getString("Imagem2");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
                // dadosImagem1Text.setText(dadosImagem1);



                if(dadosImagem2.equals("") ||dadosImagem2 == null ){


                    Glide.with(EditarBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagem2);




                }else{


                    Glide.with(EditarBike.this).load(dadosImagem2).into(imagem2);


                }


                // dadosda IMAGEM 3

                 dadosImagem3 = params.getString("Imagem3");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
                // dadosImagem1Text.setText(dadosImagem1);




                if(dadosImagem3 .equals("")  ||dadosImagem3 == null ){


                    Glide.with(EditarBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagem3);




                }else{


                    Glide.with(EditarBike.this).load(dadosImagem3).into(imagem3);


                }

                // dadosda IMAGEM 4

                  dadosImagem4 = params.getString("Imagem4");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
                // dadosImagem1Text.setText(dadosImagem1);


                if(dadosImagem4.equals("")  ||dadosImagem4 == null ){


                    Glide.with(EditarBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagem4);



                }else{


                    Glide.with(EditarBike.this).load(dadosImagem4).into(imagem4);


                }





                // dadosda IMAGEM 5

                  dadosImagem5 = params.getString("Imagem5");
                //TextView dadosImagem1Text = (TextView) findViewById(R.id.dadosBoletimID);
                // dadosImagem1Text.setText(dadosImagem1);
               // Picasso.get (). load ( fotosBikes.get(position).getFotoBikeUrl1()).into(imagem5);


                if(dadosImagem5.equals("")  ||dadosImagem5 == null ){


                    Glide.with(EditarBike.this).load("https://firebasestorage.googleapis.com/v0/b/bikejanu-62aa9.appspot.com/o/camera.png?alt=media&token=9c8f88d1-6264-4c90-baf4-cacc6b06a181").into(imagem5);




                }else{


                    Glide.with(EditarBike.this).load(dadosImagem5).into(imagem5);


                }





            }
        }

    }

    private void openGaleria() {

        imagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);


            }
        });





        imagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,2);


            }
        });


////



        imagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,3);


            }
        });


        ///




        imagem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,4);


            }
        });



        imagem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent,5);



            }
        });

    }








    private void inicializarElementos(){


        bike = new Bike();
        bike.setNumero_serie(numero_serie.getText().toString());
        bike.setMarca(spinner.getSelectedItem().toString());

        bike.setModelo(modelo.getText().toString());
        bike.setCor(cor.getText().toString());
        bike.setDescricao(descricao.getText().toString());
        bike.setAlertaEstado(alertaEstado.getText().toString());
        bike.setAlertaCidade(alertaCidade.getText().toString());
        bike.setAlertaRua(alertaRua.getText().toString());
        bike.setAlertaBairro(alertaBairro.getText().toString());
        bike.setAlertaDate(alertaDate.getText().toString());
        bike.setAlertaHora(alertaHora.getText().toString());
        bike.setBoletim(alertaBoletim.getText().toString());
        bike.setAlertaDescricao(alertaDescricao.getText().toString());

        bike.setLatitude(latitude.getText().toString());
        bike.setLongitude(longitude.getText().toString());

        bike.setStatus(statusBike);


        bike.setProprietario(nomeUsuario.toString());






        bike.setProprietarioID(proprietarioID.getText().toString());

    }

    // volta pra tela usuario
    private void abrirAreaUsuario(){



        Intent intent = new Intent(EditarBike.this ,AreaUsuario.class);
        startActivity(intent);
       // finish();


    }





    public void Upload() {


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {


            String email = user.getEmail();

            // converte o email pra base 64
            final String identificadorUsuario = Base64Custom.codificarBase64(email);

            if (uriImagem1 != null) {

                progressBar();

                progressBar.setVisibility(View.VISIBLE);
                carregando.setVisibility(View.VISIBLE);

                final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem1");
                UploadTask uploadTask = ref.putFile(uriImagem1);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {


                            Uri downloadUri = task.getResult();

                            bike.setFotoBikeUrl1(downloadUri.toString());

                            // EDITA a bike no nó todas as bikes
                            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                            firebase.child(bike.getNumero_serie()).setValue(bike);

                            //edita no nó bikes

                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                            progressBar.setVisibility(View.GONE);
                            carregando.setVisibility(View.GONE);

                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();
                        abrirAreaUsuario();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(EditarBike.this, "Falha na conexão!", Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.GONE);
                        carregando.setVisibility(View.GONE);

                    }
                });


            }



            if (uriImagem2 != null) {


                progressBar();

                progressBar.setVisibility(View.VISIBLE);
                carregando.setVisibility(View.VISIBLE);

                final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem2");
                UploadTask uploadTask = ref.putFile(uriImagem2);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {


                            Uri downloadUri = task.getResult();


                            bike.setFotoBikeUrl2(downloadUri.toString());


                            // EDITA a bike no nó todas as bikes
                            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                            firebase.child(bike.getNumero_serie()).setValue(bike);

                            //edita no nó bikes

                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                            progressBar.setVisibility(View.GONE);
                            carregando.setVisibility(View.GONE);


                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();

                        abrirAreaUsuario();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressBar.setVisibility(View.GONE);
                        carregando.setVisibility(View.GONE);

                    }
                });


            }


            if (uriImagem3 != null) {

                progressBar();


                progressBar.setVisibility(View.VISIBLE);
                carregando.setVisibility(View.VISIBLE);

                final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem3");
                UploadTask uploadTask = ref.putFile(uriImagem3);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {


                            Uri downloadUri = task.getResult();


                            bike.setFotoBikeUrl3(downloadUri.toString());


                            // EDITA a bike no nó todas as bikes
                            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                            firebase.child(bike.getNumero_serie()).setValue(bike);

                            //edita no nó bikes

                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                            progressBar.setVisibility(View.GONE);
                            carregando.setVisibility(View.GONE);


                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        abrirAreaUsuario();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressBar.setVisibility(View.GONE);
                        carregando.setVisibility(View.GONE);

                    }
                });

            }


            if (uriImagem4 != null) {

                progressBar();


                progressBar.setVisibility(View.VISIBLE);
                carregando.setVisibility(View.VISIBLE);

                final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem4");
                UploadTask uploadTask = ref.putFile(uriImagem4);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {


                            Uri downloadUri = task.getResult();


                            bike.setFotoBikeUrl4(downloadUri.toString());

                            // EDITA a bike no nó todas as bikes
                            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                            firebase.child(bike.getNumero_serie()).setValue(bike);

                            //edita no nó bikes

                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                            progressBar.setVisibility(View.GONE);
                            carregando.setVisibility(View.GONE);




                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        abrirAreaUsuario();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressBar.setVisibility(View.GONE);
                        carregando.setVisibility(View.GONE);

                    }
                });

            }

            if (uriImagem5 != null) {

                progressBar();


                progressBar.setVisibility(View.VISIBLE);
                carregando.setVisibility(View.VISIBLE);

                final StorageReference ref = storageReference.child(new StringBuilder(identificadorUsuario).toString()).child("imagem5");
                UploadTask uploadTask = ref.putFile(uriImagem5);

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        return ref.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {


                            Uri downloadUri = task.getResult();

                            bike.setFotoBikeUrl5(downloadUri.toString());

                            // EDITA a bike no nó todas as bikes
                            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
                            firebase.child(bike.getNumero_serie()).setValue(bike);

                            //edita no nó bikes

                            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
                            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);


                            progressBar.setVisibility(View.GONE);
                            carregando.setVisibility(View.GONE);


                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        abrirAreaUsuario();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressBar.setVisibility(View.GONE);
                        carregando.setVisibility(View.GONE);

                    }
                });


            }



        if(uriImagem1 == null){



            bike.setFotoBikeUrl1(dadosImagem1);

          // EDITA a bike no nó todas as bikes
             firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
             firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

               firebase = Configuracao_Firebase.getFirebase().child("Bikes");
               firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();



         }


         if(uriImagem2 ==null){


            bike.setFotoBikeUrl2(dadosImagem2);

            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();



        }


        if(uriImagem3 ==null){

            bike.setFotoBikeUrl3(dadosImagem3);

            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();



        }



        if(uriImagem4 ==null){




            bike.setFotoBikeUrl4(dadosImagem4);

            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();



        }



        if(uriImagem5 == null){


            bike.setFotoBikeUrl5(dadosImagem5);

            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();


        }



        if(uriImagem1 ==null  && uriImagem2 ==null  &&  uriImagem3 ==null &&  uriImagem4 ==null  && uriImagem5 ==null){




            bike.setFotoBikeUrl1(dadosImagem1);
            bike.setFotoBikeUrl2(dadosImagem2);
            bike.setFotoBikeUrl3(dadosImagem3);
            bike.setFotoBikeUrl4(dadosImagem4);
            bike.setFotoBikeUrl5(dadosImagem5);

            // EDITA a bike no nó todas as bikes
            firebase = Configuracao_Firebase.getFirebase().child("TodasBikes");
            firebase.child(bike.getNumero_serie()).setValue(bike);

            //edita no nó bikes

            firebase = Configuracao_Firebase.getFirebase().child("Bikes");
            firebase.child(identificadorUsuario).child(bike.getNumero_serie()).setValue(bike);



            abrirAreaUsuario();
            Toast.makeText(EditarBike.this, "Sua bike foi Alterada!", Toast.LENGTH_LONG).show();



        }








        }


    }








    public void progressBar(){



        final  Thread timerTheread = new Thread(){


            @Override
            public void  run(){

                mbActive = true;
                try {

                    int waited = 0;

                    while (mbActive && (waited < TIMER_RUNTINME)) {

                        sleep(200);

                        if (mbActive) {

                            waited += 200;
                            updateProgress(waited);

                        }


                    }

                }catch (InterruptedException e){


                }finally {


                    // Toast.makeText(CadastroBike.this, "Tudo pronto", Toast.LENGTH_SHORT).show();



                }

            }


        };
        timerTheread.start();


    }




    public void updateProgress(final int timePassed){

        if (null !=progressBar){

            final int progress = progressBar.getMax() * timePassed /TIMER_RUNTINME;
            progressBar.setProgress(progress);

        }

    }
















    public void ShowElemento(View view){

        String nome = (String)spinner.getSelectedItem();
        long id = spinner.getSelectedItemId();
        int posicao = spinner.getSelectedItemPosition();

    }


    private String getExtension(Uri uri){

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem1=data.getData();
            imagem1.setImageURI(uriImagem1);



        }


        if(requestCode == 2 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem2=data.getData();
            imagem2.setImageURI(uriImagem2);



        }


        if(requestCode == 3 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem3=data.getData();
            imagem3.setImageURI(uriImagem3);



        }


        if(requestCode == 4 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem4=data.getData();
            imagem4.setImageURI(uriImagem4);



        }



        if(requestCode == 5 && resultCode == RESULT_OK && data!=null && data.getData()!=null){

            uriImagem5=data.getData();
            imagem5.setImageURI(uriImagem5);



        }


    }



}

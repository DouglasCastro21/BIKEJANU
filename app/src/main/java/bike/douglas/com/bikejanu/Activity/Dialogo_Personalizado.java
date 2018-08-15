package bike.douglas.com.bikejanu.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import bike.douglas.com.bikejanu.R;

public class Dialogo_Personalizado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogo__personalizado);


        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



    }

}

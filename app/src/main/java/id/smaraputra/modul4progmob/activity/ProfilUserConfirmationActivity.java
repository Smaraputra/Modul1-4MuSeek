package id.smaraputra.modul4progmob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.smaraputra.modul4progmob.DBHandler;
import id.smaraputra.modul4progmob.R;

public class ProfilUserConfirmationActivity extends AppCompatActivity {

    TextView kon_nama, kon_nomor, kon_alamat, kon_gender, kon_skill, kon_time;
    Button finishReg;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user_confirmation);

        dbHandler = new DBHandler(ProfilUserConfirmationActivity.this);

        Intent intent = getIntent();

        String namaUser = intent.getStringExtra("nama");
        String nomortelpUser = intent.getStringExtra("nomor");
        String alamatUser = intent.getStringExtra("alamat");
        String genderUser = intent.getStringExtra("gender");
        String usernameUser = intent.getStringExtra("username");
        String passwordUser = intent.getStringExtra("password");
        String instrumentUser = intent.getStringExtra("instrument");
        int waktuUser = intent.getIntExtra("waktu", 1);

        kon_nama = (TextView) findViewById(R.id.profilnama);
        kon_nomor = (TextView) findViewById(R.id.profilnomor);
        kon_alamat = (TextView) findViewById(R.id.profilalamat);
        kon_gender = (TextView) findViewById(R.id.profilkelamin);
        kon_skill = (TextView) findViewById(R.id.profilskill);
        kon_time = (TextView) findViewById(R.id.profilwaktu);

        finishReg = (Button) findViewById(R.id.buttonFinish);

        kon_nama.setText(namaUser);
        kon_nomor.setText(nomortelpUser);
        kon_alamat.setText(alamatUser);
        kon_gender.setText(genderUser);
        kon_skill.setText(instrumentUser);
        kon_time.setText(String.valueOf(waktuUser));

        finishReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.addNewUser(namaUser, nomortelpUser, alamatUser, usernameUser,
                        passwordUser, genderUser,instrumentUser,waktuUser);
                Intent intent = new Intent(ProfilUserConfirmationActivity.this, StartMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast confirmAsk = Toast.makeText(context, "You almost done!", duration);
        confirmAsk.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast confirmBackAsk = Toast.makeText(context, "You have not completed the registration!", duration);
        confirmBackAsk.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title dialog
        alertDialogBuilder.setTitle("You paused the registration.");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage("Choose 'Continue' to proceed with the registration. Choose 'Cancel to go back to login page.'")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Continue",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(ProfilUserConfirmationActivity.this, StartMenuActivity.class);
                        startActivity(intent);
                    }
                });

        // Membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Menampilkan alert dialog
        alertDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast successReg = Toast.makeText(context, "Registration Completed.", duration);
        successReg.show();
    }

}
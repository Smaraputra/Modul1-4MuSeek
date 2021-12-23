package id.smaraputra.modul4progmob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.smaraputra.modul4progmob.R;

public class StartMenuActivity extends AppCompatActivity {

    TextView tblregister;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tblregister = (TextView) findViewById(R.id.tombolregister);
        loginBtn = (Button) findViewById(R.id.tombollogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartMenuActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
        tblregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartMenuActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title dialog
        alertDialogBuilder.setTitle("You want to close the app?");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage("Choose 'Exit' to close the app.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Exit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // Jika tombol diklik, maka akan menutup activity ini
                        StartMenuActivity.this.finishAffinity();
                    }
                })
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Jika tombol ini diklik, akan menutup dialog
                        dialog.cancel();
                    }
                });

        // Membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Menampilkan alert dialog
        alertDialog.show();
    }
}
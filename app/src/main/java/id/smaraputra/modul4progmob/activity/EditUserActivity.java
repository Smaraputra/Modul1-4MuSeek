package id.smaraputra.modul4progmob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.smaraputra.modul4progmob.DBHandler;
import id.smaraputra.modul4progmob.R;
import id.smaraputra.modul4progmob.model.UserModel;

public class EditUserActivity extends AppCompatActivity {

    TextView nameedit, phoneedit, addressedit, usernameedit, genderedit, instrumentedit, timeedit;
    EditText noteedit;
    RadioGroup valid_grup;
    RadioButton yes, no;
    Button canceled, saveed;

    private DBHandler dbHandler;
    private ArrayList<UserModel> userData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameedit = findViewById(R.id.nameEdit);
        phoneedit = findViewById(R.id.phoneEdit);
        addressedit = findViewById(R.id.addressEdit);
        usernameedit = findViewById(R.id.usernameEdit);
        genderedit = findViewById(R.id.genderEdit);
        instrumentedit = findViewById(R.id.instrumentEdit);
        timeedit = findViewById(R.id.timeEdit);
        noteedit = findViewById(R.id.noteEdit);
        valid_grup = findViewById(R.id.ver_ed_group);
        yes = findViewById(R.id.verifeded);
        no = findViewById(R.id.notverifeded);
        canceled = findViewById(R.id.tombolbataled);
        saveed = findViewById(R.id.tombolkirimed);

        Intent intent = getIntent();
        int iduser = intent.getIntExtra("idu", 1);

        dbHandler = new DBHandler(getApplicationContext());
        userData = dbHandler.getUserEdit(iduser);

        nameedit.setText(userData.get(0).getName());
        phoneedit.setText(userData.get(0).getPhone());
        addressedit.setText(userData.get(0).getAddress());
        usernameedit.setText(userData.get(0).getUsername());
        genderedit.setText(userData.get(0).getGender());
        instrumentedit.setText(userData.get(0).getSkill());
        timeedit.setText(String.valueOf(userData.get(0).getWaktu()));
        noteedit.setText(userData.get(0).getNote());

        if(userData.get(0).getValid()==0){
            no.setChecked(true);
        }else if(userData.get(0).getValid()==1){
            yes.setChecked(true);
        }

        canceled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        saveed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String notein = noteedit.getText().toString();
                String validinStr = ((RadioButton)findViewById(valid_grup.getCheckedRadioButtonId())).getText().toString();
                int validin;
                if(validinStr.equals("Yes")){
                    validin = 1;
                }else{
                    validin = 0;
                }

                dbHandler.updateUser(iduser, notein, validin);
                Intent intent = new Intent(EditUserActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
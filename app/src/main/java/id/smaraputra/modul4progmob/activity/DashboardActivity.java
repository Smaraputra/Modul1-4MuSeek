package id.smaraputra.modul4progmob.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import id.smaraputra.modul4progmob.R;
import id.smaraputra.modul4progmob.fragment.FragmentUserManagement;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DashboardManagerActivity";

    DrawerLayout drawerLayout;
    TextView viewname;
    TextView viewrole;
    String nama = "Smaraputra", role="Admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        viewname = headerView.findViewById(R.id.viewnama);
        viewrole = headerView.findViewById(R.id.viewrole);

        viewname.setText(nama);
        viewrole.setText(role);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(DashboardActivity.this, drawerLayout,
                toolbar, R.string.buka, R.string.tutup);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.tempatmunculfragment, new FragmentUserManagement()).commit();
            navigationView.setCheckedItem(R.id.listUser);
        }

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if((drawerLayout.isDrawerOpen(GravityCompat.START)!=true)){
            showDialogKeluar();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.listUser:
                getSupportFragmentManager().beginTransaction().replace(R.id.tempatmunculfragment, new FragmentUserManagement(), "CatatFragment").commit();
                break;
            case R.id.logout:
                showDialogLogout();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showDialogKeluar(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title dialog
        alertDialogBuilder.setTitle("You want to exit the app?");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage("Choose 'Exit' to close MuSeek.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Exit",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // Jika tombol diklik, maka akan menutup activity ini
                        DashboardActivity.this.finishAffinity();
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

    private void showDialogLogout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // Set title dialog
        alertDialogBuilder.setTitle("You want to logout?");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage("Choose 'Logout' to logout your account.")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Logout",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // Jika tombol diklik, maka akan menutup activity ini
                        Intent intent = new Intent(DashboardActivity.this, StartMenuActivity.class);
                        startActivity(intent);
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
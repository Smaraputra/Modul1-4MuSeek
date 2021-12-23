package id.smaraputra.modul4progmob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import id.smaraputra.modul4progmob.model.UserModel;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "museekdb";
    private static final int DB_VERSION = 1;

    private static final String NAMA_TABEL = "users";
    private static final String KOLOM_ID = "id_user";
    private static final String KOLOM_USER = "name_user";
    private static final String KOLOM_NOMOR = "number_user";
    private static final String KOLOM_ALAMAT = "address_user";
    private static final String KOLOM_USERNAME = "username_user";
    private static final String KOLOM_PASSWORD = "password_user";
    private static final String KOLOM_KELAMIN = "gender_user";
    private static final String KOLOM_SKILL = "skill_user";
    private static final String KOLOM_WAKTU = "time_user";
    private static final String KOLOM_NOTE = "note_user";
    private static final String KOLOM_VALID = "valid_user";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + NAMA_TABEL + " ("
                + KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KOLOM_USER + " TEXT, "
                + KOLOM_NOMOR + " TEXT,"
                + KOLOM_ALAMAT + " TEXT,"
                + KOLOM_USERNAME + " TEXT,"
                + KOLOM_PASSWORD + " TEXT,"
                + KOLOM_KELAMIN + " TEXT,"
                + KOLOM_SKILL + " TEXT,"
                + KOLOM_WAKTU + " INTEGER,"
                + KOLOM_NOTE + " TEXT,"
                + KOLOM_VALID + " INTEGER)";

        db.execSQL(query);
    }

    public ArrayList<UserModel> listUserNotVerified(){
        String sql = " select * from " + NAMA_TABEL + " where valid_user = 0; ";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserModel> storeListUserNotVerified = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String namein = cursor.getString(1);
                String phonein = cursor.getString(2);
                String addressin = cursor.getString(3);
                String usernamein = cursor.getString(4);
                String passwordin = cursor.getString(5);
                String genderin = cursor.getString(6);
                String skillin = cursor.getString(7);
                int waktuin = cursor.getInt(8);
                String notein = cursor.getString(9);
                int validin = cursor.getInt(10);
                storeListUserNotVerified.add(new UserModel(
                        id, namein, phonein,
                        addressin, usernamein, passwordin,
                         genderin, skillin, waktuin, notein,validin));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeListUserNotVerified;
    }

    public ArrayList<UserModel> listUserVerified(){
        String sql = " select * from " + NAMA_TABEL + " where valid_user = 1; ";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserModel> storeListUserVerified = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String namein = cursor.getString(1);
                String phonein = cursor.getString(2);
                String addressin = cursor.getString(3);
                String usernamein = cursor.getString(4);
                String passwordin = cursor.getString(5);
                String genderin = cursor.getString(6);
                String skillin = cursor.getString(7);
                int waktuin = cursor.getInt(8);
                String notein = cursor.getString(9);
                int validin = cursor.getInt(10);
                storeListUserVerified.add(new UserModel(
                        id, namein, phonein,
                        addressin, usernamein, passwordin,
                        genderin, skillin, waktuin, notein,validin));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeListUserVerified;
    }

    public ArrayList<UserModel> getUserEdit (int idin) {
        String sql = " select * from " + NAMA_TABEL + " where id_user = " + idin + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UserModel> storeUser = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String namein = cursor.getString(1);
                String phonein = cursor.getString(2);
                String addressin = cursor.getString(3);
                String usernamein = cursor.getString(4);
                String passwordin = cursor.getString(5);
                String genderin = cursor.getString(6);
                String skillin = cursor.getString(7);
                int waktuin = cursor.getInt(8);
                String notein = cursor.getString(9);
                int validin = cursor.getInt(10);
                storeUser.add(new UserModel(
                        id, namein, phonein,
                        addressin, usernamein, passwordin,
                        genderin, skillin, waktuin, notein,validin));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeUser;
    }

    public void addNewUser(String nama, String nomor, String alamat, String username,
                           String password, String kelamin, String skill, int waktu) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KOLOM_USER, nama);
        values.put(KOLOM_NOMOR, nomor);
        values.put(KOLOM_ALAMAT, alamat);
        values.put(KOLOM_KELAMIN, kelamin);
        values.put(KOLOM_USERNAME, username);
        values.put(KOLOM_PASSWORD, password);
        values.put(KOLOM_SKILL, skill);
        values.put(KOLOM_WAKTU, waktu);
        values.put(KOLOM_NOTE, "");
        values.put(KOLOM_VALID, 0);

        db.insert(NAMA_TABEL, null, values);
    }

    public void deleteUser(int idin) {
        String idstr = String.valueOf(idin);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NAMA_TABEL, "id_user=?", new String[]{idstr});
        db.close();
    }

    public void updateUser(int idin, String note, int valid) {
        String idstr = String.valueOf(idin);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KOLOM_NOTE, note);
        values.put(KOLOM_VALID, valid);

        db.update(NAMA_TABEL, values, "id_user=?", new String[]{idstr});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + NAMA_TABEL);
            onCreate(db);
        }
    }
}
package i005213com.motoclima.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import i005213com.motoclima.Helper.HelperUser;
import i005213com.motoclima.Models.User;

/**
 * Created by JuanDiego on 8/06/17.
 */

public class DataUser {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;


    public DataUser(Context context){
        dbHelper = new HelperUser(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();

    }

    public void close(){
        dbHelper.close();
    }

    public User create(User user){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_U_NAME, user.getName());
        values.put(HelperUser.COLUMN_U_EMAIL, user.getEmail());
        values.put(HelperUser.COLUMN_U_USERNAME, user.getUsername());
        values.put(HelperUser.COLUMN_U_PASSWORD, user.getPassword());
        values.put(HelperUser.COLUMN_U_STATUS, user.getStatus());

        long insertId = database.insert(HelperUser.TABLE_USERS, null, values);

        user.setId(insertId);
        return user;
    }

    public List<User> cursorToList(Cursor cursor){
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_U_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_EMAIL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_PASSWORD)));
                user.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_STATUS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<User> findAll(){
        Cursor cursor = database.rawQuery("select * from users", null);
        List<User> users = cursorToList(cursor);
        return users;
    }

    public String[] findUser (String username, String password){

        String[] findUser = new String[2];
        Cursor cursor = database.rawQuery("select username,password from users where username = '"+username+"' and " +
                "password = '"+password+"'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                findUser[0] = cursor.getString(0);
                findUser[1] = cursor.getString(1);
            } while (cursor.moveToNext());
        }else{
            findUser[0] =" ";
            findUser[1] = " ";
        }
        return findUser;

    }

    public void loginStatusOn (String username, String password){
        database.execSQL("update users set status = 'On' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public void loginStatusOff (String username, String password){
        database.execSQL("update users set status = 'Off' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public User statusLogin (){

        User userLogin = new User();

        Cursor cursor = database.rawQuery("select * from users where status = 'On'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                userLogin.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_U_ID)));
                userLogin.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_NAME)));
                userLogin.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_EMAIL)));
                userLogin.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_USERNAME)));
                userLogin.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_PASSWORD)));
                userLogin.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_U_STATUS)));
            } while (cursor.moveToNext());
        } else {
            userLogin = null;
        }
        return userLogin;
    }

}
package i005213com.motoclima.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import i005213com.motoclima.Data.DataUser;
import i005213com.motoclima.LoginActivity;
import i005213com.motoclima.Models.User;
import i005213com.motoclima.R;


/**
 * Created by JuanDiego on 9/05/17.
 */

public class CreateAccountActivity  extends AppCompatActivity{

    Button createAccount;
    EditText name,username,email,password, passwordConfirm;
    DataUser dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        showTolbar(getResources().getString(R.string.txt_title_toolbar),true);

        createAccount = (Button) findViewById(R.id.id_btn_joinUs);
        name = (EditText) findViewById(R.id.id_tie_name);
        username = (EditText) findViewById(R.id.id_tie_user_create);
        email = (EditText) findViewById(R.id.id_tie_email);
        password = (EditText) findViewById(R.id.id_tier_password);
        passwordConfirm  = (EditText) findViewById(R.id.id_tie_confirmPassword);


        dataUser = new DataUser(this);
        dataUser.open();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(username.getText().toString().equals("")||password.getText().toString().equals("")
                        ||passwordConfirm.getText().toString().equals("")
                        ||name.getText().toString().equals("")||email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Espacios Vacios", Toast.LENGTH_SHORT).show();
                }
                // check if both password matches
                else if(!password.getText().toString().equals(passwordConfirm.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Passwords Incorrectos", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    createData();
                    Toast.makeText(getApplicationContext(),"Se ha creado Correctamente la Cuenta", Toast.LENGTH_SHORT).show();
                    goLogginActivity();
                }

            }

        });

    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tb_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void createData(){
        User user = new User();
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setStatus("Off");

        dataUser.create(user);
    }

    public void goLogginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}




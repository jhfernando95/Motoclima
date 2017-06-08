package i005213com.motoclima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import i005213com.motoclima.Data.DataUser;
import i005213com.motoclima.Models.User;
import i005213com.motoclima.Views.ContainerActivity;
import i005213com.motoclima.Views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {

    DataUser dataUser;
    Button login;
    EditText usernameLogin,passwordLogin;
    public static User userLogin;
    String[] findUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.id_btn_login);
        usernameLogin = (EditText) findViewById(R.id.id_login_username);
        passwordLogin = (EditText) findViewById(R.id.id_login_password);
        dataUser = new DataUser(this);
        dataUser.open();

        userLogin = dataUser.statusLogin();
        if(userLogin == null ){

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(usernameLogin.getText().toString().equals("")||passwordLogin.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        findUser = new String[2];
                        findUser = dataUser.findUser(usernameLogin.getText().toString(),passwordLogin.getText().toString());
                        if (findUser[0].equals((usernameLogin.getText().toString())) && findUser[1].equals((passwordLogin.getText().toString())) ){
                            dataUser.loginStatusOn(usernameLogin.getText().toString(),passwordLogin.getText().toString());
                            Toast.makeText(getApplicationContext(),"Bienvenido", Toast.LENGTH_SHORT).show();
                            goCreateContainer();
                        }else
                        {Toast.makeText(getApplicationContext(), "Usuario y contraseña erroneos" , Toast.LENGTH_SHORT).show();}

                    }
                }
            });

        }else {
            goCreateContainer();
        }



    }

    public void onShowAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goCreateContainer(){
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

}

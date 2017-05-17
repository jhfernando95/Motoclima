package i005213com.motoclima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import i005213com.motoclima.Views.ContainerActivity;
import i005213com.motoclima.Views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onShowAccount (View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);

    }

    public void goCreateContainer(View view) {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

}

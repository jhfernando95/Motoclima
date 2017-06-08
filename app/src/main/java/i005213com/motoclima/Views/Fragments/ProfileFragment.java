package i005213com.motoclima.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import i005213com.motoclima.Data.DataUser;
import i005213com.motoclima.LoginActivity;
import i005213com.motoclima.Models.User;
import i005213com.motoclima.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    View view;
    DataUser dataUser;
    TextView name,email;
    User user;
    Button signOff;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        showTolbar("Perfil",true);
        setHasOptionsMenu(true);

        name = (TextView) view.findViewById(R.id.id_txv_time_card);
        signOff = (Button) view.findViewById(R.id.id_btn_signOff);

        dataUser = new DataUser(getActivity());
        dataUser.open();
        user = dataUser.statusLogin();

        name.setText(user.getName());

        signOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), "Cerrando Sesion", Toast.LENGTH_SHORT).show();
                dataUser.loginStatusOff(user.getUsername(),user.getPassword());
                goLogginActivity();
            }
        });

        return view ;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.id_tb_toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void goLogginActivity(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}

package i005213com.motoclima.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import i005213com.motoclima.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnMapReadyCallback {
    GoogleMaps m_map;
    boolean mapReady = false;

    static final CameraPosition PASTO = CameraPosition.builder()
            .target(new LatLng(1.2086284, -77.2779443))
            .zoom(15)
            .bearing(90)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);


        Button btnNormal = (Button) findViewById(R.id.id_btn_seattle);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motoclima(SEATTLE);
                // m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        m_map = googleMap;
        //LatLng pasto = new LatLng(1.2086284 , -77.2779443);
        //CameraPosition target = CameraPosition.builder().target(pasto).zoom(15).build();
        //m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        motoclima(PASTO);

    }
    public  void motoclima(CameraPosition perro) {

        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(perro), 2000, null);

    }
}



package pe.cibertec.demo02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.fragMap)).getMapAsync(MainActivity.this);

        ((Button)findViewById(R.id.btMainCenterCamera)).setOnClickListener(btMainCenterCameraOnClickListener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-12.088058, -77.050691)));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-12.08606, -77.050781)));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-12.08707, -77.05082)));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-12.085061, -77.050151)));

        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(-12.088237, -77.047032)));
    }

    View.OnClickListener btMainCenterCameraOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LatLngBounds.Builder builder = LatLngBounds.builder();
            builder.include(new LatLng(-12.088058, -77.050691)).include(new LatLng(-12.08606, -77.050781)).include(new LatLng(-12.08707, -77.05082)).include(new LatLng(-12.085061, -77.050151));//.include(new LatLng(-12.088237, -77.047032));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 32));
        }
    };
}

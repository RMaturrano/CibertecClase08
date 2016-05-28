package pe.cibertec.demo01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Variable del mapa para poder interactuar con el
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenego el fragmento del tipo mama declarado en mi Layout
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragMap);

        //Le digo al fragmento mapa que cuando ya esté listo me avise
        mapFragment.getMapAsync(MainActivity.this);

        ((Button) findViewById(R.id.btMainMyPosition)).setOnClickListener(btMainMyPositionOnClickListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        //Mueve la cámara sin animación a una posición
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.087, -77.049489), 17));
        //Mueve la cámara con animación a una posición
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.087, -77.049489), 17));


        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            //Muetra el botón de mi ubicación
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.setMyLocationEnabled(true);
        }else{
            //Muetra el botón de mi ubicación
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        }

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(-12.088037, -77.050744)).add(new LatLng(-12.085079, -77.01));
        mGoogleMap.addPolyline(polylineOptions);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-12.088037, -77.050744)).add(new LatLng(-12.085079, -77.048545)).add(new LatLng(-12.088237, -77.048148));
        polygonOptions.fillColor(0x33F2F204);
        mGoogleMap.addPolygon(polygonOptions);

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(new LatLng(-12.088037, -77.050744)).radius(10.0d).fillColor(0x22F50045);
        mGoogleMap.addCircle(circleOptions);
    }

    View.OnClickListener btMainMyPositionOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mGoogleMap != null)
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.087, -77.049489), 17));
        }
    };
}

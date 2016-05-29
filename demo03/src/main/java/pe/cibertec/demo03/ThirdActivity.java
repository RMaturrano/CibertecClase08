package pe.cibertec.demo03;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Android-SAB-PM on 28/05/2016.
 */
public class ThirdActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private LatLng mLatLng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.fragThirdMap)).getMapAsync(ThirdActivity.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.abSecondSave) {
            if (mLatLng == null)
                return true;
            else {
                Intent intent = new Intent();
                intent.putExtra("LatLng", mLatLng);
                setResult(RESULT_OK, intent);
                finish();
            }
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        this.mGoogleMap.setOnMapClickListener(googleMapOnMapClickListener);
    }

    GoogleMap.OnMapClickListener googleMapOnMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            mLatLng = latLng;
            mGoogleMap.clear();
            mGoogleMap.addMarker(new MarkerOptions().position(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        }
    };
}

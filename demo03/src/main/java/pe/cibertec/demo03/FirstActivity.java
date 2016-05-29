package pe.cibertec.demo03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private ArrayList<LatLng> mLstLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        ((MapFragment) getFragmentManager().findFragmentById(R.id.fragFirstMap)).getMapAsync(FirstActivity.this);

        mLstLatLng = new ArrayList<>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        setMarkers();
    }

    private void setMarkers() {
        if (mGoogleMap != null) {
            mGoogleMap.clear();
            LatLngBounds.Builder builder = LatLngBounds.builder();
            for (int i = 0; i < mLstLatLng.size(); i++) {
                builder.include(mLstLatLng.get(i));
                mGoogleMap.addMarker(new MarkerOptions().position(mLstLatLng.get(i)));
            }

            if (!mLstLatLng.isEmpty())
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 32));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.abFirstAdd) {
            startActivityForResult(new Intent(FirstActivity.this, ThirdActivity.class), 999);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mLstLatLng.add((LatLng) data.getParcelableExtra("LatLng"));
            setMarkers();
        }
    }
}

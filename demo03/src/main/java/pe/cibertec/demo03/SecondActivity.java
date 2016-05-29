package pe.cibertec.demo03;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Android-SAB-PM on 28/05/2016.
 */
public class SecondActivity extends AppCompatActivity {

    private EditText etSecondLatitude, etSecondLongitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        etSecondLatitude = (EditText) findViewById(R.id.etSecondLatitude);
        etSecondLongitude = (EditText) findViewById(R.id.etSecondLongitude);

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
            if (etSecondLatitude.getText().toString().trim().isEmpty() || etSecondLongitude.getText().toString().trim().isEmpty())
                return true;
            else {
                Intent intent = new Intent();
                intent.putExtra("LatLng", new LatLng(Double.parseDouble(etSecondLatitude.getText().toString().trim()), Double.parseDouble(etSecondLongitude.getText().toString().trim())));
                setResult(RESULT_OK, intent);
                finish();
            }
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

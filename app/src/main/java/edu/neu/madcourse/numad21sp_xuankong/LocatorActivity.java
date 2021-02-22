package edu.neu.madcourse.numad21sp_xuankong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LocatorActivity extends AppCompatActivity {
    Button btLocation;
    TextView tvLatitude, tvLongtitude;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
        btLocation = findViewById(R.id.LocatorButton);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongtitude = findViewById(R.id.tv_longtitude);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(LocatorActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurentLocation();
                } else {
                    ActivityCompat.requestPermissions(LocatorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            getCurentLocation();;
        } else {
            Toast.makeText(getApplicationContext(), "Permission denied.", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //Get last location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
//                        LocationRequest locationRequest=new LocationRequest()
//                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                                .setInterval(10000)
//                                .setFastestInterval(1000);
                        tvLatitude.setText(String.valueOf(location.getLatitude()));
                        tvLongtitude.setText(String.valueOf(location.getLongitude()));
                    } else {
                        LocationRequest locationReqest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(500)
                                .setFastestInterval(500)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                tvLatitude.setText(String.valueOf(location1.getLatitude()));
                                tvLongtitude.setText(String.valueOf(location1.getLongitude()));
                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationReqest, locationCallback, Looper.myLooper());

                    }
                }
            });
        } else {
            startActivities(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    private void startActivities(Intent setFlags) {
    }
}
package khatami.belajar.tugassensor10119026;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import khatami.belajar.tugassensor10119026.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity {


    private ActivityMapsBinding binding;
    private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        client = LocationServices.getFusedLocationProviderClient(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        getCurrentLocation();
    }


    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng lokSaatIni = new LatLng(location.getLatitude(),location.getLongitude());
                            LatLng lokasi = new LatLng(-6.904916148302193, 107.58582594127861);
                            LatLng lokasi2 = new LatLng(-6.906908535834666, 107.56229769214785);
                            LatLng lokasi3 = new LatLng(-6.900488949582766, 107.59712436901394);
                            LatLng lokasi4 = new LatLng(-6.92187413153841, 107.59336301927773);
                            LatLng lokasi5 = new LatLng(-6.926782297019317, 107.58451356897109);
                            googleMap.addMarker(new MarkerOptions().position(lokSaatIni).title("Lokasi Saat Ini"));
                            googleMap.addMarker(new MarkerOptions().position(lokasi).title("Yakitori Oda Japanese Kitchen"));
                            googleMap.addMarker(new MarkerOptions().position(lokasi2).title("Jayu Cake"));
                            googleMap.addMarker(new MarkerOptions().position(lokasi3).title("Mie Gacoan"));
                            googleMap.addMarker(new MarkerOptions().position(lokasi4).title("Sambal Bakar Ceu Neneng Pagarsih"));
                            googleMap.addMarker(new MarkerOptions().position(lokasi5).title("Roemah Seefood"));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokSaatIni, 17));
                            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        }
                    });
                }
            }
        });

    }
}
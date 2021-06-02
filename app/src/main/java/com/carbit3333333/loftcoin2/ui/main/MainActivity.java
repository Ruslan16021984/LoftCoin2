package com.carbit3333333.loftcoin2.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.carbit3333333.loftcoin2.R;
import com.carbit3333333.loftcoin2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController,
                new AppBarConfiguration.Builder(binding.bottomNav.getMenu()).build());

    }
}
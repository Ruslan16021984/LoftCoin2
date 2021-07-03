package com.carbit3333333.loftcoin2.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentFactory;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.carbit3333333.loftcoin2.BaseComponent;
import com.carbit3333333.loftcoin2.LoftApp;
import com.carbit3333333.loftcoin2.R;
import com.carbit3333333.loftcoin2.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private MainComponent mainComponent;
    @Inject
    FragmentFactory fragmentFactory;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        final BaseComponent baseComponent = ((LoftApp) newBase.getApplicationContext()).getAppComponent();
        mainComponent = DaggerMainComponent.builder().baseComponent(baseComponent).build();
        mainComponent.inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportFragmentManager().setFragmentFactory(fragmentFactory);
        final NavController navController = Navigation.findNavController(this, R.id.main_host);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController,
                new AppBarConfiguration.Builder(binding.bottomNav.getMenu()).build());

    }
}
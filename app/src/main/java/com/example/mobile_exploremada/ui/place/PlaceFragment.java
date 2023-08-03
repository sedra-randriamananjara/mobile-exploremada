package com.example.mobile_exploremada.ui.place;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.ui.account.AccountFragment;
import com.example.mobile_exploremada.ui.discover.DiscoverFragment;
import com.example.mobile_exploremada.ui.favoris.FavoritesFragment;
import com.example.mobile_exploremada.ui.lieu.LocationFragment;
import com.example.mobile_exploremada.ui.recherche.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlaceFragment extends Fragment {

    private static final int MENU_LIEU = R.id.menu_lieu;
    private static final int MENU_RECHERCHE = R.id.menu_recherche;
    private static final int MENU_DECOUVRIR = R.id.menu_decouvrir;
    private static final int MENU_FAVORIS = R.id.menu_favoris;
    private static final int MENU_COMPTE = R.id.menu_compte;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_place, container, false);

        // Initialiser la Bottom Navigation View
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Gérer la navigation entre les fragments en fonction de l'élément de menu sélectionné
            int itemId = item.getItemId();
            if (itemId == MENU_LIEU) {
                loadFragment(new LocationFragment());
                return true;
            } else if (itemId == MENU_RECHERCHE) {
                loadFragment(new SearchFragment());
                return true;
            } else if (itemId == MENU_DECOUVRIR) {
                loadFragment(new DiscoverFragment());
                return true;
            } else if (itemId == MENU_FAVORIS) {
                loadFragment(new FavoritesFragment());
                return true;
            } else if (itemId == MENU_COMPTE) {
                loadFragment(new AccountFragment());
                return true;
            }
            return false;
        });

        // Charger le premier fragment (par exemple, LocationFragment) par défaut
        loadFragment(new LocationFragment());

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}

package com.example.mobile_exploremada.ui.recherche;


import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.adapter.LieuAdapter;
import com.example.mobile_exploremada.models.LieuModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.LieuResponse;
import com.example.mobile_exploremada.ui.lieu.DetailLieuFragment;
import com.example.mobile_exploremada.utils.LieuApi;
import com.example.mobile_exploremada.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {private RecyclerView recyclerView;
    private LieuAdapter lieuAdapter;
    private androidx.appcompat.widget.SearchView rechercheEditText;
//    private SearchView rechercheEditText;

    private Handler handler = new Handler();
    private Runnable searchRunnable;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recherche, container, false);
        initViews(view);
        setupRecyclerView();
        setupSearchViewListener();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        lieuAdapter = new LieuAdapter(getActivity(),requireActivity().getSupportFragmentManager());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(lieuAdapter);
        rechercheEditText = view.findViewById(R.id.editTextRecherche); // Remplacer rechercheEditText par searchView
        rechercheEditText.setIconifiedByDefault(false);
        rechercheEditText.setQueryHint(Html.fromHtml("Entrez un nom de lieu ou une ville"));
        setupSearchViewListener();
    }

    private void setupRecyclerView() {
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        LieuModel lieu = lieuAdapter.getItem(position);
                        int idLieu = lieu.getId(); // Obtenez l'ID du lieu à partir du modèle

                        loadLieuDetailsFragment(idLieu);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // Gestion des clics longs si nécessaire
                    }
                }));
    }

    private void loadLieuDetailsFragment(int idLieu) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        DetailLieuFragment detailLieuFragment = new DetailLieuFragment(idLieu);
        transaction.replace(R.id.fragment_container, detailLieuFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    private void setupSearchViewListener() {
        rechercheEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Appelé lorsque l'utilisateur soumet la recherche
                // Effectuer l'action de recherche ici
                rechercherLieu(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Appelé lorsque le texte de recherche change
                // Mettre à jour les résultats de recherche ici

                // Supprimer le rappel de la recherche précédente
                handler.removeCallbacks(searchRunnable);

                // Mettre en place un nouveau rappel de recherche après 800 millisecondes
                searchRunnable = new Runnable() {
                    @Override
                    public void run() {
                        rechercherLieu(newText);
                    }
                };
                handler.postDelayed(searchRunnable, 800);

                return true;
            }
        });
    }


    private void rechercherLieu(String recherche) {
        LieuApi lieuApi = Servicey.getLieuApi();

        Call<LieuResponse> responseCall = lieuApi
                .chercherLieu(recherche);

        responseCall.enqueue(new Callback<LieuResponse>() {
            @Override
            public void onResponse(Call<LieuResponse> call, Response<LieuResponse> response) {
                if (response.isSuccessful()) {
                    List<LieuModel> lieux = new ArrayList<>(response.body().getLieu());
                    updateLieuList(lieux);
                } else {
                    // Handle server error responses
                    Toast.makeText(getActivity(), "Erreur lors de la recherche", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LieuResponse> call, Throwable t) {
                // Handle network or request errors
                Toast.makeText(getActivity(), "Erreur de réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateLieuList(List<LieuModel> lieux) {
        lieuAdapter.clearData();
        if (lieux != null && !lieux.isEmpty()) {
            lieuAdapter.setLieux(lieux);
        } else {
            Toast.makeText(getActivity(), "Aucun résultat trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}

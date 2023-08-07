package com.example.mobile_exploremada.ui.lieu;

import static com.example.mobile_exploremada.utils.Credentials.BASE_URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.LieuModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.LieuResponse;
import com.example.mobile_exploremada.ui.preference.PreferencesFragment;
import com.example.mobile_exploremada.ui.splashscreen.login.ui.login.RegisterFragment;
import com.example.mobile_exploremada.utils.LieuApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LocationFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private LieuAdapter lieuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
        //        View view = inflater.inflate(R.layout.fragment_account, container, false);
//        btn = view.findViewById(R.id.button_account);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GetRetrofitResponse();
//            }
//        });
//        // Code pour afficher la page "Lieu"
//        // Vous pouvez personnaliser cette page en fonction de vos besoins
//
//        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar = view.findViewById(R.id.progressBar);
        lieuAdapter = new LieuAdapter();
        recyclerView.setAdapter(lieuAdapter);

        GetRetrofitResponse();

    }

    private class LieuAdapter extends RecyclerView.Adapter<LieuAdapter.LieuViewHolder> {

        private List<LieuModel> lieux;

        public void setLieux(List<LieuModel> lieux) {
            this.lieux = lieux;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public LieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lieu, parent, false);
            return new LieuViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LieuViewHolder holder, int position) {
            LieuModel lieu = lieux.get(position);
            holder.textViewNom.setText(lieu.getNom());
            holder.textViewDescription.setText(lieu.getDescription_courte());
            holder.typelieu.setText(lieu.getNom_typelieu() +" - "+lieu.getNom_ville());
            Glide.with(holder.itemView.getContext())
                    .load(BASE_URL + "uploads/lieu/" + lieu.getImage_miniature())
                    .into(holder.imageView);
            holder.itemView.setOnClickListener(v -> {
                loadLieuDetailsFragment(lieu.getId());
            });
        }

        @Override
        public int getItemCount() {
            return lieux == null ? 0 : lieux.size();
        }

        class LieuViewHolder extends RecyclerView.ViewHolder {

            TextView textViewNom;
            TextView textViewDescription, typelieu;
            ImageView imageView;

            public LieuViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewNom = itemView.findViewById(R.id.textViewNom);
                textViewDescription = itemView.findViewById(R.id.textViewDescription);
                typelieu = itemView.findViewById(R.id.typelieu);
                imageView = itemView.findViewById(R.id.imageViewMiniature);
                progressBar = itemView.findViewById(R.id.progressBar);
            }
        }
        private void loadLieuDetailsFragment(int idlieu) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            DetailLieuFragment DetaillieuFragment = new DetailLieuFragment(idlieu);
            transaction.replace(R.id.fragment_container, DetaillieuFragment);
            transaction.addToBackStack(null); // Permet de revenir au fragment précédent en appuyant sur le bouton de retour
            transaction.commit();
        }

    }

    private void GetRetrofitResponse() {
        progressBar.setVisibility(View.VISIBLE);
        LieuApi lieuApi = Servicey.getLieuApi();

        Call<LieuResponse> responseCall = lieuApi
                .findAll();

        responseCall.enqueue(new Callback<LieuResponse>() {
            @Override
            public void onResponse(Call<LieuResponse> call, Response<LieuResponse> response) {
                if(response.code() == 200){
                    //Log.v("Tag", "the response" + response.body().toString());
                    //Toast.makeText(getActivity(), "the response" + response.body().toString(), Toast.LENGTH_LONG).show();
                    List<LieuModel> lieus = new ArrayList<>(response.body().getLieu());
                    lieuAdapter.setLieux(lieus);

                    for (LieuModel lieu: lieus){
                        Log.v("Tag","The list" +lieu.getNom());
                    }
                }else{
                    Log.v("Tag","Error" + response.errorBody().toString());
                    Toast.makeText(getActivity(), "Error" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LieuResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();
                Log.v("Tag","Error" +t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });


    }
}

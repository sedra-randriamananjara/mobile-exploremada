package com.example.mobile_exploremada.ui.lieu;

import static com.example.mobile_exploremada.utils.Credentials.BASE_URL;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.*;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.*;
import com.example.mobile_exploremada.ui.place.PlaceFragment;
import com.example.mobile_exploremada.utils.LieuApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class DetailLieuFragment extends Fragment {
    private int idLieu;
    private Runnable runnable;
    private int index =0;
    private Handler handler = new Handler();
    private TextView nomLieuTextView;
    private TextView interludeView;
    private TextView descriptionCourteTextView;
    private ImageView miniatureImageView;
    private TextView typeLieuTextView;
    private ImageView imageDetailImageView;
    private TextView descriptionLongueDetailView;

    private TextView heureOuvertureDetailView;

    private TextView ContactDetailView;

    private TextView Frais_EntreeView;
    public DetailLieuFragment(int idLieu) {
        this.idLieu=idLieu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.fragment_detail_lieu, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nomLieuTextView = view.findViewById(R.id.textViewNomlieuDetail);
        descriptionCourteTextView = view.findViewById(R.id.textViewDescriptionCourteDetail);
        miniatureImageView = view.findViewById(R.id.imageViewMiniatureDetail);
        typeLieuTextView = view.findViewById(R.id.typelieuDetail);
        imageDetailImageView = view.findViewById(R.id.imageDetail);
        ContactDetailView = view.findViewById(R.id.ContactDetail);
        descriptionLongueDetailView = view.findViewById(R.id.textViewDescriptionlongueDetail);
        heureOuvertureDetailView = view.findViewById(R.id.heureOuvertureDetail);
        Frais_EntreeView = view.findViewById(R.id.textViewFrais_Entree);
        interludeView= view.findViewById(R.id.textViewInterlude);
        final TextView descriptionHeader = view.findViewById(R.id.textViewDescriptionHeader);
        final TextView informationHeader =view.findViewById(R.id.textViewInfoHeader);
        description(view,descriptionHeader);
        information(view,informationHeader);
        getDetailLieu(view,idLieu);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                loadPrecedent();
            }
        });
    }

    public void getDetailLieu(View view,int idLieu) {
        LieuApi lieuApi = Servicey.getLieuApi();
        Call<DetailLieuResponse> detailLieuResponseCall = lieuApi
                .findLieuById(idLieu);
        detailLieuResponseCall.enqueue(new Callback<DetailLieuResponse>() {
            @Override
            public void onResponse(Call<DetailLieuResponse> call, Response<DetailLieuResponse> response) {
                if(response.code() == 200){
                   LieuModel  lieu = response.body().getDetailLieu();
                    nomLieuTextView.setText(lieu.getNom());
                    descriptionCourteTextView.setText(lieu.getDescription_courte());                    miniatureImageView = view.findViewById(R.id.imageViewMiniatureDetail);
                    typeLieuTextView.setText("types: "+lieu.getNom_typelieu());
                    ContactDetailView.setText("Contact: "+ lieu.getContact());
                    descriptionLongueDetailView.setText( lieu.getDescription_longue());
                    heureOuvertureDetailView.setText("ouvert à :" +lieu.getHeure_ouverture());
                    Frais_EntreeView.setText("Frais d'entrée :" +lieu.getFrais_entree());
                    Glide.with(view.getContext())
                            .load(BASE_URL + "uploads/lieu/" + lieu.getImage_miniature())
                            .into(miniatureImageView);
                    interludeView.setText("Voici un apercu du site :" +lieu.getNom());
                    SetImageDetail(idLieu);
                    }
                }
            @Override
            public void onFailure(Call<DetailLieuResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();
                Log.v("Tag","Error" +t.toString());
            }

        });
    }
    private void description(View view,TextView descriptionHeader){
        descriptionHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descriptionLongueDetailView.getVisibility() == View.GONE) {
                    descriptionLongueDetailView.setVisibility(View.VISIBLE);
                } else {
                    descriptionLongueDetailView.setVisibility(View.GONE);
                }
            }
        });
    }
    private void loadPrecedent() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceFragment PlaceFragment = new PlaceFragment();
        fragmentTransaction.replace(R.id.fragment_container, PlaceFragment);
        fragmentTransaction.commit();  }
    private void information(View view,TextView informationHeader){
        informationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (heureOuvertureDetailView.getVisibility() == View.GONE) {
                    Frais_EntreeView.setVisibility(View.VISIBLE);
                    typeLieuTextView.setVisibility(View.VISIBLE);
                    ContactDetailView.setVisibility(View.VISIBLE);
                    heureOuvertureDetailView.setVisibility(View.VISIBLE);
                } else {
                    heureOuvertureDetailView.setVisibility(View.GONE);
                    Frais_EntreeView.setVisibility(View.GONE);
                    typeLieuTextView.setVisibility(View.GONE);
                    ContactDetailView.setVisibility(View.GONE);
                    heureOuvertureDetailView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void SetImageDetail(int idLieu) {
        LieuApi lieuApi = Servicey.getLieuApi();

        Call<Lieu_imageResponse> Lieu_imageResponse = lieuApi
                .getLieu_image();
        Lieu_imageResponse.enqueue(new Callback<Lieu_imageResponse>() {
            @Override
            public void onResponse(Call<Lieu_imageResponse> call, Response<Lieu_imageResponse> response) {
                if (response.code() == 200) {
                    List<Lieu_imageModel> lieu_images = new ArrayList<>(response.body().getLieu_image());
                    List<String> image = new ArrayList<>();
                    for (Lieu_imageModel lieu_image : lieu_images) {
                        if (lieu_image.getId_lieu() == idLieu) {
                            image.add(lieu_image.getImage());
                        }
                    }
                    if(image.isEmpty()){

                    }else{
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(BASE_URL + "uploads/lieu/" + image.get(index)).into(imageDetailImageView);
                            index = (index + 1) % image.size();
                            handler.postDelayed(this, 3000);
                        }
                    };
                    handler.post(runnable);
                }
                }

            }
                @Override
                public void onFailure(Call<Lieu_imageResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();
                    Log.v("Tag","Error" +t.toString());
                }

    });
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }



}

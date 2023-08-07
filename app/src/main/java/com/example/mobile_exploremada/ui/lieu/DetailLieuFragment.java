package com.example.mobile_exploremada.ui.lieu;

import static com.example.mobile_exploremada.utils.Credentials.BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.MainActivity;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.*;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.*;
import com.example.mobile_exploremada.ui.place.PlaceFragment;
import com.example.mobile_exploremada.utils.Credentials;
import com.example.mobile_exploremada.utils.LieuApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.media.MediaPlayer;
public class DetailLieuFragment extends Fragment {
    private int idLieu;
    private Runnable runnable;
    private int index =0;
    private Handler handler = new Handler();
    private TextView nomLieuTextView;
    private TextView descriptionCourteTextView;
    private ImageView miniatureImageView;
    private TextView typeLieuTextView;
    private ImageView imageDetailImageView;
    private TextView descriptionLongueDetailView;

    private TextView heureOuvertureDetailView;

    private TextView ContactDetailView;
    private VideoView VideoDetailVideoView;

    private TextView Frais_EntreeView;

    private ProgressBar progressBarDetailLieu;

    private Button share_btn;
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
        VideoDetailVideoView= view.findViewById(R.id.videoView);
        progressBarDetailLieu = view.findViewById(R.id.progressBar);
                getDetailLieu(view,idLieu);
        share_btn = view.findViewById(R.id.share_btn);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                loadPrecedent();
            }
        });
    }

    public void getDetailLieu(View view,int idLieu) {
        progressBarDetailLieu.setVisibility(View.VISIBLE);

        LieuApi lieuApi = Servicey.getLieuApi();
        Call<DetailLieuResponse> detailLieuResponseCall = lieuApi
                .findLieuById(idLieu);
        detailLieuResponseCall.enqueue(new Callback<DetailLieuResponse>() {

            @Override
            public void onResponse(Call<DetailLieuResponse> call, Response<DetailLieuResponse> response) {
                if(response.code() == 200){
                    LieuModel  lieu = response.body().getDetailLieu();
                    String contact = "";
                    String heureOuverture ="";
                    String fraisEntree ="";
                    if( lieu.getContact()!=null){
                        contact= "Contact : "+lieu.getContact();
                    }
                    if( lieu.getFrais_entree()!=null){
                        fraisEntree="Frais d'entrée : "+lieu.getFrais_entree()+ " ("+ lieu.getRemarque_frais_entree()+")";
                    }

                    if( lieu.getHeure_ouverture()!=null){
                        heureOuverture ="Heure d'ouverture : "+lieu.getHeure_ouverture()+ " au "+lieu.getHeure_fermeture()+" ("+lieu.getRemarque_horaire()+")";
                    }
                    nomLieuTextView.setText(lieu.getNom());
                    descriptionCourteTextView.setText(lieu.getDescription_courte());
                    miniatureImageView = view.findViewById(R.id.imageViewMiniatureDetail);
                    typeLieuTextView.setText(lieu.getNom_typelieu()+" - "+lieu.getNom_ville());
                    ContactDetailView.setText(contact);
//                    CharSequence formattedText = Html.fromHtml(lieu.getDescription_longue());
                    Spanned formattedText = HtmlCompat.fromHtml(lieu.getDescription_longue(), HtmlCompat.FROM_HTML_MODE_LEGACY);
                    descriptionLongueDetailView.setText(formattedText);
                    heureOuvertureDetailView.setText(heureOuverture);
                    Frais_EntreeView.setText(fraisEntree);
                    Glide.with(view.getContext())
                            .load(BASE_URL + "uploads/lieu/" + lieu.getImage_miniature())
                            .into(miniatureImageView);
                    SetImageDetail(idLieu);
                    SetVideoDetail(idLieu);

                    share_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            shareData(lieu);
                        }
                    });
                    }
                progressBarDetailLieu.setVisibility(View.GONE);
                }
            @Override
            public void onFailure(Call<DetailLieuResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();
                Log.v("Tag","Error" +t.toString());
                progressBarDetailLieu.setVisibility(View.GONE);
            }

        });
    }

    public void shareData(LieuModel lieu) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        String subject = "Explore Madagascar : " + lieu.getNom();
        String text = lieu.getNom() + "<br>" + lieu.getNom_typelieu() + "<br>" + lieu.getNom_ville();

        String formattedText = android.text.Html.fromHtml(text).toString();

        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, formattedText);

        startActivity(Intent.createChooser(i, "Choisir le plateforme"));
    }



    private void loadPrecedent() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceFragment PlaceFragment = new PlaceFragment();
        fragmentTransaction.replace(R.id.fragment_container, PlaceFragment);
        fragmentTransaction.commit();  }


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
                    if (image.isEmpty()) {
                        imageDetailImageView.setVisibility(View.GONE);
                    } else {
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
                Log.v("Tag", "Error" + t.toString());
            }
        });
    }

            private void SetVideoDetail(int idLieu) {
                LieuApi lieuApi = Servicey.getLieuApi();

                Call<Lieu_videoResponse> Lieu_videoResponse = lieuApi
                        .getLieu_video();
                Lieu_videoResponse.enqueue(new Callback<Lieu_videoResponse>() {
                    @Override
                    public void onResponse(Call<Lieu_videoResponse> call, Response<Lieu_videoResponse> response) {
                        if (response.code() == 200) {
                            List<Lieu_videoModel> lieu_Videos = new ArrayList<>(response.body().getLieu_video());
                            List<String> Video = new ArrayList<>();
                            for (Lieu_videoModel lieu_Video : lieu_Videos) {
                                if (lieu_Video.getId_lieu() == idLieu) {
                                    Video.add(lieu_Video.getVideo());
                                }
                            }
                            if(Video.isEmpty()){
                                VideoDetailVideoView.setVisibility(View.GONE);
                           }else{

                                String videoPath =BASE_URL + "uploads/video/" + Video.get(0) ;
                                Uri videoUri = Uri.parse(videoPath);
                                VideoDetailVideoView.setVideoURI(videoUri);
                                VideoDetailVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        mediaPlayer.start();
                                    }
                                });
                                VideoDetailVideoView.start();
                            }

                        }

                    }
                    @Override
                    public void onFailure(Call<Lieu_videoResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();
                        Log.v("Tag", "Error" + t.toString());
                    }
                });
            }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }



}

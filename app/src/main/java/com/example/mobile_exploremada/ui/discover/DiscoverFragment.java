package com.example.mobile_exploremada.ui.discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.adapter.ImageAdapter;
import com.example.mobile_exploremada.models.ImageModel;
import com.example.mobile_exploremada.models.LieuModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.ImageResponse;
import com.example.mobile_exploremada.ui.lieu.DetailLieuFragment;
import com.example.mobile_exploremada.utils.ImageService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverFragment extends Fragment {
        private GridView gridView;
        private ImageAdapter imageAdapter;
    private ProgressBar progressBar;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_image_grid, container, false);
            gridView = view.findViewById(R.id.grid_view);
            progressBar = view.findViewById(R.id.progressBar);
            imageAdapter = new ImageAdapter(getActivity());
            gridView.setAdapter(imageAdapter);
            loadImagesFromApi();
            gridView.setOnItemClickListener((parent, view1, position, id) -> {
                ImageModel clickedImage = imageAdapter.getItem(position);
                loadLieuDetailsFragment(clickedImage.getId());
            });
            return view;
        }

        private void loadImagesFromApi() {
            progressBar.setVisibility(View.VISIBLE);
            ImageService apiService = Servicey.getImageService();
            Call<ImageResponse> call = apiService.getImages();

            call.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    if (response.code() == 200) {
                        List<ImageModel> imageDataList = new ArrayList<>(response.body().getMe());
                        if (imageDataList != null) {
                            imageAdapter.setImageDataList(imageDataList);
                        }
                        for (ImageModel img : imageDataList) {
                            Log.v("Tag", "The list" + img.getImage());
                        }
                    } else {
                        // Gérer les erreurs en cas de réponse non réussie
                        Log.v("Tag", "Error" + response.errorBody().toString());
                        Toast.makeText(getActivity(), "Error else exception" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {
                    // Gérer les erreurs en cas d'échec de l'appel
                    Log.e("Tag", "Error onFailure: " + t.toString());
                    Toast.makeText(getActivity(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

        private void loadLieuDetailsFragment(int idlieu) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            DetailLieuFragment DetaillieuFragment = new DetailLieuFragment(idlieu);
            transaction.replace(R.id.fragment_container, DetaillieuFragment);
            transaction.addToBackStack(null); // Permet de revenir au fragment précédent en appuyant sur le bouton de retour
            transaction.commit();
        }


    }
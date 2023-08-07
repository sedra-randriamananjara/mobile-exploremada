package com.example.mobile_exploremada.ui.favoris;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.adapter.VideoAdapter;
import com.example.mobile_exploremada.models.LieuVideoModel;
import com.example.mobile_exploremada.request.Servicey;
import com.example.mobile_exploremada.response.ImageResponse;
import com.example.mobile_exploremada.response.VideoResponse;
import com.example.mobile_exploremada.utils.Credentials;
import com.example.mobile_exploremada.utils.ImageService;
import com.example.mobile_exploremada.utils.VideoService;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoAdapter = new VideoAdapter(getChildFragmentManager());
        recyclerView.setAdapter(videoAdapter);
        loadLieuVideosFromApi();
        return view;
    }

    private void loadLieuVideosFromApi() {
        VideoService service = Servicey.getVideoService();
        Call<VideoResponse> call = service.findAllVideo();

        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<LieuVideoModel> lieuVideoList = response.body().getLieu();
                    if (lieuVideoList != null && !lieuVideoList.isEmpty()) {
                        videoAdapter.setVideoList(lieuVideoList);
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

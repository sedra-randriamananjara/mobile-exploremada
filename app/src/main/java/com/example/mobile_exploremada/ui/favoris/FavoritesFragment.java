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

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.R;
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
    private VideoView videoView;
    private TextView tvName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        videoView = view.findViewById(R.id.video_view);
        tvName = view.findViewById(R.id.tv_name);
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
                        for (LieuVideoModel lieuVideo : lieuVideoList) {
                            Log.v("Tag","Video" + lieuVideo.getVideo());
//                            tvName.setText(lieuVideo.getId_lieu());
                            MediaController mediaController = new MediaController(getContext());
                            mediaController.setAnchorView(videoView);
                            videoView.setMediaController(mediaController);
                            videoView.setVideoURI(Uri.parse(Credentials.BASE_URL + "uploads/video/" + lieuVideo.getVideo()));
                            videoView.start();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.e("Tag", "Error onFailure: " + t.toString());
                Toast.makeText(getActivity(), "Error: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

package com.example.mobile_exploremada.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.LieuVideoModel;
import com.example.mobile_exploremada.ui.lieu.DetailLieuFragment;
import com.example.mobile_exploremada.utils.Credentials;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<LieuVideoModel> videoList = new ArrayList<>();
    private OnVideoClickListener onVideoClickListener;
    private FragmentManager fragmentManager;

    public VideoAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setOnVideoClickListener(OnVideoClickListener listener) {
        this.onVideoClickListener = listener;
    }


    public void setVideoList(List<LieuVideoModel> videoList) {
        this.videoList = videoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        LieuVideoModel lieuVideo = videoList.get(position);
        holder.tvName.setText((lieuVideo.getNom()));
        holder.tv_descri.setText(lieuVideo.getDescription_courte());
        holder.ville.setText(lieuVideo.getNom_ville());

        holder.itemView.setOnClickListener(v -> {
            if (onVideoClickListener != null) {
                onVideoClickListener.onVideoClick(lieuVideo.getId_lieu());
            }
        });

        MediaController mediaController = new MediaController(holder.itemView.getContext());
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setVideoURI(Uri.parse(Credentials.BASE_URL + "uploads/video/" + lieuVideo.getVideo()));
        holder.videoView.start();
        holder.tvName.setOnClickListener(v -> {
            loadLieuDetailsFragment(lieuVideo.getId_lieu());
        });
        holder.tv_descri.setOnClickListener(v -> {
            loadLieuDetailsFragment(lieuVideo.getId_lieu());
        });
        holder.ville.setOnClickListener(v -> {
            loadLieuDetailsFragment(lieuVideo.getId_lieu());
        });
    }

    private void loadLieuDetailsFragment(int idlieu) {
//        FragmentTransaction transaction = FragmentManager.beginTransaction();
//        DetailLieuFragment detailLieuFragment = new DetailLieuFragment(idlieu);
//        transaction.replace(R.id.fragment_container, detailLieuFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }


    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView tvName,tv_descri,ville;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            tvName = itemView.findViewById(R.id.tv_name);
            tv_descri = itemView.findViewById(R.id.tv_descri);

            ville = itemView.findViewById(R.id.ville);
        }
    }
    public interface OnVideoClickListener {
        void onVideoClick(int idlieu);
    }
}

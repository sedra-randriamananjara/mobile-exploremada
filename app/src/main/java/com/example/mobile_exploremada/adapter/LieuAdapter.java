package com.example.mobile_exploremada.adapter;

import static com.example.mobile_exploremada.utils.Credentials.BASE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_exploremada.R;
import com.example.mobile_exploremada.models.LieuModel;

import java.util.ArrayList;
import java.util.List;

public class LieuAdapter extends RecyclerView.Adapter<LieuAdapter.LieuViewHolder> {

    private List<LieuModel> lieux;
    private LayoutInflater inflater;

    public LieuAdapter(Context context) {
        this.lieux = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    public void setLieux(List<LieuModel> lieux) {
        this.lieux.clear();
        this.lieux.addAll(lieux);
        notifyDataSetChanged();
    }

    public void clearData() {
        lieux.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_lieu, parent, false);
        return new LieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LieuViewHolder holder, int position) {
        LieuModel lieu = lieux.get(position);

        if (lieu != null) {
            Glide.with(holder.itemView.getContext())
                    .load(BASE_URL + "uploads/lieu/" + lieu.getImage_miniature())
                    .into(holder.imageView);
            holder.textViewNom.setText(lieu.getNom());
            holder.textViewDescription.setText(lieu.getDescription_courte());
            holder.textViewTypeLieu.setText(lieu.getNom_typelieu());
            holder.textViewVille.setText(lieu.getNom_ville());
        }
    }

    @Override
    public int getItemCount() {
        return lieux.size();
    }

    static class LieuViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNom;
        TextView textViewDescription;
        TextView textViewTypeLieu;
        TextView textViewVille;

        LieuViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewTypeLieu = itemView.findViewById(R.id.textViewTypeLieu);
            textViewVille = itemView.findViewById(R.id.textViewVille);
        }
    }
}

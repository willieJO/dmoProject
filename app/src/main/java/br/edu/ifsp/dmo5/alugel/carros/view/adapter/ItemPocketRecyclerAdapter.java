package br.edu.ifsp.dmo5.alugel.carros.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.UserMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.RecyclerViewItemClickListener;

public class ItemPocketRecyclerAdapter  extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder> {
    private Context context;
    private UserMVP.Presenter presenter;
    private List<Carro> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Carro> data, UserMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ItemPocketRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_locatario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPocketRecyclerAdapter.ViewHolder holder, int position) {
        Carro carro = data.get(position);
        holder.marcaDoCarro.setText(carro.getMarca());
        holder.proprietario.setText(carro.getCpf());
        if (carro.getFoto() != null) {
            byte[] decodedBytes = Base64.decode(carro.getFoto(), Base64.DEFAULT);
            if (decodedBytes.length > 0) {
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.imagemCarro.setImageBitmap(decodedBitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView marcaDoCarro;
        public TextView proprietario;
        public ImageView imagemCarro;


        public ViewHolder(View itemView){
            super(itemView);
            marcaDoCarro = itemView.findViewById(R.id.text_modelo_carro_listitem_r);
            proprietario = itemView.findViewById(R.id.text_proprietario_listitem_r);
            imagemCarro = itemView.findViewById(R.id.image_car_listitem_r);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
}}

package br.edu.ifsp.dmo5.alugel.carros.view.adapter;

import android.annotation.SuppressLint;
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
import br.edu.ifsp.dmo5.alugel.carros.mvp.MeusCarrosAlugadosMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.UserMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.MeusCarrosAlugadosPresenter;
import br.edu.ifsp.dmo5.alugel.carros.view.RecyclerViewItemClickListener;

public class MeusCarroAlugadoPocketRecyclerAdapter extends RecyclerView.Adapter<MeusCarroAlugadoPocketRecyclerAdapter.ViewHolder> {
    private Context context;
    private MeusCarrosAlugadosMVP.Presenter presenter;
    private List<Carro> data;
    private static RecyclerViewItemClickListener clickListener;

    public MeusCarroAlugadoPocketRecyclerAdapter(Context context, List<Carro> data, MeusCarrosAlugadosMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public MeusCarroAlugadoPocketRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_meus_carros_alugados, parent, false);
        MeusCarroAlugadoPocketRecyclerAdapter.ViewHolder viewHolder = new MeusCarroAlugadoPocketRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeusCarroAlugadoPocketRecyclerAdapter.ViewHolder holder, int position) {
        Carro carro = data.get(position);
        if (carro.getFoto() != null) {
            byte[] decodedBytes = Base64.decode(carro.getFoto(), Base64.DEFAULT);
            if (decodedBytes.length > 0) {
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.imagemCarro.setImageBitmap(decodedBitmap);
            }
        }
        holder.editarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCarro(carro);
            }
        });
        holder.excluirCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirCarro(carro);
            }
        });
        holder.modeloCarro.setText(carro.getModelo());

    }

    private void editCarro(Carro carro){
        presenter.editVeiculo(carro);

    }
    @SuppressLint("NotifyDataSetChanged")
    private void excluirCarro(Carro carro) {
        presenter.excluirVeiculo(carro);
        int position = data.indexOf(carro);
        if (position != -1) {
            data.remove(position);
            notifyItemRemoved(position);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView modeloCarro;
        public ImageView imagemCarro;
        public ImageView excluirCarro;
        public ImageView editarCarro;


        public ViewHolder(View itemView){
            super(itemView);
            modeloCarro = itemView.findViewById(R.id.texto_modelo_carro_meus_carros_alugado);
            imagemCarro = itemView.findViewById(R.id.meus_carros_alugados_imagem);
            excluirCarro = itemView.findViewById(R.id.excluir_carro);
            editarCarro = itemView.findViewById(R.id.editar_carro);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }}

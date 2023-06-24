package br.edu.ifsp.dmo5.alugel.carros.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.model.CarroXUserAlugado;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CarrosQueAlugueiMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MeusCarrosAlugadosMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.CarrosQueAlugueiPresenter;
import br.edu.ifsp.dmo5.alugel.carros.view.RecyclerViewItemClickListener;

public class CarrosQueAlugueiPocketRecyclerAdapter extends RecyclerView.Adapter<CarrosQueAlugueiPocketRecyclerAdapter.ViewHolder> {
    private Context context;
    private CarrosQueAlugueiMVP.Presenter presenter;
    private List<CarroXUserAlugado> data;
    Calendar calAtual;
    private static RecyclerViewItemClickListener clickListener;

    public CarrosQueAlugueiPocketRecyclerAdapter(Context context, List<CarroXUserAlugado> data, CarrosQueAlugueiMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
        calAtual = Calendar.getInstance();
    }

    @NonNull
    @Override
    public CarrosQueAlugueiPocketRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_carros_que_aluguei, parent, false);
        CarrosQueAlugueiPocketRecyclerAdapter.ViewHolder viewHolder = new CarrosQueAlugueiPocketRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarrosQueAlugueiPocketRecyclerAdapter.ViewHolder holder, int position) {
        CarroXUserAlugado carro = data.get(position);
        if (carro.getFoto() != null) {
            byte[] decodedBytes = Base64.decode(carro.getFoto(), Base64.DEFAULT);
            if (decodedBytes.length > 0) {
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                holder.imagemCarro.setImageBitmap(decodedBitmap);
            }
        }

        String dataString = carro.getDataInicio();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date dataInicio = null;
        try {
            dataInicio = sdf.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calDataInicio = Calendar.getInstance();
        calDataInicio.setTime(dataInicio);

        Calendar calAtual = Calendar.getInstance();
        calAtual.set(Calendar.HOUR_OF_DAY, 0);
        calAtual.set(Calendar.MINUTE, 0);
        calAtual.set(Calendar.SECOND, 0);
        calAtual.set(Calendar.MILLISECOND, 0);
        Date dataAtual = new Date();
        if (carro.getCarroEntregue() == 1) {
            holder.entregar.setVisibility(View.INVISIBLE);
        } else {
            if (dataInicio != null && !dataInicio.after(dataAtual)) {
                holder.entregar.setVisibility(View.VISIBLE);
            } else {
                holder.entregar.setVisibility(View.INVISIBLE);
            }
        }

        long diferencaEmMillis = calDataInicio.getTimeInMillis() - calAtual.getTimeInMillis();
        int diferencaEmDias = (int) (diferencaEmMillis / (24 * 60 * 60 * 1000));

        if (diferencaEmDias >= 2) {
            holder.cancelar.setVisibility(View.VISIBLE);
        } else {
            holder.cancelar.setVisibility(View.INVISIBLE);
        }

        holder.entregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entregar(carro);
            }
        });

        holder.cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar(carro);
            }
        });

        holder.modeloCarro.setText(carro.getModeloCarro());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<CarroXUserAlugado> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void entregar(CarroXUserAlugado carro){
        presenter.entregar(carro);
        updateData(presenter.obterCarrosAlugados());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void cancelar(CarroXUserAlugado carro) {
        presenter.cancelar(carro);
        updateData(presenter.obterCarrosAlugados());
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
        public Button entregar;
        public Button cancelar;

        public ViewHolder(View itemView){
            super(itemView);
            modeloCarro = itemView.findViewById(R.id.texto_modelo_carro_meus_carros_alugado);
            imagemCarro = itemView.findViewById(R.id.meus_carros_alugados_imagem_view);
            entregar = itemView.findViewById(R.id.entregar_button);
            cancelar = itemView.findViewById(R.id.cancelar_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}

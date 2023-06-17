package br.edu.ifsp.dmo5.alugel.carros.presenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo5.alugel.carros.dao.CarroSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroDao;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.model.User;
import br.edu.ifsp.dmo5.alugel.carros.mvp.PhotoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.UserMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.RecyclerViewItemClickListener;
import br.edu.ifsp.dmo5.alugel.carros.view.adapter.ItemPocketRecyclerAdapter;

public class UserPresenter implements UserMVP.Presenter {

    private UserMVP.View view;
    private ICarroDao dao;
    private Carro carro;

    public UserPresenter(UserMVP.View viewRecept) {
        view = viewRecept;
        dao = new CarroSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                carro = dao.findAll().get(position);
                //openDetails(article);
            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

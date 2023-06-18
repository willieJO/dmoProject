package br.edu.ifsp.dmo5.alugel.carros.presenter;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.dao.CarroSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroDao;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MeusCarrosAlugadosMVP;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;
import br.edu.ifsp.dmo5.alugel.carros.view.DetalhesCarroActivity;
import br.edu.ifsp.dmo5.alugel.carros.view.EditVeiculoActivity;
import br.edu.ifsp.dmo5.alugel.carros.view.RecyclerViewItemClickListener;
import br.edu.ifsp.dmo5.alugel.carros.view.adapter.ItemPocketRecyclerAdapter;
import br.edu.ifsp.dmo5.alugel.carros.view.adapter.MeusCarroAlugadoPocketRecyclerAdapter;
import br.edu.ifsp.dmo5.alugel.carros.view.new_car_fim;

public class MeusCarrosAlugadosPresenter implements MeusCarrosAlugadosMVP.Presenter {
    private MeusCarrosAlugadosMVP.View view;
    private ICarroDao dao;
    private Carro carro;

    public MeusCarrosAlugadosPresenter(MeusCarrosAlugadosMVP.View viewRecept) {
        view = viewRecept;
        dao = new CarroSQLite(view.getContext());
    }

    @Override
    public void editVeiculo(Carro carro) {
        Intent intent = new Intent(view.getContext(), EditVeiculoActivity.class);
        intent.putExtra(Constant.CARRO_MODEL, carro);
        view.getContext().startActivity(intent);
    }

    @Override
    public void salvarEdicaoVeiculo(Carro carro) {

    }

    @Override
    public void excluirVeiculo(Carro carro) {
        dao.excluirCarro(carro);
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        MeusCarroAlugadoPocketRecyclerAdapter adapter = new
                MeusCarroAlugadoPocketRecyclerAdapter(view.getContext(), dao.findAllById(), this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

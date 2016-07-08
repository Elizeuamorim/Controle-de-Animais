package br.edu.ifba.mobile.cadastrodeanimais.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.cadastrodeanimais.R;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.ListagemAnimal;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.ListagemConsulta;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.RemocaoAnimal;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.RemocaoConsulta;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Consulta;

/**
 * Created by alunoifba on 20/05/2016.
 */
public class FragmentoListaCadastros extends Fragment {

    private static FragmentoListaCadastros instancia = null;

    public static FragmentoListaCadastros getInstancia(){
        if(instancia==null){
            instancia = new FragmentoListaCadastros();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){

        tela = inflador.inflate(R.layout.fragmento_cadastro_consulta,vgrupo,false);

        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador){

        super.onCreateOptionsMenu(menu, inflador);

        inflador.inflate(R.menu.menu_controle_notas, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        long id = item.getItemId();
        if (id != AdapterView.INVALID_POSITION){
            if (id == R.id.cadastro_remover){
                RemocaoConsulta remocao = new RemocaoConsulta(this.getContext(),this.getConsultaSelecionada());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaDisciplinas);

        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void atualizar(){
        ListagemConsulta listagem = new ListagemConsulta(this.getContext(),lista);
        listagem.execute();
    }
    public Consulta getConsultaSelecionada(){
        Consulta consulta = new Consulta();

        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION){
            consulta = (Consulta) lista.getItemAtPosition(posicao);
        }
        return consulta;
    }

}

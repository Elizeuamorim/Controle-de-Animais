package br.edu.ifba.mobile.cadastrodeanimais.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.RemocaoAnimal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;

/**
 * Created by alunoifba on 20/05/2016.
 */
public class FragmentoListaAnimais extends Fragment {

    private static FragmentoListaAnimais instancia = null;

    public static FragmentoListaAnimais getInstancia(){
        if(instancia==null){
            instancia = new FragmentoListaAnimais();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){

        tela = inflador.inflate(R.layout.fragmento_listagem_animais,vgrupo,false);

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
                RemocaoAnimal remocao = new RemocaoAnimal(this.getContext(),this.getAnimalSelecionado());
                remocao.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar(){
        lista = (ListView) tela.findViewById(R.id.listaDisciplinas);
        atualizar();
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    public void atualizar(){
        ListagemAnimal listagem = new ListagemAnimal(this.getContext(),lista);
        listagem.execute();
    }
    public Animal getAnimalSelecionado(){
        Animal animal = new Animal();

        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION){
            animal = (Animal) lista.getItemAtPosition(posicao);
        }
        return animal;
    }

}

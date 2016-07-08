package br.edu.ifba.mobile.cadastrodeanimais.Tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.FachadaBD;

/**
 * Created by alunoifba on 27/05/2016.
 */
public class ListagemAnimal extends AsyncTask <Void,Void,List<Animal>>{

    private Context contexto = null;
    private Animal animal = null;
    private ListView listaDisciplinas = null;


    public ListagemAnimal(Context contexto, ListView listaDisciplinas){
        this.contexto = contexto;
        this.listaDisciplinas = listaDisciplinas;
    }
    @Override
    protected List<Animal> doInBackground(Void... params) {
        String mensagem = "";
        List<Animal> animais = FachadaBD.getInstancia().listarAnimais();

      return animais;
    }


    @Override
    protected void onPostExecute(List<Animal> animais){
        if (animais.isEmpty()) {

            Toast.makeText(contexto, "Lista Vazia. Cadastre uma animal!", Toast.LENGTH_LONG).show();

        }else{

            ArrayAdapter<Animal> adaptador = new ArrayAdapter<Animal>(contexto, android.R.layout.simple_list_item_single_choice, animais);
            listaDisciplinas.setAdapter(adaptador);

        }
    }

}

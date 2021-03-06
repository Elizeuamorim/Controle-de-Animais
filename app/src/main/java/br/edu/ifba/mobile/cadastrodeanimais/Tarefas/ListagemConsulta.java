package br.edu.ifba.mobile.cadastrodeanimais.Tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Consulta;
import br.edu.ifba.mobile.cadastrodeanimais.bd.FachadaBD;

/**
 * Created by alunoifba on 27/05/2016.
 */
public class ListagemConsulta extends AsyncTask <Void,Void,List<Consulta>>{

    private Context contexto = null;
    private Consulta consulta = null;
    private ListView listaDisciplinas = null;


    public ListagemConsulta(Context contexto, ListView listaDisciplinas){
        this.contexto = contexto;
        this.listaDisciplinas = listaDisciplinas;
    }



    @Override
    protected List<Consulta> doInBackground(Void... params) {
        String mensagem = "";
        List<Consulta> consultas = FachadaBD.getInstancia().listarConsultas();

      return consultas;
    }


    @Override
    protected void onPostExecute(List<Consulta> consultas){
        if (consultas.isEmpty()) {

            Toast.makeText(contexto, "Lista Vazia. Cadastre uma consulta!", Toast.LENGTH_LONG).show();

        }else{

            ArrayAdapter<Consulta> adaptador = new ArrayAdapter<Consulta>(contexto, android.R.layout.simple_list_item_single_choice, consultas);
            listaDisciplinas.setAdapter(adaptador);

        }
    }

}

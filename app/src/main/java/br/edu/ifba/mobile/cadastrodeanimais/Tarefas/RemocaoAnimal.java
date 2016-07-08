package br.edu.ifba.mobile.cadastrodeanimais.Tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.FachadaBD;

/**
 * Created by alunoifba on 27/05/2016.
 */
public class RemocaoAnimal extends AsyncTask <Void,Void,String>{

    private Context contexto = null;
    private Animal animal = null;


    public RemocaoAnimal(Context contexto, Animal animal){
        this.contexto = contexto;
        this.animal = animal;
    }
    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if (animal.getNome() != null){
            if (FachadaBD.getInstancia().remover(animal)== 0){
            mensagem = "Problema de remoção!";
        }else {
                mensagem = "Animal removida!";
            }
        } else {
            mensagem = "Selecione uma Animal!";
        }

        return mensagem;
    }



    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}

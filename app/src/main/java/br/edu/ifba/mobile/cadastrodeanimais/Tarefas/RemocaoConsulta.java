package br.edu.ifba.mobile.cadastrodeanimais.Tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Consulta;
import br.edu.ifba.mobile.cadastrodeanimais.bd.FachadaBD;

/**
 * Created by alunoifba on 27/05/2016.
 */
public class RemocaoConsulta extends AsyncTask <Void,Void,String>{

    private Context contexto = null;
    private Consulta consulta = null;


    public RemocaoConsulta(Context contexto, Consulta animal){
        this.contexto = contexto;
        this.consulta = consulta;
    }
    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if (consulta.getCodigo() != -1){
            if (FachadaBD.getInstancia().remover(consulta)== 0){
            mensagem = "Problema de remoção!";
        }else {
                mensagem = "Consulta removida!";
            }
        } else {
            mensagem = "Selecione uma Consulta!";
        }

        return mensagem;
    }



    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}

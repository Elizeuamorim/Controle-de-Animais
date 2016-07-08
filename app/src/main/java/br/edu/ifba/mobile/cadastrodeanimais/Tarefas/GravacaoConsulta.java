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
public class GravacaoConsulta extends AsyncTask <Void,Void,String>{

    private Context contexto = null;
    private Consulta consulta = null;


    public GravacaoConsulta(Context contexto, Consulta consulta){
        this.contexto = contexto;
        this.consulta = consulta;
    }
    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = FachadaBD.getInstancia().inserir_consulta(consulta);

        if (codigo > 0){
            mensagem = "Consulta gravado com sucesso!";
        }else{
            mensagem = "Erro de gravaçao!";
        }

        return mensagem;
    }


    @Override
    protected void onPostExecute(String mensagem){
        Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
    }
}

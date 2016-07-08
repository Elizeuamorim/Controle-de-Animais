package br.edu.ifba.mobile.cadastrodeanimais.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.ifba.mobile.cadastrodeanimais.R;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.GravacaoAnimal;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.GravacaoConsulta;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Consulta;

/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoCadastroConsulta extends Fragment {

    private static FragmentoCadastroConsulta instancia = null;
    public static FragmentoCadastroConsulta getInstancia(){
        if(instancia==null){
            instancia=new FragmentoCadastroConsulta();
        }
        return instancia;
    }

    private View tela = null;

    private TextView nomeAnimal1 = null;
    private EditText dataConsulta = null;
    private EditText sintomasAnimal = null;
    private EditText procedimentoMedicacao = null;
    private Button botaoGravar1 = null;

    private Consulta consulta = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cadastro_consulta,vgrupo,false);
        preparar();
        return tela;
    }

    private void preparar(){
        nomeAnimal1=(TextView)tela.findViewById(R.id.nomeAnimal1);
        dataConsulta=(EditText)tela.findViewById(R.id.dataConsulta);
        sintomasAnimal=(EditText)tela.findViewById(R.id.sintomasAnimal);
        procedimentoMedicacao=(EditText)tela.findViewById(R.id.procedimentoMedicacao);
        botaoGravar1=(Button)tela.findViewById(R.id.botaoGravar1);
        botaoGravar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    GravacaoConsulta gravacao = new GravacaoConsulta(getContexto(), getConsulta());
                    gravacao.execute();

            }
        });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private Consulta getConsulta(){
        consulta = new Consulta();
        consulta.setNome1(nomeAnimal1.getText().toString());
        consulta.setData(dataConsulta.getText().toString());
        consulta.setSintomas(sintomasAnimal.getText().toString());
        consulta.setProcedimentos(procedimentoMedicacao.getText().toString());
        return consulta;
    }
    public void exibirAnimalSelecionado(){
        Animal animal = FragmentoListaAnimais.getInstancia().getAnimalSelecionado();
        if (animal.getCodigo() == -1) {
            limparCampos();
        } else {
            this.nomeAnimal1.setText(animal.getNome());
        }
    }

    private void limparCampos(){
        nomeAnimal1.setText("");
        dataConsulta.setText("");
        sintomasAnimal.setText("");
        procedimentoMedicacao.setText("");
    }

    private void carregarCampos(){
        nomeAnimal1.setText(consulta.getNome1());
        dataConsulta.setText(consulta.getData() + "");
        sintomasAnimal.setText(consulta.getSintomas() + "");
        procedimentoMedicacao.setText(consulta.getProcedimentos() + "");
    }

}
package br.edu.ifba.mobile.cadastrodeanimais.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.cadastrodeanimais.R;
import br.edu.ifba.mobile.cadastrodeanimais.Tarefas.GravacaoAnimal;
import br.edu.ifba.mobile.cadastrodeanimais.bd.Animal;

/**
 * Created by alunoifba on 13/05/2016.
 */
public class FragmentoCadastro extends Fragment {

    private static FragmentoCadastro instancia = null;
    public static FragmentoCadastro getInstancia(){
        if(instancia==null){
            instancia=new FragmentoCadastro();
        }
        return instancia;
    }

    private View tela = null;

    private EditText nomeAnimal = null;
    private EditText especie = null;
    private EditText raca = null;
    private EditText idade = null;
    private Button botaoGravar = null;

    private Animal animal = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle){
        tela = inflador.inflate(R.layout.fragmento_cadastro_animais,vgrupo,false);
        preparar();
        return tela;
    }

    private void preparar(){
        nomeAnimal=(EditText)tela.findViewById(R.id.nome);
        especie=(EditText)tela.findViewById(R.id.dataConsulta);
        raca=(EditText)tela.findViewById(R.id.racaAnimal);
        idade=(EditText)tela.findViewById(R.id.idadeAnimal);
        botaoGravar=(Button)tela.findViewById(R.id.botaoGravar);
        botaoGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animal.getNome() == null) {
                    GravacaoAnimal gravacao = new GravacaoAnimal(getContexto(), getAnimal());
                    gravacao.execute();
                }
            }
        });
    }

    private Context getContexto(){
        return this.getContext();
    }

    private Animal getAnimal(){
        animal = new Animal();
        animal.setNome(nomeAnimal.getText().toString());
        animal.setEspecie(especie.getText().toString());
        animal.setRaca(raca.getText().toString());
        animal.setIdade(idade.getText().toString());
        return animal;
    }
    public void exibirAnimalSelecionado(){
        animal = FragmentoListaAnimais.getInstancia().getAnimalSelecionado();
        if (animal.getNome() == null) {
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos(){
        nomeAnimal.setText("");
        especie.setText("");
        raca.setText("");
        idade.setText("");
    }
    private void carregarCampos(){
        nomeAnimal.setText(animal.getNome());
        especie.setText(animal.getEspecie() + "");
        raca.setText(animal.getRaca() + "");
        idade.setText(animal.getIdade() + "");
    }

}
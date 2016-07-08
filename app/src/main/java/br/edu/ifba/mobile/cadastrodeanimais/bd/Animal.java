package br.edu.ifba.mobile.cadastrodeanimais.bd;

public class Animal {
	private long codigo = -1;
	private String nome;
	private String especie;
	private String raca;
	private String idade;

	// construtor


	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Override
	public String toString(){
		return "Nome: "+this.getNome()+". Espécie: "+this.getEspecie()+".";
	}




}

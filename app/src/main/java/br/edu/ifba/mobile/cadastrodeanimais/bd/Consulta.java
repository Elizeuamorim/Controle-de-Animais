package br.edu.ifba.mobile.cadastrodeanimais.bd;

public class Consulta {
	private long codigo = -1;
	private String nome1;
	private String data;
	private String sintomas;
	private String procedimentos;

	// construtor


	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome1() {
		return nome1;
	}

	public void setNome1(String nome1) {
		this.nome1 = nome1;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(String procedimentos) {
		this.procedimentos = procedimentos;
	}

	@Override
	public String toString(){
		return "Nome: "+this.getNome1()+". Sintomas: "+this.getSintomas()+".";
	}




}

package br.edu.ifba.mobile.cadastrodeanimais.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FachadaBD extends SQLiteOpenHelper {

	private static FachadaBD instancia = null;

	public static FachadaBD criarInstancia(Context context){
		if(instancia == null){
			instancia = new FachadaBD(context);
		}
		return instancia;
	}

	public static FachadaBD getInstancia(){
		return instancia;
	}

	private static String NOME_BANCO = "Cadastroanm";
	private static int VERSAO_BANCO = 1;



	public FachadaBD(Context context) {

		super(context,NOME_BANCO,null,VERSAO_BANCO);


	}

	private static String COMANDO_CRIACAO_TABELA_DISCIPLINAS =
			"CREATE TABLE ANIMAIS(" +
					"NOME TEXT PRIMARY KEY, ESPECIE TEXT, RACA TEXT, IDADE TEXT)";

	private static String COMANDO_CRIACAO_TABELA_CONSULTAS =
			"CREATE TABLE CONS(" +
					"NOMEANIMAL TEXT PRIMARY KEY, DT TEXT, SINTOMAS TEXT, PROC TEXT)";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COMANDO_CRIACAO_TABELA_DISCIPLINAS);
		db.execSQL(COMANDO_CRIACAO_TABELA_CONSULTAS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
		// TODO Auto-generated method stub
	}

	// metodos de criacao de um CRUD utilizando o SQLite

	public long inserir(Animal animal) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();

		valores.put("NOME", animal.getNome());
		valores.put("ESPECIE", animal.getEspecie());
		valores.put("RACA", animal.getRaca());
		valores.put("IDADE", animal.getIdade());

		long codigo = db.insert("ANIMAIS",null, valores);
		return codigo;


	}


	public long atualizar(Animal animal) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();

		valores.put("NOME", animal.getNome());
		valores.put("ESPECIE", animal.getEspecie());
		valores.put("RACA", animal.getRaca());
		valores.put("IDADE", animal.getIdade());

		long codigo = db.update("ANIMAIS",valores,"NOME "+ animal.getNome(),null);

		return codigo;
	}

	public int remover(Animal animal) {
		SQLiteDatabase db = this.getWritableDatabase();

		return db.delete("ANIMAIS", "NOME ='" + animal.getNome()+"'",null);

	}

	public List<Animal> listarAnimais() {

		List<Animal> animais = new ArrayList<Animal>();
		SQLiteDatabase db = this.getReadableDatabase();

		String selecao = "SELECT NOME, ESPECIE, RACA, IDADE FROM ANIMAIS";

		Cursor cursor = db.rawQuery(selecao,null);

		if(cursor != null){

			boolean temProximo = cursor.moveToFirst();
			while(temProximo){
				Animal animal = new Animal();
				animal.setNome(cursor.getString(cursor.getColumnIndex(("NOME"))));
				animal.setEspecie(cursor.getString(cursor.getColumnIndex("ESPECIE")));
				animal.setRaca(cursor.getString(cursor.getColumnIndex("RACA")));
				animal.setIdade(cursor.getString(cursor.getColumnIndex("IDADE")));
				animais.add(animal);
				temProximo = cursor.moveToNext();
			}
		}

		return animais;
	}

	public long inserir_consulta(Consulta consulta) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put("PROC", consulta.getProcedimentos());
		valores.put("SINTOMAS", consulta.getSintomas());
		valores.put("DT", consulta.getData());
		valores.put("NOMEANIMAL", consulta.getNome1());



		long codigo = db.insert("CONS",null,valores);
		return codigo;


	}



	public int remover(Consulta consulta) {
		SQLiteDatabase db = this.getWritableDatabase();

		return db.delete("CONSULTAS", "CODIGO =" + consulta.getCodigo(),null);

	}

	public List<Consulta> listarConsultas(String codigo) {

		List<Consulta> consultas = new ArrayList<Consulta>();
		SQLiteDatabase db = this.getReadableDatabase();

		String selecao = "SELECT NOMEANIMAL,DT, SINTOMAS, PROC FROM CONS WHERE NOMEANIMAL ='"+codigo+"'";

		Cursor cursor = db.rawQuery(selecao,null);

		if(cursor != null){

			boolean temProximo = cursor.moveToFirst();
			while(temProximo){
				Consulta consulta= new Consulta();
				consulta.setNome1(cursor.getString(cursor.getColumnIndex(("NOMEANIMAL"))));
				consulta.setData(cursor.getString(cursor.getColumnIndex("DT")));
				consulta.setSintomas(cursor.getString(cursor.getColumnIndex("SINTOMAS")));
				consulta.setProcedimentos(cursor.getString(cursor.getColumnIndex("PROC")));
				consultas.add(consulta);
				temProximo = cursor.moveToNext();
			}
		}

		return consultas;
	}
}
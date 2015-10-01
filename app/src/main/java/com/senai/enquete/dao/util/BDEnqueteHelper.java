package com.senai.enquete.dao.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mr Menezes on 01/10/2015.
 */
public class BDEnqueteHelper extends SQLiteOpenHelper {

	// Nome do banco de dados
	private static final String NOME_DO_BANCO = "bdenquete";
	// Versão atual do banco de dados
	private static final int VERSAO_DO_BANCO = 1;

	public BDEnqueteHelper(Context context) {
	super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
	}

	/**
	* Cria a tabela no banco de dados, caso ela não exista.
	*/
	@Override
	public void onCreate(SQLiteDatabase db) {
	String sql = "CREATE TABLE enquete (" +
	"id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
	",resposta TEXT NOT NULL" +
	",data INTEGER NOT NULL" + ");";
	db.execSQL(sql);

	}

	/**
	* Atualiza a estrutura da tabela no banco de dados, caso sua versão tenha mudado.
	*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS enquete";
		db.execSQL(sql);
	    onCreate(db);
	}


}

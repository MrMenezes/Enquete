package com.senai.enquete.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.SystemClock;

import com.senai.enquete.dao.util.BDEnqueteHelper;
import com.senai.enquete.dto.EnqueteDTO;

/**
 * Created by Mr Menezes on 01/10/2015.
 */
public class EnqueteDAO  {
    BDEnqueteHelper bdEnquete;
    private Context context;

    public EnqueteDAO(Context context) {
        bdEnquete = new BDEnqueteHelper(context);
        this.context = context;
    }



    public int getMax(String resposta){
        SQLiteDatabase db = bdEnquete.getReadableDatabase();
        Cursor mCount= db.rawQuery("select count(resposta) from enquete where resposta='" + resposta + "'", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;

    }

    public void adicionar(EnqueteDTO enqueteDTO) {
        try{

            SQLiteDatabase db = bdEnquete.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("resposta", enqueteDTO.getResposta());
            values.put("data", System.currentTimeMillis());
            Long count = db.insert("enquete", null, values);
            db.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    }


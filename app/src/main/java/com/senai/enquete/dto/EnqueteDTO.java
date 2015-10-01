package com.senai.enquete.dto;

import java.util.Date;

/**
 * Created by Mr Menezes on 01/10/2015.
 */
public class EnqueteDTO {

    private int id;
    private String resposta;
    private Date data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnqueteDTO(String resposta) {
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}


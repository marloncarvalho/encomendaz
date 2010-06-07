/*
 *  This file is part of Rastreador.
 *
 *  Rastreador is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Rastreador is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Rastreador.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.encomendaz.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Entidade que representa uma Situação do Rastreio.
 *
 * @author Marlon Silva Carvalho
 */
@Entity
@Table
public class SituacaoRastreio implements com.googlecode.mastercrud.entity.Entity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="acao")
    private String acao;

    @Column(name="detalhes")
    private String detalhes;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="dataCadastro")
    private Date data;

    @Column(name="localizacao")
    private String local;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_RASTREIO")
    private Rastreio rastreio;

    /**
     * @return the acao
     */
    public String getAcao() {
        return acao;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     * @return the detalhes
     */
    public String getDetalhes() {
        return detalhes;
    }

    /**
     * @param detalhes the detalhes to set
     */
    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the rastreio
     */
    public Rastreio getRastreio() {
        return rastreio;
    }

    /**
     * @param rastreio the rastreio to set
     */
    public void setRastreio(Rastreio rastreio) {
        this.rastreio = rastreio;
    }
}

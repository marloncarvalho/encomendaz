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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Entidade que representa um Rastreio.
 *
 * @author Marlon Silva Carvalho
 */
@Entity
@Table
@NamedQueries(
    {
        @NamedQuery(query="select rastreio from Rastreio rastreio where rastreio.codigo = :rastreio", name="obterRastreioPorCodigo")
    }
)
public class Rastreio implements com.googlecode.mastercrud.entity.Entity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="codigo")
    private String codigo;

    @Column(name="dataCadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;

    @OneToMany(fetch=FetchType.EAGER)
    @Cascade({CascadeType.DELETE_ORPHAN, CascadeType.SAVE_UPDATE})
    @JoinColumn(name="ID_RASTREIO")
    private List<SituacaoRastreio> situacoes = new ArrayList<SituacaoRastreio>();

    @Column(name="entregue")
    private boolean entregue;

    public SituacaoRastreio getUltimaSituacao() {
        SituacaoRastreio srUltimo = null;
        for(SituacaoRastreio sr:situacoes) {
            if ( srUltimo == null ) {
                srUltimo = sr;
            } else {
                if ( srUltimo != null && srUltimo.getData() != null && srUltimo.getData().before(sr.getData()) ) {
                    srUltimo = sr;
                }
            }
        }
        return srUltimo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the situacoes
     */
    public List<SituacaoRastreio> getSituacoes() {
        return situacoes;
    }

    /**
     * @param situacoes the situacoes to set
     */
    public void setSituacoes(List<SituacaoRastreio> situacoes) {
        this.situacoes = situacoes;
    }

    /**
     * @return the entregue
     */
    public boolean isEntregue() {
        return entregue;
    }

    /**
     * @param entregue the entregue to set
     */
    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }
}

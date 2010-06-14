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
package com.googlecode.encomendaz.businesscontrollers.implementors;

import com.googlecode.mastercrud.bc.simple.SimpleBC;
import com.googlecode.mastercrud.exceptions.BCException;
import com.googlecode.mastercrud.exceptions.DAOException;
import com.googlecode.mastercrud.persistence.GenericDAO;
import com.googlecode.encomendaz.entidades.Rastreio;
import com.googlecode.encomendaz.persistence.implementors.RastreioDAOImpl;
import com.googlecode.encomendaz.businesscontrollers.Rastreador;
import com.googlecode.encomendaz.entidades.SituacaoRastreio;
import com.googlecode.encomendaz.persistence.RastreioDAO;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.correios.Rastreamento;
import org.alfredlibrary.utilitarios.correios.RegistroRastreamento;

/**
 * Classe de Negócio para a entidade de Rastreio.
 *
 * @author Marlon Silva Carvalho
 * @since 1.0
 */
public class RastreadorImpl extends SimpleBC<Rastreio> implements Rastreador {
    private RastreioDAO dao = new RastreioDAOImpl();

    @Override
    public GenericDAO<Rastreio> getDao() {
        return dao;
    }

    @Override
    public List<Rastreio> obterRastreiosAtivos() throws BCException {
        try {
            return dao.obterRastreiosAtivos();
        } catch (DAOException ex) {
            Logger.getLogger(RastreadorImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BCException(ex);
        }
    }

    @Override
    public List<Rastreio> obterTodosRastreios() throws BCException {
        try {
            return dao.obterTodosRastreios();
        } catch (DAOException ex) {
            Logger.getLogger(RastreadorImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BCException(ex);
        }
    }

    @Override
    public Rastreio atualizarRastreios(String codigo) throws BCException {
        if ( codigo == null || "".equals(codigo) ) {
            throw new BCException("Código não pode ser nulo.");
        }

        Collection<RegistroRastreamento> rastreios = null;
        try {
            rastreios = Rastreamento.rastrear(codigo);
        } catch (AlfredException ae) {
            throw new BCException(ae);
        }

        // Obter o Rastreio e limpar as situações.
        // Isto, caso exista. Senão, precisa criar um novo rastreio.
        Rastreio rastreio = this.obterRastreioPorCodigo(codigo);
        if ( rastreio == null ) {
            rastreio = new Rastreio();
            rastreio.setCodigo(codigo);
            rastreio.setDataCadastro(new Date());
            this.insert(rastreio);
        } else {
            rastreio.getSituacoes().clear();
            this.update(rastreio);
        }

        try {
            
            for(RegistroRastreamento rr : rastreios ) {
                SituacaoRastreio sr = new SituacaoRastreio();
                if ( "entregue".equals(rr.getAcao().toLowerCase()) ) {
                    rastreio.setEntregue(true);
                }
                sr.setAcao(rr.getAcao());
                sr.setData(rr.getDataHora());
                sr.setDetalhes(rr.getDetalhe());
                sr.setLocal(rr.getLocal());
                sr.setRastreio(rastreio);
                rastreio.getSituacoes().add(sr);
            }
        } catch ( AlfredException ae) {
            throw new BCException(ae);
        }
        this.update(rastreio);
        return rastreio;
    }

    @Override
    public Rastreio obterRastreioPorCodigo(String codigo) throws BCException {
        try {
            return dao.obterRastreioPorCodigo(codigo);
        } catch (DAOException ex) {
            Logger.getLogger(RastreadorImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BCException(ex);
        }
    }

    @Override
    public List<Rastreio> obterRastreiosInativos() throws BCException {
         try {
            return dao.obterRastreiosInativos();
        } catch (DAOException ex) {
            Logger.getLogger(RastreadorImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BCException(ex);
        }
    }

    @Override
    public String atualizarRastreiosAtivos() throws BCException {
        List<Rastreio> rastreios = obterRastreiosAtivos();
        StringBuilder sb = new StringBuilder();
        for(Rastreio rastreio:rastreios) {
            int total = rastreio.getSituacoes().size();
            Rastreio r2 = atualizarRastreios(rastreio.getCodigo());
            if ( total != r2.getSituacoes().size() ) {
                sb.append("[");
                sb.append(r2.getCodigo());
                sb.append(" - ");
                sb.append(r2.getUltimaSituacao().getAcao());
                sb.append("], ");
            }
        }
        return sb.toString();
    }


}
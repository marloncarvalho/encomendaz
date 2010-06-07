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
package com.googlecode.encomendaz.persistence.implementors;

import com.googlecode.mastercrud.exceptions.DAOException;
import com.googlecode.mastercrud.persistence.Criterias;
import com.googlecode.mastercrud.persistence.hibernate.DAOHibernate;
import com.googlecode.encomendaz.entidades.Rastreio;
import com.googlecode.encomendaz.persistence.RastreioDAO;
import java.util.List;

/**
 * Classe de Persistência para a entidade de Rastreio.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0
 */
public class RastreioDAOImpl extends DAOHibernate<Rastreio> implements RastreioDAO { 

    @Override
    public Rastreio obterRastreioPorCodigo(String codigo) throws DAOException {
        String query = "obterRastreioPorCodigo";
        Criterias c = new Criterias();
        c.setMaxResults(1);
        c.setQuery(query);
        c.getParameters().put("rastreio", codigo);
        List<Rastreio> list = super.listByCriteria(c).getResult();
        if ( list.size() > 0 ) {
            return (Rastreio)list.iterator().next();
        }
        return null;
    }

    @Override
    public List<Rastreio> obterRastreiosAtivos() throws DAOException {
        String query = "obterRastreiosAtivos";
        Criterias c = new Criterias();
        c.setQuery(query);
        List<Rastreio> list = super.listByCriteria(c).getResult();
        return list;
    }

    @Override
    public List<Rastreio> obterRastreiosInativos() throws DAOException {
        String query = "obterRastreiosInativos";
        Criterias c = new Criterias();
        c.setQuery(query);
        List<Rastreio> list = super.listByCriteria(c).getResult();
        return list;
    }

}
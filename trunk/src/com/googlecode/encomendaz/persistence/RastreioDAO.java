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
package com.googlecode.encomendaz.persistence;

import com.googlecode.mastercrud.exceptions.DAOException;
import com.googlecode.mastercrud.persistence.GenericDAO;
import com.googlecode.encomendaz.entidades.Rastreio;
import java.util.List;

/**
 * Interface que define os métodos de DAOs para Rastreio.
 *
 * @author Marlon Silva Carvalho
 * @since 1.0
 */
public interface RastreioDAO extends GenericDAO<Rastreio>{

    Rastreio obterRastreioPorCodigo(String codigo) throws DAOException;
    List<Rastreio> obterRastreiosAtivos() throws DAOException;
    List<Rastreio> obterRastreiosInativos() throws DAOException;

}
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
package com.googlecode.encomendaz.businesscontrollers;

import com.googlecode.mastercrud.bc.GenericBC;
import com.googlecode.mastercrud.exceptions.BCException;
import com.googlecode.encomendaz.entidades.Rastreio;
import java.util.List;

/**
 * Classe de Negócio para a entidade de Rastreio.
 * 
 * @author Marlon Silva Carvalho
 * @since 1.0
 */
public interface Rastreador extends GenericBC<Rastreio>{
    String atualizarRastreiosAtivos() throws BCException;
    List<Rastreio> obterRastreiosInativos() throws BCException;
    List<Rastreio> obterRastreiosAtivos() throws BCException;
    Rastreio atualizarRastreios(String codigo) throws BCException;
    Rastreio obterRastreioPorCodigo(String codigo) throws BCException;
}
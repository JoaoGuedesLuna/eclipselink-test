package std.guedes.eclipselinktest.dao;

import std.guedes.eclipselinktest.dao.impl.PersonDAOImpl;
import std.guedes.eclipselinktest.exceptions.DatabaseException;

/**
 * Classe que disponibiliza métodos que retornam subtipos de ‘interfaces’ DAO das entidades do banco de dados.
 */
public class FactoryDAO {

    public static PersonDAO createPersonDAO() throws DatabaseException {
        return new PersonDAOImpl();
    }

}

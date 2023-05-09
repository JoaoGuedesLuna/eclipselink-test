package std.guedes.eclipselinktest.exceptions;

/**
 * Exceção para métodos que fazem operações com o banco de dados.
 */
public class DatabaseException extends Exception{

    public DatabaseException(String message) {
        super(message);
    }

}

package std.guedes.eclipselinktest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EclipseLinkTestRun {

    /**
     * Verifica a conexão com o banco de dados.
     */
    private static void verifyConnection() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EclipseLinkPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) {

    }

}

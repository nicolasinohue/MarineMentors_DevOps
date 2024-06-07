package br.com.fiap.servers;

import br.com.fiap.classes.Eventos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEventos {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    //Método para inicializar o EntityManager
    private static void initEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        entityManager = entityManagerFactory.createEntityManager();
    }

    //Método para fechar o EntityManager
    private static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            initEntityManager(); // Inicializa o EntityManager aqui
            serverSocket = new ServerSocket(1521);
            System.out.println("Servidor Eventos iniciado na porta 1521...");
            while (true) {
                Socket socket = serverSocket.accept();
                try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                    int id = inputStream.readInt();
                    Eventos eventos = buscarPorId(id);
                    outputStream.writeObject(eventos);
                    outputStream.flush();
                }
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            closeEntityManager(); // Certifique-se de fechar o EntityManager ao final
        }
    }

    // Método para buscar informações no banco de dados
    private static Eventos buscarPorId(int id) {
        Eventos eventos = null;
        try {
            // Inicia uma transação
            entityManager.getTransaction().begin();

            // Consulta JPQL para buscar pelo ID
            Query query = entityManager.createQuery("SELECT p FROM Eventos p WHERE p.id = :id");
            query.setParameter("id", id);
            eventos = (Eventos) query.getSingleResult();

            // Commita a transação
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Se houver uma exceção, rollback na transação
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return eventos;
    }
}
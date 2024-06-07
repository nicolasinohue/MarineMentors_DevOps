package br.com.fiap.servers;
import br.com.fiap.classes.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner declarado fora do try
        try {
            // Seletor de opções
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| AVISO: ESPERE A CONCLUSÃO DA CONEXÃO DO BANCO DE DADOS! |");
            System.out.println("+----+----------------------------------------------------+");
            System.out.println("| N° | Classe a ser escolhida                             |");
            System.out.println("+----+----------------------------------------------------+");
            System.out.println("| 01 | Artigos                                            |");
            System.out.println("| 02 | Eventos                                            |");
            System.out.println("| 03 | Usuarios                                           |");
            System.out.println("+----+----------------------------------------------------+");
            System.out.print("-> Selecione a opção de (1-3): ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha para evitar problemas na leitura seguinte
            Thread serverThread = null;

            switch (opcao) {
                case 1 -> {
                    serverThread = new Thread(() -> ServidorArtigo.main(args));
                    serverThread.start();
                }
                case 2 -> {
                    serverThread = new Thread(() -> ServidorEventos.main(args));
                    serverThread.start();
                }
                case 3 -> {
                    serverThread = new Thread(() -> ServidorUsuario.main(args));
                    serverThread.start();
                }
                default -> {
                    System.out.println("Opção inválida.");
                    return; // Sai do método se a opção for inválida
                }
            }

            // Aguarda um breve momento para garantir que o servidor tenha tempo de iniciar
            try {
                Thread.sleep(45000); // Pausa por 10.5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (opcao) {
                case 1 -> {
                    System.out.print("-> Digite o ID do Artigo: ");
                    int idArtigo = scanner.nextInt();
                    try (Socket socket = new Socket("localhost", 1521);
                         ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

                        // Envia o ID ao servidor
                        outputStream.writeInt(idArtigo);
                        outputStream.flush();

                        // Recebe e exibe informações do servidor
                        Artigo artigo = (Artigo) inputStream.readObject();
                        System.out.println(artigo);
                    }
                }
                case 2 -> {
                    System.out.print("-> Digite o ID do Evento: ");
                    int idEvento = scanner.nextInt();
                    try (Socket socket = new Socket("localhost", 1521);
                         ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

                        // Envia o ID ao servidor
                        outputStream.writeInt(idEvento);
                        outputStream.flush();

                        // Recebe e exibe informações do servidor
                        Eventos eventos = (Eventos) inputStream.readObject();
                        System.out.println(eventos);
                    }
                }
                case 3 -> {
                    System.out.print("-> Digite o ID do Usuario: ");
                    int idUsuario = scanner.nextInt();
                    try (Socket socket = new Socket("localhost", 1521);
                         ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

                        // Envia o ID ao servidor
                        outputStream.writeInt(idUsuario);
                        outputStream.flush();

                        // Recebe e exibe informações do servidor
                        Usuario usuario = (Usuario) inputStream.readObject();
                        System.out.println(usuario);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Fecha o scanner
        }
    }
}
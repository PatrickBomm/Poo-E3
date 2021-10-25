import java.io.*;
import java.text.ParseException;
import java.util.*;

public class App {
    static Scanner sc = new Scanner(System.in);

    static List<Processamento> process = new ArrayList<Processamento>();
    static List<Processamento> salvos = new ArrayList<Processamento>();

    public static void main(String[] args) throws IOException, ParseException {
        menu();
    }

    public static void menu() throws IOException, ParseException {
        boolean cond = true;

        while (cond) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n>>>Menu Principal<<<");
            System.out.println(
                    "Escolha a opção desejada:\n0- Sair.\n1- Carregar os dados.\n2- Apresentar os dados.\n3- Consultar por endereço.\n4- Salvar os dados da consulta.");
            int opc = sc.nextInt();
            switch (opc) {
            case 0:
                cond = false;
                System.out.println("\n!!!Saindo!!!");
                break;
            case 1:
                load();
                break;

            case 2:
                if (process.size() > 0)
                    System.out.println(process);
                else
                    System.out.println("\nNenhum dado foi carregado ainda!");

                break;

            case 3:
                if (process.size() > 0)
                    search();
                else
                    System.out.println("\nNenhum dado foi carregado ainda!");
                break;

            case 4:
                if (process.size() > 0)
                    gravarArquivo();
                else
                    System.out.println("\nNenhum dado foi carregado ainda!");
                break;

            }

        }

    }

    public static void load() throws ParseException {

        System.out.println("\nArquivos Disponíveis: ");

        File raiz = new File("assets");

        for (File f : raiz.listFiles()) {
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
        System.out.println("\nDigite o nome do arquivo: ");

        String path = sc.next();
        path = "assets/" + path;
        // tenta ler o arquivo csv
        try (BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            String linhaProcessamento = bReader.readLine();
            linhaProcessamento = bReader.readLine();

            // continua lendo o arquivo ate que chegue em uma linha vazia
            while (linhaProcessamento != null) {

                String[] arrayProcessamento = linhaProcessamento.split(";");

                // cada atributo de obito equivale a um indice da linha
                String dataExtracao = arrayProcessamento[0];
                String dataObito = arrayProcessamento[2];
                String dataNascimento = arrayProcessamento[3];
                String sexo = arrayProcessamento[5];
                String raca = arrayProcessamento[6];
                String tipoObito = arrayProcessamento[9];
                String obitoPrematuro = arrayProcessamento[12];

                // cria um objeto internacao para cada linha e informa os atributos acima no
                // construtor (ver Processamento.java)
                Processamento processamento = new Processamento(dataExtracao, dataObito, dataNascimento, sexo, raca,
                        obitoPrematuro, tipoObito);

                // adiciona os objetos internao criados na lista de processamento
                process.add(processamento);
                linhaProcessamento = bReader.readLine();

            }

        } catch (IOException e) {
            // caso o arquivo nao seja encontrado, exibe esta mensagem
            System.err.println("\nArquivo não encontrado. Verifique o caminho especificado.");

        }
        if (process.size() > 0) {
            System.out.println("\nDados carregados!");
            System.out.println("Tamanho do arquivo: " + process.size());
        }

    }

    public static void search() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\nDigite o que deseja pesquisar:\n1- Data de nascimento.\n2- Data de óbito.\n3- Raça.\n4- Sexo.\n0- Voltar ");
        int opc = sc.nextInt();
        switch (opc) {
        case 1:
            System.out.println("Digite a data: ");
            String dataNas = sc.next();
            for (Processamento p : process) {
                if (dataNas.equals(p.getDataNascimento())) {
                    System.out.println(p);
                    salvos.add(p);
                }
            }
            break;
        case 2:
            System.out.println("Digite a data: ");
            String dataObi = sc.next();
            for (Processamento p : process) {
                if (dataObi.equals(p.getDataObito())) {
                    System.out.println(p);
                    salvos.add(p);
                }
            }
            break;

        case 3:
            System.out.println("Digite a raça:");
            String raca = sc.next().toUpperCase();
            for (Processamento p : process) {
                if (raca.equals(p.getRaca())) {
                    System.out.println(p);
                    salvos.add(p);
                }

            }
            break;

        case 4:
            System.out.println("Digite o sexo: ");
            String sexo = sc.next().toUpperCase();
            for (Processamento p : process) {
                if (sexo.equals(p.getSexo())) {
                    System.out.println(p);
                    salvos.add(p);
                }
            }
            break;

        case 0:
            System.out.println("Voltando ao menu principal!");
            menu();
            break;
        }

    }

    public static void gravarArquivo() throws IOException {
        if (salvos.size() > 0) {
            System.out.println("\nDigite o local para salvar o arquivo: ");
            FileWriter arq = new FileWriter(sc.next() + ".txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(salvos);

            arq.close();
            System.out.println("\nSalvo com sucesso!");
        } else {
            System.out.println("\nNada foi pesquisado ainda!");
        }
    }
}

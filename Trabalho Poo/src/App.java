import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
public class App {
    static Scanner sc = new Scanner(System.in);

    // cria uma lista de objetos do tipo internacao (cada linha do csv corresponde a
    // uma internacao)
    static List<Processamento> processamento = new ArrayList<Processamento>();
    // instancia o calendario Gregoriano (o calendario gregoriano eh o calendario
    // que utilizamos com meses de janeiro a dezembro e dias da semana de domingo
    // ate sabado)
    static Calendar calendar = new GregorianCalendar();

    static SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat formataDataSemHorario = new SimpleDateFormat("yyyy-MM-dd");

    // constante com o caminho para o arquivo a ser lido
    static final String path = "assets/sim_obitos.csv";
    
    public static void main(String[] args) {
        boolean isWorking = false;

        // tenta ler o arquivo csv
        try (BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            String linhaProcessamento = bReader.readLine();
            // evita que os headers sejam lidos e incluidos na lista, por isso le 2 vezes,
            // visto que a primeira linha contem apenas os headers do csv
            linhaProcessamento = bReader.readLine();

            // continua lendo o arquivo ate que chegue em uma linha vazia
            while (linhaProcessamento != null) {
                // cada linha do csv se torna um array e cada indice eh separado por ";"
                String[] arrayProcessamento = linhaProcessamento.split("");

                // cada atributo da internacao equivale a um indice da linha
                String dataExtracao = arrayProcessamento[0];
                String dataObito = arrayProcessamento[2];
                String dataNascimento = arrayProcessamento[3];
                String sexo = arrayProcessamento[5];
                String raca = arrayProcessamento[6];
                String tipoObito = arrayProcessamento[9];
                String obitoPrematuro = arrayProcessamento[12];

                // cria um objeto internacao para cada linha e informa os atributos acima no
                // construtor (ver Processamento.java)
                Processamento processamento = new Processamento(dataExtracao, dataObito, dataNascimento, sexo, raca, obitoPrematuro, tipoObito);

                // adiciona os objetos internao criados na lista de internacoes
                linhaProcessamento = bReader.readLine();

                // se nao houver erros, isWorking passa a ser true
                isWorking = true;
            }

        } catch (IOException e) {
            // caso o arquivo nao seja encontrado, exibe esta mensagem
            System.err.println("Arquivo não encontrado. Verifique o caminho especificado.");
        }

        if (isWorking) {
            menu();
        }
    }

    public static void menu(){
        System.out.println("Certo");
        System.out.println(App.processamento);
    }

}


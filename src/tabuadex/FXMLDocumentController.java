package tabuadex;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Sávio Andres
 */
public class FXMLDocumentController implements Initializable {

    Tabuadex tabuadex = new Tabuadex();
    Dados dados = new Dados();
    int nivel;
    int tentativas;
    int acertos;
    int erros;
    ArrayList nivel_array = new ArrayList();
    ArrayList n1_array = new ArrayList();
    ArrayList operacao_array = new ArrayList();
    ArrayList n2_array = new ArrayList();
    ArrayList respostaUsuario_array = new ArrayList();
    ArrayList resultado_array = new ArrayList();
    
    @FXML
    private AnchorPane tela_bemvindo;
    @FXML
    private AnchorPane tela_principal;
    @FXML
    private AnchorPane tela_historico;
    @FXML
    private AnchorPane tela_arquivo;
    @FXML
    private Label nTentativas;
    @FXML
    private Label nAcertos;
    @FXML
    private Label nErros;
    @FXML
    private Button btn_1;
    @FXML
    private Button btn_2;
    @FXML
    private Button btn_3;
    @FXML
    private Button btn_4;
    @FXML
    private Label n1;
    @FXML
    private Label simboloOperacao;
    @FXML
    private Label n2;
    @FXML
    private Label resuladoDeclarado;
    @FXML
    private TextField campoDigitavel;
    @FXML
    private ComboBox<String> operacao;
    @FXML
    private TextArea txtarea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        operacao.getItems().setAll("Somar", "Subtrair", "Multiplicar", "Dividir");
        escolhaTela("bemvindo");
        //txtarea.setWrapText(true);
        //txtarea.setEditable(false);
    }

    @FXML
    private void click_niv_1(ActionEvent event) {
        gerarNumeros(1);
        btn_1.setVisible(false);
        btn_2.setVisible(true);
        btn_3.setVisible(true);
        btn_4.setVisible(true);
    }

    @FXML
    private void click_niv_2(ActionEvent event) {
        gerarNumeros(2);
        btn_1.setVisible(true);
        btn_2.setVisible(false);
        btn_3.setVisible(true);
        btn_4.setVisible(true);
    }

    @FXML
    private void click_niv_3(ActionEvent event) {
        gerarNumeros(3);
        btn_1.setVisible(true);
        btn_2.setVisible(true);
        btn_3.setVisible(false);
        btn_4.setVisible(true);
    }

    @FXML
    private void click_niv_4(ActionEvent event) {
        gerarNumeros(4);
        btn_1.setVisible(true);
        btn_2.setVisible(true);
        btn_3.setVisible(true);
        btn_4.setVisible(false);
    }

    @FXML
    private void salvarArquivo(ActionEvent event) {
        if(tentativas == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sem conteúdo");
            alert.setHeaderText("Você ainda não fez nenhum cálculo");
            alert.setContentText("Favor, primeiro responda algum cálculo para não salvar o arquivo em branco");
            alert.showAndWait();
        }else{
           String textos = "";
            for (int i = 0; i < tentativas; i++) {
                textos = i + "º calculo, nível: " + nivel_array.get(i) + ", formúla: " + n1_array.get(i) + " " + operacao_array.get(i) + " " + n2_array.get(i) + " = " + resultado_array.get(i) + ", sua resposta: " + respostaUsuario_array.get(i) + "<br>" + textos;
            }
            String arquivo = "]Relatório Tabuadex'\n'" + dados.dataHora() + " do arquivo gerado./nNúmero de tentativas: /\n/"
                    + nTentativas.getText() + ", Número de acertos: " + nAcertos.getText() + ", Número de erros: " + nErros.getText() + "<br><br>" + textos + "[";
            
            tabuadex.janelaSalvarArquivo(txtarea.getText()); 
        }
    }

    @FXML
    private void abrirArquivo(ActionEvent event) {
        File file = tabuadex.janelaAbrirArquivo();
        if(file != null){
            String relatorio = lendoArquivo(file);
            txtarea.setText(relatorio);
            escolhaTela("arquivo");
        }
    }

    @FXML
    private void fechar(ActionEvent event) {
        tabuadex.fecharPrograma();
    }

    @FXML
    private void calcular(ActionEvent event) {
        int resulN = 0;
        int nGerado1 = Integer.parseInt(n1.getText());
        int nGerado2 = Integer.parseInt(n2.getText());
        n1_array.add(nGerado1);
        n2_array.add(nGerado2);

        if (campoDigitavel.getText().equals("")) {
            campoDigitavel.setText("0");
        }
        int resultadoDoUsuario = Integer.parseInt(campoDigitavel.getText());
        respostaUsuario_array.add(resultadoDoUsuario);

        try {
            if (operacao.getValue().equals("Somar")) {
                resulN = nGerado1 + nGerado2;
                operacao_array.add("+");
            }
            if (operacao.getValue().equals("Subtrair")) {
                resulN = nGerado1 - nGerado2;
                operacao_array.add("−");
            }
            if (operacao.getValue().equals("Multiplicar")) {
                resulN = nGerado1 * nGerado2;
                operacao_array.add("×");
            }
            if (operacao.getValue().equals("Dividir")) {
                resulN = nGerado1 / nGerado2;
                operacao_array.add("÷");
            }
        } catch (Exception e) {
            resulN = nGerado1 + nGerado2;
            operacao_array.add("+");
        }
        resultado_array.add(resulN);

        if (resultadoDoUsuario == resulN) {
            resuladoDeclarado.setText("Acertou");
            int somaErrAcer = Integer.parseInt(nAcertos.getText()) + 1;
            nAcertos.setText("" + somaErrAcer);
            acertos = somaErrAcer;
        } else {
            resuladoDeclarado.setText("Errou, o resultado era " + resulN);
            int somaErrAcer = Integer.parseInt(nErros.getText()) + 1;
            nErros.setText("" + somaErrAcer);
            erros = somaErrAcer;
        }
        int somaTentativas = Integer.parseInt(nAcertos.getText()) + Integer.parseInt(nErros.getText());
        nTentativas.setText("" + somaTentativas);
        tentativas = somaTentativas;
        
        campoDigitavel.setText("");
        
        nivel_array.add(nivel);
        gerarNumeros(nivel);
    }
    
    public void escolhaTela(String tela){
        switch (tela) {
            case "bemvindo":
                tela_bemvindo.setVisible(true);
                tela_principal.setVisible(false);
                tela_historico.setVisible(false);
                tela_arquivo.setVisible(false);
                break;
            case "principal":
                tela_bemvindo.setVisible(false);
                tela_principal.setVisible(true);
                tela_historico.setVisible(false);
                tela_arquivo.setVisible(false);
                break;
            case "historico":
                tela_bemvindo.setVisible(false);
                tela_principal.setVisible(false);
                tela_historico.setVisible(true);
                tela_arquivo.setVisible(false);
                break;
            case "arquivo":
                tela_bemvindo.setVisible(false);
                tela_principal.setVisible(false);
                tela_historico.setVisible(false);
                tela_arquivo.setVisible(true);
                break;
        }
    }
    
    private void gerarNumeros(int nivelG) {
        nivel = nivelG;
        Random aleatorio = new Random();
        int a = 0;
        int b = 0;
        switch (nivelG) {
            case 1:
                a = aleatorio.nextInt(10);
                b = aleatorio.nextInt(10);
                break;
            case 2:
                a = aleatorio.nextInt(100);
                b = aleatorio.nextInt(100);
                break;
            case 3:
                a = aleatorio.nextInt(1000);
                b = aleatorio.nextInt(1000);
                break;
            case 4:
                a = aleatorio.nextInt(10000);
                b = aleatorio.nextInt(10000);
                break;
        }
        if (a < b) {
            n1.setText("" + b);
            n2.setText("" + a);
        } else {
            n1.setText("" + a);
            n2.setText("" + b);
        }
        escolhaTela("principal");
    }

    @FXML
    private void operacaoArit(ActionEvent event) {
        if (operacao.getValue().equals("Somar")) {
            simboloOperacao.setText("+");
        }
        if (operacao.getValue().equals("Subtrair")) {
            simboloOperacao.setText("−");
        }
        if (operacao.getValue().equals("Multiplicar")) {
            simboloOperacao.setText("×");
        }
        if (operacao.getValue().equals("Dividir")) {
            simboloOperacao.setText("÷");
        }
    }

    @FXML
    private void abrirHistorico(ActionEvent event) {
        escolhaTela("historico");
    }
    
    public String lendoArquivo(File caminho) {
        List<String> linha = new ArrayList<>();
        String a = "";
        Path arquivo = Paths.get(caminho + "");
        if (Files.exists(arquivo)) {
            try {
                linha = Files.readAllLines(arquivo, Charset.defaultCharset());
                a = linha + a;
            } catch (IOException ex) {
                //JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Erro", JOptionPane.PLAIN_MESSAGE);
            }
        }
        return a;
    }
    
}

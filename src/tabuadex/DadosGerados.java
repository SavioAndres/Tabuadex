package tabuadex;

import java.util.ArrayList;

/**
 *
 * @author Sávio Andres
 */
public class DadosGerados {
    
    private int tentativas;
    private int acertos;
    private int erros;
    public ArrayList nivel = new ArrayList();
    public ArrayList n1 = new ArrayList();
    public ArrayList operacao = new ArrayList();
    public ArrayList n2 = new ArrayList();
    public ArrayList respostaUsuario = new ArrayList();
    public ArrayList resultado = new ArrayList();

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public int getAcertos() {
        return acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
    
}

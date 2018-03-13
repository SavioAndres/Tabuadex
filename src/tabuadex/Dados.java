package tabuadex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

/**
 *
 * @author Sávio Andres
 */
public class Dados {
    
    public String dataHora(){
        java.util.Date agora = new java.util.Date();;
        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        String data = formata.format(agora);
        formata = new SimpleDateFormat("HH:mm");
        String hora = formata.format(agora);
        String dataEhora = "Data: " + data + " hora: " + hora;

        return dataEhora;
    }
    
    public static void EscreverArquivo(String caminho, String json) {
        Path arquivo = Paths.get(caminho);
        if (!Files.exists(arquivo)) {
            BufferedWriter bw = null;
            try {
                Files.createFile(arquivo);
                bw = new BufferedWriter(new FileWriter(arquivo.toFile(), true));
                bw.write(json);
                //JOptionPane.showMessageDialog(null, "O arquivo foi salvo com sucesso", "Salvo", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                //System.out.println("Arquivo com problema");
            } finally {
                try {
                    bw.close();
                } catch (IOException ex) {
                    //System.out.println("Não foi possível fechar");
                }
            }
        } else {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(arquivo.toFile(), true));
                bw.newLine();
                bw.write(json);
                //JOptionPane.showMessageDialog(null, "O arquivo foi alterado com sucesso", "Salvo", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                //System.out.println("Não foi possível abrir o arquivo");
            } finally {
                try {
                    bw.close();
                } catch (IOException ex) {
                    //System.out.println("Problema no arquivo");
                }
            }
        }
    }
    
    
    
}

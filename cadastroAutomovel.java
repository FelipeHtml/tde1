package cadastro;


import java.io.*;
import java.util.*;

public class cadastroAutomovel {
    private List<automovel> automoveis = new ArrayList<>();
    private final String arquivo = "automoveis.txt";

    public cadastroAutomovel() {
        carregarDoArquivo();
    }

    public boolean inserirAutomovel(automovel a) {
        if (buscarPorPlaca(a.getPlaca()) != null) {
            return false;
        }
        automoveis.add(a);
        return true;
    }

    public boolean removerAutomovel(String placa) {
        automovel a = buscarPorPlaca(placa);
        if (a != null) {
            automoveis.remove(a);
            return true;
        }
        return false;
    }

    public boolean alterarAutomovel(String placa, String modelo, String marca, int ano, double valor) {
        automovel a = buscarPorPlaca(placa);
        if (a != null) {
            a.setModelo(modelo);
            a.setMarca(marca);
            a.setAno(ano);
            a.setValor(valor);
            return true;
        }
        return false;
    }

    public automovel buscarPorPlaca(String placa) {
        for (automovel a : automoveis) {
            if (a.getPlaca().equalsIgnoreCase(placa)) {
                return a;
            }
        }
        return null;
    }

    public List<automovel> listarOrdenado(String criterio) {
        List<automovel> copia = new ArrayList<>(automoveis);
        switch (criterio.toLowerCase()) {
            case "placa":
                copia.sort(Comparator.comparing(automovel::getPlaca));
                break;
            case "modelo":
                copia.sort(Comparator.comparing(automovel::getModelo));
                break;
            case "marca":
                copia.sort(Comparator.comparing(automovel::getMarca));
                break;
        }
        return copia;
    }

    public void salvarNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (automovel a : automoveis) {
                bw.write(a.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public void carregarDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 5) {
                    String placa = dados[0];
                    String modelo = dados[1];
                    String marca = dados[2];
                    int ano = Integer.parseInt(dados[3]);
                    double valor = Double.parseDouble(dados[4]);
                    automoveis.add(new automovel(placa, modelo, marca, ano, valor));
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo de dados não encontrado. Será criado ao salvar.");
        }
    }
}
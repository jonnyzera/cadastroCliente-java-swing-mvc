package br.com.cadastroclientes.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.com.cadastroclientes.model.Cliente;

public class ClienteController {
    private List<Cliente> listaClientes = new ArrayList<>();
    private static final String ARQUIVO = "clientes.txt";

    public ClienteController() {
        carregarClientes();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco, String email) {
        Cliente cliente = new Cliente(nome, cpf, telefone, endereco, email);
        listaClientes.add(cliente);
        System.out.println("Cliente cadastrado: " + nome);
        salvarClientes(); // Salva automaticamente ao cadastrar
    }

    public List<Cliente> listarClientes() {
        return listaClientes;
    }

    // Mude para público se quiser chamar direto da View
    public void salvarClientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Cliente c : listaClientes) {
                writer.write(c.getNome() + ";" + c.getCpf() + ";" + c.getTelefone() + ";" + c.getEndereco() + ";"
                        + c.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarClientes() {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    Cliente c = new Cliente(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    listaClientes.add(c);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

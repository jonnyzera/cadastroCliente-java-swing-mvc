package br.com.cadastroclientes.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import br.com.cadastroclientes.controller.ClienteController;

public class TelaCliente extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone, txtEndereco, txtEmail;
    private JButton btnCadastrar, btnListar;

    public TelaCliente(ClienteController controller) {
        setTitle("Cadastro de Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel principal com borda (margens)
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(20, 30, 20, 30)); // (topo, esquerda, baixo, direita)

        // ===== Painel de Campos =====
        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 5, 5));

        painelCampos.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCampos.add(txtNome);

        painelCampos.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painelCampos.add(txtCpf);

        painelCampos.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelCampos.add(txtTelefone);

        painelCampos.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelCampos.add(txtEndereco);

        painelCampos.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painelCampos.add(txtEmail);

        add(painelCampos, BorderLayout.CENTER);

        // ===== Painel dos Botões na parte inferior =====
        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));

        btnCadastrar = new JButton("Cadastrar");
        btnListar = new JButton("Listar Clientes");

        // Aumenta a altura dos botões
        btnCadastrar.setPreferredSize(new Dimension(150, 50));
        btnListar.setPreferredSize(new Dimension(150, 50));

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnListar);

        add(painelBotoes, BorderLayout.SOUTH);

        // ===== Ações =====
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String telefone = txtTelefone.getText();
            String endereco = txtEndereco.getText();
            String email = txtEmail.getText();

            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha ao menos Nome e CPF!");
                return;
            }

            controller.cadastrarCliente(nome, cpf, telefone, endereco, email);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos();
        });

        btnListar.addActionListener(e -> {
            java.util.List<br.com.cadastroclientes.model.Cliente> clientes = controller.listarClientes();
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (br.com.cadastroclientes.model.Cliente c : clientes) {
                    sb.append("Nome: ").append(c.getNome()).append("\n")
                            .append("CPF: ").append(c.getCpf()).append("\n")
                            .append("Telefone: ").append(c.getTelefone()).append("\n")
                            .append("Endereço: ").append(c.getEndereco()).append("\n")
                            .append("Email: ").append(c.getEmail()).append("\n")
                            .append("-----------------------------\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        });

        setVisible(true);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
    }
}

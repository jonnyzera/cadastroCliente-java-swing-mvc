package br.com.cadastroclientes;

import br.com.cadastroclientes.controller.ClienteController;
import br.com.cadastroclientes.view.TelaCliente;

public class Main {
    public static void main(String[] args) {
        ClienteController controller = new ClienteController();
        new TelaCliente(controller);
    }
}

package br.com.fiap.microservices.cliente.service;

import br.com.fiap.microservices.cliente.model.Cliente;
import br.com.fiap.microservices.cliente.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criar(Cliente cliente) {
        logger.info("Criando novo cliente: {}", cliente.getNome());
        // validar email duplicado
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado: "+cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        logger.info("Buscando cliente com id: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id "+id));
    }

    public List<Cliente> listarTodos() {
        logger.info("Listando todos os clientes");
        return clienteRepository.findAll();
    }

}

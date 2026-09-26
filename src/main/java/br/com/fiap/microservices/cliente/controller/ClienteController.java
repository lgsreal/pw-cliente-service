package br.com.fiap.microservices.cliente.controller;

import br.com.fiap.microservices.cliente.model.Cliente;
import br.com.fiap.microservices.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        logger.info("POST /api/clientes - Cliente: {}", cliente.getNome());
        Cliente clienteSalvo = clienteService.criar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorID(@PathVariable Long id) {
        logger.info("GET /api/clientes/{}", id);
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        logger.info("GET /api/clientes");
        return ResponseEntity.ok(clienteService.listarTodos());
    }
}

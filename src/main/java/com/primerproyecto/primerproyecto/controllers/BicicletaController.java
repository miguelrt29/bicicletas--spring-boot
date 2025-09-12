package com.primerproyecto.primerproyecto.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.primerproyecto.primerproyecto.dto.BicicletaDTO;
import com.primerproyecto.primerproyecto.services.BicicletaService;

@RestController
@RequestMapping("/bicicletas")
public class BicicletaController {

    @Autowired
    private BicicletaService bicicletaService;

    @GetMapping
    public ResponseEntity<List<BicicletaDTO>> listarBicicletas() {
        return ResponseEntity.ok(bicicletaService.obtenerBicicletas());
    }

    @PostMapping
    public ResponseEntity<BicicletaDTO> crearBicicleta(@RequestBody BicicletaDTO dto) {
        return ResponseEntity.ok(bicicletaService.guardarBicicleta(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BicicletaDTO> encontrarBicicleta(@PathVariable Long id) {
        BicicletaDTO dto = bicicletaService.encontrarBicicleta(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BicicletaDTO> actualizarBicicleta(@PathVariable Long id, @RequestBody BicicletaDTO dto) {
        BicicletaDTO actualizado = bicicletaService.actualizarBicicleta(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BicicletaDTO> eliminarBicicleta(@PathVariable Long id) {
        BicicletaDTO eliminado = bicicletaService.eliminarBicicleta(id);
        return eliminado != null ? ResponseEntity.ok(eliminado) : ResponseEntity.notFound().build();
    }

    // Endpoint para comprar bicicleta
    @PostMapping("/{id}/comprar")
    public ResponseEntity<String> comprarBicicleta(@PathVariable Long id, @RequestParam int cantidad) {
        String respuesta = bicicletaService.comprarBicicleta(id, cantidad);
        return ResponseEntity.ok(respuesta);
    }
}


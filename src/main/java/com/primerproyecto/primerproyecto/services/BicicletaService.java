package com.primerproyecto.primerproyecto.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primerproyecto.primerproyecto.dto.BicicletaDTO;
import com.primerproyecto.primerproyecto.entity.Bicicleta;
import com.primerproyecto.primerproyecto.repository.BicicletaRepository;

@Service
public class BicicletaService {

    @Autowired
    private BicicletaRepository bicicletaRepository;

    public List<BicicletaDTO> obtenerBicicletas() {
        return bicicletaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BicicletaDTO guardarBicicleta(BicicletaDTO dto) {
        Bicicleta bici = mapToEntity(dto);
        Bicicleta guardada = bicicletaRepository.save(bici);
        return mapToDTO(guardada);
    }

    public BicicletaDTO actualizarBicicleta(Long id, BicicletaDTO dto) {
        Optional<Bicicleta> encontrada = bicicletaRepository.findById(id);

        if (encontrada.isPresent()) {
            Bicicleta bici = encontrada.get();
            bici.setModelo(dto.getModelo());
            bici.setColor(dto.getColor());
            bici.setMarca(dto.getMarca());
            bici.setPrecio(dto.getPrecio());
            bici.setStock(dto.getStock());

            return mapToDTO(bicicletaRepository.save(bici));
        }
        return null;
    }

    public BicicletaDTO encontrarBicicleta(Long id) {
        return bicicletaRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public BicicletaDTO eliminarBicicleta(Long id) {
        return bicicletaRepository.findById(id)
                .map(bici -> {
                    bicicletaRepository.delete(bici);
                    return mapToDTO(bici);
                }).orElse(null);
    }

    public String comprarBicicleta(Long id, int cantidad) {
        Optional<Bicicleta> encontrada = bicicletaRepository.findById(id);

        if (encontrada.isPresent()) {
            Bicicleta bici = encontrada.get();
            if (bici.getStock() >= cantidad) {
                bici.setStock(bici.getStock() - cantidad);
                bicicletaRepository.save(bici);
                return "Compra realizada con Ã©xito. Stock restante: " + bici.getStock();
            } else {
                return "No hay suficiente stock disponible.";
            }
        }
        return "Bicicleta no encontrada.";
    }

    private BicicletaDTO mapToDTO(Bicicleta bici) {
        return BicicletaDTO.builder()
                .id(bici.getId())
                .modelo(bici.getModelo())
                .color(bici.getColor())
                .marca(bici.getMarca())
                .precio(bici.getPrecio())
                .stock(bici.getStock())
                .build();
    }

    private Bicicleta mapToEntity(BicicletaDTO dto) {
        Bicicleta bici = new Bicicleta();
        bici.setModelo(dto.getModelo());
        bici.setColor(dto.getColor());
        bici.setMarca(dto.getMarca());
        bici.setPrecio(dto.getPrecio());
        bici.setStock(dto.getStock());
        return bici;
    }
}
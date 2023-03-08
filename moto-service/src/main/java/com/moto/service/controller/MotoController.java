package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.servicios.MotoService;
import com.moto.service.entity.Moto;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;

	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {

		List<Moto> motos = motoService.getAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(motos);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
		Moto Moto = motoService.getMotoById(id);
		if (Moto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(Moto);

	}

	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto Moto) {
		Moto nuevoMoto = motoService.save(Moto);
		return ResponseEntity.ok(nuevoMoto);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listaMotosPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
		List<Moto> motos = motoService.byUsuarioId(usuarioId);
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(motos);

	}
}

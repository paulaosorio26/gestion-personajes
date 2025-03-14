package com.example.gestion_personajes.controller;

import com.example.gestion_personajes.model.Personaje;
import com.example.gestion_personajes.service.PersonajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:3000") // Permitir peticiones desde React
@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public List<Personaje> obtenerTodos() {
        return personajeService.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Personaje personaje) {
        try {
            Personaje nuevoPersonaje = personajeService.guardar(personaje);
            return ResponseEntity.ok(nuevoPersonaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable Integer id) {
        return personajeService.obtenerPorId(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personaje no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        personajeService.eliminar(id);
        return ResponseEntity.ok("Personaje eliminado correctamente.");
    }
}


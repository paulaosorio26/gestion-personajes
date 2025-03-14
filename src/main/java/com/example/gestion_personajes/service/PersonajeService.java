package com.example.gestion_personajes.service;

import com.example.gestion_personajes.model.Personaje;
import com.example.gestion_personajes.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    // Obtener todos los personajes
    public List<Personaje> obtenerTodos() {
        return personajeRepository.findAll();
    }

    // Obtener un personaje por ID
    public Optional<Personaje> obtenerPorId(Integer id) {
        return personajeRepository.findById(id);
    }

    // Guardar o actualizar un personaje
    public Personaje guardar(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    // Eliminar un personaje por ID
    public void eliminar(Integer id) {
        personajeRepository.deleteById(id);
    }
}


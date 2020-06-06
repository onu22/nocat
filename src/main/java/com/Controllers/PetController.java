
package com.Controllers;

import com.model.Pet;
import com.model.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path="/pets")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @PostMapping
    public @ResponseBody String createPet(@RequestBody Pet pet) {
        petRepository.save(pet);
        return String.format("Added %s", pet);
    }

    @GetMapping
    public @ResponseBody Iterable<Pet> getAllPets() {

        try {
            Iterable<Pet> pets = petRepository.findAll();
            return  pets;
        }
        catch (Exception ex)
        {

        }
        return null;
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Pet> getPet(@PathVariable Integer id) {
        return Optional.ofNullable(petRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deletePet(@PathVariable Integer id) {
        petRepository.deleteById(id);
        return "Deleted " + id;
    }
}

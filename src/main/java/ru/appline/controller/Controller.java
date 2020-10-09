package ru.appline.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PostMapping(value="/createPet", consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
         petModel.add(pet, newId.getAndIncrement());
         return "{\"result\":Успешно}";
    }




    @GetMapping(value= "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String,Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/delPet", consumes = "application/json", produces = "application/json")
    public void delete (@RequestBody Map<String,Integer> pet) {
        petModel.delete(pet.get("id"));
    }
    @PutMapping(value = "/putPet", consumes = "application/json", produces = "application/json")
    public String putPet(@RequestBody Map<String,String> pet) {
        int id = Integer.parseInt(pet.get("id"));
        String name = pet.get("name");
        String type = pet.get("type");
        int age = Integer.parseInt(pet.get("age"));

        petModel.putPet(id, name, type, age);

        return gson.toJson("Питомец с id = " + id + " изменён!");

    }
}

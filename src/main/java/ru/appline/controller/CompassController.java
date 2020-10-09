package ru.appline.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;
import ru.appline.logic.CompassModel;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CompassController {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final CompassModel compassModel = CompassModel.getInstance();

    @PutMapping(value = "/setCompass", produces = "application/json")
    public String setCompass(@RequestBody Map<String, String> compass){
        if (compassModel.addRanges(compass))
        {
            return gson.toJson("Градусы успешно добавлены!");
        }else {
            return gson.toJson("Какая-то ошибка :(");
        }
    }

    @GetMapping(value = "/getSideCompass",consumes = "application/json",produces = "application/json")
    public Map<String, String> getSideCompass(@RequestBody Map<String,Integer> degrees){
        return compassModel.getSide(degrees.get("Degree"));
    }

    @GetMapping(value = "/getCompass", produces = "application/json")
    public Map<String, String> getCompass() {
        return compassModel.getCompass();
    }
}

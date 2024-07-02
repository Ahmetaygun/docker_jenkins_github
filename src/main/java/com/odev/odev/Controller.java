package com.odev.odev;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller {
    @GetMapping("/getProjeBilgileri")
    public String getProjeBilgileri(){
        return "en proje";
    }

}

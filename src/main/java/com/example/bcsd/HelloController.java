package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {


    //@GetMapping("/introduce")
    //public String introduce(@RequestParam(name="name" , required=false, defaultValue = "조원희") String name, Model model) {
    //  model.addAttribute("name", name);
    //return "hello";

    //}
    @GetMapping("/introduce")
    public String introduce() {
        // Thymeleaf가 templates/introduce.html 을 렌더링
        return "hello";
    }

    @GetMapping("/json")
    @ResponseBody
    public Map<String, Object> json() {
        Map<String, Object> result = new HashMap<>();
        result.put("age", 24);
        result.put("name", "조원희");
        return result;
    }
}

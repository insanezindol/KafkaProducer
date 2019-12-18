package kr.co.lunasoft.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lunasoft.service.KafkaService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
	
	@Autowired
    private KafkaService kafkaService;
	
	@GetMapping(value = "/send/{topic}/{message}")
    public JSONObject send(@PathVariable String topic, @PathVariable String message) {
    	kafkaService.send(topic, message);
    	
        JSONObject obj = new JSONObject();
        obj.put("code", "100200");
        obj.put("msg", "success");
        obj.put("data", null);
        return obj;
    }
	

}

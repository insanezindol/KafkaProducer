package kr.co.lunasoft.controller;

import java.util.concurrent.CompletableFuture;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import kr.co.lunasoft.model.MessageInfo;
import kr.co.lunasoft.model.NoticeInfo;
import kr.co.lunasoft.service.KafkaService;
import kr.co.lunasoft.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

	@Autowired
	private KafkaService kafkaService;

	@GetMapping(value = "/send/message/{topic}")
	public JSONObject sendMessage(@PathVariable String topic, @RequestBody MessageInfo messageInfo) {
		messageInfo.setCreated(DateUtil.getNowDatetime());

		Gson gson = new Gson();
		String message = gson.toJson(messageInfo);

		kafkaService.send(topic, message);

		JSONObject obj = new JSONObject();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", null);
		return obj;
	}

	@GetMapping(value = "/send/notice/{topic}")
	public JSONObject sendNotice(@PathVariable String topic, @RequestBody NoticeInfo noticeInfo) {
		Gson gson = new Gson();
		String message = gson.toJson(noticeInfo);

		CompletableFuture.supplyAsync(() -> {
			kafkaService.send(topic, message);
			return Thread.currentThread().getId();
		}).thenAccept(str -> log.info("[END]" + String.valueOf(str)));

		JSONObject obj = new JSONObject();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", null);
		return obj;
	}

}

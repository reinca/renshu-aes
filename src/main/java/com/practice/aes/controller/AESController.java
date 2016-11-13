package com.practice.aes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.aes.domain.model.Result;
import com.practice.aes.domain.model.UserData;
import com.practice.aes.service.AESService;

@RestController
@RequestMapping("/")
public class AESController {
	
	@Autowired private AESService aess;

	// Get key: hexString, please (browser problem)
	@RequestMapping(value="{id}/auth", method = RequestMethod.GET)
	public Result getAuth(@PathVariable("id") String id, @RequestParam(value="key", defaultValue="failed") String key){
		return aess.auth(id, key);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public UserData getOne(@PathVariable("id") String id){
		return aess.find(id);
	}

	@RequestMapping(value="{id}", method = RequestMethod.POST)
	public Result addUser(@PathVariable("id") String id){
		return aess.addUser(id);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public Result refreshPassword(@PathVariable("id") String id){
		return aess.refreshPassword(id);
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public Result deleteOne(@PathVariable("id") String id){
		return aess.delete(id);
	}
	
	@RequestMapping(value="admin/list", method = RequestMethod.GET)
	public List<UserData> listUp() {
		return aess.list();
	}

	@RequestMapping(value="admin/reset", method = RequestMethod.DELETE)
	public Result reset(){
		return aess.reset();
	}
}
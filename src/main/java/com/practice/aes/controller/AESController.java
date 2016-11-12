package com.practice.aes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.aes.domain.entity.Result;
import com.practice.aes.domain.entity.UserData;
import com.practice.aes.service.AESService;

@RestController
@RequestMapping("/")
public class AESController {
	
	@Autowired private AESService aess;

	@RequestMapping(method = RequestMethod.GET)
	public List<UserData> listUp() {
		return aess.list();
	}

	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public UserData getOne(@PathVariable("id") long id){
		return aess.find(id);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.POST)
	public Result addUser(@PathVariable("id") long id){
		return aess.addUser(id);
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public Result refreshPassword(@PathVariable("id") long id){
		return aess.refreshPassword(id);
	}

	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public Result deleteOne(@PathVariable("id") long id){
		return aess.delete(id);
	}

	@RequestMapping(value="reset", method = RequestMethod.DELETE)
	public Result reset(){
		return aess.reset();
	}
}
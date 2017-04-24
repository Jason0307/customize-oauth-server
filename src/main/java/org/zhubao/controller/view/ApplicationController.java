package org.zhubao.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhubao.entity.Application;
import org.zhubao.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	private ApplicationService applicationService;

	@PostMapping
	public Application createApplication(@RequestParam String applicationName, @RequestParam(required = false) String description, @RequestParam String redirectUrl) {
		
		return null;
	}
}

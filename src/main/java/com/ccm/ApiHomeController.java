package com.ccm;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class ApiHomeController {
	// 首页
	@GetMapping("/api")
	@ApiOperation(value = "首页", notes = "跳转到/swagger-ui.html")
	public ResponseEntity<Void> home() {
		return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).header("Location", "/swagger-ui.html").build();
	}
}
package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.Duck;
import com.example.demo.service.DucksService;

@RestController
public class DucksController {

	private DucksService ducksService;

	public DucksController(DucksService ducksService) {
		this.ducksService = ducksService;
	}

	@GetMapping("/")
	public String Hello() {
		return "Hello";
	}

	@PostMapping("/duck/")
	public int createDucks(@RequestBody Duck duck) {
		return ducksService.createDucks(duck);
	}

	@GetMapping("/duck/{index}")
	public ResponseEntity<Duck> getDucks(@PathVariable int index) {
		return ducksService.getDucks(index);
	}

	@PutMapping("/duck/{index}/put")
	public ResponseEntity<Boolean> putDucks(@PathVariable int index, @RequestBody Duck duck) {
		return ducksService.putDucks(index, duck);
	}

	@DeleteMapping("/duck/{index}/delete")
	public ResponseEntity<Boolean> putDucks(@PathVariable int index) {
		return ducksService.deleteDucks(index);
	}

	@PatchMapping("/duck/{index}/patch")
	public ResponseEntity<?> patchDuck(@PathVariable int index, Duck duck) {
		return ducksService.patchDuck(index, duck);
	}

	@GetMapping("hello/")
	public String HelloWorld() {
		return "   (\r\n" + "  `-`-.\r\n" + "  '( @ >\r\n" + "   _) (\r\n" + "  /    )\r\n" + " /_,'  / \r\n"
				+ "   \\  / \r\n" + "===m\"\"m===";
	}

}

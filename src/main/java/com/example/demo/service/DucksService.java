package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.data.Duck;

public interface DucksService {

	public ResponseEntity<Duck> createDucks(Duck duck);

	public ResponseEntity<Duck> getDucks(int index);

	public ResponseEntity<Boolean> putDucks(int index, @RequestBody Duck duck);

	public ResponseEntity<Boolean> deleteDucks(int index);

	public ResponseEntity<?> patchDuck(int index, Duck duck);

	public ResponseEntity<List<Duck>> findDuckByName(String name);
}

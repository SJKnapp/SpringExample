package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.data.Duck;

@Service
public class DucksServiceList implements DucksService {

	private Map<Integer, Duck> ducks = new HashMap<>();

	@Override
	public ResponseEntity<Duck> createDucks(Duck duck) {
		this.ducks.putIfAbsent(duck.getId(), duck);
		return new ResponseEntity<>(duck, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Duck> getDucks(int index) {
		return new ResponseEntity<Duck>(ducks.get(index), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Boolean> putDucks(int index, Duck duck) {
		if (ducks.get(index) != null) {
			duck.setId(index);
			ducks.put(duck.getId(), duck);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_MODIFIED);
	}

	@Override
	public ResponseEntity<Boolean> deleteDucks(int index) {
		if (ducks.get(index) != null) {
			ducks.remove(index);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> patchDuck(int index, Duck duck) {
		if (ducks.get(index) == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
		}

		ducks.get(index).setAge(duck.getAge());

		if (duck.getName() != null)
			ducks.get(index).setAge(duck.getAge());

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Duck>> findDuckByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

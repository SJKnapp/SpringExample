package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.data.Duck;

public class DucksServiceDb implements DucksService {

	@Override
	public int createDucks(Duck duck) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseEntity<Duck> getDucks(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Boolean> putDucks(int index, Duck duck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Boolean> deleteDucks(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> patchDuck(int index, Duck duck) {
		// TODO Auto-generated method stub
		return null;
	}

}

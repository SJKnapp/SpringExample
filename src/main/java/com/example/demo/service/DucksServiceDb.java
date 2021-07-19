package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.data.Duck;
import com.example.demo.data.repos.DuckRepo;

@Service
@Primary
public class DucksServiceDb implements DucksService {

	DuckRepo duckRepo;

	public DucksServiceDb(DuckRepo duckRepo) {
		super();
		this.duckRepo = duckRepo;
	}

	@Override
	public ResponseEntity<Duck> createDucks(Duck duck) {
		return new ResponseEntity<>(duckRepo.save(duck), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Duck> getDucks(int index) {
		return new ResponseEntity<>(duckRepo.findById(index).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> putDucks(int index, Duck duck) {
		try {
			Duck found = this.duckRepo.getById(index);
			found.setAge(duck.getAge());
			found.setName(duck.getName());
			return new ResponseEntity<>(true, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Boolean> deleteDucks(int index) {
		duckRepo.deleteById(index);
		return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<?> patchDuck(int index, Duck duck) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Duck>> findDuckByName(String name) {
		return new ResponseEntity<>(duckRepo.findByNameIgnoreCase(name), HttpStatus.OK);
	}

}

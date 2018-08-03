package com.ra.challenge.api.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.ra.challenge.api.documents.Complain;
import com.ra.challenge.api.responses.Response;
import com.ra.challenge.api.services.ComplainService;





@RestController
@RequestMapping(path = "/api/complain")
public class ComplainController {
	
	@Autowired
	private ComplainService complainService;
	
	@GetMapping
	public ResponseEntity<Response<List<Complain>>> listAllComplains() {
		return ResponseEntity.ok(new Response<List<Complain>>(this.complainService.listAll()));
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Complain>> listById(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Complain>(this.complainService.listById(id)));
	}


	@GetMapping(path = "/qtComplainbyLocale/{locale}")
	public ResponseEntity<Response<Integer>> listByLocale(@PathVariable(name = "locale") String locale) {
		MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("applicationChallenge");
        DBCollection collection = db.getCollection("complain");
        
        System.out.println(locale);
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
       // obj.add(new BasicDBObject("locale", locale));
        andQuery.append("locale", locale);
      
        System.out.println(andQuery.toString());
      
        DBCursor cursor = collection.find(andQuery);
        System.out.println(andQuery.toString());
        
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        
		return ResponseEntity.ok(new Response<Integer>(cursor.count()));
	}

	@GetMapping(path = "/qtComplainbyLocaleandComp/{locale}&{company}")
	public ResponseEntity<Response<Integer>> listByLocaleandCompany(@PathVariable(name = "locale") String locale, @PathVariable(name="company") String company) {
		MongoClient mongo = new MongoClient("localhost", 27017);
        DB db = mongo.getDB("applicationChallenge");
        DBCollection collection = db.getCollection("complain");
        
        System.out.println(locale);
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("locale", locale));
        obj.add(new BasicDBObject("company", company));
        andQuery.put("$and", obj);
        System.out.println(andQuery.toString());
      
        DBCursor cursor = collection.find(andQuery);
   
		return ResponseEntity.ok(new Response<Integer>(cursor.count()));
	}

	
	@PostMapping
	public ResponseEntity<Response<Complain>> create(@Valid @RequestBody Complain complain, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Complain>(erros));
		}
		
		return ResponseEntity.ok(new Response<Complain>(this.complainService.create(complain)));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Complain>> update(@PathVariable(name = "id") String id, @Valid @RequestBody Complain complain, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Complain>(erros));
		}
		complain.setId(id);
		return ResponseEntity.ok(new Response<Complain>(this.complainService.update(complain)));
	}
	

	@DeleteMapping(path="/delete/{id}")
	public ResponseEntity<Response<Integer>> removeComplain(@PathVariable (name ="id") String id){
		this.complainService.remove(id);
		return ResponseEntity.ok(new Response<Integer>(1));
		
	}
}

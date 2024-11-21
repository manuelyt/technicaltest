package com.technical.test.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.technical.test.json.JsonReader;

@RestController
@RequestMapping("/init/v1")
public class restController {

	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam String idValue) throws RestClientException {
			Long idv = Long.parseLong(idValue);
			String data = JsonReader.readUser("users.json", idv);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	@RequestMapping(path = "/putCiudad/{id}/{ciudad}", method = RequestMethod.PUT)
	public ResponseEntity<?> getUser(@PathVariable("id") String id, @PathVariable("ciudad") String ciudad)
			throws RestClientException {
		String data = "[{error:}]";
		try {
			Long idv = Long.parseLong(id);
			String res = JsonReader.putCiudad("users.json", idv, ciudad);
			if (res.equals("ok")) {
				data = "[{ok: se ha modificado la ciudad }]";
			} else {
				data = "[{error: " + res + "}]";
			}
		} catch (NumberFormatException e) {
			data = "[{error: el id del usuario deber ser numérico}]";
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers() throws RestClientException {
		String data = JsonReader.readOrd("users.json");
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
	}

	@RequestMapping(path = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id)
			throws RestClientException {
		String data = "[{error:}]";
		try {
			Long idv = Long.parseLong(id);
			String res = JsonReader.deleteUser("users.json", idv);
			if (res.equals("ok")) {
				data = "[{ok: se ha eliminado el usuario }]";
			} else {
				data = "[{error: " + res + "}]";
			}
		} catch (NumberFormatException e) {
			data = "[{error: el id del usuario deber ser numérico}]";
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);
	}
	
}

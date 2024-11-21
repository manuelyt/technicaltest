package com.technical.test.json;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.test.DTO.User;
import com.technical.test.DTO.Users;
import com.technical.test.DTO.userData;

public class JsonReader {

	public static String read(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Usa el class loader para obtener el archivo desde resources
			InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
			if (inputStream == null) {
				throw new IllegalArgumentException("File not found!");
			}
			// Mapea los valores del objeto
			Users users = objectMapper.readValue(inputStream, Users.class);
			// Convierte el objeto a una cadena JSON
			return objectMapper.writeValueAsString(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"error\": \"No se ha podido parsear el fichero\" }";
	}

	public static Users readObj(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Usa el class loader para obtener el archivo desde resources
			InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
			if (inputStream == null) {
				throw new IllegalArgumentException("File not found!");
			}
			// Mapea los valores del objeto
			Users users = objectMapper.readValue(inputStream, Users.class);
			return users;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<User> readList(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Usa el class loader para obtener el archivo desde resources
			InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);
			if (inputStream == null) {
				throw new IllegalArgumentException("File not found!");
			}
			// Mapea los valores del objeto
			Users users = objectMapper.readValue(inputStream, Users.class);
			return users.getUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String putCiudad(String filePath, Long id, String ciudad) {
		try {
			List<User> users = JsonReader.readList(filePath);
			List<User> userRes = users.stream().filter(user -> user.getId() == id).collect(Collectors.toList());
			if (userRes.size() == 1) {
				User userMod = userRes.get(0);
				userMod.setCity(ciudad);

				users.removeIf(x -> x.getId() == id);
				users.add(userMod);

				FileOutputStream fout = new FileOutputStream("src/main/resources/users.json");
				JsonFactory factory = new JsonFactory();
				JsonGenerator generator = factory.createGenerator(fout, JsonEncoding.UTF8);
				generator.writeStartObject();
				generator.writeArrayFieldStart("users");
				for (User myuser : users) {
					generator.writeStartObject();
					generator.writeStringField("id", myuser.getId().toString());
					generator.writeStringField("name", myuser.getName());
					generator.writeStringField("age", String.valueOf(myuser.getAge()));
					generator.writeStringField("city", myuser.getCity());
					generator.writeBooleanField("deleted", myuser.isDeleted());
					generator.writeArrayFieldStart("userData");
					for (userData myud : myuser.getUserData()) {
						generator.writeStartObject();
						generator.writeStringField("jobs", myud.getJobs());
						generator.writeStringField("experience", myud.getExperience());
						generator.writeEndObject();
					}
					generator.writeEndArray();
					generator.writeEndObject();
				}
				generator.writeEndArray();
				generator.close();
				fout.close();
				return "ok";
			}
			return " ";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readOrd(String filePath) {
		String data = "[{error:}]";
		try {
			List<User> users22 = JsonReader.readList("users.json");
			List<User> userRes = users22.stream().sorted(Comparator.comparing(User::getAge))
					.filter(user -> !user.isDeleted() )
					.collect(Collectors.toList());
			ObjectMapper objectMapper = new ObjectMapper();
			data = objectMapper.writeValueAsString(userRes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static String deleteUser(String filePath, Long id) {
		try {
			List<User> users = JsonReader.readList(filePath);
			List<User> userRes = users.stream().filter(user -> user.getId() == id).collect(Collectors.toList());
			if (userRes.size() == 1) {
				User userMod = userRes.get(0);
				userMod.setDeleted(true);

				users.removeIf(x -> x.getId() == id);
				users.add(userMod);

				FileOutputStream fout = new FileOutputStream("src/main/resources/users.json");
				JsonFactory factory = new JsonFactory();
				JsonGenerator generator = factory.createGenerator(fout, JsonEncoding.UTF8);
				generator.writeStartObject();
				generator.writeArrayFieldStart("users");
				for (User myuser : users) {
					generator.writeStartObject();
					generator.writeStringField("id", myuser.getId().toString());
					generator.writeStringField("name", myuser.getName());
					generator.writeStringField("age", String.valueOf(myuser.getAge()));
					generator.writeStringField("city", myuser.getCity());
					generator.writeBooleanField("deleted", myuser.isDeleted());
					generator.writeArrayFieldStart("userData");
					for (userData myud : myuser.getUserData()) {
						generator.writeStartObject();
						generator.writeStringField("jobs", myud.getJobs());
						generator.writeStringField("experience", myud.getExperience());
						generator.writeEndObject();
					}
					generator.writeEndArray();
					generator.writeEndObject();
				}
				generator.writeEndArray();
				generator.close();
				fout.close();
				return "ok";
			}
			return " ";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readUser(String filePath, Long id) {
		String data = "[{error:}]";
		try {
			List<User> users22 = JsonReader.readList("users.json");
			List<User> userRes = users22.stream()
					.filter(user -> (user.getId() == id && !user.isDeleted() ))
					.collect(Collectors.toList());
			ObjectMapper objectMapper = new ObjectMapper();
			data = objectMapper.writeValueAsString(userRes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}

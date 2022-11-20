package br.com.client;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.model.dto.FilmeEntradaDto;
import br.com.model.dto.FilmeSaidaDto;
import br.com.util.JsonUtil;

@Component
public class FilmeClient {

	@Autowired
	private JsonUtil jsonUtil = new JsonUtil();

	public FilmeSaidaDto pegarUm(Long id) throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/filmes/id/" + id);
		Builder builder = target.request();
		
		Response response = builder.get();
		
		int status = response.getStatus();
		if(status == 200 || status == 201) {
			String jsonVindo = response.readEntity(String.class);
			
			FilmeSaidaDto dto = jsonUtil.objeto(jsonVindo, FilmeSaidaDto.class);
			
			return dto;
		}
		throw new RuntimeException("status: " + status);
	}
	
	public FilmeSaidaDto criar(FilmeEntradaDto entradaDto) throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/filmes/");
		Builder builder = target.request();
		
		String jsonIndo = jsonUtil.json(entradaDto);
		
		Entity<String> entity = Entity.json(jsonIndo);	
		
		Response response = builder.post(entity);
		
		int status = response.getStatus();
		if(status == 200 || status == 201) {
			String jsonVindo = response.readEntity(String.class);
			
			FilmeSaidaDto dto = jsonUtil.objeto(jsonVindo, FilmeSaidaDto.class);
			
			return dto;
		}
		throw new RuntimeException("status: " + status);
	}
	
	public FilmeSaidaDto[] listar() throws IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/filmes/");
		Builder builder = target.request();
		
		Response response = builder.get();
		
		int status = response.getStatus();
		if(status == 200 || status == 201) {
			String jsonVindo = response.readEntity(String.class);
			
			FilmeSaidaDto[] dtos = jsonUtil.objeto(jsonVindo, FilmeSaidaDto[].class);
			
			return dtos;
		}
		throw new RuntimeException("status: " + status);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(new FilmeClient().pegarUm(10L));
	}
}

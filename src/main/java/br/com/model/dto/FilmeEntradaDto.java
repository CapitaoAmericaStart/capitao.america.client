package br.com.model.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilmeEntradaDto {

	private String nome;
	private String descricao;
	private String genero;
	private String url;
}

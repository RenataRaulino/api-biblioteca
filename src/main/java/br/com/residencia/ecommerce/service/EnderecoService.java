package br.com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.ecommerce.entity.Endereco;
import br.com.residencia.ecommerce.entity.Pedido;
import br.com.residencia.ecommerce.repository.EnderecoRepository;


@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Endereco> getAllEnderecos(){
		return enderecoRepository.findAll();	
	}
	
	public Endereco getEnderecoById(Integer id) {
		return enderecoRepository.findById(id).orElse(null);
		
	}
	
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco,Integer id) {
		Endereco enderecoExistenteNoBanco = getEnderecoById(id);
		 
		if(  enderecoExistenteNoBanco!= null) {
		enderecoExistenteNoBanco.setIdEndereco(enderecoExistenteNoBanco.getIdEndereco());
		enderecoExistenteNoBanco.setCep(endereco.getCep());
		enderecoExistenteNoBanco.setRua(endereco.getRua());
		enderecoExistenteNoBanco.setBairro(endereco.getBairro());
		enderecoExistenteNoBanco.setCidade(endereco.getCidade());
		enderecoExistenteNoBanco.setNumero(endereco.getNumero());
		enderecoExistenteNoBanco.setComplemento(endereco.getComplemento());
		enderecoExistenteNoBanco.setUf(endereco.getUf());
		}
		return enderecoRepository.save(enderecoExistenteNoBanco);
		
	}
	
	public Endereco deleteEndereco(Integer id) {
		enderecoRepository.deleteById(id);
		return getEnderecoById(id);
	}
}

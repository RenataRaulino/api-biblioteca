package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.AlunoRepository;
import br.com.residencia.biblioteca.repository.EmprestimoRepository;

@Service
public class AlunoService {
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	EmprestimoService emprestimoService;
	
	public List<Aluno> getAllAlunos(){
		return alunoRepository.findAll();
	}
	
	public List<AlunoDTO> getAllAlunosDTO(){
		List<Aluno> listaAluno = alunoRepository.findAll();
		List <AlunoDTO> listaAlunoDTO = new ArrayList<>();		
		
		for(Aluno aluno:listaAluno) {
			
			AlunoDTO alunoDTO = toDTO(aluno);
		
			listaAlunoDTO.add(alunoDTO);
			
		}
		
		return listaAlunoDTO;
	}
	
	public List<AlunoDTO> getAllAlunosEmprestimosDTO(){
		List<Aluno> listaAluno = alunoRepository.findAll();
		List <AlunoDTO> listaAlunoDTO = new ArrayList<>();		
		
		
		for(Aluno aluno:listaAluno) {
			
			AlunoDTO alunoDTO = toDTO(aluno);
			List<Emprestimo> listaEmprestimos = new ArrayList<>();	
			List<EmprestimoDTO> listaEmprestimosDTO = new ArrayList<>();
			
			listaEmprestimos = emprestimoRepository.findByAluno(aluno);
			for(Emprestimo emprestimo:listaEmprestimos) {
				EmprestimoDTO emprestimoDTO =  emprestimoService.toDTO(emprestimo);
				listaEmprestimosDTO.add(emprestimoDTO);
				
			}
			alunoDTO.setListaEmprestimosDTO(listaEmprestimosDTO);
			
			listaAlunoDTO.add(alunoDTO);
				
		}
		return listaAlunoDTO;
	}
	
	public Aluno getAlunoById(Integer id) {
		return alunoRepository.findById(id).orElse(null);
	}
	
	public Aluno saveAluno(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public AlunoDTO saveAlunoDTO(AlunoDTO alunoDTO) {
		Aluno aluno =toEntidade(alunoDTO) ;
		Aluno novaAluno = alunoRepository.save(aluno);
		
		AlunoDTO alunoAtualizadaDTO = toDTO(novaAluno);
		return alunoAtualizadaDTO;
	}
	
	public Aluno updateAluno(Aluno aluno, Integer id) {
		//Aluno alunoExistenteNoBanco = alunoRepository.findById(id).get();
		
		Aluno alunoExistenteNoBanco = getAlunoById(id);

		alunoExistenteNoBanco.setBairro(aluno.getBairro()); 
		alunoExistenteNoBanco.setCidade(aluno.getCidade()); 
		alunoExistenteNoBanco.setComplemento(aluno.getComplemento()); 
		alunoExistenteNoBanco.setCpf(aluno.getCpf()); 
		alunoExistenteNoBanco.setDataNascimento(aluno.getDataNascimento());
		alunoExistenteNoBanco.setLogradouro(aluno.getLogradouro());
		alunoExistenteNoBanco.setNome(aluno.getNome());
		alunoExistenteNoBanco.setNumeroLogradouro(aluno.getNumeroLogradouro());
		
		return alunoRepository.save(alunoExistenteNoBanco);
		
		
	}
	
	public AlunoDTO updateAlunoDTO(AlunoDTO alunoDTO, Integer id) {	
		Aluno alunoExistenteNoBanco = getAlunoById(id);
		AlunoDTO alunoAtualizadaDTO = new AlunoDTO();
		
		if(alunoExistenteNoBanco != null) {
			alunoDTO.setNumeroMatriculaAluno(alunoExistenteNoBanco.getNumeroMatriculaAluno());
			alunoExistenteNoBanco = toEntidade(alunoDTO);

			Aluno alunoAtualizada = alunoRepository.save(alunoExistenteNoBanco);
			
			alunoAtualizadaDTO = toDTO(alunoAtualizada);
			
		}
		return alunoAtualizadaDTO;
	}
	
	public Aluno deleteAluno(Integer id) {
		alunoRepository.deleteById(id);
		return getAlunoById(id);
	}
	
	private Aluno toEntidade(AlunoDTO alunoDTO){
		Aluno aluno = new Aluno();
		aluno.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		aluno.setNome(aluno.getNome());
		//aluno.setLogradouro(alunoDTO.getLogradouro());
		//aluno.setNumeroLogradouro(alunoDTO.getNumeroLogradouro());
		//aluno.setComplemento(alunoDTO.getComplemento());
		//aluno.setBairro(alunoDTO.getBairro());
		//aluno.setCidade(alunoDTO.getCidade());
		aluno.setCpf(alunoDTO.getCpf());
		//aluno.setDataNascimento(alunoDTO.getDataNascimento());
		
		return aluno;
}

	private AlunoDTO toDTO(Aluno aluno){
		AlunoDTO alunoDTO = new AlunoDTO();
	
		alunoDTO.setNumeroMatriculaAluno(aluno.getNumeroMatriculaAluno());
		alunoDTO.setNome(aluno.getNome());
		//alunoDTO.setLogradouro(aluno.getLogradouro());
		//alunoDTO.setNumeroLogradouro(aluno.getNumeroLogradouro());
		//alunoDTO.setComplemento(aluno.getComplemento());
		//alunoDTO.setBairro(aluno.getBairro());
		//alunoDTO.setCidade(aluno.getCidade());
		alunoDTO.setCpf(aluno.getCpf());
		//alunoDTO.setDataNascimento(aluno.getDataNascimento());
		
		return alunoDTO;
}

	
	
}
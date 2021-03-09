package com.doacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import com.doacao.model.Cliente;
import com.doacao.repository.ClienteRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClienteControllerTest {
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	
	 @Test
	 public void insertClientTest(){
		Cliente cliente = new Cliente();
		cliente.setEmail("renato.re2012@hotmail.com");
		cliente.setNome("renato");
		cliente.setTelefone("484984918");
		clienteRepository.save(cliente);
        Integer countUser = clienteRepository.findAll().size();
        assertEquals(1, countUser);
	  }
	 
	 @Test
	    public void checkUserSavedWithDocument() {
	        Cliente cliente = new Cliente();
	        cliente.setEmail("renato.re2012@hotmail.com");
			cliente.setNome("renato");
			cliente.setTelefone("484984918");
	        clienteRepository.save(cliente);
	        Integer countUser = clienteRepository.findAll().size();
	        assertEquals(1, countUser);
	        Optional<Cliente> cliente1 = clienteRepository.findById("renato.re2012@hotmail.com");
	        assertNotNull(cliente1.get());
	        assertEquals(cliente, cliente1.get());
	    }
}

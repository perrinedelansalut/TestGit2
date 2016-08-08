package test;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Client;
import vol.model.ClientPhysique;
import vol.model.Login;
import vol.model.dao.ClientDao;
import vol.model.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestLoginClient {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Test
	public void clientlogin(){
		
		Login log1 = new Login();
		
		Client boby = new ClientPhysique("Boby");
		
		loginDao.create(log1);
		
		boby.setLog(log1);
		
		clientDao.create(boby);
		
		boby = (Client) clientDao.findById(boby.getIdCli());
		
		Assert.assertNotNull(boby);
		Assert.assertEquals(log1.getIdLog(), boby.getLog().getIdLog());
		
	}
	
	
}

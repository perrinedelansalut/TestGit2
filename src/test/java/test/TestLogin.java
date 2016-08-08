package test;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Login;
import vol.model.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestLogin {
	
	@Autowired
	private LoginDao loginDao;
	
	@Test
	public void login(){
		
		Login log1 = new Login();
		log1.setLogin("BestPlace");
		log1.setMotDePasse("ThePlaceToBe");
		
		loginDao.create(log1);
		
		log1 = (Login) loginDao.findById(log1.getIdLog());
		
		Assert.assertNotNull(log1);
		Assert.assertEquals("BestPlace", log1.getLogin());
		Assert.assertEquals("ThePlaceToBe", log1.getMotDePasse());
		
		log1.setLogin("login");
		log1.setMotDePasse("motdepasse");
		
		log1 = loginDao.update(log1);
		
		log1 = (Login) loginDao.findById(log1.getIdLog());
		
		Assert.assertNotNull(log1);
		Assert.assertEquals("login", log1.getLogin());
		Assert.assertEquals("motdepasse", log1.getMotDePasse());
		
		List<Login> logins = loginDao.findAll();
		
		Assert.assertEquals(1, logins.size());
		
		loginDao.delete(log1);
		
		log1 = (Login) loginDao.findById(log1.getIdLog());
		
	}

}

package test;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vol.model.Adresse;
import vol.model.Client;
import vol.model.ClientPhysique;
import vol.model.dao.ClientDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestClient {
	@Autowired
	private ClientDao clientDao;

	// private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void client() throws ParseException {
		
		Adresse adresse = new Adresse("12 rue La Vache sur le toît", "12345", "C'est Vachement Sympa", "France");
		
		Client boby = new ClientPhysique("Boby");
		boby.setNom("Bybul");
		boby.setEmail("leBGdu33@hotmail.fr");
		boby.setNumeroTel("0606060606");
		boby.setNumeroFax("0909090909");
		boby.setAdresse(adresse);
		
		clientDao.create(boby);

		// SELECT
		boby = (Client) clientDao.findById(boby.getIdCli());

		Assert.assertNotNull(boby);

		Assert.assertEquals("Bybul", boby.getNom());
		Assert.assertEquals("leBGdu33@hotmail.fr", boby.getEmail());
		Assert.assertEquals("0606060606", boby.getNumeroTel());
		Assert.assertEquals("0909090909", boby.getNumeroFax());
		Assert.assertEquals(adresse.getRue(), boby.getAdresse().getRue());
		Assert.assertEquals(adresse.getCodePostal(), boby.getAdresse().getCodePostal());
		Assert.assertEquals(adresse.getPays(), boby.getAdresse().getPays());
		Assert.assertEquals(adresse.getVille(), boby.getAdresse().getVille());


		boby.setNumeroTel("0707070707");

		clientDao.update(boby);

		boby = (Client) clientDao.findById(boby.getIdCli());
		Assert.assertEquals("0707070707", boby.getNumeroTel());

		List<Client> listClient = clientDao.findAll();

		Assert.assertEquals(1, listClient.size());
		
		clientDao.delete(boby);
		boby = (Client) clientDao.findById(boby.getIdCli());
		
		}
		
}

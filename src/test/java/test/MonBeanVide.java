package test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vol.model.dao.ClientDao;

@Component
public class MonBeanVide {
	
	@Autowired
	private ClientDao cliDao;

	public MonBeanVide() {
		// TODO Auto-generated constructor stub
	}

}

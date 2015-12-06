package br.com.simpif.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.simpif.database.UserDAO;
import br.com.simpif.entities.Erro;
import br.com.simpif.entities.User;

@Path("/services")
public class Services {

	private static Logger logger = LogManager.getLogger(Services.class);
	
	@POST
	@Path("/deliver-kit")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deliverKit(User user) {
		
		logger.info("Entregando o kit:" + user.getFullName());
		
		UserDAO.getInstance().update(user);

		ResponseBuilder builder = Response.status(Response.Status.ACCEPTED);

		return builder.build();
	}

	@GET
	@Path("/get-all")
	@Produces("application/json")
	public List<User> getAllUsers() {

		List<User> users = UserDAO.getInstance().getAll();

		return users;
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertUser(User user) {
		
		logger.info("Inserindo usuário:" + user.getFullName());
		
		ResponseBuilder builder;
		
		try {
			
			UserDAO.getInstance().insert(user);
			builder = Response.status(Response.Status.CREATED);
			
		} catch (HibernateException hexp) {
			
			Erro erro = new Erro(1, "Problema na inserção do participante");
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(erro);
		}

		return builder.build();
	}
	
	@POST
	@Path("/get-byname")
	@Consumes("application/json")
	@Produces("application/json")
	public List<User> findUserByName(User user){
		
		String fullName = user.getFullName().trim().toUpperCase();
		
		List<User> users = UserDAO.getInstance().getByName(fullName);

		return users;		
	}
}

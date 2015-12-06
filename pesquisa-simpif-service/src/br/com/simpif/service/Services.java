package br.com.simpif.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.hibernate.HibernateException;

import br.com.simpif.database.UserDAO;
import br.com.simpif.entities.User;

@Path("/services")
public class Services {
	
	@POST
	@Path("/deliver-kit")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deliverKit(User user){
		
		UserDAO userDao = new UserDAO();
		userDao.update(user);
		
		ResponseBuilder builder = Response.status(Response.Status.ACCEPTED);
		
		return builder.build();
	}
	
	@GET
	@Path("/get-all")
	@Produces("application/json")
	public List<User> getAllUsers(){
		
		UserDAO userDao = new UserDAO();
		List<User> list = userDao.getAll();
		
		return list;
	}
	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertUser(User user){
		UserDAO userDao = new UserDAO();
		ResponseBuilder builder;
		try{
			userDao.insert(user);
			builder = Response.status(Response.Status.CREATED);
		}catch(HibernateException hexp){
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		
		return builder.build();
	}
	
//	@GET
//	@Path("/get-delivered")
//	@Produces("application/json")
//	public List<User> getDelivered(){
//		
//		UserDAO userDao = new UserDAO();
//		List<User> list = userDao.getDelivered();
//		
//		return list;
//	}
//	
//	@GET
//	@Path("/get-not-delivered")
//	@Produces("application/json")
//	public List<User> getNotDelivered(){
//
//		UserDAO userDao = new UserDAO();
//		List<User> list = userDao.getNotDelivered();
//		
//		return list;
//	}
}
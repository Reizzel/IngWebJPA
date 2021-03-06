package com.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.udea.iw.Exception.ReizzelException;
import com.udea.iw.dto.UsuarioDTO;

//@Author Cristian Berrio Pulido - cbp453252.hdrl@gmail.com @Version = 1.0

//Clase de implementacion de UsuarioDAO
public class UsuarioDAOImp {
	
	private SessionFactory sessionFactory;//Usa el patron de diseño factory para el manejo de sesiones
	
	//Getter y setter para sessionFactory
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//Metodo para obtener todas los usuarios
	public List<UsuarioDTO> obtener() throws ReizzelException{
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		Session session=null;
		try{
			session = sessionFactory.getCurrentSession();//Se obtiene la sesion
			Criteria criteria = session.createCriteria(UsuarioDTO.class);//Con el criteria se hace la consulta a la base de datos
			lista=criteria.list();
		}catch(HibernateException e){
			throw new ReizzelException("Error consultando ciudades", e);
		}
		return lista;
	}
	//Metodo para obtener solo 1 ciudad con codigo como parametro
	public UsuarioDTO obtener(String login) throws ReizzelException{
		UsuarioDTO usuario= new UsuarioDTO();
		Session session=null;
		try{
			session = sessionFactory.getCurrentSession();//Se obtiene la sesion
			Criteria criteria = session.createCriteria(UsuarioDTO.class);
			criteria.add(Restrictions.eq("login", login));//Se agrega la condicion con la que se hace la consulta
			usuario = (UsuarioDTO)criteria.uniqueResult();//Unique porque sé y estoy seguro que me va a arrojar solo 1 valor
			//uniqueResult retorna un objeto tipo "object"
		}catch(HibernateException e){
			throw new ReizzelException("Error consultando ciudades", e);
		}
		return usuario;
	}
		
}

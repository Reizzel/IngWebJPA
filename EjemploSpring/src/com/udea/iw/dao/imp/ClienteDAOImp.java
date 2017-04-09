package com.udea.iw.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.udea.iw.Exception.ReizzelException;
import com.udea.iw.dao.ClienteDAO;
import com.udea.iw.dto.ClienteDTO;

public class ClienteDAOImp implements ClienteDAO{
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<ClienteDTO> obtener() throws ReizzelException {
		List<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Session session=null;
		Criteria criteria = null;
		try{
			session = sessionFactory.getCurrentSession();//Se obtiene la sesion
			criteria = session.createCriteria(ClienteDTO.class);//Con el criteria se hace la consulta a la base de datos
			criteria.addOrder(Order.asc("fechaCreacion"));//Ordena ascendentemente con la propiedad fechaCreacion
			clientes=criteria.list();
		}catch(HibernateException e){
			throw new ReizzelException("Error consultando clientes", e);
		}
		return clientes;
	}

	@Override
	public void guardar(ClienteDTO cliente) throws ReizzelException {
		Session session=null;
		try{
			session = sessionFactory.getCurrentSession();//Se obtiene la sesion
			session.save(cliente);
		}catch(HibernateException e){
			throw new ReizzelException("Error en la transaccion guardando el cliente", e);
		}
		
		
	}

}

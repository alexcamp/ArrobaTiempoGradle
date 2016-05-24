package co.com.atiempo.dto;

import java.util.ArrayList;

import co.com.telefonica.atiempo.interfaces.atiempo.Address;


public class SuperDirecPs 
{
	private Address address;
	private ArrayList listSuperAgrupPsPeticion;
	
	public SuperDirecPs()
	{
		address=new Address();
		listSuperAgrupPsPeticion=new ArrayList();
	}
	/**
	 * @return
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public ArrayList getSuperAgrupPsPeticion() {
		return listSuperAgrupPsPeticion;
	}

	/**
	 * @param peticion
	 */
	public void setSuperAgrupPsPeticion(ArrayList peticion) {
		listSuperAgrupPsPeticion = peticion;
	}

}

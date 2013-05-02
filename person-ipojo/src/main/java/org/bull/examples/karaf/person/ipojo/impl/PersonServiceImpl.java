package org.bull.examples.karaf.person.ipojo.impl;

import java.util.List;

import org.bull.examples.karaf.person.model.PersonDAO;
import org.bull.examples.karaf.person.model.PersonModel;
import org.bull.examples.karaf.person.model.PersonService;

public class PersonServiceImpl implements PersonService
{

	/**
	 * Injected by iPOJO
	 */
	private PersonDAO	personDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PersonModel> getAll()
	{
		return personDAO.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonModel getPerson(final String pLogin)
	{
		PersonModel returned = null;
		final List<PersonModel> all = personDAO.getAll();
		for (final PersonModel person : all)
		{
			if (person.getLogin().equals(pLogin))
			{
				returned = person;
				break;
			}
		}
		return returned;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPerson(final PersonModel pPerson)
	{
		final PersonModel person = personDAO.newEntity();
		person.setLogin(pPerson.getLogin());
		person.setName(pPerson.getName());
		person.setEmail(pPerson.getEmail());
		personDAO.add(person);
	}

}

package org.novaforge.sandboxes.karaf.person.dao;

import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.bull.examples.karaf.person.dao.PersonEntity;
import org.bull.examples.karaf.person.dao.impl.PersonDAOImpl;
import org.bull.examples.karaf.person.model.PersonModel;
import org.junit.Assert;
import org.junit.Test;

public class PersonDAOImplTest
{

	private static final String	EMAIL	= "guillaume.lamirand@bull.net";
	private static final String	NAME	= "Guillaume Lamirand";
	private static final String	LOGIN	= "lamirand-g";

	public final class MyInitialcontextFactoryBuilder implements InitialContextFactoryBuilder
	{
		@Override
		public InitialContextFactory createInitialContextFactory(final Hashtable<?, ?> environment)
				throws NamingException
		{
			return new MyInitialContextFactory();
		}
	}

	@Test
	public void testWriteRead() throws Exception
	{
		NamingManager.setInitialContextFactoryBuilder(new MyInitialcontextFactoryBuilder());
		final InitialContext ic = new InitialContext();
		final EmbeddedDataSource ds = new EmbeddedDataSource();
		ds.setDatabaseName("target/test");
		ds.setCreateDatabase("create");
		ic.bind("osgi:service/javax.sql.DataSource/(osgi.jndi.service.name=jdbc/person)", ds);
		final PersonDAOImpl personService = new PersonDAOImpl();
		final EntityManagerFactory emf = Persistence.createEntityManagerFactory("personTest",
				System.getProperties());
		final EntityManager em = emf.createEntityManager();
		personService.setEntityManager(em);
		em.getTransaction().begin();
		personService.deleteAll();
		personService.add(new PersonEntity(LOGIN, NAME, EMAIL));
		em.getTransaction().commit();
		final List<PersonModel> persons = personService.getAll();
		Assert.assertEquals(1, persons.size());
		Assert.assertEquals(LOGIN, persons.get(0).getLogin());
		Assert.assertEquals(NAME, persons.get(0).getName());
		Assert.assertEquals(EMAIL, persons.get(0).getEmail());
	}

}

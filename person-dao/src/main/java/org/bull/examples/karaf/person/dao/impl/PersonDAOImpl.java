/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bull.examples.karaf.person.dao.impl;
import java.util.List;

import javax.persistence.EntityManager;

import org.bull.examples.karaf.person.dao.PersonEntity;
import org.bull.examples.karaf.person.model.PersonDAO;
import org.bull.examples.karaf.person.model.PersonModel;


public class PersonDAOImpl implements PersonDAO
{
	private EntityManager	em;

	public void setEntityManager(final EntityManager em)
	{
		this.em = em;
	}

	@Override
	public void add(final PersonModel person)
	{
		if (person instanceof PersonEntity)
		{
			em.persist(person);
			em.flush();
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void deleteAll()
	{
		em.createQuery("delete from PersonEntity").executeUpdate();
		em.flush();
	}

	@Override
	public List<PersonModel> getAll()
	{
		return em.createQuery("select p from PersonEntity p").getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonModel newEntity()
	{
		return new PersonEntity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersonModel newEntity(final PersonModel pPerson)
	{

		if (pPerson instanceof PersonEntity)
		{
			return pPerson;
		}
		else
		{
			return new PersonEntity(pPerson);

		}

	}
}

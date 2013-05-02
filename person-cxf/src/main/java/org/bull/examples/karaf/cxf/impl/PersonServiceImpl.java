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
package org.bull.examples.karaf.cxf.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bull.examples.karaf.person.model.PersonDAO;
import org.bull.examples.karaf.person.model.PersonDTO;
import org.bull.examples.karaf.person.model.PersonModel;
import org.bull.examples.karaf.person.model.PersonService;

@Produces(MediaType.APPLICATION_XML)
@WebService
public class PersonServiceImpl implements PersonService
{
	private PersonDAO	personDAO;

	public void setPersonDAO(final PersonDAO pPersonDAO)
	{
		personDAO = pPersonDAO;
	}

	@Override
	@GET
	@Path("/")
	public List<PersonModel> getAll()
	{
		final List<PersonModel> returnList = new ArrayList<PersonModel>();
		final List<PersonModel> all = personDAO.getAll();
		for (final PersonModel personModel : all)
		{
			returnList.add(new PersonDTO(personModel));
		}
		return returnList;
	}

	@Override
	@GET
	@Path("/{login}")
	public PersonModel getPerson(@PathParam("login") final String pLogin)
	{
		PersonModel returned = null;
		final List<PersonModel> all = personDAO.getAll();
		for (final PersonModel person : all)
		{
			if (person.getLogin().equals(pLogin))
			{
				returned = new PersonDTO(person);
				break;
			}
		}
		return returned;
	}

	@Override
	@POST
	@Path("/")
	public void addPerson(final PersonModel pPerson)
	{
		System.out.println("Add request received for " + pPerson.getLogin() + " name:" + pPerson.getName());

		final PersonModel person = personDAO.newEntity();
		person.setLogin(pPerson.getLogin());
		person.setName(pPerson.getName());
		person.setEmail(pPerson.getEmail());
		personDAO.add(person);
	}

}

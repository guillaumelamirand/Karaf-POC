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
package org.bull.examples.karaf.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.bull.examples.karaf.person.model.PersonDAO;
import org.bull.examples.karaf.person.model.PersonModel;

public class PersonRoute extends RouteBuilder
{
	private PersonDAO	personDAO;

	public void setPersonDAO(final PersonDAO pPersonDAO)
	{
		personDAO = pPersonDAO;
	}

	@Override
	public void configure() throws Exception
	{
		final DataFormat jaxb = new JaxbDataFormat("org.novaforge.sandboxes.karaf.person.model");
		from("file://in").id("file2DAO").unmarshal(jaxb).process(new Processor()
		{

			@Override
			public void process(final Exchange pExchange) throws Exception
			{
				System.out.println("Get new person file");
				final Object body = pExchange.getIn().getBody();
				final PersonModel personBody = (PersonModel) body;
				final PersonModel person = personDAO.newEntity();
				person.setLogin(personBody.getLogin());
				person.setName(personBody.getName());
				person.setEmail(personBody.getEmail());
				personDAO.add(person);

			}
		});

	}
}

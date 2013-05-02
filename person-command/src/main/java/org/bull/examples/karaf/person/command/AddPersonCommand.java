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
package org.bull.examples.karaf.person.command;

import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.commands.Action;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.bull.examples.karaf.person.model.PersonDAO;
import org.bull.examples.karaf.person.model.PersonModel;

@Command(scope = "person", name = "add", description = "Adds a person")
public class AddPersonCommand implements Action
{
	@Argument(index = 0, name = "Login", required = true, description = "Login", multiValued = false)
	String				login;
	@Argument(index = 1, name = "Name", required = true, description = "Name", multiValued = false)
	String				name;
	@Argument(index = 2, name = "Email", required = true, description = "Email", multiValued = false)
	String				email;
	private PersonDAO	personDAO;

	public void setPersonDAO(final PersonDAO pPersonDAO)
	{
		personDAO = pPersonDAO;
	}

	@Override
	public Object execute(final CommandSession session) throws Exception
	{
		final PersonModel person = personDAO.newEntity();
		person.setLogin(login);
		person.setName(name);
		person.setEmail(email);
		personDAO.add(person);
		return null;
	}

}

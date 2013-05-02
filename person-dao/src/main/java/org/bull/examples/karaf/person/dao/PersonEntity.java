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
package org.bull.examples.karaf.person.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bull.examples.karaf.person.model.PersonModel;

@Entity
@Table(name = "Person")
public class PersonEntity implements PersonModel
{
	private String	login;
	private String	name;
	private String	email;

	public PersonEntity()
	{
	}

	public PersonEntity(final PersonModel pPersonModel)
	{
		super();
		login = pPersonModel.getLogin();
		name = pPersonModel.getName();
		email = pPersonModel.getEmail();
	}

	public PersonEntity(final String pLogin, final String pName, final String pEmail)
	{
		super();
		login = pLogin;
		name = pName;
		email = pEmail;
	}

	@Override
	public String getLogin()
	{
		return login;
	}

	@Override
	public void setLogin(final String pLogin)
	{
		login = pLogin;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(final String pName)
	{
		name = pName;
	}

	@Override
	public String getEmail()
	{
		return email;
	}

	@Override
	public void setEmail(final String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("Person [login=").append(login).append(", name=").append(name).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}

}

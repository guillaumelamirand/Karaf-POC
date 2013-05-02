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
package org.bull.examples.karaf.person.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonDTO implements PersonModel
{
	private String	login;
	private String	name;
	private String	email;

	public PersonDTO()
	{
	}

	public PersonDTO(final PersonModel pPersonModel)
	{
		login = pPersonModel.getLogin();
		name = pPersonModel.getName();
		email = pPersonModel.getEmail();
	}

	public PersonDTO(final String pLogin, final String pName, final String pEmail)
	{
		super();
		login = pLogin;
		name = pName;
		email = pEmail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLogin()
	{
		return login;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLogin(final String pLogin)
	{
		login = pLogin;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(final String pName)
	{
		name = pName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEmail()
	{
		return email;
	}

	/**
	 * {@inheritDoc}
	 */
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

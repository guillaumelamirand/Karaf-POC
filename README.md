Karaf-POC
=========

This repository contains some modules around Karaf


Build
=========

mvn clean install

Install && run :

wget http://www.apache.org/dyn/closer.cgi/karaf/2.2.10/apache-karaf-2.2.10.tar.gz
tar xvf apache-karaf-2.2.10.tar.gz
cd apache-karaf-2.2.10
./bin/karaf

features:addurl mvn:org.apache.cxf.karaf/apache-cxf/2.7.3/xml/features
features:addurl mvn:org.novaforge.sandboxes.karaf/person-features/1.0.0-SNAPSHOT/xml
features:addUrl mvn:org.apache.camel.karaf/apache-camel/2.10.4/xml/features

features:install person-service


Test the felix command for datasource
=========

> db:select <your datasource name>
> db:exec "create table person (name VARCHAR(100))"
> db:query "select * from test"

Test the felix command for person dao
=========

> person:add lamirand-g 'Guillaume Lamirand' guillaume.lamirand@bull.net

> person:list

Test the cxf service
=========

The person service should show up in the list of currently installed services that can be found here
http://localhost:8181/cxf/ 

List the known persons
http://localhost:8181/cxf/person

Send the content of deploy/user.xml to the following url using POST:
http://localhost:8181/cxf/person

Test the camel route
=========

Copy the file deploy/user.xml to 'in' directory bellow karaf directory

Test the ipojo instance
=========

Just use command instances to see ipojo instance


Thanks to Christian Schneider and its tutorial: https://github.com/cschneider/Karaf-Tutorial


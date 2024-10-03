# phonebook

## Description
A RESTful API built using Java/Spring, inspired by the 'Contacts' app in /e/OS. This API allows users to manage contacts efficiently with a robust backend.

## Features
- CRUD operations for contact management
- Full API documentation available via Swagger

## Prerequisites
Before you begin, ensure you have the following installed on your machine:
- [Git](https://git-scm.com)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) (if not using Docker)

## Getting Started

### Clone the Repository
To get a local copy of the project, run the following command in your terminal:

```bash
git clone git@github.com:cesaugusto108/phonebook.git
cd phonebook/phonebook
```

### Setting Up the Environment with Docker

1. **Create a Docker Network** (optional):
   ```bash
   docker network create phonebook-network
   ```
2. **Create a .env file**
   The .env file should contain the PASSWORD and PROFILE keys.
   Example:
   PASSWORD=1234
   PROFILE=dev
   
   ```bash
   vim .env
   ```
3. **Use Gradle Wrapper to build the application**
   ```bash
   ./gradlew clean build -x test
   ```
4. **Build and Run the Application**:
   Use Docker Compose to build and run the application:

   ```bash
   docker-compose up --build
   ```

   If it fails on Windows, disable BuildKit temporarily using this instead:

   ```bash
   DOCKER_BUILDKIT=0 docker-compose up --build
   ```

### Running the Application Locally (without Docker)
If you prefer to run the application locally without Docker, follow these steps:

1. **Navigate to Project Directory**:
   ```bash
   cd phonebook/phonebook
   ```

2. **Run with Gradle**:
   Use the following command to start the application with necessary arguments (replace details where necessary):

   ```bash
   ./gradlew bootRun --args='--USERNAME=admin --PASSWORD=123456 --DB-HOST=localhost --DB-PORT=3306 --ACTIVE_PROFILE=dev --SERVER-PORT=8880'
   ```

## Accessing the API Documentation
Once the application is running, you can access the full API documentation via Swagger at:

[http://localhost:8880/phonebook/swagger-ui/index.html](http://localhost:8880/phonebook/swagger-ui/index.html)

## Author
César Augusto Silva  
cesaraugustosilva@proton.me

## Example usage:
```
$ curl http://localhost:8880/phonebook/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801 | json_pp
{
	"id": "e8fd1a04-1c85-45e0-8f35-8ee8520e1801",
	"firstName": "Rebeca",
	"middleName": "Alves",
	"lastName": "Souza",
	"nickname": null,
	"phoneticFirstName": null,
	"phoneticMiddleName": null,
	"phoneticLastName": null,
	"relationship": "PARTNER",
	"company": null,
	"title": null,
	"website": null,
	"date": null,
	"dateType": null,
	"note": null,
	"telephones": [
		{
			"countryCode": "55",
			"areaCode": "79",
			"number": "999989892",
			"telephoneType": "MOBILE"
		}
	],
	"addresses": [
		{
			"street": "Porto da Folha",
			"number": "39",
			"complement": null,
			"district": null,
			"postalCode": null,
			"city": {
				"name": "Aracaju",
				"state": "Sergipe",
				"country": {
					"name": "Brasil"
				}
			},
			"addressType": "HOME"
		}
	],
	"emails": [
		{
			"username": "kdufton1",
			"domain": "netlog.com",
			"emailType": "WORK"
		},
		{
			"username": "dluetchford8",
			"domain": "yahoo.com.br",
			"emailType": "PERSONAL"
		}
	],
	"messengers": [],
	"_links": {
		"self": {
			"href": "http://localhost:8880/phonebook/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801"
		},
		"contacts": {
			"href": "http://localhost:8880/phonebook/api/v1/contacts"
		}
	}
}
```

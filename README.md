# phonebook
RESTful API built using Java/Spring. Functionalities inspired by the 'Contacts' app in /e/OS.

Gradle EXAMPLE command: `./gradlew bootRun --args='--USERNAME=admin --PASSWORD=123456 --DB-HOST=localhost --DB-PORT=3306 --ACTIVE_PROFILE=dev --SERVER-PORT=9999'`

Example usage (full api docs with Swagger (`http://localhost:8880/phonebook/swagger-ui/index.html`)):
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

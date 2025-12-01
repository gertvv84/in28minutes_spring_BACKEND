package com.in28minutes.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

//	URL Versioning	
	@GetMapping(path = "/v1/person")
	public PersonV1 getPersonVersion1() {
		return new PersonV1("Gert Van Vaerenbergh");
	}

	@GetMapping(path = "/v2/person")
	public PersonV2 getPersonVersion2() {
		return new PersonV2(new Name("Gert", "Van Vaerenbergh"));
	}

//	Versioning via Request Parameters	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonVersion1Requestparam() {
		return new PersonV1("Gert Van Vaerenbergh");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonVersion2Requestparam() {
		return new PersonV2(new Name("Gert", "Van Vaerenbergh"));
	}

//	(Custom) Headers versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonVersion1Headers() {
		return new PersonV1("Gert Van Vaerenbergh");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonVersion2Headers() {
		return new PersonV2(new Name("Gert", "Van Vaerenbergh"));
	}

//	Media type versioning
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonVersion1MediaType() {
		return new PersonV1("Gert Van Vaerenbergh");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonVersion2MediaType() {
		return new PersonV2(new Name("Gert", "Van Vaerenbergh"));
	}
}

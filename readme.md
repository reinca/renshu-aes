# renshu-aes

## Introduction
AES Encryption/Simplist auth api

## Controller list (base port: _8080_)

### GET /admin/list
- List all users information (ID, based time, current time, encrypted key) in database 

### DELETE /admin/reset
- _Initialize all database_

### /{id} <br />
- _GET_ get one user information
- _POST_ add one user information<br />
No parameter required. Just set method
- _PUT_ refresh one user encrypted key<br />
No parameter required. Just set method
- _DELETE_ delete one user information

### GET /{id}/auth?key={key}
- compare encrypted key server-client, execute auth<br />

### Parameters
- String id<br />
	username or user ID, unique key
- String key<br />
	Encrypted key must be get _HEX string_
- Encrypted key example<br />
(X)	INFMpDA/bTdYm/l45a23o/jm7XudEoqmbn0nzqxaPjc=<br />
(O)	494E464D7044412F625464596D2F6C34356132336F2F6A6D37587564456F716D626E306E7A717861506A633D

### Sample Response Body
	{
	    "id": "tester-API",
	    "status": "success"
	}

## Build Spec
- Java 8
- Gradle
- Spring-boot
- H2 Database

## Undeveloped Controller
- /admin/decode/{id}<br />
(GET Method) decode one user encrypted key (return id, key)<br />
I made service but, it is useless and damaged security.
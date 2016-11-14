# renshu-aes

## Introduction
AES Encryption/Simplist auth api

## Controller list (base port: _8080_)

### GET /admin/list
- List all users information (ID, based time, current time, encrypted key) in database 

### DELETE /admin/reset
- **Initialize all database**

### /{id} <br />
- *GET* get one user information
- *POST* add one user information<br />
No parameter required. Just set method
- *PUT* refresh one user encrypted key<br />
No parameter required. Just set method
- *DELETE* delete one user information

### GET /{id}/auth?key={key}
- Compare encrypted key server-client, execute auth<br />

### Parameters
- **id** *String*<br />
	username or user ID, unique key
- **key** *String*<br />
	Encrypted key must be get _HEX string_
- Encrypted key example<br />
**(X)**	INFMpDA/bTdYm/l45a23o/jm7XudEoqmbn0nzqxaPjc=<br />
**(O)**	494E464D7044412F625464596D2F6C34356132336F2F6A6D37587564456F716D626E306E7A717861506A633D

### Sample Response Body (POST, PUT, DELETE, AUTH)
	{
	    "id": "tester-API",
	    "status": "success"
	}

### Response parameters
- **ID** *String*<br />
Username or user ID, as unique key
- **status** *String*<br />
Result status message (success, failed, error)

### Sample Response Body (GET)
	{
		"uid": "test2",
		"encryptedKey": "b4Ge9ugIA5hkBkVfyDWs2K1/u/IzhV99q3HGiakhcWs=",
		"baseTime":{"offset":{"totalSeconds": 32400, "id": "+09:00", "rules":{"transitionRules":[],・},
		"requestedTime":{"offset":{"totalSeconds": 32400, "id": "+09:00", "rules":{"transitionRules":[],・}
	}

### Sample Response Body (LIST GET)
	[
		{"uid": "test1", "encryptedKey": "b4Ge9ugIA5hkBkVfyDWs2HMdcJ37PVeBACgho0DMsmE=", "baseTime":{"offset":{"totalSeconds": 32400,・},
		{"uid": "test2", "encryptedKey": "b4Ge9ugIA5hkBkVfyDWs2K1/u/IzhV99q3HGiakhcWs=", "baseTime":{"offset":{"totalSeconds": 32400,・}
	]

### Response parameters
- **uid** *String*<br />
Username or user ID, as unique key
- **encryptedKey** *String*<br />
Encrypted key, base64 encoded
- **baseTime** *OffsetDateTime*<br />
Application started time (Differ by user add time)
- **requestedTime** *OffsetDateTime*<br />
Request received time

## Build Spec
- Java 8
- Gradle
- Spring-boot
- H2 Database

## Undeveloped Controller
- *GET* /admin/decode/{id}<br />
Decode one user encrypted key (return id, key)<br />
I made service but, it will be occurred security problem.
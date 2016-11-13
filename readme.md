# renshu-aes
AES Encryption/Simplist auth api

### Controller list (base port: _8080_)
- /admin/list<br />
(GET Method) list all user information (ID, based time, current time, encrypted key) in database 
- /admin/reset <br />
(DELETE Method) _initialize database_
- /{id} <br />
(GET Method) get one user information<br />
(POST Method) add one user information<br />
(PUT Method) refresh one user encrypted key<br />
(DELETE Method) delete one user information
- /{id}/auth?key={key}<br />
(GET Method) compare encrypted key server-client, execute auth<br />
key must be get _HEX string_ of encrypted key
- Encrypted key example<br />
(X)	INFMpDA/bTdYm/l45a23o/jm7XudEoqmbn0nzqxaPjc=<br />
(O)	494E464D7044412F625464596D2F6C34356132336F2F6A6D37587564456F716D626E306E7A717861506A633D

### Entity/Schema
- User Data<br />
	- long id: unique id for database<br />
	- String uid: unique id for server-client (main search index)<br />
	- OffsetDateTime baseTime: renshu-aes UP-TIME<br />
	- OffsetDateTime currentTime: requested time<br />
- Result<br />
	- String id: unique id<br />
	- String status: success, failed, error
	
### Spec
- Java 8
- Gradle
- Spring-boot
- H2 Database

### Undeveloped Controller
- /admin/decode/{id}<br />
(GET Method) decode one user encrypted key (return id, key)<br />
I made service but, it is useless and damaged security.
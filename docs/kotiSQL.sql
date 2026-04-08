CREATE TABLE hoitosuunnitelmat (
	suunnitelma_id SERIAL PRIMARY KEY
	,kuvaus VARCHAR(1000)
);

CREATE TABLE laakkeet (
	laake_id SERIAL PRIMARY KEY
	,suunnitelma_id INT REFERENCES hoitosuunnitelmat(suunnitelma_id)
	,nimi VARCHAR(150) NOT NULL
	,annos VARCHAR(150) NOT NULL
	,aika TIME
	,kuinka_usein VARCHAR(150)
	,aloituspv DATE
	,lopetuspv DATE
);

CREATE TABLE hoitajat (
	hoitaja_id SERIAL PRIMARY KEY
	,sahkoposti VARCHAR(150) NOT NULL
	,puhelin VARCHAR(20)
	,etunimi VARCHAR(150) NOT NULL
	,sukunimi VARCHAR(150) NOT NULL
	,passwordhash VARCHAR(150) NOT NULL
	,kayttajaluotu TIMESTAMP
);

CREATE TABLE potilaat (
	potilas_id SERIAL PRIMARY KEY
	,suunnitelma_id INT REFERENCES hoitosuunnitelmat(suunnitelma_id)
	,etunimi VARCHAR(150) NOT NULL
	,sukunimi VARCHAR(150) NOT NULL
	,hetu VARCHAR(20) UNIQUE NOT NULL
	,puhelin VARCHAR(20)
	,osoite VARCHAR(150)
);

CREATE TABLE potilaslista (
	potilaslista_id SERIAL PRIMARY KEY
	,hoitaja_id INT REFERENCES hoitajat(hoitaja_id)
	,potilas_id INT REFERENCES potilaat(potilas_id)
);

CREATE TABLE hoitopaivakirjat (
	paivakirja_id SERIAL PRIMARY KEY
	,potilas_id INT REFERENCES potilaat(potilas_id)
	,hoitaja_id INT REFERENCES hoitajat(hoitaja_id)
	,merkinta VARCHAR(1000)
	,paivays TIMESTAMP
);

	
docs-kansiosta löytyy SQL queryt PostgreSQL lokaaliin tietokantaan

Pääkäyttäjän tunnus on admin ja salasana admin123.

Sovellus käynnistyy portista 8000 ja ohjaa sivulle /login. Loginista pääsee sivulle /hoitajat, jossa adminilla täydet oikeudet.

Käyttäjäroolien testaus
1. /hoitajat sivulta voi admin luoda hoitaja-käyttäjän. Kun käyttäjä on luotu se ilmestyy myös pgAdmin4.
2. kun Springboot sovelluksen käynnistää uudelleen voi testaa kirjautua hoitajan käyttäjällä
3. hoitaja ei pysty tekemään muutoksia, näkee vain listan muiden hoitajien yhteystiedoista

Tietokannan salasanan voi laittaa application.properties tiedostoon, se on nyt viety .gitignoreen
Tietokannan nimi on kotidb

Projektissa pyritty toteuttamaan seuraavat vaaditut aiheet
- MVC-malli, JPA rajapinnat ja OneToMany-rakenne 
- muut CRUD toiminnallisuudet ja OneToMany-rakenne.
- Login ominaisuus Spring Securitylla ja eri käyttäjäroolit oikeuksineen (admin ja hoitaja)
- Validointi @Valid avulla
- REST Controllerit (GET, POST, PUT, DELETE)
- Swagger API dokumentaatio
- PostgreSQL lokaali tietokanta PgAdmin 4 avulla
- Thymeleaf HTML sivu

JSON testidataa Swaggeriin
LISÄÄ HOITAJA
{
  "sahkoposti": "anna@hoito.fi",
  "etunimi": "Anna",
  "sukunimi": "Hoitaja",
  "passwordHash": "anna123",
  "puhelin": "0401234567"
}

{
  "sahkoposti": "matti@hoito.fi",
  "etunimi": "Matti",
  "sukunimi": "Hoitaja",
  "passwordHash": "matti123",
  "puhelin": "0509876543"
}

{
  "sahkoposti": "sara@hoito.fi",
  "etunimi": "Sara",
  "sukunimi": "Hoitaja",
  "passwordHash": "sara123",
  "puhelin": "0509876543"
}


LISÄÄ POTILAS
{
  "etunimi": "Maija",
  "sukunimi": "Meikäläinen",
  "hetu": "010101-123A",
  "puhelin": "0404567890",
  "osoite": "Testikatu 1"
}

LISÄÄ SUUNNITELMA
{
  "kuvaus": "Liikkumisen tuki ja ravitsemuksen seuranta"
}

LISÄÄ MERKINTÄ
{
  "merkinta": "Asiakas on pirteä, aamulääkkeet otettu ja aamupala syöty"
}

Tulevaisuuden kehityskohteet
- frontendin teko loppuun esim. html adminilla dashboard-sivu potilaiden ja hoitosuunnitelmien hallintaan
- hoitajalle dashboard-sivu hoitopäiväkirjamerkintöiden merkitsemiseen
- hoitajalle potilaslista, josta näkee vain omat potilaat eikä kaikkia potilaita
- frontendin hienosäätö saavutettavammaksi
- virhekoodien ja validoinnin parantaminen

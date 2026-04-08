docs-kansiosta löytyy SQL queryt PostgreSQL lokaaliin tietokantaan

Pääkäyttäjän tunnus on admin ja salasana admin123.

Sovellus käynnistyy portista 8000 ja ohjaa sivulle /login. Loginista pääsee sivulle /hoitajat, jossa adminilla täydet oikeudet.

Käyttäjäroolien testaus
1. /hoitajat sivulta voi admin luoda hoitaja-käyttäjän. Kun käyttäjä on luotu se ilmestyy myös pgAdmin4.
2. kun Springboot sovelluksen käynnistää uudelleen voi testaa kirjautua hoitajan käyttäjällä
3. hoitaja ei pysty tekemään muutoksia, näkee vain listan muiden hoitajien yhteystiedoista

Tietokannan salasanan voi laittaa application.properties tiedostoon, se on nyt viety .gitignoreen

Projektissa pyritty toteuttamaan seuraavat vaaditut aiheet
- MVC-malli, JPA rajapinnat ja OneToMany-rakenne 
- muut CRUD toiminnallisuudet ja OneToMany-rakenne.
- Login ominaisuus Spring Securitylla ja eri käyttäjäroolit oikeuksineen (admin ja hoitaja)
- Validointi @Valid avulla
- REST Controllerit (GET, POST, PUT, DELETE)
- Swagger API dokumentaatio
- PostgreSQL lokaali tietokanta PgAdmin 4 avulla
- Thymeleaf HTML sivu

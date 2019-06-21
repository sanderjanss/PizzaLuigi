package be.vdab.luigi.domain;

public class Adres {
    private final String straat;
    private final String huisNr;
    private final int postcode;
    private final String gemeente;

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public Adres(String straat, String huisNr, int postcode, String gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }
    //Programmeurs maken weinig setters, ze maken op deze manier hun objecten IMMUTABLE.
    //Je initialiseerd de variabelen van je object in de constructor, je wijzigt daarna de variabelen niet meer.
    //IMMUTABLE OBJECTS zijn thread safe, met meerderen een object lezen is altijd veilig, tegelijk een object wijzigen kan fataal zijn.
    //Vben van immutable classes: STRING, BIGDECIMAL, LOCALDATE, ...
    //Om fouten hierbij te vermijden maak je de variabelen FINAL!
}

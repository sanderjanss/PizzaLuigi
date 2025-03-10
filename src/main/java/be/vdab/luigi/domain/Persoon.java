package be.vdab.luigi.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Persoon {
    private final String voornaam;
    private final String familienaam;
    private final int aantalKinderen;
    private final boolean gehuwd;
    @DateTimeFormat(style="M-")
    private final LocalDate geboorte;
    private final Adres adres;
    // ADRES is een GENEST OBJECT,

    public Persoon(String voornaam, String familienaam, int aantalKinderen, boolean gehuwd, LocalDate geboorte, Adres adres) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.aantalKinderen = aantalKinderen;
        this.gehuwd = gehuwd;
        this.geboorte = geboorte;
        this.adres = adres;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public int getAantalKinderen() {
        return aantalKinderen;
    }

    public boolean isGehuwd() {
        return gehuwd;
    }

    public LocalDate getGeboorte() {
        return geboorte;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getNaam(){
        return voornaam + " " + familienaam;
    }
}

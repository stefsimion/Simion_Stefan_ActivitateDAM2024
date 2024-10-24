package com.example.seminar_4;

import java.io.Serializable;

public class Sarpe implements Serializable {

    private String specie;
    private String lungime_medie;
    private String culoare;
    private boolean veninos;

    public Sarpe(String specie, String lungime_medie, String culoare, boolean veninos) {
        this.specie = specie;
        this.lungime_medie = lungime_medie;
        this.culoare = culoare;
        this.veninos = veninos;
    }

    public Sarpe() {
        this.specie = "";
        this.lungime_medie = "";
        this.culoare = "";
        this.veninos = false;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getLungime_medie() {
        return lungime_medie;
    }

    public void setLungime_medie(String lungime_medie) {
        this.lungime_medie = lungime_medie;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public boolean isVeninos() {
        return veninos;
    }

    public void setVeninos(boolean veninos) {
        this.veninos = veninos;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sarpe{");
        sb.append("specie='").append(specie).append('\'');
        sb.append(", lungime_medie=").append(lungime_medie).append('\'');
        sb.append(", culoare='").append(culoare).append('\'');
        sb.append(", veninos=").append(veninos);
        sb.append('}');
        return sb.toString();
    }
}

package com.example.seminar_4;

import android.graphics.Bitmap;

public class ImaginiSarpe {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImaginiSarpe() {
    }

    public ImaginiSarpe(String textAfisat, Bitmap imagine, String link) {
        this.textAfisat = textAfisat;
        this.link = link;
        this.imagine = imagine;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public void setTextAfisat(String textAfisat) {
        this.textAfisat = textAfisat;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImaginiSarpe{");
        sb.append("textAfisat='").append(textAfisat).append('\'');
        sb.append(", imagine=").append(imagine);
        sb.append(", link='").append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

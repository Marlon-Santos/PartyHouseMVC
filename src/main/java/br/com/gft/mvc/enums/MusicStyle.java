package br.com.gft.mvc.enums;

public enum MusicStyle {
    AXE("Axe"),
    BLUES("Blues"),
    COUNTRY("Country"),
    CLASSICA("Classica"),
    ELETRONICA("Eletronica"),
    FORRO("Forro"),
    FUNK("Funk"),
    GOSPEL("Gospel"),
    HIPHOP("Hip-Hop"),
    JAZZ("Jazz"),
    MPB("MPB"),
    PAGODE("Pagode"),
    POP("Pop"),
    RAP("Rap"),
    REGGAE("Reggae"),
    ROCK("Rock"),
    SAMBA("Samba"),
    SERTANEJO("Sertanejo");

    private String musicStyle;

    MusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getMusicStyle() {
        return musicStyle;
    }
}

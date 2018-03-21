package edu.lewis.cs.joshjurss.sevenwondersapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by joshjurss on 4/19/2017.
 */

public class Score {
    private UUID id;
    private String p1Name;
    private String p2Name;
    private int p1Total;
    private int p2Total;

    private int p1Blue;
    private int p1Green;
    private int p1Yellow;
    private int p1Purple;
    private int p1Wonder;
    private int p1Science;
    private int p1Money;
    private int p1Military;
    private boolean p1MilitaryVic;
    private boolean p1ScienceVic;

    private int p2Blue;
    private int p2Green;
    private int p2Yellow;
    private int p2Purple;
    private int p2Wonder;
    private int p2Science;
    private int p2Money;
    private int p2Military;
    private boolean p2MilitaryVic;
    private boolean p2ScienceVic;

    private Date gameDate;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");



    public Score() {
        id = UUID.randomUUID();
        gameDate = new Date();
    }

    public Score(String id, String p1Name, String p2Name, int p1Blue, int p1Green, int p1Yellow,
                 int p1Purple, int p1Wonder, int p1Science, int p1Money, int p1Military, int p2Blue,
                 int p2Green, int p2Yellow, int p2Purple, int p2Wonder, int p2Science, int p2Money,
                 int p2Military, long gameDate, int p1ScienceVic, int p1MilitaryVic,
                 int p2ScienceVic, int p2MilitaryVic, int p1Total, int p2Total) {

        this.id=UUID.fromString(id);
        this.p1Name = p1Name;
        this.p2Name = p2Name;

        this.p1Blue = p1Blue;
        this.p1Green = p1Green;
        this.p1Yellow = p1Yellow;
        this.p1Purple = p1Purple;
        this.p1Wonder = p1Wonder;
        this.p1Science = p1Science;
        this.p1Money = p1Money;
        this.p1Military = p1Military;

        if(p1ScienceVic==1)
            this.p1ScienceVic=true;
        else
            this.p1ScienceVic=false;

        if(p1MilitaryVic==1)
            this.p1MilitaryVic=true;
        else
            this.p1MilitaryVic=false;

        this.p2Blue = p2Blue;
        this.p2Green = p2Green;
        this.p2Yellow = p2Yellow;
        this.p2Purple = p2Purple;
        this.p2Wonder = p2Wonder;
        this.p2Science = p2Science;
        this.p2Money = p2Money;
        this.p2Military = p2Military;

        if(p2ScienceVic==1)
            this.p2ScienceVic=true;
        else
            this.p2ScienceVic=false;

        if(p2MilitaryVic==1)
            this.p2MilitaryVic=true;
        else
            this.p2MilitaryVic=false;

        this.p1Total = p1Total;
        this.p2Total = p2Total;

        this.gameDate = new Date(gameDate);
    }

    public Score(String p1Name, String p2Name, int p1Blue, int p1Green, int p1Yellow,
                 int p1Purple, int p1Wonder, int p1Science, int p1Money, int p1Military, int p2Blue,
                 int p2Green, int p2Yellow, int p2Purple, int p2Wonder, int p2Science, int p2Money,
                 int p2Military, long gameDate, boolean p1ScienceVic, boolean p1MilitaryVic,
                 boolean p2ScienceVic, boolean p2MilitaryVic, int p1Total, int p2Total) {
        id = UUID.randomUUID();

        this.p1Name = p1Name;
        this.p2Name = p2Name;

        this.p1Blue = p1Blue;
        this.p1Green = p1Green;
        this.p1Yellow = p1Yellow;
        this.p1Purple = p1Purple;
        this.p1Wonder = p1Wonder;
        this.p1Science = p1Science;
        this.p1Money = p1Money;
        this.p1Military = p1Military;
        this.p1ScienceVic = p1ScienceVic;
        this.p1MilitaryVic = p1MilitaryVic;

        this.p2Blue = p2Blue;
        this.p2Green = p2Green;
        this.p2Yellow = p2Yellow;
        this.p2Purple = p2Purple;
        this.p2Wonder = p2Wonder;
        this.p2Science = p2Science;
        this.p2Money = p2Money;
        this.p2Military = p2Military;
        this.p2ScienceVic = p2ScienceVic;
        this.p2MilitaryVic = p2MilitaryVic;

        this.p1Total = p1Total;
        this.p2Total = p2Total;

        this.gameDate = new Date(gameDate);
    }

    public Score(UUID id, String p1Name, String p2Name, int p1Total, int p2Total, int p1Blue,
                 int p1Green, int p1Yellow, int p1Purple, int p1Wonder, int p1Science, int p1Money,
                 int p1Military, boolean p1MilitaryVictory, boolean p1ScienceVictory, int p2Blue,
                 int p2Green, int p2Yellow, int p2Purple, int p2Wonder, int p2Science, int p2Money,
                 int p2Military, boolean p2MilitaryVictory, boolean p2ScienceVictory) {
        this.id = UUID.randomUUID();
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Total = p1Total;
        this.p2Total = p2Total;
        this.p1Blue = p1Blue;
        this.p1Green = p1Green;
        this.p1Yellow = p1Yellow;
        this.p1Purple = p1Purple;
        this.p1Wonder = p1Wonder;
        this.p1Science = p1Science;
        this.p1Money = p1Money;
        this.p1Military = p1Military;
        this.p1MilitaryVic = p1MilitaryVictory;
        this.p1ScienceVic = p1ScienceVictory;
        this.p2Blue = p2Blue;
        this.p2Green = p2Green;
        this.p2Yellow = p2Yellow;
        this.p2Purple = p2Purple;
        this.p2Wonder = p2Wonder;
        this.p2Science = p2Science;
        this.p2Money = p2Money;
        this.p2Military = p2Military;
        this.p2MilitaryVic = p2MilitaryVictory;
        this.p2ScienceVic = p2ScienceVictory;
    }

    public Score(String id, String p1Name, String p2Name, int p1Total, int p2Total, int p1Blue,
                 int p1Green, int p1Yellow, int p1Purple, int p1Wonder, int p1Science, int p1Money,
                 int p1Military, int p1MilitaryVictory, int p1ScienceVictory, int p2Blue,
                 int p2Green, int p2Yellow, int p2Purple, int p2Wonder, int p2Science, int p2Money,
                 int p2Military, int p2MilitaryVictory, int p2ScienceVictory) {

        this.id=UUID.fromString(id);
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Total = p1Total;
        this.p2Total = p2Total;
        this.p1Blue = p1Blue;
        this.p1Green = p1Green;
        this.p1Yellow = p1Yellow;
        this.p1Purple = p1Purple;
        this.p1Wonder = p1Wonder;
        this.p1Science = p1Science;
        this.p1Money = p1Money;
        this.p1Military = p1Military;

        if(p1MilitaryVictory==1)
            this.p1MilitaryVic=true;
        else
            this.p1MilitaryVic=false;

        if(p1ScienceVictory==1)
            this.p1ScienceVic=true;
        else
            this.p1ScienceVic=false;

        this.p2Blue = p2Blue;
        this.p2Green = p2Green;
        this.p2Yellow = p2Yellow;
        this.p2Purple = p2Purple;
        this.p2Wonder = p2Wonder;
        this.p2Science = p2Science;
        this.p2Money = p2Money;
        this.p2Military = p2Military;

        if(p2MilitaryVictory==1)
            this.p2MilitaryVic=true;
        else
            this.p2MilitaryVic=false;

        if(p2ScienceVictory==1)
            this.p2ScienceVic=true;
        else
            this.p2ScienceVic=false;

    }


    public UUID getId() {
        return id;
    }

    public String getP1Name() {
        return p1Name;
    }

    public void setP1Name(String p1Name) {
        this.p1Name = p1Name;
    }


    public String getP2Name() {
        return p2Name;
    }

    public void setP2Name(String p2Name) {
        this.p2Name = p2Name;
    }

    public int getP1Total() {
        return p1Total;
    }

    public void setP1Total(int p1Total) {
        this.p1Total = p1Total;
    }

    public int getP2Total() {
        return p2Total;
    }

    public void setP2Total(int p2Total) {
        this.p2Total = p2Total;
    }

    public int getP1Blue() {
        return p1Blue;
    }

    public void setP1Blue(int p1Blue) {
        this.p1Blue = p1Blue;
    }

    public int getP1Green() {
        return p1Green;
    }

    public void setP1Green(int p1Green) {
        this.p1Green = p1Green;
    }

    public int getP1Yellow() {
        return p1Yellow;
    }

    public void setP1Yellow(int p1Yellow) {
        this.p1Yellow = p1Yellow;
    }

    public int getP1Purple() {
        return p1Purple;
    }

    public void setP1Purple(int p1Purple) {
        this.p1Purple = p1Purple;
    }

    public int getP1Wonder() {
        return p1Wonder;
    }

    public void setP1Wonder(int p1Wonder) {
        this.p1Wonder = p1Wonder;
    }

    public int getP1Science() {
        return p1Science;
    }

    public void setP1Science(int p1Science) {
        this.p1Science = p1Science;
    }

    public int getP1Money() {
        return p1Money;
    }

    public void setP1Money(int p1Money) {
        this.p1Money = p1Money;
    }

    public int getP1Military() {
        return p1Military;
    }

    public void setP1Military(int p1Military) {
        this.p1Military = p1Military;
    }


    public int getP2Blue() {
        return p2Blue;
    }

    public void setP2Blue(int p2Blue) {
        this.p2Blue = p2Blue;
    }

    public int getP2Green() {
        return p2Green;
    }

    public void setP2Green(int p2Green) {
        this.p2Green = p2Green;
    }

    public int getP2Yellow() {
        return p2Yellow;
    }

    public void setP2Yellow(int p2Yellow) {
        this.p2Yellow = p2Yellow;
    }

    public int getP2Purple() {
        return p2Purple;
    }

    public void setP2Purple(int p2Purple) {
        this.p2Purple = p2Purple;
    }

    public int getP2Wonder() {
        return p2Wonder;
    }

    public void setP2Wonder(int p2Wonder) {
        this.p2Wonder = p2Wonder;
    }

    public int getP2Science() {
        return p2Science;
    }

    public void setP2Science(int p2Science) {
        this.p2Science = p2Science;
    }

    public int getP2Money() {
        return p2Money;
    }

    public void setP2Money(int p2Money) {
        this.p2Money = p2Money;
    }

    public int getP2Military() {
        return p2Military;
    }

    public void setP2Military(int p2Military) {
        this.p2Military = p2Military;
    }


    public Long getGameDate() {

        long milis = gameDate.getTime();

        return milis;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Date getGameDateFull(){
        return gameDate;
    }

    public boolean isP1MilitaryVic() {
        return p1MilitaryVic;
    }

    public void setP1MilitaryVic(boolean p1MilitaryVic) {
        this.p1MilitaryVic = p1MilitaryVic;
    }

    public boolean isP1ScienceVic() {
        return p1ScienceVic;
    }

    public void setP1ScienceVic(boolean p1ScienceVic) {
        this.p1ScienceVic = p1ScienceVic;
    }

    public boolean isP2MilitaryVic() {
        return p2MilitaryVic;
    }

    public void setP2MilitaryVic(boolean p2MilitaryVic) {
        this.p2MilitaryVic = p2MilitaryVic;
    }

    public boolean isP2ScienceVic() {
        return p2ScienceVic;
    }

    public void setP2ScienceVic(boolean p2ScienceVic) {
        this.p2ScienceVic = p2ScienceVic;
    }
}
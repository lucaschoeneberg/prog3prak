package com.example.swingolf;

public class ScoreCard {
    private final Player[] players;
    private int[] scores;
    private final int[] par;
    private final int[] hole;

    public ScoreCard(Player[] players, int[] scores, int[] par, int[] hole) {
        this.players = players;
        this.scores = scores;
        this.par = par;
        this.hole = hole;
    }

}

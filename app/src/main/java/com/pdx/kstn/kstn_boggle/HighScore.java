package com.pdx.kstn.kstn_boggle;

import android.widget.ListView;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by thanhhoang on 1/28/17.
 */

public class HighScore {
    private ArrayList<Tuple> scores;
    private ListView myList;

    HighScore () {
        loadScores();
    }

    public void loadScores() {
        // load scores from text file
        Resources res = getResources();
        Scanner s = new Scanner(res.openRawResource(R.raw.scores));
        scores = new ArrayList<Tuple>();
        String tempString;
        Integer tempInt;
        while(s.hasNext()) {
            s.useDelimiter(":");
            tempString = s.next();
            s.skip(":");
            s.useDelimiter("\n");
            tempInt = s.nextInt();
            scores.add(new Tuple(tempString, tempInt));
        }
        s.close();
    }

    //Check list of scores and update if new high score is available
    public boolean updateScore(String name, int score) {
        Integer newScore = score;
        Iterator<Tuple> tupleListIterator = scores.iterator();
        while(tupleListIterator.hasNext()) {
            Tuple<String, Integer> entry = tupleListIterator.next();
            if(entry.getScore() < newScore) {
                tupleListIterator.remove();
                scores.add(new Tuple(name, score));
                return true;
            }
            if(entry.getScore() == newScore) {
                scores.add(new Tuple(name, score));
                return true;
            }
        }
        return false;
    }

    private void writeBack() {
        try {
            Resources res = getResources();
            Scanner s = new Scanner(res.openRawResource(R.raw.scores));
        } catch (Exception e) {

        }

        return;
    }
}

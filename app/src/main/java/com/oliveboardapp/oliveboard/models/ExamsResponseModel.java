package com.oliveboardapp.oliveboard.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shivam on 3/29/2016.
 */

public class ExamsResponseModel implements Serializable{


    private List<List<String>> exams = new ArrayList<List<String>>();

    /**
     *
     * @return
     * The exams
     */
    public List<List<String>> getExams() {
        return exams;
    }

    /**
     *
     * @param exams
     * The exams
     */
    public void setExams(List<List<String>> exams) {
        this.exams = exams;
    }

}

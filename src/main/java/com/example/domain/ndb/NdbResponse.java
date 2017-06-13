package com.example.domain.ndb;

/**
 * Created by bsheen on 6/12/17.
 */

public class NdbResponse {

    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "NdbResponse{" +
                "report=" + report +
                '}';
    }
}

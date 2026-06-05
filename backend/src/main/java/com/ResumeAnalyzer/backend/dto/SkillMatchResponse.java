package com.ResumeAnalyzer.backend.dto;

public class SkillMatchResponse {

    private String skill;
    private boolean matched;

    public SkillMatchResponse() {
    }

    public SkillMatchResponse(String skill, boolean matched) {
        this.skill = skill;
        this.matched = matched;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}

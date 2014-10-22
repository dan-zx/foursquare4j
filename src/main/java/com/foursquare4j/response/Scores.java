package com.foursquare4j.response;

public class Scores {

    private Long total;
    private Long scores;
    private Long recent;
    private Long max;
    private Long goal;
    private Long checkinsCount;

    public Long getTotal() {
        return total;
    }

    protected void setTotal(Long total) {
        this.total = total;
    }

    public Long getScores() {
        return scores;
    }

    protected void setScores(Long scores) {
        this.scores = scores;
    }

    public Long getRecent() {
        return recent;
    }

    protected void setRecent(Long recent) {
        this.recent = recent;
    }

    public Long getMax() {
        return max;
    }

    protected void setMax(Long max) {
        this.max = max;
    }

    public Long getGoal() {
        return goal;
    }

    protected void setGoal(Long goal) {
        this.goal = goal;
    }

    public Long getCheckinsCount() {
        return checkinsCount;
    }

    protected void setCheckinsCount(Long checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((checkinsCount == null) ? 0 : checkinsCount.hashCode());
        result = prime * result + ((goal == null) ? 0 : goal.hashCode());
        result = prime * result + ((max == null) ? 0 : max.hashCode());
        result = prime * result + ((recent == null) ? 0 : recent.hashCode());
        result = prime * result + ((scores == null) ? 0 : scores.hashCode());
        result = prime * result + ((total == null) ? 0 : total.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Scores other = (Scores) obj;
        if (checkinsCount == null) {
            if (other.checkinsCount != null) return false;
        } else if (!checkinsCount.equals(other.checkinsCount)) return false;
        if (goal == null) {
            if (other.goal != null) return false;
        } else if (!goal.equals(other.goal)) return false;
        if (max == null) {
            if (other.max != null) return false;
        } else if (!max.equals(other.max)) return false;
        if (recent == null) {
            if (other.recent != null) return false;
        } else if (!recent.equals(other.recent)) return false;
        if (scores == null) {
            if (other.scores != null) return false;
        } else if (!scores.equals(other.scores)) return false;
        if (total == null) {
            if (other.total != null) return false;
        } else if (!total.equals(other.total)) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Scores [total=").append(total).append(", scores=").append(scores).append(", recent=").append(recent).append(", max=").append(max).append(", goal=").append(goal).append(", checkinsCount=").append(checkinsCount).append("]");
        return builder.toString();
    }
}
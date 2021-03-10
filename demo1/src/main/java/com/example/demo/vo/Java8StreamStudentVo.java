package com.example.demo.vo;

/**
 * @Classname Java8StreamStudentVo
 * @Description TODO
 * @Date 2021/3/1 10:03
 * @Created by Administrator
 */
public class Java8StreamStudentVo {
    private int id;
    private String name;
    private Double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Java8StreamStudentVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

}

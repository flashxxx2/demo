package ru.sanua.demo.dto;

public class AverageDto {
    private Integer id;
    private String studentName;
    private Double avrValue;
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Double getAvrValue() {
        return avrValue;
    }

    public void setAvrValue(Double avrValue) {
        this.avrValue = avrValue;
    }


}

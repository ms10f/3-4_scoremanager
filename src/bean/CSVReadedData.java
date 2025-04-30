package bean;

import java.io.Serializable;

public class CSVReadedData implements Serializable {
    private String entYear;
    private String no;
    private String name;
    private String classNum;
    private String error;

    public String getEntYear() {
        return entYear;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getClassNum() {
        return classNum;
    }

    public String getError() {
        return error;
    }

    public void setEntYear(String entYear) {
        this.entYear = entYear;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public void setError(String error) {
        this.error = error;
    }
}
package de.accesor.controller;

import java.util.Objects;

public class ResponseErrorDto {
    private String title;
    private int status;

    public ResponseErrorDto() {
        super();
    }

    public ResponseErrorDto(final String title, final int status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseErrorDto that = (ResponseErrorDto) o;
        return status == that.status && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, status);
    }

    @Override
    public String toString() {
        return "ResponseErrorDto{" +
               "title='" + title + '\'' +
               ", status=" + status +
               '}';
    }
}



package br.com.gft.mvc.config.errorsHandle;

import java.util.Objects;

public class BadRequestDto {
    private String error;
    private String message;

    public BadRequestDto(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public BadRequestDto() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadRequestDto that = (BadRequestDto) o;
        return Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error);
    }
}

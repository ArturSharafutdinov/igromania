package ru.igromania.server.persistence.dto;

public class AuthorDto extends LongIdEntityDto {

    private String name; // Author name

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.vidula.DTO;

public class LikesDTO {
    
    private Long id;
    
    private OnlyIdDTO person;

    public LikesDTO(Long id, OnlyIdDTO person) {
        this.id = id;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OnlyIdDTO getPerson() {
        return person;
    }

    public void setPerson(OnlyIdDTO person) {
        this.person = person;
    }
    
    
}

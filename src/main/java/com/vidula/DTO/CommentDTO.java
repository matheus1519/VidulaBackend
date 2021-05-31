    package com.vidula.DTO;

import com.vidula.model.Likes;
import java.util.List;

/**
 *
 * @author 1519m
 */
public class CommentDTO {
    private Long id;

    private String doubt;

    private String answer;

    private List<LikesDTO> likes;

    private PersonDTO person;

    public CommentDTO(Long id, String doubt, String answer, List<LikesDTO> likes, PersonDTO person) {
        this.id = id;
        this.doubt = doubt;
        this.answer = answer;
        this.likes = likes;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoubt() {
        return doubt;
    }

    public void setDoubt(String doubt) {
        this.doubt = doubt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<LikesDTO> getLikes() {
        return likes;
    }

    public void setLikes(List<LikesDTO> likes) {
        this.likes = likes;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

   
    
    
}

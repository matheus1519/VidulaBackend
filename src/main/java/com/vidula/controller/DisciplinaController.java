package com.vidula.controller;

import com.vidula.DTO.CommentDTO;
import com.vidula.DTO.DisciplineDTO;
import com.vidula.DTO.LikesDTO;
import com.vidula.DTO.PersonDTO;
import com.vidula.DTO.OnlyIdDTO;
import com.vidula.DTO.SubjectExpandedDTO;
import com.vidula.DTO.TeacherDTO;
import com.vidula.DTO.WatchDTO;
import com.vidula.model.Disciplina;
import com.vidula.repository.DisciplinaRepository;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinas;

    @GetMapping()
    public List<DisciplineDTO> listar() {
        List<Disciplina> allDisciplines = disciplinas.findAll();
        List<DisciplineDTO> allDisciplinesModified = new ArrayList<>();

        allDisciplines.forEach(discipline -> {
            List<SubjectExpandedDTO> allSubjectsModifieds = new ArrayList<>();

            discipline.getAssuntos().forEach(subject -> {
                List<WatchDTO> watchesModifieds = new ArrayList<>();
                List<CommentDTO> commentsModifieds = new ArrayList<>();
                TeacherDTO teacherModified = null;

                if (subject.getTeacher() != null) {
                    teacherModified = new TeacherDTO(subject.getTeacher().getId(), subject.getTeacher().getVideoUrl(), subject.getTeacher().getArea(), subject.getTeacher().getStatus(), new PersonDTO(subject.getTeacher().getPerson().getId(), subject.getTeacher().getPerson().getName(), subject.getTeacher().getPerson().getEmail(), subject.getTeacher().getPerson().getLevelAccess(), subject.getTeacher().getPerson().getAvatarUrl(), subject.getTeacher().getPerson().getGender()));
                }

                subject.getWatches().forEach(watch -> {
                    PersonDTO personModified = null;

                    if (watch.getPerson() != null) {
                        personModified = new PersonDTO(watch.getPerson().getId(), watch.getPerson().getName(), watch.getPerson().getEmail(), watch.getPerson().getLevelAccess(), watch.getPerson().getAvatarUrl(), watch.getPerson().getGender());
                    }

                    watchesModifieds.add(new WatchDTO(watch.getId(), watch.getPath(), personModified, new OnlyIdDTO(watch.getSubject().getId())));
                });

                subject.getComments().forEach(comment -> {
                    PersonDTO personModified = null;
                    List<LikesDTO> likesModifieds = new ArrayList<>();

                    if (comment.getPerson() != null) {
                        personModified = new PersonDTO(comment.getPerson().getId(), comment.getPerson().getName(), comment.getPerson().getEmail(), comment.getPerson().getLevelAccess(), comment.getPerson().getAvatarUrl(), comment.getPerson().getGender());
                    }

                    comment.getLikes().forEach(like -> {
                        likesModifieds.add(new LikesDTO(like.getId(), new OnlyIdDTO(like.getPerson().getId())));
                    });

                    commentsModifieds.add(new CommentDTO(comment.getId(), comment.getDoubt(), comment.getAnswer(), likesModifieds, personModified));
                });

                allSubjectsModifieds.add(new SubjectExpandedDTO(subject.getId(), subject.getNome(), subject.getStatus(), subject.getInicio(), watchesModifieds, commentsModifieds, teacherModified));

            });

            allDisciplinesModified.add(new DisciplineDTO(discipline.getId(), discipline.getNome(), discipline.getDescricao(), allSubjectsModifieds));
        });

        return allDisciplinesModified;
    }

    @GetMapping("/{id}")
    public Optional<Disciplina> listarUm(@PathVariable Long id) {
        return disciplinas.findById(id);
    }

    @PutMapping("/{id}")
    public boolean put(@PathVariable Long id, @RequestBody Disciplina v) {
        if (disciplinas.findById(id) != null) {
            disciplinas.save(v);
            return true;
        }
        return false;
    }

    @PostMapping
    public Disciplina post(@RequestBody Disciplina v) {
        Disciplina disc = disciplinas.save(v);
        return disc;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        if (disciplinas.findById(id) != null) {
            disciplinas.deleteById(id);
            return true;
        }

        return false;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}

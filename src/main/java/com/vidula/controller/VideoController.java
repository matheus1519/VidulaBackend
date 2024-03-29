package com.vidula.controller;

import com.vidula.model.Video;
import com.vidula.util.VideoUrl;
import com.vidula.repository.VideoRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository videos;
    
    @Value("${video.path}")
    private String path;

    @GetMapping()
    public List<Video> listar() {
        return videos.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Video> listarUm(@PathVariable Long id) {
        return videos.findById(id);
    }

    @PutMapping("/{id}")
    public boolean alterar(@PathVariable Long id, @RequestBody Video v) {
        Optional<Video> videoDb = videos.findById(id);
        if (videoDb.isPresent() || id == v.getId()) {
//            if (v.getUrl() == null) {
//                v.setNome(videoDb.get().getNome());
//                v.setUrl(videoDb.get().getUrl());
//                v.setDetalhe(videoDb.get().getDetalhe());
//                v.setProximo(videoDb.get().getProximo());
//
//            }
            videos.save(v);
            return true;
        }
        return false;
    }

    @PostMapping("/send")
    public VideoUrl cadastrarVideo(@RequestParam("videoFile") MultipartFile videoFile) {
        if (videoFile.isEmpty()) {
            return null;
        }
        
        
        String url = VideoFileController.saveVideo(videoFile, path);
        Video videoData = new Video();
        videoData = videos.save(videoData);
        
        return new VideoUrl(url, videoData.getId());
    }
    
    @PostMapping
    public Video cadastrar(@RequestBody Video video) {
        return videos.save(video);
    }

    @DeleteMapping("/{id}")
    public boolean deletar(@PathVariable Long id) {
        if (videos.findById(id) != null) {
            videos.deleteById(id);
            return true;
        }

        return false;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}

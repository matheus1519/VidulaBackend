package com.vidula.controller;

import com.vidula.model.Video;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class VideoFileController {

    @RequestMapping("/videosview/{videoname}")
    public void videoView(HttpServletRequest req,
            HttpServletResponse res,
            @PathVariable("videoname") String videoName) {
        String path = "C:\\Users\\1519m\\Pictures\\";
        Path video = Paths.get(path, videoName);

        if (Files.exists(video)) {
            res.setHeader("Content-Disposition", "inline");
            res.setContentType(MediaType.ALL_VALUE);
            try {
                Files.copy(video, res.getOutputStream());
                res.getOutputStream().flush();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }
    
    public static void salvarVideo(MultipartFile videoFile, Video video){
        try {
            String name = Calendar.getInstance().getTimeInMillis() + videoFile.getOriginalFilename();
            videoFile.transferTo(Paths.get("C:\\Users\\1519m\\Pictures\\" + name));
//            mv.addObject("path", context.getContextPath() +"/images/"+name);
            video.setUrl("http://localhost:8080/videosview/" + name);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
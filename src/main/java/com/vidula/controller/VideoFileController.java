package com.vidula.controller;

import com.vidula.model.Video;
import com.vidula.repository.VideoRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("videofile")
public class VideoFileController {

    @Autowired
    VideoRepository videos;

    @RequestMapping("/{videoname}")
    public void videoView(HttpServletRequest req,
            HttpServletResponse res,
            @PathVariable("videoname") String videoName) {
        String path = "C:\\Users\\1519m\\Videos\\";
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

    public static String salvarVideo(MultipartFile videoFile) {
         try {
            String name = Calendar.getInstance().getTimeInMillis() + videoFile.getOriginalFilename();
            videoFile.transferTo(Paths.get("C:\\Users\\1519m\\Videos\\" + name));
            return "http://localhost:8080/videofile/" + name;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

}

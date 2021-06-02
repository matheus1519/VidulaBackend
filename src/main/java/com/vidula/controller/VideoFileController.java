package com.vidula.controller;

import com.vidula.repository.VideoRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("videofile")
public class VideoFileController {

    @Autowired
    VideoRepository videos;

    @Value("${video.path}")
    String path;

    @RequestMapping("/{videoname}")
    public void videoView(HttpServletRequest req, HttpServletResponse res,
            @PathVariable("videoname") String videoName) {

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

    public static String saveVideo(MultipartFile videoFile, String path) {

        try {
            String name = Calendar.getInstance().getTimeInMillis() + videoFile.getOriginalFilename();
            videoFile.transferTo(Paths.get(path + name));
            return "http://152.67.45.159:8080/videofile/" + name;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "";
    }

}

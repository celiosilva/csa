package br.com.delogic.csa.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.csa.controller.util.UploadedFile;
import br.com.delogic.csa.manager.ContentManager;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Inject
    private ContentManager contentManager;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    @ResponseBody
    public ModelAndView upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String fileName = contentManager.create(
                multipartFile.getInputStream(),
                multipartFile.getOriginalFilename());

            String filePath = contentManager.get(fileName);

            return new ModelAndView().addObject("files", Arrays.asList(new UploadedFile(fileName, multipartFile
                .getSize(), filePath, null, null, null)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ContentManager getUploadManager() {
        return contentManager;
    }

    public void setUploadManager(ContentManager uploadManager) {
        this.contentManager = uploadManager;
    }

}

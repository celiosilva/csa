package br.com.delogic.csa.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.csa.controller.util.UploadedFile;
import br.com.delogic.csa.manager.UploadManager;
import br.com.delogic.csa.util.f;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Inject
    private UploadManager uploadManager;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    @ResponseBody
    public ModelAndView upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String fileName = uploadManager.create(
                multipartFile.getInputStream(),
                multipartFile.getOriginalFilename());
            String filePath = uploadManager.get(fileName);
            return new ModelAndView().addObject("files", f
                .arrayList(new UploadedFile(fileName, multipartFile
                    .getSize(), filePath, null, null, null)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public UploadManager getUploadManager() {
        return uploadManager;
    }

    public void setUploadManager(UploadManager uploadManager) {
        this.uploadManager = uploadManager;
    }

}

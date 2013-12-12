package br.com.delogic.csa.manager.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.ServletContextAware;

import br.com.delogic.csa.manager.ContentManager;
import br.com.delogic.csa.util.is;

public class ContentManagerImpl implements ContentManager, ServletContextAware {

    private Resource directory;
    private String absolutePath;
    private Iterator<? extends Object> iterator;
    private String contextPath;
    private String path = "/static-content/";
    private ServletContext context;

    private static final Logger logger = Logger
            .getLogger(ContentManagerImpl.class);

    @PostConstruct
    public void init() throws Exception {
        absolutePath = directory.getFile().getAbsolutePath();
        contextPath = context.getContextPath();
    }

    @Override
    public String get(String name) {
        if (is.empty(name)){
            return "";
        }
        return contextPath + path + name;
    }

    @Override
    public String create(InputStream inputStream, String fileName) {
        String val = iterator.next().toString();
        String newFileName = "file" + val + "." + getFileExtension(fileName);
        saveToServer(inputStream, absolutePath + File.separatorChar,
                newFileName);
        return newFileName;
    }

    @Override
    public void update(InputStream inputStream, String fileName) {
        saveToServer(inputStream, absolutePath + File.separatorChar, fileName);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")
                || fileName.endsWith(".")) {
            throw new IllegalArgumentException(
                    "File name doesn't have any extension");
        }
        return fileName.substring(fileName.indexOf(".") + 1);
    }

    public static void saveToServer(InputStream inputStream,
            String directoryPath, String fileName) {
        try {
            logger.debug("Saving content " + directoryPath + fileName);
            FileCopyUtils.copy(inputStream, new FileOutputStream(directoryPath
                    + fileName));
        } catch (IOException e) {
            logger.error("Cannot save content", e);
            throw new RuntimeException("Cannot save content with name "
                    + fileName, e);
        }
    }

    public Resource getDirectory() {
        return directory;
    }

    public void setDirectory(Resource directory) {
        this.directory = directory;
    }

    public Iterator<? extends Object> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<? extends Object> iterator) {
        this.iterator = iterator;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

}

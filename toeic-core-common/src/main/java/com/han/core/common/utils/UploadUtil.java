package com.han.core.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by VO VAN NHAN on 6/12/2018.
 */
public class UploadUtil {
    private final Logger log = Logger.getLogger(this.getClass());
    private final int maxMemorySize = 1024 * 1024 * 3; //3MBl
    private final int maxRequestSize = 1024 * 1024 * 50; //50 MB

    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) throws FileUploadException, Exception {
        ServletContext context = request.getServletContext();
        String address = context.getRealPath("fileupload");

        boolean check = true;
        String fileLocation = null;
        String name = null;
        Map<String, String> mapReturnValue = new HashMap<>();
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            System.out.println("not having enctype multipart/form-data ");
            check = false;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxRequestSize);

        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (!item.isFormField()) {
                name = item.getName();
                if (name != null) {
                    if (StringUtils.isNotBlank(name)) {
                        File uploadedFile = new File(address + File.separator + path + File.separator + name);
//                    System.out.println(address + File.separator + path + File.separator + name);
                        fileLocation = address + File.separator + path + File.separator + name;

                        boolean isExist = uploadedFile.exists();

                        if (isExist) {
                            if (uploadedFile.delete())
                                item.write(uploadedFile);
                            else check = false;
                        } else {
                            item.write(uploadedFile);
                        }

                    }
                }

            } else {
                if (titleValue != null) {
                    String nameField = item.getFieldName();
                    String valueField = null;
                    try {
                        valueField = item.getString("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        log.error(e.getMessage(), e);
                    }
                    if (titleValue.contains(nameField)) {
                        mapReturnValue.put(nameField, valueField);
                    }
                }
            }
        }
        return new Object[]{check, fileLocation, name, mapReturnValue};
    }

    private void checkAndCreateFolder(String address, String path) {
        File folderRoot = new File(address);
        if (!folderRoot.exists()) {
            folderRoot.mkdirs();
        }
        File folderChild = new File(address + File.separator + path);
        if (!folderChild.exists()) {
            folderChild.mkdirs();
        }
    }
}

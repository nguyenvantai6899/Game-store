package com.gamesstorebe.util;

import jakarta.servlet.http.Part;

public class FileUtil {
        public static final String PATH_FILE_UPLOAD = "src/main/resources";

        public static String reNameFile(String fileName) {
            StringBuilder fileNamePart = new StringBuilder();
            if (!fileName.isEmpty()) {
                String[] arrImg = fileName.split("\\.");
                String fileExtension = arrImg[arrImg.length - 1];

                for (int i = 0; i < (arrImg.length - 1); i++) {
                    if (i == 0) {
                        fileNamePart = new StringBuilder(arrImg[i]);
                    } else {
                        fileNamePart.append("-").append(arrImg[i]);
                    }
                }
                fileNamePart.append("-").append(System.nanoTime()).append(".").append(fileExtension);
            }
            return fileNamePart.toString();
        }
        public static String getName(final Part part) {
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }
}

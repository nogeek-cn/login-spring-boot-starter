package com.darian.darianSecurityDemo.controller;

import com.darian.darianSercurityCore.exception.CustomException;
import com.darian.darianSercurityCore.exception.Response;
import com.darian.darianSercurityCore.log.LogCustom;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private final static String userDir = System.getProperty("user.dir");

    @PostMapping
    @LogCustom(ignore = true)
    public Response<Object> upload(MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String path = System.getProperty("user.dir");
        File localFile = new File(path, new Date().getTime() + ".txt");
        try {
            // 写到本地的文件里边
            file.transferTo(localFile);
        } catch (IOException e) {
            throw CustomException.generatorRuntimeException("上传文件异常");
        }
        return Response.success("xxxx");
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downLoad(@PathVariable String id) {
        System.out.println(userDir);
        try (InputStream inputStream = new FileInputStream(
                new File(userDir, "1.txt"))
        ) {
//            OutputStream outputStream = new FileOutputStream(new File(".."));
//            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            throw CustomException.generatorRuntimeException("文件错误");
        }
        try {
            FileInputStream is  = new FileInputStream("D:\\my_idea_workspace\\darian-security" +
                    "\\darian-security-demo\\src\\main\\resources\\application.properties");
            byte[] body =new byte[is.available()];
            is.read(body);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=text.txt")
                    .body(body);
        } catch (Exception e) {
            throw CustomException.generatorRuntimeException("文件错误");
        }

    }


}

package springMVC.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @RequestMapping("/down")
    public ResponseEntity<byte[]> file(HttpSession session) throws IOException {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件路径
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建输入流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //创建字节数组
        byte[] bytes = new byte[fileInputStream.available()];
        //将流读到字节数组中
        fileInputStream.read(bytes);
        //创建httpHeaders对象设置响应头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置下载方式和下载文件名称
        httpHeaders.add("Content-Disposition", "attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建responseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, statusCode);
        //关闭输入流
        fileInputStream.close();
        return responseEntity;
    }

    @RequestMapping("/up")
    public String up(MultipartFile photo, HttpSession session) throws IOException {
        //获取上传的文件的文件名称
        String fileName = photo.getOriginalFilename();
        //获取上传文件的后缀名
        String substring = fileName.substring(fileName.lastIndexOf("."));
        //将UUID+后缀名作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + substring;
        //通过servletContext获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String phtotPath = servletContext.getRealPath("photo");
        File file = new File(phtotPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = phtotPath + File.separator + fileName;
        photo.transferTo(new File(path));
        return "target";
    }
}

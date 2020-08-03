package com.den.controller;
import com.den.Entity.User;
import com.den.util.UserValidator;
import com.den.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;



@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private ServletContext servletContext;


    @Autowired
    public UserValidator userValidator;

    @Autowired
    public UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "usersList";
    }




    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable ("id") int id ,Model model){
        model.addAttribute("user1",userService.getById(id));
    return "showUser";
    }



    @PostMapping("/users")
    public String   deleteById(@RequestParam ("id") int id ) {
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/hello")
    public String getFile(Model model){
        Iterable<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "hello";
    }



    @PostMapping("/loadFile")
            public String uploadDate(@RequestParam ("fileName") MultipartFile file,
                                     Model model)
            throws IOException, ServletException {
        String filePath = "C:/Users/quit2/IdeaProjects/gogo/target/gogo-1.0-SNAPSHOT/WEB-INF/upload";
//
       if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
           List<User> users1 = userService.findAll();
           File uploadDir = new File(filePath);
           if (!uploadDir.exists()) {
               uploadDir.mkdir();
           }
           String uuidFile = UUID.randomUUID().toString();
           String resultFileName = uuidFile + "." + file.getOriginalFilename();
           file.transferTo(new File(filePath + "/" + resultFileName));
           for (User user : users1) {
               user.setFileName(resultFileName);
               userService.update(user);
           }

       }
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "hello";
    }


    @PostMapping("/deletePage")
    public String deletePage(@RequestParam ("id") int id,
                             Map<String,Object> model){
        User user = userService.getById(id);
        userService.deletePage(user);
        model.put("users",userService.findAll());
        return "hello";
    }


    @PostMapping("/restorePage")
    public String restorePage(@RequestParam("email") String email,
                              @RequestParam Map<String,Object> model){
        User user = userService.getByEmail(email);
        userService.restorePage(user);
        model.put("users",userService.findAll());
        return "redirect:/hello";
    }

    @PostMapping("/deleteImage")
    public String deleteImage(@RequestParam ("id") int id){
        User user = userService.getById(id);
        userService.deleteImage(user);
        return "redirect:/hello";
    }
    @GetMapping("/downloadFile")
    public void  downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response,
    HttpServletRequest request)
            throws IOException {

        Object attributeError = request.getAttribute("javax.servlet.error.exception");
        String filePath = "C:/Users/quit2/IdeaProjects/gogo/target/gogo-1.0-SNAPSHOT/WEB-INF/upload";
        File file = new File(filePath + File.separator + fileName);
        String mimeType = servletContext.getMimeType(file.getAbsolutePath());



        response.setContentLength((int) file.length());
        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());

        InputStream is;
        ServletOutputStream os;

            is = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] bufferData = new byte[1024];
            int read;
            while((read = is.read(bufferData))!= -1){
                os.write(bufferData, 0, read);
            }
            os.flush();
            os.close();
            is.close();
            System.out.println("File downloaded at client successfully");


    }


}

package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    // @Autowired annotation is used to autowire bean on the setter method
    @Autowired
    ImageService imageService;

    @Autowired
    CommentService commentService;

    //@RequestMapping annotation can be applied to class-level or method-level in controller
    @RequestMapping("/image/{imageId}/{imageTitle}/comments")
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String text, HttpSession session) {
        Image image =imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreatedDate(LocalDate.now());
        comment.setImage(image);
        comment.setUser(user);

        commentService.createComment(comment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}

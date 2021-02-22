package ImageHoster.service;

import ImageHoster.model.Tag;
import ImageHoster.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//@Service annotation is used with classes that provide some business functionalities
@Service
public class TagService {
    // @Autowired annotation is used to autowire bean on the setter method
    @Autowired
    private TagRepository tagRepository;

    //The method calls the getTagByName() method in the Repository and passes the title of the tag to be fetched
    public Tag getTagByName(String title) {
        return tagRepository.findTag(title);
    }

    //The method calls the createTag() method in the Repository and passes the tag for creation of tag
    public Tag createTag(Tag tag) {
        return tagRepository.createTag(tag);
    }
}

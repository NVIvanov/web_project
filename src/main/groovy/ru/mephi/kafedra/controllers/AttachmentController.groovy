package ru.mephi.kafedra.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import ru.mephi.kafedra.dto.AttachmentDTO
import ru.mephi.kafedra.services.AttachmentService

/**
 * @author nivanov
 * on 28.12.16.
 */
//@RestController("/user/files")
@Controller
class AttachmentController {

    @Autowired
    private AttachmentService attachmentService

    @GetMapping
    List<AttachmentDTO> getAttachments() {
        attachmentService.getForCurrentUser()
    }

    @PostMapping
    void createFile(@RequestParam("file") MultipartFile file) {
        attachmentService.createAttachment(file)
    }
}

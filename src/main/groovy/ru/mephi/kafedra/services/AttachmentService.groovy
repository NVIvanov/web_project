package ru.mephi.kafedra.services

import org.springframework.web.multipart.MultipartFile
import ru.mephi.kafedra.dto.AttachmentDTO

/**
 * @author nivanov
 * on 27.12.16.
 */
interface AttachmentService {
    AttachmentDTO createAttachment(MultipartFile file)

    void createUserFolder()

    List<AttachmentDTO> getForCurrentUser()
}
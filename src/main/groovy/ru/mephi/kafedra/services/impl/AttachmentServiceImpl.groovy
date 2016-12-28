package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.mephi.kafedra.data.entities.Attachment
import ru.mephi.kafedra.data.repositories.AttachmentRepository
import ru.mephi.kafedra.data.repositories.UserRepository
import ru.mephi.kafedra.dto.AttachmentDTO
import ru.mephi.kafedra.dto.UserDTO
import ru.mephi.kafedra.services.AttachmentService
import ru.mephi.kafedra.services.StorageService
import ru.mephi.kafedra.services.UserService

import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * @author nivanov
 * on 27.12.16.
 */
@Service
class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    UserService userService

    @Autowired
    AttachmentRepository attachmentRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    StorageService storageService

    @Override
    AttachmentDTO createAttachment(MultipartFile file) {
        UserDTO user = userService.getCurrentUser()
        def attachment = new Attachment()
        attachment.relativePath = user.username
        attachment.filename = file.originalFilename
        attachment.user = userRepository.findByUsername(user.username).orElseThrow {
            throw new IllegalStateException()
        }
        attachmentRepository.save(attachment)
        storageService.store(file)
        def dto = new AttachmentDTO()
        dto.filename = attachment.filename
        dto.owner = user.username
        dto.relativePath = attachment.relativePath
        return dto
    }

    @Override
    void createUserFolder() {
        Files.createFile(Paths.get("upload-dir/" + userService.getCurrentUser().username))
    }

    @Override
    List<AttachmentDTO> getForCurrentUser() {
        return attachmentRepository.findByUserUsername(userService.getCurrentUser().username)
                .stream().map { model ->
            def dto = new AttachmentDTO()
            dto.owner = model.user.username
            dto.filename = model.filename
            dto.relativePath = model.relativePath
            dto.id = model.id
            return dto
        }.collect(Collectors.toList())
    }
}

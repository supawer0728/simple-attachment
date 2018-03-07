package com.parfait.study.simpleattachment.shared.model.board;

import com.parfait.study.simpleattachment.shared.model.attachment.Attachment;
import lombok.Data;

@Data
public class WriterDto implements Attachment {
    private Long id;
    private String username;
    private String email;
}

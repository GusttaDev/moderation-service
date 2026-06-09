package com.linotech.algacomment.moderationservice.domain.service;

import com.linotech.algacomment.moderationservice.api.model.ModerationInput;
import com.linotech.algacomment.moderationservice.api.model.ModerationOutput;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModerationService {

    private static final List<String> UNWANTED_WORDS = List.of("ódio", "xingamento");
    private static final String APPROVED_REASON = "Comment validated successfully";
    private static final String REJECTED_REASON = "Comment contains inappropriate words";

    public ModerationOutput validate(ModerationInput input) {
        log.info("Validating comment with id: {}", input.getCommentId());

        boolean containsUnwantedWord = containsInappropriateContent(input.getText());

        if (containsUnwantedWord) {
            log.warn("Comment {} rejected due to inappropriate content", input.getCommentId());
            return buildRejectedOutput();
        }

        log.info("Comment {} approved", input.getCommentId());
        return buildApprovedOutput();
    }

    private boolean containsInappropriateContent(String text) {
        if (text == null || text.isBlank()) {
            return false;
        }

        String lowerCaseText = text.toLowerCase();
        return UNWANTED_WORDS.stream().anyMatch(lowerCaseText::contains);
    }

    private ModerationOutput buildApprovedOutput() {
        return ModerationOutput.builder()
                .approved(Boolean.TRUE)
                .reason(APPROVED_REASON)
                .build();
    }

    private ModerationOutput buildRejectedOutput() {
        return ModerationOutput.builder()
                .approved(Boolean.FALSE)
                .reason(REJECTED_REASON)
                .build();
    }
}

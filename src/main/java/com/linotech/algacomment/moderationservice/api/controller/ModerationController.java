package com.linotech.algacomment.moderationservice.api.controller;

import com.linotech.algacomment.moderationservice.api.model.ModerationInput;
import com.linotech.algacomment.moderationservice.api.model.ModerationOutput;
import com.linotech.algacomment.moderationservice.domain.service.ModerationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/moderate")
@RequiredArgsConstructor
public class ModerationController {

    private final ModerationService moderationService;

    @PostMapping
    public ModerationOutput validate(@Valid @RequestBody ModerationInput input) {
        return moderationService.validate(input);
    }
}

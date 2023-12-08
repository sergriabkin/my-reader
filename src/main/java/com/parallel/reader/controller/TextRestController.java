package com.parallel.reader.controller;

import com.parallel.reader.exception.TextNotFoundException;
import com.parallel.reader.model.Text;
import com.parallel.reader.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TextRestController {

    private final TextService textService;

    @GetMapping
    public List<Text> getAllTexts() {
        return textService.getAllTexts();
    }

    @GetMapping("/{id}")
    public Text getTextById(@PathVariable long id) {
        return textService.getTextById(id)
                .orElseThrow(() -> new TextNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Text saveText(@RequestBody Text text) {
        return textService.saveText(text);
    }

    @PutMapping("/{id}")
    public Text updateText(@PathVariable long id, @RequestBody Text text) {
        return textService.updateText(id, text)
                .orElseThrow(() -> new TextNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteText(@PathVariable long id) {
        textService.deleteText(id);
    }
}

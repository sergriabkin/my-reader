package com.parallel.reader.controller;

import com.parallel.reader.model.Text;
import com.parallel.reader.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;

    @GetMapping("/textView")
    public String textView(Model model) {
        // For demonstration, let's assume text IDs 1 and 2 are the texts to be displayed
        Long primaryTextId = 1L;
        Long secondaryTextId = 2L;

        Text primaryText = textService.getTextById(primaryTextId).orElse(null);
        Text secondaryText = textService.getTextById(secondaryTextId).orElse(null);

        model.addAttribute("primaryText", primaryText != null ? primaryText.getContent() : "Primary text not found.");
        model.addAttribute("secondaryText", secondaryText != null ? secondaryText.getContent() : "Secondary text not found.");

        return "textView";
    }

    @GetMapping("/uploadText")
    public String showUploadForm(Model model) {
        model.addAttribute("text", new Text());
        return "uploadText";
    }

    @PostMapping("/uploadText")
    public String uploadText(@ModelAttribute Text text) {
        textService.saveText(text);
        return "redirect:/textView";
    }
}

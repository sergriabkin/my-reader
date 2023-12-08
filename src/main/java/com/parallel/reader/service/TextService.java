package com.parallel.reader.service;

import com.parallel.reader.model.Text;
import com.parallel.reader.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TextService {

    private final TextRepository textRepository;

    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    public Text saveText(Text text) {
        return textRepository.save(text);
    }

    public Optional<Text> getTextById(Long id) {
        return textRepository.findById(id);
    }

    public Optional<Text> updateText(Long id, Text newText) {
        return textRepository.findById(id)
                .map(text -> updateTextFields(newText, text))
                .map(textRepository::save);
    }

    private Text updateTextFields(Text newText, Text text) {
        text.setContent(newText.getContent());
        text.setLanguage(newText.getLanguage());
        return text;
    }

    public void deleteText(Long id) {
        textRepository.deleteById(id);
    }
}

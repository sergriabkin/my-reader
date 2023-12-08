package com.parallel.reader.service;

import com.parallel.reader.model.Text;
import com.parallel.reader.repository.TextRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TextServiceTest {

    @Mock
    private TextRepository textRepository;

    @InjectMocks
    private TextService textService;

    private Text text;

    @BeforeEach
    void setUp() {
        text = new Text();
        text.setId(1L);
        text.setContent("Sample content");
    }

    @Test
    void whenSaveText_thenTextIsSaved() {
        when(textRepository.save(any(Text.class))).thenReturn(text);
        Text savedText = textService.saveText(text);
        assertNotNull(savedText);
        assertEquals("Sample content", savedText.getContent());
    }

    @Test
    void givenTextId_whenGetTextById_thenTextIsReturned() {
        when(textRepository.findById(1L)).thenReturn(Optional.of(text));
        Optional<Text> foundText = textService.getTextById(1L);
        assertTrue(foundText.isPresent());
        assertEquals("Sample content", foundText.get().getContent());
    }


    @Test
    void givenUpdatedText_whenUpdateText_thenTextIsUpdated() {
        Text updatedText = new Text();
        updatedText.setId(1L);
        updatedText.setContent("Updated content");

        when(textRepository.findById(1L)).thenReturn(Optional.of(text));
        when(textRepository.save(any(Text.class))).thenReturn(updatedText);

        Optional<Text> result = textService.updateText(1L, updatedText);

        assertTrue(result.isPresent());
        assertEquals("Updated content", result.get().getContent());
    }

    @Test
    void whenDeleteText_thenTextRepositoryDeleteIsCalled() {
        doNothing().when(textRepository).deleteById(1L);
        textService.deleteText(1L);
        verify(textRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenGetAllTexts_thenAllTextsAreReturned() {
        List<Text> texts = Arrays.asList(text, new Text());
        when(textRepository.findAll()).thenReturn(texts);

        List<Text> retrievedTexts = textService.getAllTexts();

        assertEquals(2, retrievedTexts.size());
    }

}

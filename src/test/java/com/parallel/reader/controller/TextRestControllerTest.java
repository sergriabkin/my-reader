package com.parallel.reader.controller;

import com.parallel.reader.model.Text;
import com.parallel.reader.service.TextService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TextRestControllerTest {

    @Mock
    private TextService textService;

    @InjectMocks
    private TextRestController textRestController;

    @Test
    void whenGetAllTexts_thenAllTextsAreReturned() {
        List<Text> texts = Arrays.asList(new Text(), new Text());
        when(textService.getAllTexts())
                .thenReturn(texts);

        List<Text> result = textRestController.getAllTexts();

        assertEquals(2, result.size());
        verify(textService).getAllTexts();
    }

    @Test
    void whenGetTextById_thenTextIsReturned() {
        long id = 1L;
        Text text = new Text();
        text.setId(id);
        when(textService.getTextById(id))
                .thenReturn(Optional.of(text));

        Text result = textRestController.getTextById(id);

        assertEquals(id, result.getId());
        verify(textService).getTextById(id);
    }

    @Test
    void whenSaveText_thenTextIsSaved() {
        Text text = new Text();
        when(textService.saveText(any(Text.class)))
                .thenReturn(text);

        Text result = textRestController.saveText(text);

        assertNotNull(result);
        verify(textService).saveText(text);
    }

    @Test
    void whenUpdateText_thenTextIsUpdated() {
        long id = 1L;
        Text updatedText = new Text();
        updatedText.setId(id);
        when(textService.updateText(eq(id), any(Text.class)))
                .thenReturn(Optional.of(updatedText));

        Text result = textRestController.updateText(id, updatedText);

        assertEquals(id, result.getId());
        verify(textService).updateText(id, updatedText);
    }

    @Test
    void whenDeleteText_thenTextIsDeleted() {
        long id = 1L;
        doNothing().when(textService).deleteText(id);

        textRestController.deleteText(id);

        verify(textService).deleteText(id);
    }

}

package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class WordService {
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository, UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    public int getAllUserWords() {
        return this.wordRepository.findAllByAddedBy(currentUser.getId()).size();
    }

    public int germanWordsCount() {
        int germanWordsCounter = 0;
        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        for (Word word : allByAddedBy) {
            if (word.getLanguage().getName().equals(LanguageNameEnum.GERMAN)) {
                germanWordsCounter++;
            }
        }
        return germanWordsCounter;
    }

    public int spanishWordsCount() {
        int spanishWordsCounter = 0;
        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        for (Word word : allByAddedBy) {
            if (word.getLanguage().getName().equals(LanguageNameEnum.SPANISH)) {
                spanishWordsCounter++;
            }
        }
        return spanishWordsCounter;
    }

    public int frenchWordsCount() {
        int frenchWordsCounter = 0;
        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        for (Word word : allByAddedBy) {
            if (word.getLanguage().getName().equals(LanguageNameEnum.FRENCH)) {
                frenchWordsCounter++;
            }
        }
        return frenchWordsCounter;
    }

    public int italianWordsCount() {
        int italianWordsCounter = 0;
        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        for (Word word : allByAddedBy) {
            if (word.getLanguage().getName().equals(LanguageNameEnum.ITALIAN)) {
                italianWordsCounter++;
            }
        }
        return italianWordsCounter;
    }


    public void addWord(AddWordDTO addWordDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(currentUser.getUsername());
        Language byName = this.languageRepository.findByName(addWordDTO.getLanguage());

        if (byUsername.isEmpty()) {
            throw new NoSuchElementException();
        }

        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        Word word = new Word();
        word.setTerm(addWordDTO.getTerm());
        word.setTranslation(addWordDTO.getTranslation());
        word.setExample(addWordDTO.getExample());
        word.setInputDate(addWordDTO.getInputDate());
        word.setLanguage(byName);

        allByAddedBy.add(word);
        this.wordRepository.save(word);
    }
}

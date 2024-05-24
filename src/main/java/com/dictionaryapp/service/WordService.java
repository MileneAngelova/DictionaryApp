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
import java.util.stream.Collectors;


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

    public long allWordsCount() {
        return this.wordRepository.count();
    }

    public List<Word> getAllWords() {
        return this.wordRepository.findAll();
    }

    public long germanWordsCount() {
        List<Word> germanWords = this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.GERMAN);
        return germanWords.size();
    }

    public int spanishWordsCount() {
        List<Word> spanishWords = this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.SPANISH);
        return spanishWords.size();
    }

    public int frenchWordsCount() {
        List<Word> frenchWords = this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.FRENCH);
        return frenchWords.size();
    }

    public int italianWordsCount() {
        List<Word> italianWords = this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.GERMAN);
        return italianWords.size();
    }

    public void addWord(AddWordDTO addWordDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(currentUser.getUsername());
        Language byName = this.languageRepository.findByName(addWordDTO.getLanguage());

        if (byUsername.isEmpty()) {
            throw new NoSuchElementException();
        }

        User addedBy = this.modelMapper.map(byUsername, User.class);
        List<Word> allByAddedBy = this.wordRepository.findAllByAddedBy(currentUser.getId());
        Word word = new Word();

        word.setTerm(addWordDTO.getTerm());
        word.setTranslation(addWordDTO.getTranslation());
        word.setExample(addWordDTO.getExample());
        word.setInputDate(addWordDTO.getInputDate());
        word.setAddedBy(addedBy);
        word.setLanguage(byName);

        System.out.println(currentUser.getId());
        System.out.println(currentUser.getUsername());
        allByAddedBy.add(word);
        this.wordRepository.save(word);
    }

    public void deleteSingleWord(Word word) {
        this.wordRepository.delete(word);
    }
}

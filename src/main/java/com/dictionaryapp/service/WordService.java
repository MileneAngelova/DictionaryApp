package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.AddWordDTO;
import com.dictionaryapp.model.dto.WordDTO;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WordService {
    private final WordRepository wordRepository;
    private final LanguageRepository languageRepository;
    private final CurrentUser currentUser;

    private final ModelMapper modelMapper;

    public WordService(WordRepository wordRepository, LanguageRepository languageRepository, CurrentUser currentUser, ModelMapper modelMapper) {
        this.wordRepository = wordRepository;
        this.languageRepository = languageRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    public Long getAllWords() {
        return this.wordRepository.count();
    }

    public long germanWordsCount() {
        return languageRepository.findAllByName(LanguageNameEnum.GERMAN).get().getWords().size();
    }

    public long spanishWordsCount() {
        return languageRepository.findAllByName(LanguageNameEnum.SPANISH).get().getWords().size();
    }

    public long frenchWordsCount() {
        return languageRepository.findAllByName(LanguageNameEnum.FRENCH).get().getWords().size();
    }

    public long italianWordsCount() {
//        List<Word> allByLanguageName = wordRepository.findAllByLanguage_Name(LanguageNameEnum.ITALIAN);
//
//        long counter = 0;
//        for (int i = 0; i < allByLanguageName.size(); i++) {
//            counter++;
//        }
//        return counter;
//       return wordRepository.findAllByLanguage_Name(LanguageNameEnum.ITALIAN)
//                .stream().map(l -> l.getLanguage().getWords()).count();
        return languageRepository.findAllByName(LanguageNameEnum.ITALIAN).get().getWords().size();
    }

    public List<Language> getAllLanguages() {
        return this.wordRepository.findAllByAddedBy(currentUser.getId()).stream().map(Word::getLanguage).collect(Collectors.toList());
    }

    public void addWord(AddWordDTO addWordDTO) {
        this.currentUser.getWords().add(this.modelMapper.map(addWordDTO, Word.class));
    }
}

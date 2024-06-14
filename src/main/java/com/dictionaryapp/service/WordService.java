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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
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

    public List<Word> getAllWords() {
        return this.wordRepository.findAll();
    }

    public List<Word> germanWords() {
        return this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.GERMAN);
    }

    public List<Word> spanishWords() {
        return this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.SPANISH);
    }

    public List<Word> frenchWordsCount() {
        return this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.FRENCH);
    }

    public List<Word> italianWords() {
        return this.wordRepository.findAllByLanguage_Name(LanguageNameEnum.ITALIAN);
    }

    public void addWord(AddWordDTO addWordDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(currentUser.getUsername());
        Language byName = this.languageRepository.findByName(addWordDTO.getLanguage());

        if (byUsername.isEmpty()) {
            throw new NoSuchElementException();
        }

        User addedBy = this.modelMapper.map(byUsername, User.class);
        List<Word> allByAddedBy = this.wordRepository.getAllByAddedBy(currentUser.getId());
        Word word = new Word();

        word.setTerm(addWordDTO.getTerm());
        word.setTranslation(addWordDTO.getTranslation());
        word.setExample(addWordDTO.getExample());
        word.setInputDate(addWordDTO.getInputDate());
        word.setAddedBy(addedBy);
        word.setLanguage(byName);

        allByAddedBy.add(word);
        this.wordRepository.save(word);
        this.userRepository.save(addedBy);
    }

    @Transactional
    public void deleteSingleWord(Long id) {
        Word wordToDelete = wordRepository.findById(id).orElse(null);
        User user = wordToDelete.getAddedBy();
        Set<Word> allUserWords = user.getWords();
        allUserWords.remove(wordToDelete);
        this.userRepository.save(user);
        this.wordRepository.deleteById(id);
    }
    @Transactional
    public void deleteAllWords() {
        this.userRepository.findAll()
                .stream().map(User::getWords)
                .forEach(this.wordRepository::deleteAll);
        this.wordRepository.deleteAll();
    }

}

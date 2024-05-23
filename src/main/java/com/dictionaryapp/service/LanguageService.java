package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.repo.LanguageRepository;
import com.dictionaryapp.repo.WordRepository;
import com.dictionaryapp.session.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final CurrentUser currentUser;

    public LanguageService(LanguageRepository languageRepository, CurrentUser currentUser, WordRepository wordRepository) {
        this.languageRepository = languageRepository;
        this.currentUser = currentUser;
        this.wordRepository = wordRepository;
    }


    private final WordRepository wordRepository;

    public void seedLanguages() {
        if (this.languageRepository.count() == 0) {
            for (LanguageNameEnum name : LanguageNameEnum.values()) {
                Language language = new Language();
                language.setName(name);

                if (language.getName().equals(LanguageNameEnum.GERMAN)) {
                    language.setDescription("A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.");
                } else if (language.getName().equals(LanguageNameEnum.SPANISH)) {
                    language.setDescription("A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.");
                } else if (language.getName().equals(LanguageNameEnum.FRENCH)) {
                    language.setDescription("A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.");
                } else if (language.getName().equals(LanguageNameEnum.ITALIAN)) {
                    language.setDescription("A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history.");
                }
                this.languageRepository.save(language);
            }
        }
    }

    public List<LanguageNameEnum> getAllLanguages() {
//        return currentUser.getWords().stream().map(w -> w.getLanguage().getName()).collect(Collectors.toList());
    return this.languageRepository.findAll().stream().map(Language::getName).collect(Collectors.toList());
    }

}

package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageNameEnum;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> getAllByAddedBy(Long id);

    List<Word> findAllByLanguage_Name(LanguageNameEnum languageNameEnum);
    Optional<Word> findByTerm(Word word);

    Optional<Word> findById(Long id);
}

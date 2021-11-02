package ELpharmacie.Service;


import ELpharmacie.entities.Dictionary;

import java.util.List;

public interface DictionaryManagerInterface {
    List<String> retrieveAllBadWords();
    Dictionary addBadWord(Dictionary w);
    void deleteBadWord(Long id);
    void deleteBadWord(String id);
    Dictionary updateBadWord(Dictionary w);
    Dictionary FindBadWord(Long id);
    Dictionary FindBadWord(String id);
}

package com.example.fush.service;

import com.example.fush.dao.FushRepository;
import com.example.fush.model.Fush;
import org.springframework.stereotype.Service;

@Service
public class FushService {

    private FushRepository fushRepository;

    public FushService(FushRepository fushRepository) {
        this.fushRepository = fushRepository;
    }

    public String findOne(final Long id) {
        final Iterable<Fush> allFushes = fushRepository.findAll();

        String returnValue = null;
        for (final Fush fush : allFushes) {
            if (fush.getId() == id) {
                returnValue = fush.getName();
            }
        }

        return returnValue;
    }

    public boolean checkForDuplicates() {
        final Iterable<Fush> allFushes = fushRepository.findAll();
        final Iterable<Fush> allFushes2 = fushRepository.findAll();

        boolean hasDuplicates = false;
        for (final Fush fush : allFushes) {
            for (final Fush fush2 : allFushes2) {
                if (fush.getId() == fush2.getId()) {
                    hasDuplicates = true;
                }
            }
        }

        return hasDuplicates;
    }

}

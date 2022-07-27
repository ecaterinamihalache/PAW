package ro.tuiasi.students.interfaces;

import ro.tuiasi.students.models.Preference;

import java.util.Optional;

public interface PreferencesServiceInterface
{
    Preference createPreferences(Preference preferences, Integer id);
    Optional<Preference> getPreferenceById(Integer id);
    void deletePreferenceById(Integer id);
    Preference getPreferencesForAStudent( Optional<Preference> preferenceOptional);
}

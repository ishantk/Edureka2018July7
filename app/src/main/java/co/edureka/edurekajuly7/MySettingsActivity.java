package co.edureka.edurekajuly7;

import android.app.Activity;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MySettingsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // No setContentView

        getFragmentManager().beginTransaction().add(android.R.id.content,new MySettingsFragment()).commit();
    }


    public static class MySettingsFragment extends PreferenceFragment{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings_prefs);

            EditTextPreference eTxtPref = (EditTextPreference) findPreference("keySecret");
            Toast.makeText(getActivity(),"Value: "+eTxtPref.getText(),Toast.LENGTH_LONG).show();

        }
    }
}

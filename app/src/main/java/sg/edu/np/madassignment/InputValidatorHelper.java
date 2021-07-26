package sg.edu.np.madassignment;

import android.text.TextUtils;

public class InputValidatorHelper {
    public boolean isNullOrEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public boolean isNumeric(String string){
        return TextUtils.isDigitsOnly(string);
    }
}

package MainForm;

import DataBaseControl.GetActiveProfile;

public class ActiveProfile {
    private GetActiveProfile request = new GetActiveProfile();
    private final String ProfileName = request.getName();

    public String getActiveProfile() {
        return ProfileName;
    }
}

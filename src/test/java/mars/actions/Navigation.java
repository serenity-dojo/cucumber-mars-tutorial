package mars.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractions;

public class Navigation extends UIInteractions {

    @Step
    public void openMarsApplication() {
        openUrl("http://marsair.recruiting.thoughtworks.net/shannonaung");
    }
}

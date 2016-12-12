package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerTest {
    private Controller controller = new Controller();

    @Test
    public void testHandleCountryNameAction()
    {
        controller.handleCountryNameAction();
        assertEquals(50, controller.list.size());
    }

    @Test
    public void testHandleRegionNameAction()
    {
        controller.handleRegionNameAction("East Asia & Pacific");
        assertEquals(11, controller.list.size());
        controller.handleRegionNameAction("Europe & Central Asia");
        assertEquals(21, controller.list.size());
        controller.handleRegionNameAction("Latin America & Caribbean ");
        assertEquals(6, controller.list.size());
        controller.handleRegionNameAction("Middle East & North Africa");
        assertEquals(5, controller.list.size());
        controller.handleRegionNameAction("North America");
        assertEquals(2, controller.list.size());
        controller.handleRegionNameAction("South Asia");
        assertEquals(3, controller.list.size());
        controller.handleRegionNameAction("Sub-Saharan Africa ");
        assertEquals(2, controller.list.size());
    }

    @Test
    public void testHandleIncomeLevelAction() {
        controller.handleIncomeLevelAction("High income");
        assertEquals(29, controller.list.size());
        controller.handleIncomeLevelAction("Lower middle income");
        assertEquals(8, controller.list.size());
        controller.handleIncomeLevelAction("Upper middle income");
        assertEquals(13, controller.list.size());
    }
}

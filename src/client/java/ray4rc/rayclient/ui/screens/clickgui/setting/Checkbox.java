package ray4rc.rayclient.ui.screens.clickgui.setting;

import net.minecraft.client.gui.DrawContext;
import ray4rc.rayclient.modules.settings.BooleanSetting;
import ray4rc.rayclient.modules.settings.Setting;
import ray4rc.rayclient.ui.screens.clickgui.ModuleButton;

import java.awt.*;


public class Checkbox extends Component {
    private BooleanSetting boolSet = (BooleanSetting) setting;
    public Checkbox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.boolSet = (BooleanSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int left = parent.parent.x;
        int top = parent.parent.y + parent.offset + offset;
        int right = left + parent.parent.width;
        int bottom = top + parent.parent.height;

        context.fill(left, top, right, bottom, new Color(0, 0, 0, 120).getRGB());
        context.drawText(tr, boolSet.getName() + ": " + boolSet.isEnabled(), left + 2, top + 2, Color.WHITE.getRGB(), false);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0)
            boolSet.toggle();

        super.mouseClicked(mouseX, mouseY, button);
    }
}

package ray4rc.rayclient.modules;

import net.minecraft.client.MinecraftClient;

public class Mod {
    private String name;
    private String description;

    public Category category;
    private int key;
    private boolean enabled = false;

    protected MinecraftClient mc = MinecraftClient.getInstance();

    public void toggle(){
        this.enabled = !this.enabled;

        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) onEnable();
        else onDisable();
    }

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }
    public enum Category {
        COMBAT,
        PLAYER,
        MOVEMENT,
        RENDER,
        WORLD
    }
}

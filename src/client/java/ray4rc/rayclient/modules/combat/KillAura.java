package ray4rc.rayclient.modules.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;
import ray4rc.rayclient.modules.Mod;
import ray4rc.rayclient.modules.settings.BooleanSetting;
import ray4rc.rayclient.modules.settings.ModeSetting;
import ray4rc.rayclient.modules.settings.NumberSetting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class KillAura extends Mod {
    public BooleanSetting critical = new BooleanSetting("Criticals", false);
    public NumberSetting chargeProgress = new NumberSetting("Speed", 0, 1, 0.9, 0.05);
    public ModeSetting mode = new ModeSetting("Mode", "Single", "Single", "Switch", "Multi");
    public NumberSetting range = new NumberSetting("Range", 0, 5, 5, 0.05);

    public KillAura() {
        super("KillAura", "monkey see monkey kill", Category.COMBAT);
        this.setKey(GLFW.GLFW_KEY_R);
        addSettings(critical, chargeProgress, range, mode);
    }

    @Override
    public void onTick() {
        assert mc.player != null;
        assert mc.world != null;
        assert mc.interactionManager != null;

        Vec3d playerPos = mc.player.getPos();
        float playerYaw = mc.player.getHeadYaw();

        Iterable<Entity> entities = mc.world.getEntities();
        List<EntityAttribute> targets = new ArrayList<>();

        if (mc.player.getAttackCooldownProgress(0.0f) < chargeProgress.getValue()) {
            return;
        }

        if (critical.isEnabled()) {
            if (mc.player.getVelocity().y >= -0.08f || mc.player.isOnGround()) {
                return;
            }
        }

        for (Entity entity : entities) {
            if (!entity.isAlive() || entity == mc.player || !(entity instanceof LivingEntity)) {
                continue;
            }
            float dist = entity.distanceTo(mc.player);
            if (dist > range.getValue()) {
                continue;
            }

            Vec3d entityPos = entity.getPos();
            double diffX = entityPos.x - playerPos.x;
            double diffZ = entityPos.z - playerPos.z;

            float yaw = MathHelper.wrapDegrees((float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f);
            yaw = MathHelper.wrapDegrees(yaw - playerYaw);
            yaw = Math.abs(yaw);
            float health = ((LivingEntity) entity).getHealth();

            targets.add(new EntityAttribute(entity, dist, health, yaw));
        }
        if (targets.isEmpty()) {
            return;
        }

        targets.sort(Comparator.comparing(EntityAttribute::getYaw));

        Entity target = targets.getFirst().getEntity();


        mc.interactionManager.attackEntity(mc.player, target);
        mc.player.swingHand(Hand.MAIN_HAND);

    }

    static class EntityAttribute {
        Entity entity;
        float distance;
        float health;
        float yaw;

        public EntityAttribute(Entity entity, float distance, float health, float yaw) {
            this.entity = entity;
            this.distance = distance;
            this.health = health;
            this.yaw = yaw;
        }

        public Entity getEntity() {
            return entity;
        }

        public float getDistance() {
            return distance;
        }

        public float getHealth() {
            return health;
        }

        public float getYaw() {
            return yaw;
        }
    }
}
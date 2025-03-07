package ray4rc.rayclient.modules.settings;

public class NumberSetting extends Setting {
    private double min, max, increment;
    private double value;

    public NumberSetting(String name, double min, double max, double defaultValue, double increment) {
        super(name);
        this.min = min;
        this.max = max;
        this.increment = increment;
        this.value = defaultValue;
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.max(min, value));
    }

    public double getValue() {
        return value;
    }

    public double getValueFloat() {
        return (float) value;
    }

    public int getValueInt() {
        return (int) value;
    }

    public double getIncrement() {
        return increment;
    }


    public void setValue(double value) {
        value = clamp(value, this.min, this.max);
        value = Math.round(value * (1.0f / this.increment)) / (1.0f / this.increment);
        this.value = value;
    }

    public void increment(boolean positive) {
        if (positive) {
            setValue(getValue() + getIncrement());
        } else {
            setValue(getValue() - getIncrement());
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}





package common.data;

public interface Validatable {
    /**
     * validates all fields after json deserialization
     *
     * @return
     */
    boolean validate();
}

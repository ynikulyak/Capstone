package capstone.preconditions;

import java.util.Collection;

/**
 * Utility for validating preconditions.
 */
public final class Preconditions {
  private Preconditions() {
  }

  public static void check(String object, String valueName) {
    if (object == null || object.trim().isEmpty()) {
      throw new IllegalArgumentException(valueName + " can't be null or empty");
    }
  }

  public static void check(String object, int maxLength, String valueName) {
    if (object == null || object.trim().isEmpty()) {
      throw new IllegalArgumentException(valueName + " can't be null or empty");
    }
    checkNotLonger(object, maxLength, valueName);
  }

  public static void checkNotLonger(String object, int maxLength, String valueName) {
    if (object != null && object.length() > maxLength) {
      throw new IllegalArgumentException(valueName + " can't be longer than " + maxLength);
    }
  }

  public static void check(Collection<?> object, String valueName) {
    if (object == null || object.isEmpty()) {
      throw new IllegalArgumentException(valueName + " can't be null or empty");
    }
  }

  public static void check(Boolean b, String message) {
    if (b == null || Boolean.FALSE.equals(b)) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkNotNull(Object object, String valueName) {
    if (object == null) {
      throw new IllegalArgumentException(valueName + " can't be null");
    }
  }
}

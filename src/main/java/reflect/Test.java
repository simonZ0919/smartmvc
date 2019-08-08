package reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author simon
 * set lifetime of @Test to runtime
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
	// attribute of annotation
	public String value();
}

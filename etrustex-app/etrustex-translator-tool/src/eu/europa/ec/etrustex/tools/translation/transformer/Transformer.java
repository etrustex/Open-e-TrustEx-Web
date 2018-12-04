package eu.europa.ec.etrustex.tools.translation.transformer;

import java.util.Collection;

/**
 * A transformer for virtually anything.
 *
 * @author keschma
 *
 */
public interface Transformer<I, O> {
	
	/**
	 * Transforms some objects into other objects.
	 * @param objects the objects
	 * @throws Exception on underlying errors
	 */
	public Collection<O> transform(Collection<? extends I> objects)	throws Exception;

}

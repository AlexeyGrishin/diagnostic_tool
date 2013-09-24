package com.actimind.diagnostic.listeners;

import com.actimind.diagnostic.db.Stat;

/**
 * Is called any time when some stat is collected and when its state is changed.
 * Single listener is called for any stat, it is impossible to listen for single stat only.
 *
 * In order to implement your own listener and use it you'll need to:
 *   1. implement this interface
 *   2. put your class into the same package as this interface is located in
 *   3. include it into monitor.xml:
 * <code>
 *
 *    <monitor>
 *       ...
 *       <listeners>
 *          <listen with="[class-name]"/>
 *       </listeners>
 *    </monitor>
 *
 * </code>
 *   4. if you need to specify some external params, make your class implementing the {@link com.actimind.diagnostic.stats.ParamsAware} interface
 *      and add parameters the following way:
 * <code>
 *
 *    <monitor>
 *       ...
 *       <listeners>
 *          <listen with="[class-name]">
 *              <param name="param-name">value</param>
 *              <param name="param-name">value</param>
 *          </listen>
 *       </listeners>
 *    </monitor>
 *
 * </code>
 */
public interface StatProcessorListener {

    /**
     * Is called when new value for stat is collected.
     * @param stat stat that was collected
     */
    public void onNewStatCollected(Stat stat);

    /**
     * Is called in the following cases:
     * <list>
     *  <item>When stat's status was changed to normal or to error in comparison with previous collected stat
     *  <item>When stat's error description was changed
     * </list>
     * @param stat
     */
    public void onStatStateChanged(Stat stat);

    /**
     * Is called when stat is collected first time after monitor run
     * @param stat
     */
    public void onFirstStat(Stat stat);

    /**
     * Is called when value of stat was changed
     * @param stat
     */
    void onStatValueChanged(Stat stat);
}

package de.saring.sportstracker.data;

import de.saring.util.data.IdObject;
import de.saring.util.data.Nameable;

/**
 * This class contains all informations of a subtype of a sport type (e.g.
 * mountainbiking for sport type cycling).
 *
 * @author Stefan Saring
 * @version 1.0
 */
public final class SportSubType extends IdObject implements Nameable, Cloneable {

    /**
     * Name of sport subtype.
     */
    private String name;

    /**
     * Standard c'tor.
     *
     * @param id the ID of the object
     */
    public SportSubType(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a deep clone copy of this SportSubType object.
     *
     * @return clone of this object
     */
    @Override
    public SportSubType clone() {
        try {
            // nothing more to do (contains only primitives and an immutable string)
            // (the exception can't happen)
            return (SportSubType) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(this.getClass().getName()).append(":\n");
        sBuilder.append(" [id=").append(this.getId()).append("\n");
        sBuilder.append("  name=").append(this.name).append("]\n");
        return sBuilder.toString();
    }
}

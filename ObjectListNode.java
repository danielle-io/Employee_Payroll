
public class ObjectListNode implements ObjectListNodeInterface {
    private Object info;
    private ObjectListNode next;

    // Create Job object with a line from file
    // Employee employee = new Employee();

    // Default ctor
    public ObjectListNode() {
        info = null;
        next = null;
    }

    // One-arg ctor
    public ObjectListNode (Object o) {
        info = o;
        next = null;
    }

    // Two-arg ctor
    public ObjectListNode (Object o, ObjectListNode p) {
        info = o;
        next = p;
    }

    /**
     * Sets info field
     * @param o Object containing data
     */
    public void setInfo(Object o) {
        info = o;
    }

    /**
     * Returns object in info field
     * @return Object representing node's information
     */
    public Object getInfo() {
        return info;
    }

    /**
     * Sets next field
     * @param p ObjectListNode containing the node to set next to
     */
    public void setNext(ObjectListNode p) {
        next = p;
    }

    /**
     * Returns object in info field
     * @return Object representing the next node in the linked list
     */
    public ObjectListNode getNext() {
        return next;
    }
}


// -----------------------------------------------------
// Assignment 4
// Part 2: Linked List
// Written by: Kevin Tran - 40209451
// -----------------------------------------------------
// This assignment is to practice using LinkedList. This program reads through a list of cellphone information and creates
// a LinkedList with that information.
package pack;

import java.util.NoSuchElementException;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 15 2022
 */
public class CellList {
    /**
     * Inner CellNode class
     * Privacy leak: CellNode class is available for direct use anywhere in the CellList class, but not outside that class.
     */
    private class CellNode {
        CellPhone cellphone;
        CellNode nextNode;

        /**
         * Default constructor
         */
        public CellNode() {
            this.cellphone = null;
            this.nextNode = null;
        }

        /**
         * Parameterized constructor
         *
         * @param cellphone
         * @param nextNode
         */
        public CellNode(CellPhone cellphone, CellNode nextNode) {
            this.cellphone = cellphone;
            this.nextNode = nextNode;
        }

        /**
         * Copy constructor
         *
         * @param other
         */
        public CellNode(CellNode other) {
            this.cellphone = other.cellphone;
            this.nextNode = other.nextNode;
        }

        /**
         * Clone method
         *
         * @return CellNode
         */
        public CellNode clone() {
            // Use CellPhone copy constructor to make a copy of the cellphone from specific serial number
            return new CellNode(new CellPhone(this.cellphone, this.cellphone.getSerialNum()), this.nextNode);
        }

        /**
         * Get cellphone
         *
         * @return CellPhone
         */
        public CellPhone getCellphone() {
            return this.cellphone;
        }

        /**
         * Set CellPhone
         *
         * @param cell
         */
        public void setCellphone(CellPhone cell) {
            this.cellphone = cell;
        }

        /**
         * Get NextNode
         *
         * @return NextNode
         */
        public CellNode getNextNode() {
            return nextNode;
        }

        /**
         * Set NextNode
         *
         * @param nextNode
         */
        public void setNextNode(CellNode nextNode) {
            this.nextNode = nextNode;
        }

        /**
         * Equals method
         *
         * @param objRef
         * @return boolean
         */
        public boolean equals(Object objRef) {
            if (this == objRef)
                return  true;
            if (objRef == null)
                return false;
            if (this.getClass() != objRef.getClass())
                return false;
            CellNode node = (CellNode) objRef;
            if (nextNode == null || node.nextNode == null) {
                return nextNode == node.nextNode;
            } else {
                return cellphone.equals(node.cellphone) && nextNode.equals(node.nextNode);
            }
        }
    }

    private CellNode head;
    private int size;

    /**
     * CellList default constructor
     */
    public CellList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * CellList parameterized constructor
     *
     * @param list
     */
    public CellList(CellList list) {
        // Initialize a copy to the head of the copied list
        CellNode nodeCopy = list.head;
        for (int i = 0; i < list.size; i++) {
            if (nodeCopy != null) {
                this.add(nodeCopy.cellphone);
                nodeCopy = nodeCopy.nextNode;
            }
        }
    }

    /**
     * Method adds cellphone at the beginning of the list
     *
     * @param cell
     */
    public void addToStart(CellPhone cell) {
        // Add the new node to the head
        // Assign previous head as the nextNode
        head = new CellNode(cell, head);
        size++;
    }

    /**
     * Method adds cellphone at a specific position of the list
     *
     * @param cell
     * @param index
     */
    public void insertAtIndex(CellPhone cell, int index) {
        if (size == 0) {
            addToStart(cell);
        } else {
            // Assign related nodes
            CellNode previousNode = nodeAtIndex(index-1, index);
            CellNode indexNode = previousNode.nextNode;
            // New node points to node at index
            CellNode insertNode = new CellNode(cell, indexNode);
            size++;
            // Point previous node to new node
            previousNode.nextNode = insertNode;

            // Avoid privacy leaks
            previousNode = null;
            indexNode = null;
            insertNode = null;
        }
    }

    /**
     * Deletes node at certain index
     *
     * @param index
     */
    public void deleteFromIndex(int index) {
        try {
            if (size == 0) {
                throw new NullPointerException();
            }
            // Get previous node
            CellNode previousNode = nodeAtIndex(index-1, index);
            // Skip next node and assign the one after that to the previous node
            previousNode.nextNode = previousNode.nextNode.nextNode;
            size--;

            // Avoid privacy leaks
            previousNode = null;
        } catch (NullPointerException e) {
            System.out.println("Error: the list is empty.");
            System.exit(0);
        }
    }

    /**
     * Delete the node at the start
     */
    public void deleteFromStart() {
        if (size > 1) {
            head = head.nextNode;
            size--;
        }
        else if (size == 1) {
            head = null;
            size = 0;
        } else {
            System.out.println("No elements to delete.");
        }
    }

    /**
     * Replace the cellphone at a specific index
     *
     * @param cell
     * @param index
     */
    public void replaceAtIndex(CellPhone cell, int index) {
        if (!(index >= 0 && index < size)) {
            System.out.println("Invalid index");
            return;
        } else {
            // Get to node at index
            CellNode previousNode = nodeAtIndex(index-1, index);
            // Replace node
            previousNode.nextNode = new CellNode(cell, previousNode.nextNode.nextNode);
        }
    }

    /**
     * Since it is private, this method does not have a privacy leak
     * This method helps find a node with a specific serial number
     * (used by contains and searchInfo)
     *
     * @param serialNumber
     * @return CellNode
     */
    private CellNode find(long serialNumber) {
        CellNode node = head;

        while (node != null) {
            // Check if it is equal, return the nodeToCheck if true
            if (node.cellphone.getSerialNum() == serialNumber) {
                return node;
            } else {
                // Go to next node
                node = node.nextNode;
            }
        }
        return null;
    }

    /**
     * Prints the information of the cellphone if it is found
     *
     * @param serialNumb
     */
    public void searchInfo(long serialNumb) {
        System.out.println("\nLooking for serial number " + serialNumb);
        CellNode node = find(serialNumb);

        if (node != null) {
            System.out.println("Match found: " + node.cellphone);
        } else {
            System.out.println("No matches found.");
        }
    }

    /**
     * Look for a serial number
     *
     * @param serialNumb
     * @return boolean
     */
    public boolean contains(long serialNumb) {
        if (find(serialNumb) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Prints the contents of all cellphones in the list
     */
    public void showContents() {
        if (head != null) {
            int counter = 0;
            CellNode currentNode = head;

            // Header
            String message = "The current size of the list is " + size + ". Here are the contents of the list";
            System.out.println("\n" + message);
            for (int i = 0; i < message.length(); i++) {
                System.out.print("=");
            }
            System.out.println("");

            // 3 cellphones per row
            while (currentNode != null) {
                if (counter % 3 == 0 && counter != 0) {
                    System.out.println("");
                }
                System.out.print(currentNode.cellphone);
                currentNode = currentNode.nextNode;
                System.out.print(" ---> ");
                counter++;
            }

            System.out.println(" X");
        } else {
            System.out.println("Empty list.");
        }
    }

    /**
     * Add cellphone at the end
     *
     * @param cell
     */
    public void add(CellPhone cell) {
        if (size != 0) {
            CellNode lastNode = nodeAtIndex(size-1, size-1);
            lastNode.nextNode = new CellNode(cell, null);
            size++;
        } else {
            addToStart(cell);
        }
    }

    /**
     * This method has no privacy leak since it is private
     * Returns the node at a certain index
     * @param checkIndex
     * @param callingIndex
     * @return
     */
    private CellNode nodeAtIndex(int checkIndex, int callingIndex) {
        try {
            // Check if it's a valid index
            if (checkIndex < 0 || checkIndex >= size || callingIndex < 0 || callingIndex >= size) {
                throw new NoSuchElementException();
            } else {
                // Return the node at the specified index
                CellNode node = head;
                for (int i = 0; i < checkIndex; i++) {
                    if (node != null) {
                        node = node.nextNode;
                    } else {
                        throw new NullPointerException();
                    }
                }
                return node;
            }

        } catch (NoSuchElementException e) {
            System.out.println("Error: " + callingIndex + " is invalid." + e.getMessage());
            System.exit(0);
        } catch (NullPointerException e) {
            System.out.println("Error: unexpected null node.");
            System.exit(0);
        }
        return null;
    }

    /**
     *Equals method
     *
     * @param objRef
     * @return boolean
     */
    public boolean equals(Object objRef) {
        if (this == objRef)
            return  true;
        if (objRef == null)
            return false;
        if (this.getClass() != objRef.getClass())
            return false;
        CellList list = (CellList) objRef;
        return size == list.size && checkSameElements(list);
    }

    /**
     * Check if nodes contain the same elements
     *
     * @param list
     * @return boolean
     */
    public boolean checkSameElements(CellList list) {
        CellNode firstListNode = head;
        CellNode secondListNode = list.head;
        int count = 0;
        boolean checkSame = false;
        if (firstListNode == null || secondListNode == null) {
            return firstListNode == null && secondListNode == null;
        } else {
            while (count < this.size) {
                if (firstListNode.equals(secondListNode)) {
                    firstListNode = firstListNode.nextNode;
                    secondListNode = secondListNode.nextNode;
                    checkSame = true;
                    count++;
                } else {
                    return checkSame;
                }
            }
            return checkSame;
        }
    }
}

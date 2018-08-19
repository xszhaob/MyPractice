package bo.zhao.practice.nio.action3;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/8/19
 */
public class QueueIntFlip {

    public int[] elements = null;

    public int capacity = 0;
    public int writePos = 0;
    public int readPos = 0;
    public boolean flipped = false;

    public QueueIntFlip(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public boolean put(int element) {
        if (!flipped) {
            if (writePos == capacity) {
                writePos = 0;
                flipped = true;

                if (writePos < readPos) {
                    elements[writePos++] = element;
                    return true;
                } else {
                    return false;
                }
            } else {
                elements[writePos++] = element;
                return true;
            }
        } else {
            if (writePos < readPos) {
                elements[writePos++] = element;
                return true;
            } else {
                return false;
            }
        }
    }

    public int take() {
        if(!flipped){
            if(readPos < writePos){
                return elements[readPos++];
            } else {
                return -1;
            }
        } else {
            if(readPos == capacity){
                readPos = 0;
                flipped = false;

                if(readPos < writePos){
                    return elements[readPos++];
                } else {
                    return -1;
                }
            } else {
                return elements[readPos++];
            }
        }
    }
}

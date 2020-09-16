package serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author dingchenchen
 * @since 2020/9/15
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 4644753740588166024L;

    private String teacherNo;

    public Teacher(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    @Override
    public String toString() {
        return "老师:" + teacherNo;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("Teacher readObject exec...");
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        System.out.println("Teacher writeObject exec...");
    }

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("Teacher writeReplace exec...");
        return this;
    }

    private Object readResolve() throws ObjectStreamException{
        System.out.println("Teacher readResolve exec...");
        return new Teacher("001");
    }
}

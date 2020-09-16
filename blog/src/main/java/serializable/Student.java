package serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @author dingchenchen
 * @since 2020/9/15
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 140932343675039705L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "学生:" + name;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("readObject exec...");
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        System.out.println("writeObject exec...");
    }

    private Object writeReplace() throws ObjectStreamException{
        System.out.println("writeReplace exec...");
        return new Teacher("007");
    }

    private Object readResolve() throws ObjectStreamException{
        System.out.println("readResolve exec...");
        return new Teacher("001");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student();
        student.setName("小明");
        String filePath = "/Users/dingchenchen/Downloads/student";
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeObject(student);
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath));
        Teacher teacher = (Teacher) in.readObject();
        System.out.println(teacher);
    }

}

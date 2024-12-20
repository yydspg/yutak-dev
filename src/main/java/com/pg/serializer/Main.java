package com.pg.serializer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DemoObject object = new DemoObject();
        object.setName("yydspg");
        byte[] data = serialize(object);
        DemoObject object1 = deserialize(data);

    }

    public static byte[] serialize(DemoObject obj ) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(stream);
        outputStream.writeObject(obj);
        outputStream.close();

        // 将序列化后的字节流转化为字符串并输出
        System.out.println(stream.toString());
        // 将字节流转化为字节数组并返回
        return  stream.toByteArray();
    }
    public static DemoObject deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream = new ObjectInputStream(stream);
        return (DemoObject) inputStream.readObject();
    }
}

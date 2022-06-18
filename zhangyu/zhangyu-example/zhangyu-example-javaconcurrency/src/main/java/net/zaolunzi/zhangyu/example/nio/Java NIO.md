项目中常用到的读取配置文件，便是java中典型的io操作

如 PropertiesUtilLocal 类，是我在开源项目中经常用到的读取配置类

《on java8》

hadoop common 的 Configuration
参考书籍《hadoop技术内幕：深入解析HadoopCommon和HDFS架构设计与实现原理》

二进制文件和字节流
InputStream/OutputStream 基类/抽象类
FileInputStream/FileOutputStream：输入源和输出目标是文件的流。
ByteArrayInputStream/ByteArrayOutputStream：输入源和输出目标是字节数组的流
DataInputStream/DataOutputStream：装饰类，按基本类型和字符串而非只是字节读写流。
BufferedInputStream/BufferedOutputStream：装饰类，对输入输出流提供缓冲功能

文本文件和字符流
1）Reader/Writer：字符流的基类，它们是抽象类；
2）InputStreamReader/OutputStreamWriter：适配器类，将字节流转换为字符流；
3）FileReader/FileWriter：输入源和输出目标是文件的字符流；
4）CharArrayReader/CharArrayWriter：输入源和输出目标是char数组的字符流；
5）StringReader/StringWriter：输入源和输出目标是String的字符流；
6）BufferedReader/BufferedWriter：装饰类，对输入/输出流提供缓冲，以及按行读写功能
7）PrintWriter：装饰类，可将基本类型和对象转换为其字符串形式输出的类

文件和目录操作
File 类

RandomAccessFile 


